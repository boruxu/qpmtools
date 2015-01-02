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

@Controller
@RequestMapping("/correlation")
public class CorrelationController {

	//进入相关性分析主页
	@RequestMapping()
	public String correlationHome()
	{
		return "correlation.jsp";
	}
	//计算相关性
	@ExceptionHandler
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
		
		 CorrelationOut[] rAndPs=new  CorrelationOut[3];
		 for(int i=0;i<3;i++)
    	 {
    		 rAndPs[i]=new  CorrelationOut();
    		 rAndPs[i].setP(i);
    		 rAndPs[i].setR(i);
    	 }
         return rAndPs;
	}

	

}
