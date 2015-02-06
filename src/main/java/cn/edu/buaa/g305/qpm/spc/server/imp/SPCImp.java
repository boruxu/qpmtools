package cn.edu.buaa.g305.qpm.spc.server.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.spc.dao.SPCXRRepository;
import cn.edu.buaa.g305.qpm.spc.domain.*;
import cn.edu.buaa.g305.qpm.spc.server.SPCService;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;
import static cn.edu.buaa.g305.qpm.spc.system.VariableControlChartsFactor.*;
import static cn.edu.buaa.g305.qpm.system.StringArrayToDoubleArray.*;
import static cn.edu.buaa.g305.qpm.system.DoublePrecisonArrayToStringArray.*;
@Component
public class SPCImp implements SPCService{
	
	@Autowired
	private SPCXRRepository spcxrRepository;
	@Autowired
	private SystemFind systemFind;

	public SPCXROut computeXR(SPCXRIn spcxrIn) {
		
		SPCXROut spcxrOut=new SPCXROut();
		int precision=4;
		//样本数
		int n=spcxrIn.getX().length;
		//样本内样品数
		int group_n=spcxrIn.getX()[0].length;
		//样本内X平均值
		double[] xAverage=new double[n];
		//样本内极差
		double[] r=new double[n];
		//样本间X的均值
		double xSumAverage=0;
		//样本间R的均值
		double rAverage=0;
		int i=0;
		//计算X总平均值和R的平均值
		for (double[] xSum :toDouble(spcxrIn.getX())) {

			double min=xSum[0];
			double max=xSum[0];
			
			for (double x : xSum) {
				
				if(x<min)
				{
					min=x;
				}
				else if(x>max)
				{
					max=x;
				}
				xAverage[i]+=x;
								
			}
			xAverage[i]=xAverage[i]/group_n;
			xSumAverage+=xAverage[i];
			r[i]=max-min;
			rAverage+=r[i];
			i++;	
		}
		
		xSumAverage=xSumAverage/n;
		rAverage=rAverage/n;
		//计算X图的UCL和LCL，设置X图输出

		spcxrOut.setX(toStringPrecision(xAverage, precision));
		spcxrOut.setxCL(toStringPrecision(xSumAverage, precision));
		spcxrOut.setxUCL(toStringPrecision(xSumAverage+computeA2(group_n)*rAverage,  precision));
		spcxrOut.setxLCL(toStringPrecision(xSumAverage-computeA2(group_n)*rAverage,  precision));
		
		//计算R图的UCL和LCL，设置R图输出

		spcxrOut.setR(toStringPrecision(r,precision));
		spcxrOut.setrCL(toStringPrecision(rAverage, 4));
		spcxrOut.setrUCL(toStringPrecision(rAverage*computeD4(group_n), 4));
		spcxrOut.setrLCL(toStringPrecision(rAverage*computeD3(group_n), 4));
		
		spcxrOut.setTime(spcxrIn.getTime());
		
	
		return spcxrOut;
	}
/*
	public SPCXSOut computeXS(SPCXSIn spcxsIn) {
		SPCXSOut spcxsOut=new SPCXSOut();
		//样本数
		int n=spcxsIn.getX().length;
		//样本内样品数
		int group_n=spcxsIn.getX()[0].length;
		//样本内X平均值
		double[] xAverage=new double[n];
		//样本内标准差估计
		double[] s=new double[n];
		//样本间X的均值
		double xSumAverage=0;
		//样本间S的均值
		double sAverage=0;
		int i=0;
		//各个X平均值
		for (double[] xSum : spcxsIn.getX()) {

			for (double x : xSum) {				
				xAverage[i]+=x;								
			}
			xAverage[i]=xAverage[i]/group_n;
			xSumAverage+=xAverage[i];
			i++;	
		}
		//计算X总平均值
		xSumAverage=xSumAverage/n;
	    //计算各个s
		for (int m=0;m<n;m++)
		{
			for(int k=0;k<group_n;k++)
			{
				s[m]+=Math.pow(spcxsIn.getX()[m][k]-xAverage[m],2);
			}
			s[m]=Math.sqrt(s[m]/(group_n-1));
			sAverage+=s[m];
		}
		//计算s的平均值
		sAverage=sAverage/n;
		//计算X图的UCL和LCL，设置X图输出
		for (double x : xAverage) {
			
			x=precision(x, 4);
			
		}
		for (double sv : s) {
				
				sv=precision(sv, 4);
				
	    }
		if(spcxsIn.getSigma()<0)
		{
			spcxsOut.setX(xAverage);
			spcxsOut.setxCL(precision(xSumAverage, 4));
			spcxsOut.setxUCL(precision(xSumAverage+computeA3(group_n)*sAverage, 4));
			spcxsOut.setxLCL(precision(xSumAverage-computeA3(group_n)*sAverage, 4));
			//计算S图的UCL和LCL，设置S图输出
	      
			spcxsOut.setS(s);
			spcxsOut.setsCL(precision(sAverage, 4));
			spcxsOut.setsUCL(precision(sAverage*computeB4(group_n), 4));
			spcxsOut.setsLCL(precision(sAverage*computeB3(group_n), 4));
			
			spcxsOut.setTime(spcxsIn.getTime());
			return spcxsOut;
		}
		else
		{
			spcxsOut.setX(xAverage);
			spcxsOut.setxCL(precision(xSumAverage, 4));
			spcxsOut.setxUCL(precision(xSumAverage+3*spcxsIn.getSigma(), 4));
			spcxsOut.setxLCL(precision(xSumAverage-3*spcxsIn.getSigma(), 4));
			//计算S图的UCL和LCL，设置S图输出
	      
			spcxsOut.setS(s);
			spcxsOut.setsCL(precision(computeC4(group_n)*spcxsIn.getSigma(), 4));
			spcxsOut.setsUCL(precision(spcxsIn.getSigma()*computeB6(group_n), 4));
			spcxsOut.setsLCL(precision(spcxsIn.getSigma()*computeB5(group_n), 4));
			
			spcxsOut.setTime(spcxsIn.getTime());
			return spcxsOut;
			
		}
		
		
	}
	public SPCXMROut computeXMR(SPCXMRIn spcxmrIn) {
		SPCXMROut spcxmrOut=new SPCXMROut();
		
		double[] x= spcxmrIn.getX();
		//样本数
		int n=spcxmrIn.getX().length;
		//样本X平均值
		double xAverage=0;
		//移动极差
		double[] mR=new double[n-1];
		//移动极差均值
		double mRAverage=0;
	
		//计算X平均值,移动极差,移动极差平均值
		for (int i=0;i<n;i++) {
			if(i<n-1)
			{
				mR[i]=Math.abs(x[i+1]-x[i]);
				mRAverage+=mR[i];
			}			
			xAverage+=x[i];
		}
		xAverage=xAverage/n;
		mRAverage=mRAverage/(n-1);

		for (double xv : x) {
			
			xv=precision(xv, 4);
			
		}
		for (double mrv : mR) {
				
				mrv=precision(mrv, 4);
				
	    }
		if(spcxmrIn.getSigma()<0)
		{
			spcxmrOut.setX(x);
			spcxmrOut.setxCL(precision(xAverage, 4));
			spcxmrOut.setxUCL(precision(xAverage+2.66*mRAverage,4));
			spcxmrOut.setxLCL(precision(xAverage-2.66*mRAverage,4));
			//计算MR图的UCL，设置S图输出
	      
			spcxmrOut.setMr(mR);
			spcxmrOut.setMrCL(precision(mRAverage, 4));
			spcxmrOut.setMrUCL(precision(mRAverage*computeD4(2), 4));
			
			
			spcxmrOut.setTime(spcxmrIn.getTime());
			return spcxmrOut;
		}
		else
		{
			spcxmrOut.setX(x);
			spcxmrOut.setxCL(precision(xAverage, 4));
			spcxmrOut.setxUCL(precision(xAverage+3*spcxmrIn.getSigma(),4));
			spcxmrOut.setxLCL(precision(xAverage-3*spcxmrIn.getSigma(),4));
			//计算MR图的UCL，设置S图输出
	      
			spcxmrOut.setMr(mR);
			spcxmrOut.setMrCL(precision(1.13*spcxmrIn.getSigma(), 4));
			spcxmrOut.setMrUCL(precision(3.69*spcxmrIn.getSigma(), 4));
			
			
			spcxmrOut.setTime(spcxmrIn.getTime());
			return spcxmrOut;
			
		}
		
		
	}*/

