package cn.edu.buaa.g305.qpm.spc.server.imp;

import static cn.edu.buaa.g305.qpm.system.DoublePrecisonArrayToStringArray.toStringPrecision;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.edu.buaa.g305.qpm.spc.dao.SpcXRRepository;
import cn.edu.buaa.g305.qpm.spc.domain.*;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.spcxr.SpcXR;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration("classpath:spring.xml") 
public class SPCImpTest {

	@Autowired
	private SpcXRRepository spcxrRepository;
	@Autowired
	private SystemFind systemFind;
	@Test
	public void testDBCreate() {
		
		SPCImp  spcImp=new SPCImp();
		SpcXR spcXR=new SpcXR();
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
		spcXR.setInput(spcxrIn);
		
		spcXR.setOutput(spcImp.computeXR(spcxrIn));
		
		spcXR.setName("Test1");
		
		
		System.out.println(spcXR);
		
		spcXR.setError("111");
		
		@SuppressWarnings("unused")
		List<Link> links=spcXR.getLinks();
		links=null;
		
	    spcxrRepository.save(spcXR);	
		SpcXR spcXR2=spcxrRepository.save(spcXR);
		System.out.println(spcXR2);
	
	}

}
