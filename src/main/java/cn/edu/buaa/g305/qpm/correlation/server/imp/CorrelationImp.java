package cn.edu.buaa.g305.qpm.correlation.server.imp;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.Correlation;

@Component
public class CorrelationImp implements Correlation{

	private PearsonsCorrelation pearsonsCorrelation;

	private CorrelationOut correlationOut;
		
	public CorrelationImp()
	{
		pearsonsCorrelation=new PearsonsCorrelation();
	}
	/**
	 * 利用common-math3包，计算两数组的相关系数和假设检验的p值
	 * @param xArray double[]  x数组
	 * @param yArray double[]  y数组
	 * @return CorrealtionOut
	 */
	public CorrelationOut computeCorrelationAndPValue(double[] xArray,
			double[] yArray) {
		correlationOut=new CorrelationOut();
		double r=pearsonsCorrelation.correlation(xArray, yArray);
		double p=rToTDistribution(r, xArray.length);
		correlationOut.setR(r);
		correlationOut.setP(p);
		return correlationOut;
	}
	
	/**
	 * 转换r为t分布的统计量 ,计算t统计量相应的P值（计算P值的双尾端概率）
	 * @param  r double 相关系数
	 * @param  n int 相关数组的个数
	 * @return p double P值
	 */
	private double rToTDistribution(double r,int n)
	{
		TDistribution tDistribution=new TDistribution(n-2);
		double t=r/Math.sqrt(((1-r*r)/(n-2)));
		if(t>=0)
		{
			return (1-tDistribution.cumulativeProbability(t))*2;
		}
		else {
			return 2*tDistribution.cumulativeProbability(t);
		}
	}


	
}
