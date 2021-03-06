package cn.edu.buaa.g305.qpm.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String loginPage()
	{
		return "index";
	}
	//主页
	@RequestMapping("/home/{name}")
	public String homePage(@PathVariable String name)
	{
		return "home/"+name;
	}
	
	@RequestMapping("/spc/{name}")
	public String spcPage(@PathVariable String name)
	{
		return "spc/"+name;
	}
	@RequestMapping("/risk/{name}")
	public String riskPage(@PathVariable String name)
	{
		return "risk/"+name;
	}
	@RequestMapping("/risk/{name}/{name2}")
	public String riskPage2(@PathVariable String name,
			@PathVariable String name2)
	{
		return "risk/"+name+"/"+name2;
	}
	@RequestMapping("/mc/{name}")
	public String mcPage(@PathVariable String name)
	{
		return "mc/"+name;
	}
	@RequestMapping("/asset/{name}")
	public String assetPage(@PathVariable String name)
	{
		return "asset/"+name;
	}
	@RequestMapping("/regress/{name}")
	public String regressPage(@PathVariable String name)
	{
		return "regress/"+name;
	}
	
	@RequestMapping("/pm/{name1}/{name2}")
	public String pmTagPage(@PathVariable String name1,@PathVariable String name2)
	{
		return "pm/"+name1+"/"+name2;
	}
	
	
	//主页
	@RequestMapping("/test/angular-spc")
	public String testSpc()
	{
		return "html/angular-spc.html";
	}


}
