package cn.edu.buaa.g305.qpm.correlation.server.imp;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.Correlation;
import static cn.edu.buaa.g305.qpm.system.DoublePrecisonArrayToStringArray.*;

@Component
public class CorrelationImp implements Correlation{

	private PearsonsCorrelation pearsonsCorrelation;

	private CorrelationOut correlationOut;
		
	public CorrelationImp()
	{
		pearsonsCorrelation=new PearsonsCorrelation();
	}
	/**
	 * 利用common-math3计算相关性的P和R
	 * @param xArray double[]  x数组
	 * @param yArray double[]  y数组
	 * @return CorrealtionOut
	 */
	public CorrelationOut computeCorrelationAndPValue(double[] xArray,
			double[] yArray) {
		correlationOut=new CorrelationOut();
		double r=pearsonsCorrelation.correlation(xArray, yArray);
		double p=rToTDistribution(r, xArray.length);
		correlationOut.setR(Double.parseDouble(toStringPrecision(r, 4)));
		correlationOut.setP(Double.parseDouble(toStringPrecision(p, 4)));
		return correlationOut;
	}
	
	/**
	 * 相关系数R转化为统计量T，T是t分布
	 * @param  r double 皮尔森相关系数
	 * @param  n int 数组个数
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
