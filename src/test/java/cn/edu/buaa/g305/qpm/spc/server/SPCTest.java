package cn.edu.buaa.g305.qpm.spc.server;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.buaa.g305.qpm.spc.domain.*;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.SpcXMRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxmr.SpcXMROut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXROut;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXSIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXSOut;
import cn.edu.buaa.g305.qpm.spc.server.imp.SPCImp;
import cn.edu.buaa.g305.qpm.system.DoublePrecisonArrayToStringArray;
import static cn.edu.buaa.g305.qpm.system.DoublePrecisonArrayToStringArray.*;

public class SPCTest {

	@Test
	public void testXR() {
		SPCImp spc=new SPCImp();
		SpcXRIn spcxrIn=new SpcXRIn();
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
	    spcxrIn.setX(toStringPrecision(x,2));
	    System.out.println(spcxrIn);
	    SpcXROut spcxrOut=spc.computeXR(spcxrIn);
	    System.out.println(spcxrOut);
	    String[] expecteds=new String[]{"10.9837","10.9502","10.9168","0.1226","0.0580","0.0000"};
	    String[] actuals=new String[]{spcxrOut.getxUCL(),spcxrOut.getxCL(),spcxrOut.getxLCL(),
	    		                      spcxrOut.getrUCL(),spcxrOut.getrCL(),spcxrOut.getrLCL()};
	    assertArrayEquals(expecteds, actuals);
	   
	}
	@Test
	public void testXS() {
		SPCImp spc=new SPCImp();
		SpcXSIn spcxsIn=new SpcXSIn();
		String[] time=new String[4];
		for (int i = 0; i < time.length; i++) {
			time[i]=i+1+"";
		}
		spcxsIn.setTime(time);
		double[][] x=new double[][]{{171.6,40.5,98,48.9,145.7,92.1,47,86.8,92.5,
			                         26,77.6,129.2,73.8,24,46.9},
				                    {100,45.9,28.7,60,79.4,63.2,26.7,39.7,79.2,
			                         27.7,58.3,13.4,129.5,22.1,18.5},
			                        {27.5,27.6,39.5,37.7,79.9,37.1,32.2,11,20.9,
			                         26,56.8,46.9,17.4,41.4,32.2},
			                        {18.1,27.1,65.4,27.5,26.5,26.9,76,14.3,15.2,
			                         72.2,9.2,33.1,20.5,33.5,25.3}
				                    };
	    spcxsIn.setX(toStringPrecision(x,1));
	    SpcXSOut spcxsOut=spc.computeXS(spcxsIn);
	    System.out.println("spcxsOut:"+spcxsOut);
	    String[] expecteds=new String[]{"72.94","50.30","27.65","45.14","28.72","12.29"};
	    String[] actuals=new String[]{spcxsOut.getxUCL(),spcxsOut.getxCL(),spcxsOut.getxLCL(),
	    		                      spcxsOut.getsUCL(),spcxsOut.getsCL(),spcxsOut.getsLCL()};
	    assertArrayEquals(expecteds, actuals);
	   
	}
	
	@Test
	public void testXMR() {
		SPCImp spc=new SPCImp();
		SpcXMRIn spcxmrIn=new SpcXMRIn();
		String[] time=new String[80];
		for (int i = 0; i < time.length; i++) {
			time[i]=i+1+"";
		}
		spcxmrIn.setTime(time);
		double[] x=new double[]{50.5,43.5,45.5,39.8,42.9,44.3,44.9,42.9,39.8,39.3,
				                48.8,51,44.3,43,51.3,46.3,45.2,48.1,45.7,44.1,
				                40.6,45.7,51.9,47.3,46.4,44.4,49,47.9,45.5,44.8,
				                46,41.1,44.1,41.8,47.9,44.9,43.4,49,45.5,47.4,
				                50,49,42.6,41.7,38.5,44.5,46.5,41.7,42.6,41.7,
				                43.8,41.8,45.5,44.5,38.6,43.2,43.8,44.8,43.5,40.9,
				                50,43.4,48.3,46.4,43.4,52.3,45.2,42.2,44.8,42.2,
				                50,46.2,47.4,42.2,47,47.3,49.7,48,42,41
				                };
	    spcxmrIn.setX(toStringPrecision(x, 1));
	    SpcXMROut spcxmrOut=spc.computeXMR(spcxmrIn);
	    System.out.println("spcxmr:"+spcxmrIn);
	    System.out.println(spcxmrOut);
	    String[] expecteds=new String[]{"54.07","45.05","36.03","11.08","3.39","0"};
	    String[] actuals=new String[]{spcxmrOut.getxUCL(),spcxmrOut.getxCL(),spcxmrOut.getxLCL(),
	    		                      spcxmrOut.getMrUCL(),spcxmrOut.getMrCL(),spcxmrOut.getMrLCL()};
	    
	    assertArrayEquals(expecteds, actuals);
	  
	}
	@Test
	public void testC() {
		SPCImp spc=new SPCImp();
		SPCCIn spccIn=new SPCCIn();
		String[] time=new String[21];
		for (int i = 0; i < time.length; i++) {
			time[i]=i+1+"";
		}
		spccIn.setTime(time);
		double[] x=new double[]{0,0,0,4,0,
				                0,0,1,0,0,
				                0,0,3,1,1,
				                0,2,0,1,0,
				                0
				                };
	    spccIn.setX(x);
	    SPCCOut spccOut=spc.computeC(spccIn);
	    double[] expecteds=new double[]{0.62,2.98,0};
	    double[] actuals=new double[]{spccOut.getC(),spccOut.getcUCL(),spccOut.getcLCL()};
	    
	    assertArrayEquals(expecteds, actuals, 0.01);
	   
	}

}
