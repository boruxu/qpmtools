package cn.edu.buaa.g305.qpm.spc.system;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.buaa.g305.qpm.system.DoublePrecision;

public class VariableControlChartsFactorTest {

	@Test
	public void testA() {
		double[] expecteds=new double[]{2.121,1.732,1.500,1.342,1.225,1.134,1.061,1.000,
				                        0.949,0.905,0.866,0.832,0.802,0.775,0.750,0.728,
				                        0.707,0.688,0.671,0.655,0.640,0.626,0.612,0.600};
		double[] actuals=new double[24];
		for(int i=2;i<=25;i++)
		{
            actuals[i-2]=DoublePrecision.precision(VariableControlChartsFactor.computeA(i), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testA2() {
		double[] expecteds=new double[]{-1,1.880,1.023,0.729,0.577,0.483,-1};
		double[] actuals=new double[7];
		for(int i=1;i<=7;i++)
		{
            actuals[i-1]=DoublePrecision.precision(VariableControlChartsFactor.computeA2(i), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testD3() {
		double[] expecteds=new double[]{-1,0,0,0,0,0,-1};
		double[] actuals=new double[7];
		for(int i=1;i<=7;i++)
		{
            actuals[i-1]=DoublePrecision.precision(VariableControlChartsFactor.computeD3(i), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}
	@Test
	public void testD4() {
		double[] expecteds=new double[]{-1,3.267,2.574,2.282,2.114,2.004,-1};
		double[] actuals=new double[7];
		for(int i=1;i<=7;i++)
		{
            actuals[i-1]=DoublePrecision.precision(VariableControlChartsFactor.computeD4(i), 3);			
		}
	    assertArrayEquals(expecteds, actuals, 0);
	}




}
