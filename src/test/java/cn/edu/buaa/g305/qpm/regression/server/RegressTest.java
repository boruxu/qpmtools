package cn.edu.buaa.g305.qpm.regression.server;

import java.util.Arrays;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.util.Decimal64;
import org.junit.Test;

import cn.edu.buaa.g305.qpm.regression.domain.Regress;
import cn.edu.buaa.g305.qpm.regression.domain.RegressIn;
import cn.edu.buaa.g305.qpm.regression.server.imp.RegressServerImp;


public class RegressTest {

	@Test
	public void test() {
		OLSMultipleLinearRegression ols=new OLSMultipleLinearRegression();
		double[] y={64.7,61.0,59.8,58.3,57.6,
				66.7,67.6,58.0,66.3,60.0,
				66.8,62.2,61.7,70.1,62.8,
				67.5,72.2,62.8,66.1,72.8};
		double[][] x={{120,5,5.6},
					  {120,4,4.9},
					  {122,5,4.5},
					  {122,5,4.5},
					  {124,3,3.8},
					  {124,3,5.8},
					  {126,4,5.8},
					  {126,5,4.0},
					  {128,3,5.1},
					  {128,3,4.1},
					  {130,5,5.2},
					  {130,4,4.2},
					  {132,3,4.4},
					  {132,4,6.6},
					  {134,4,4.6},
					  {134,3,5.5},
					  {136,4,6.1},
					  {136,5,4.5},
					  {138,4,5.0},
					  {138,3,6.7}				
		};
		double[][] xeone=new double[20][2];
		for(int i=0;i<20;i++)
		{
			xeone[i]=Arrays.copyOfRange(x[i], 1, 3);
		}
		System.out.println(Arrays.deepToString(xeone));
		ols.newSampleData(y, x);
		double r_sq_ad=ols.calculateAdjustedRSquared();
		double r_sq=ols.calculateRSquared();
		double s=ols.calculateTotalSumOfSquares();
		double s2=ols.calculateResidualSumOfSquares();
		System.out.println("r_sq_ad:"+r_sq_ad);
		System.out.println("r_sq:"+r_sq);
		System.out.println(Arrays.toString(ols.estimateRegressionParametersStandardErrors()));
		System.out.println(s);
		System.out.println(s2);
		System.out.println(ols.calculateResidualSumOfSquares());
		
		System.out.println(Arrays.toString(ols.estimateRegressionParameters()));
		
		TDistribution tDistribution=new TDistribution(16);
		double mmm=-0.79;
		double p=(1-tDistribution.cumulativeProbability(mmm))*2;//-tDistribution.density(-3.76);
		double p2=tDistribution.cumulativeProbability(-0)*2;

		System.out.println(p);
		System.out.println(p2);
		
		System.out.println(Arrays.toString(arraySquareDifference(y,ols.calculateHat().operate(y))));
		
		System.out.println(Arrays.toString(ols.estimateResiduals()));
		
		double[] resi=ols.estimateResiduals();
		double mid=StatUtils.mean(resi);
		double var=StatUtils.variance(resi);
		Decimal64[] resi64=new Decimal64[y.length];
		Decimal64 miD=new Decimal64(0);
		for(int i=0;i<y.length;i++)
		{
			resi64[i]=new Decimal64(resi[i]);
			System.out.println("resi64[i]"+resi64[i]);
		}
		//mid=-1.59872E-14;
		//var=1.052;
		double[] normal=new double[y.length];
		System.out.println("MID:"+mid+":"+var+":");
		mid=0;
		NormalDistribution no=new NormalDistribution();
		double ad=0;
		for(int i=0;i<y.length;i++)
		{
			 miD=miD.add(resi64[i]);
			 System.out.println(miD+"/"+resi64[i]);
		}
		System.out.println("miD.divide(y.length)"+miD.divide(y.length));
		miD=new Decimal64(1).multiply(miD.divide(y.length));
		System.out.println("miD"+mid);
		for(int i=0;i<y.length;i++)
		{
			normal[i]=(resi[i]-mid)/var;
		}
		
		Arrays.sort(normal);
		System.out.println(Arrays.toString(normal));
		for(int i=0;i<y.length;i++)
		{
			ad+=-1-(Math.log(no.cumulativeProbability(normal[i]))+
					Math.log(1-no.cumulativeProbability(normal[normal.length-1-i])))
					*(2*(i+1)-1)/normal.length;
/*			System.out.println(no.cumulativeProbability((test[i]-40)/3.741657));
			System.out.println(1-no.cumulativeProbability((test[test.length-1-i]-40)/3.741657));
			System.out.println(Math.log(no.cumulativeProbability((test[i]-40)/3.741657)));
			System.out.println(Math.log(1-no.cumulativeProbability((test[test.length-1-i]-40)/3.741657)));
			System.out.println(	-1-(Math.log(no.cumulativeProbability((test[i]-40)/3.741657))+
					Math.log(1-no.cumulativeProbability((test[test.length-1-i]-40)/3.741657)))
					*(2*(i+1)-1)/test.length);*/
		}
		
		System.out.println("ad:"+ad);
		String[] yStrings={"64.7","61.0","59.8","58.3","57.6",
				"66.7","67.6","58.0","66.3","60.0",
				"66.8","62.2","61.7","70.1","62.8",
				"67.5","72.2","62.8","66.1","72.8"};
		String[][] xStrings={{"120","5","5.6"},
					  {"120","4","4.9"},
					  {"122","5","4.5"},
					  {"122","5","4.5"},
					  {"124","3","3.8"},
					  {"124","3","5.8"},
					  {"126","4","5.8"},
					  {"126","5","4.0"},
					  {"128","3","5.1"},
					  {"128","3","4.1"},
					  {"130","5","5.2"},
					  {"130","4","4.2"},
					  {"132","3","4.4"},
					  {"132","4","6.6"},
					  {"134","4","4.6"},
					  {"134","3","5.5"},
					  {"136","4","6.1"},
					  {"136","5","4.5"},
					  {"138","4","5.0"},
					  {"138","3","6.7"}				
		};
		RegressServerImp regressServerImp=new RegressServerImp();
		Regress regress=new Regress();
		RegressIn regressIn=new RegressIn();
		regressIn.setX(xStrings);
		regressIn.setY(yStrings);
		regressIn.setxName(new String[]{"温度","时间","焊膏量"});
		regressIn.setyName("拉拔力");
		
		regress.setInput(regressIn);
		System.out.println(regressServerImp.computer(regress));

		
	}
	//y为测量值，x为估计值
	public double[] arraySquareDifference(double[] y , double[] x)
	{
		double[] result=new double[y.length];
		for(int i=0;i<y.length;i++)
		{
			result[i]=y[i]-x[i];
		}
		return result;
	}

}
