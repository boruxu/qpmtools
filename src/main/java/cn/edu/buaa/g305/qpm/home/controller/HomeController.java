package cn.edu.buaa.g305.qpm.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String loginPage()
	{
		return "jsp/login.jsp";
	}
	//主页
	@RequestMapping("/home")
	public String homePage()
	{
		return "jsp/home.jsp";
	}
	
	//主页
	@RequestMapping("/test/angular-spc")
	public String testSpc()
	{
		return "html/angular-spc.html";
	}


}
