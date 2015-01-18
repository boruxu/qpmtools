package cn.edu.buaa.g305.qpm.correlation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationInXYArray;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.CorrelationServer;

@Controller
@RequestMapping("/correlation")
public class CorrelationController {

	@Autowired
	private CorrelationServer correlationServer;

	//相关分析主页
	@RequestMapping()
	public String correlationHome()
	{
		return "correlation.jsp";
	}
	//计算相关分析
	//@ExceptionHandler
	@RequestMapping(value="/outputProduct",method=RequestMethod.POST)
	@ResponseBody
	public  CorrelationOut[] getOutputProduct(@RequestBody CorrelationIn correlationIn)
	{
			
		for (CorrelationInXYArray dataArray : correlationIn.getCorrelationIn()) {
			
			System.out.print("x:[");
			for (Double x : dataArray.getX()) {
				
				System.out.print(x+",");
				
			}
			System.out.println("]");
			

			System.out.print("y:[");
			for (Double y : dataArray.getY()) {
				
				System.out.print(y+",");
				
			}
			System.out.println("]");
	
		}
		correlationServer.saveCorrelationIn(correlationIn);
		 /*CorrelationOut[] rAndPs=new  CorrelationOut[3];
		 CorrelationImp correlationImp=new CorrelationImp();
		 double[] xArray=new double[]{45,59,33,81,77,26,19,55,50,34,99,61,38,72,59,25};
		 double[] yArray=new double[]{25,36,19,45,42,23,16,38,32,22,48,42,29,36,29,17};
		 rAndPs[0]=correlationImp.computeCorrelationAndPValue(xArray, yArray);
		 for(int i=1;i<3;i++)
    	 {
    		 rAndPs[i]=new  CorrelationOut();
    		 rAndPs[i].setP(i);
    		 rAndPs[i].setR(i);
    	 }*/
         return correlationServer.computeRAndP(correlationIn);
	}

	

}
