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
	 * ����common-math3������������������ϵ���ͼ�������pֵ
	 * @param xArray double[]  x����
	 * @param yArray double[]  y����
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
	 * ת��rΪt�ֲ���ͳ���� ,����tͳ������Ӧ��Pֵ������Pֵ��˫β�˸��ʣ�
	 * @param  r double ���ϵ��
	 * @param  n int �������ĸ���
	 * @return p double Pֵ
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
