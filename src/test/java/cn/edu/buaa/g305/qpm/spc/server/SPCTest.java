package cn.edu.buaa.g305.qpm.spc.server;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.buaa.g305.qpm.spc.domain.SPCXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXROut;
import cn.edu.buaa.g305.qpm.spc.server.imp.SPCImp;

public class SPCTest {

	@Test
	public void testXR() {
		SPCImp spc=new SPCImp();
		SPCXRIn spcxrIn=new SPCXRIn();
		String[] time=new String[25];
		for (int i = 0; i < time.length; i++) {
			time[i]=i+1+"";
		}
		spcxrIn.setTime(time);
		double[][] x=new double[][]{{10.95,10.9,10.95,10.96,10.98},
				                    {10.91,10.97,10.95,10.98,10.94},
				                    {10.97,10.91,10.94,10.95,10.93},
				                    {10.92,10.94,10.95,10.95,10.93},
				                    {11.02,10.96,10.92,10.98,10.99},
				                    {10.92,10.94,10.93,10.98,10.95},
				                    {10.98,10.91,10.96,10.9,10.93},
				                    {10.96,10.93,10.94,10.93,10.96},
				                    {10.94,10.93,10.97,10.96,10.95},
				                    {10.91,10.95,10.93,10.96,10.92},
				                    {10.94,10.94,10.98,10.94,10.97},
				                    {10.97,10.95,10.93,10.92,10.98},
				                    {10.99,10.95,10.95,10.95,10.96},
				                    {10.93,10.97,10.94,10.92,10.93},
				                    {11.02,10.98,10.97,10.96,10.91},
				                    {10.95,10.95,10.93,10.94,10.93},
				                    {10.96,10.95,10.97,10.99,10.95},
				                    {10.97,10.97,10.93,10.95,11.01},
				                    {11,10.93,10.95,10.96,10.96},
				                    {10.95,10.92,10.92,10.98,10.93},
				                    {10.95,10.94,10.95,10.96,10.97},
				                    {10.92,10.97,11,10.94,10.94},
				                    {10.95,10.94,10.93,10.96,10.95},
				                    {11,10.99,10.9,10.94,10.98},
				                    {10.94,10.92,10.96,10.93,10.96}
				                    };
	    spcxrIn.setX(x);
	    SPCXROut spcxrOut=spc.computeXR(spcxrIn);
	    double[] expecteds=new double[]{10.9837,10.9502,10.9168,0.1226,0.058,0.0};
	    double[] actuals=new double[]{spcxrOut.getxUCL(),spcxrOut.getxCL(),spcxrOut.getxLCL(),
	    		                      spcxrOut.getrUCL(),spcxrOut.getrCL(),spcxrOut.getrLCL()};
	    assertArrayEquals(expecteds, actuals, 0);
	   
	}

}
