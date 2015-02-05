package cn.edu.buaa.g305.qpm.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String loginPage()
	{
		return "login.jsp";
	}
	//主页
	@RequestMapping("/home")
	public String homePage()
	{
		return "home.jsp";
	}
	


}
