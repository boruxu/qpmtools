package cn.edu.buaa.g305.qpm.regression.server.imp;

import java.util.List;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.regression.dao.RegressRepository;
import cn.edu.buaa.g305.qpm.regression.domain.Regress;
import cn.edu.buaa.g305.qpm.regression.domain.RegressAnalysis;
import cn.edu.buaa.g305.qpm.regression.domain.RegressOut;
import cn.edu.buaa.g305.qpm.regression.server.RegressServer;
import static cn.edu.buaa.g305.qpm.system.DoublePrecisonArrayToStringArray.*;
import static cn.edu.buaa.g305.qpm.system.StringArrayToDoubleArray.*;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;
@Component
public class RegressServerImp implements RegressServer{
	
	@Autowired
	private RegressRepository regressRepository;
	@Autowired
	private SystemFind systemFind;

	@Override
	public Regress getByName(String name) {
		Regress regress=regressRepository.findByName(name);
		if(regress==null)
		{
			regress=new Regress("名为"+name+"的资源不存在！");
		}

		return regress;
	}

	@Override
	public Regress getById(String id) {
		
		Regress mc=regressRepository.findOne(id);
		if(mc==null)
		{
			mc=new Regress("ID为"+id+"的资源不存在！");
		}

		return mc;
	}

	@Override
	public Regress save(Regress regress) {
		
		regress.setId(null);
		regress.setProject(systemFind.findProductAffiliation(regress.getProject().getName()));
		regress=computer(regress);
		try{
			regress=regressRepository.save(regress);
		}
		catch(DuplicateKeyException e)
		{
			regress=new Regress("名重复");
			return regress;
		}
		
		regress=computer(regress);
		return regress;
		
	}

	@Override
	public Regress update(Regress mc, String id) {
		
		mc.setId(id);
		mc.setProject(systemFind.findProductAffiliation(mc.getProject().getName()));
		mc=computer(mc);
		try{
			mc=regressRepository.save(mc);
		}
		catch(DuplicateKeyException e)
		{
			mc=new Regress("名重复");
			return mc;
		}
		
		
		return mc;
				
	}
	@Override
	public Regress delete(String id) {
		Regress mc=regressRepository.findOne(id);
		if(mc!=null)
		{
			regressRepository.delete(id);
			return null;
		}
		else {
			mc=new Regress("id："+id+"的资源不存在！");
			return mc;			
		}
		
	}

	@Override
	public Regress deleteByName(String name) {
		Regress mc=regressRepository.findByName(name);
		if(mc!=null)
		{
			regressRepository.delete(mc);
			return null;
		}
		else {
			mc=new Regress("name："+name+"的资源不存在！");
			return mc;		
		}
	}

	@Override
	public List<Regress> getAllList() {
		
		return (List<Regress>) regressRepository.findAll();
	}

	@Override
	public List<Regress> getListByProject(String name) {
		Project project=systemFind.findProjectByName(name);
		if(project==null)
		{
			return null;
		}
		else {
			return regressRepository.findByProject(project);
		}
	}

	@Override
	public Regress computer(Regress regress) {
		int precision=3;
		OLSMultipleLinearRegression ols=new OLSMultipleLinearRegression();
		double[] y=toDouble(regress.getInput().getY());
		String yName=regress.getInput().getyName();
		double[][] x=toDouble(regress.getInput().getX());
		ols.newSampleData(y, x);
		//系数
		double[] beta=ols.estimateRegressionParameters();
		//系数对应的标准误差（第一个为常量）
		double[] standardErrors=ols.estimateRegressionParametersStandardErrors();
        
		//自变量个数、回归自由度
		int dfr=beta.length-1;
		//总自由度
		int dft=y.length-1;
		//残差自由度
		int dfe=dft-dfr;
		String regressFunction=yName+"=";
		String[] xName=regress.getInput().getxName();
		double[] t=new double[beta.length];
		double[] p=new double[beta.length];
		RegressAnalysis[] raArray=new RegressAnalysis[beta.length];
		TDistribution tDistribution=new TDistribution(dfe);
		//系数对应T
		for(int i=0;i<beta.length;i++)
		{
			t[i]=beta[i]/standardErrors[i];
			p[i]=tDistribution.cumulativeProbability(t[i]>0?-t[i]:t[i])*2;
			if(i==0)
			{
				raArray[i]=new RegressAnalysis("常量",
						toStringPrecision(beta[i], 3),
						toStringPrecision(standardErrors[i], 3),
						toStringPrecision(t[i], 3),
						toStringPrecision(p[i], 3)
						);
				
				regressFunction+=raArray[i].getBeta();
				
				
			}
			else{
				raArray[i]=new RegressAnalysis(xName[i-1],
						toStringPrecision(beta[i], precision),
						toStringPrecision(standardErrors[i], precision),
						toStringPrecision(t[i], precision),
						toStringPrecision(p[i], precision)
						);
				if(beta[i]>0)
				{
					regressFunction+="+"+raArray[i].getBeta()+"*"+xName[i-1];
				}
				else{
					regressFunction+=raArray[i].getBeta()+"*"+xName[i-1];
				}
				
			}								
		} 
		//回归R-sq  R-sq调整
		double r_sq=ols.calculateRSquared();
		double r_sq_ad=ols.calculateAdjustedRSquared();
		
		double sst=ols.calculateTotalSumOfSquares();
		double sse=ols.calculateResidualSumOfSquares();
		double ssr=sst-sse;
		
		double msr=ssr/dfr;
		double mse=sse/dfe;
		
		double f=msr/mse;
		FDistribution fDistribution=new FDistribution(dfr,dfe);
		double p_f=1-fDistribution.cumulativeProbability(f);
		double s=Math.sqrt(mse);
		
		RegressOut regressOut=new RegressOut();
		regressOut.setRegressAnalysis(raArray);
		regressOut.setS(toStringPrecision(s, precision));
		regressOut.setR_sq(toStringPrecision(r_sq, precision));
		regressOut.setR_sq_ad(toStringPrecision(r_sq_ad, precision));
		regressOut.setDfe(dfe);
		regressOut.setDfr(dfr);
		regressOut.setDft(dft);
		regressOut.setSst(toStringPrecision(sst, precision));
		regressOut.setSse(toStringPrecision(sse, precision));
		regressOut.setSsr(toStringPrecision(ssr, precision));
		regressOut.setMse(toStringPrecision(mse, precision));
		regressOut.setMsr(toStringPrecision(msr, precision));
		regressOut.setF(toStringPrecision(f, precision));
		regressOut.setP(toStringPrecision(p_f, precision));
		regressOut.setRegressFunction(regressFunction);
		
		regress.setOutput(regressOut);

		return regress;
	}

	
	


}
