package cn.edu.buaa.g305.qpm.spc.system;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.buaa.g305.qpm.system.ValuePrecision;

public class VariableControlChartsFactorTest  {

	@Test
	public void testA() {
		double[] expecteds=new double[]{2.121,1.732,1.500,1.342,1.225,1.134,1.061,1.000,
				                        0.949,0.905,0.866,0.832,0.802,0.775,0.750,0.728,
				                        0.707,0.688,0.671,0.655,0.640,0.626,0.612,0.600};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeA(i+2), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testA2() {
		double[] expecteds=new double[]{-1,1.880,1.023,0.729,0.577,0.483,-1};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeA2(i+1), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testD3() {
		double[] expecteds=new double[]{-1,0,0,0,0,0,-1};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeD3(i+1), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testD4() {
		double[] expecteds=new double[]{-1,3.267,2.574,2.282,2.114,2.004,-1};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeD4(i+1), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	/*改为私有接口，本测试方法保留
	@Test
	public void testC2() {
		double[] expecteds=new double[]{0.5642,0.7236,0.7979,0.8407,0.8686,0.8882,
				                        0.9027,0.9139,0.9227,0.9300,0.9359,0.9410,
				                        0.9453,0.9490,0.9523,0.9551,0.9576};
		double[] actuals=new double[expecteds.length];
	
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=DoublePrecision.precision(VariableControlChartsFactor.computeC2(i+2), 4);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}*/
	@Test
	public void testC4() {
		double[] expecteds=new double[]{0.7979,0.8862,0.9213,0.9400,0.9515,0.9594,0.9650,
				                        0.9693,0.9727,0.9754,0.9776,0.9794,0.9810,0.9823,
				                        0.9835,0.9845,0.9854};
		double[] actuals=new double[expecteds.length];
	
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeC4(i+2), 4);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testA3() {
		double[] expecteds=new double[]{2.659,1.954,1.628,1.427,1.287,1.182,1.099,
				                        1.032,0.975,0.927,0.886,0.850,0.817,0.789,
				                        0.763,0.739,0.718,0.698,0.680,0.663,0.647,
				                        0.633,0.619,0.606};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeA3(i+2), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testB3() {
		double[] expecteds=new double[]{0,0,0,0,0.030,0.118,0.185,0.239,0.284,
				                        0.321,0.354,0.382,0.406,0.428,0.448,
				                        0.466,0.482,0.497,0.510,0.523,0.534,
				                        0.545,0.555,0.565};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeB3(i+2), 3);
		}
		assertArrayEquals(expecteds, actuals,0);
		
	}
	@Test
	public void testB4() {
		double[] expecteds=new double[50];
		for (int i = 0; i < expecteds.length; i++) {
			expecteds[i]=2;
		}
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(
            		VariableControlChartsFactor.computeB3(i+6)+
            		VariableControlChartsFactor.computeB4(i+6), 3);
		}
		assertArrayEquals(expecteds, actuals, 0.02);
		double[] expectedsOther=new double[]{3.267,2.568,2.266,2.089};
		double[] actualsOther=new double[expectedsOther.length];
		for(int i=0;i<expectedsOther.length;i++)
		{
            actualsOther[i]=ValuePrecision.precision(
            		VariableControlChartsFactor.computeB4(i+2), 3);
		}
		assertArrayEquals(expectedsOther, actualsOther, 0);
	}
	@Test
	public void testB5() {
		double[] expecteds=new double[]{ 0,0,0,0,0.029,0.113,0.179,
				                         0.232,0.276,0.313,0.346,0.374,0.399,0.421,0.440,
				                         0.458,0.475,0.490,0.504,0.516,0.528,0.539,0.549,
				                         0.559};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeB5(i+2), 3);
		}
	
		assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testB6() {
		double[] expecteds=new double[]{2.606,2.276,2.088,1.964,1.874,1.806,1.751,1.707,
				                        1.669,1.637,1.61,1.585,1.563,1.544,1.526,1.511,
				                        1.496,1.483,1.47,1.459,1.448,1.438,1.429,1.42};
		double[] actuals=new double[expecteds.length];
		for(int i=0;i<expecteds.length;i++)
		{
            actuals[i]=ValuePrecision.precision(VariableControlChartsFactor.computeB6(i+2), 3);
		}
	
		assertArrayEquals(expecteds, actuals, 0);
		
	}





}
