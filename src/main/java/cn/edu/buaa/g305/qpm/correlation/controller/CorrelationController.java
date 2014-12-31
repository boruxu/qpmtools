package cn.edu.buaa.g305.qpm.correlation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/correlation")
public class CorrelationController {
	
	@RequestMapping()
	public String correlationHome()
	{
		return "correlation.jsp";
	}
	@RequestMapping("/add")
	public String correlationAdd()
	{
		return "add.jsp";
	}
	
	@ExceptionHandler
	@RequestMapping(value="/outputProduct",method=RequestMethod.POST)
	@ResponseBody
	public  RAndP[] getOutputProduct(@RequestBody DataArrays dataArrays)
	{
			
		for (DataArray dataArray : dataArrays.getDataArrays()) {
			
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
		
		RAndP[] rAndPs=new RAndP[3];
		 for(int i=0;i<3;i++)
    	 {
    		 rAndPs[i]=new RAndP();
    		 rAndPs[i].setP(i);
    		 rAndPs[i].setR(i);
    	 }
         return rAndPs;
	}

	

}