	public SPCCOut computeC(SPCCIn spccIn) {
		double xAverage=0;
		SPCCOut spccOut=new SPCCOut();
		for (double x : spccIn.getX()) {
			xAverage+=x;
		}
		xAverage=xAverage/spccIn.getX().length;
		double sigma3=3*Math.sqrt(xAverage);
		spccOut.setC(xAverage);
		spccOut.setcUCL(xAverage+sigma3);
		if((xAverage-sigma3)<0)
		{
			spccOut.setcLCL(0);
		}
		else {
			spccOut.setcLCL(xAverage-sigma3);
		}
		spccOut.setX(spccIn.getX());
		spccOut.setTime(spccIn.getTime());
		
		return spccOut;
		
	}

	public SPCXSOut computeXS(SPCXSIn spcxsIn) {
		// TODO Auto-generated method stub
		return null;
	}

	public SPCXMROut computeXMR(SPCXMRIn spcxmrIn) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public SpcXR getSpcxrByName(String name) {
		
		SpcXR spcXR=spcxrRepository.findByName(name);
		if(spcXR==null)
		{
			spcXR=new SpcXR();
			spcXR.setStauts(null);
			spcXR.setError("名为"+name+"的X-R图资源不存在");
			return spcXR;
		}
		
		return spcXR;
	}

	public SpcXR getById(String id) {
		
		SpcXR spcXR=spcxrRepository.findOne(id);
		if(spcXR==null)
		{
			spcXR=new SpcXR();
			spcXR.setStauts(null);
			spcXR.setError("id为"+id+"的X-R图资源不存在");
			return spcXR;
		}
		
		return spcXR;
		
	}
	public SpcXR save(SpcXR spcXR) {
		
		spcXR.setOutput(computeXR(spcXR.getInput()));
		spcXR.setOrganization(systemFind.findProductAffiliation(spcXR.getOrganization()));
		spcXR.setProject(systemFind.findProductAffiliation(spcXR.getProject()));
		spcXR.setError(null);
		spcXR.setLinks(null);
	    return spcxrRepository.save(spcXR);
	    
	}
	public void delete(String id) {
		
		spcxrRepository.delete(id);
		
	}


	

}
