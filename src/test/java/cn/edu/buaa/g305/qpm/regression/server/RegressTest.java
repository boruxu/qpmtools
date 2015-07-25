package cn.edu.buaa.g305.qpm.regression.server;


import org.junit.Test;

import cn.edu.buaa.g305.qpm.regression.domain.Regress;
import cn.edu.buaa.g305.qpm.regression.domain.RegressIn;
import cn.edu.buaa.g305.qpm.regression.server.imp.RegressServerImp;


public class RegressTest {

	@Test
	public void test() {
		/*OLSMultipleLinearRegression ols=new OLSMultipleLinearRegression();*/
		//输入y数据，x数据
		/*double[] y={64.7,61.0,59.8,58.3,57.6,
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
		};*/
		/*ols.newSampleData(y, x);
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
		System.out.println(p2);*/
		String[] y={"64.7","61.0","59.8","58.3","57.6",
				"66.7","67.6","58.0","66.3","60.0",
				"66.8","62.2","61.7","70.1","62.8",
				"67.5","72.2","62.8","66.1","72.8"};
		String[][] x={{"120","5","5.6"},
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
		regressIn.setX(x);
		regressIn.setY(y);
		regressIn.setxName(new String[]{"温度","时间","焊膏量"});
		regressIn.setyName("拉拔力");
		
		regress.setInput(regressIn);
		System.out.println(regressServerImp.computer(regress));

		
	}

}
