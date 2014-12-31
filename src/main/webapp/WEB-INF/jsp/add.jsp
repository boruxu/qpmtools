<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <!--  >base href="<%=basePath%>"-->
    
    <title>RestfulJSON</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../styles/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../styles/datetimepicker.css" rel="stylesheet" media="screen">

  </head>
  
  <body>
       <input  width="50px" height="10px" value="发送json数据" onclick="ajax()"/>
  </body>
  	<script type="text/javascript" src="../scripts/jquery-1.8.3.js" charset="UTF-8"></script>


	<script type="text/javascript">

	function ajax(){

		 
		 var dataXY={
					x:[45,59,33,81,77,26,19,55,50,34,99,61,38,72,59,25],
					y:[25,36,19,45,42,23,16,38,32,22,48,42,29,36,29,17],
					xName:"测试1X轴（单位）",
					yName:"测试1y轴（单位）" };
				var dataXY1={
					x:[10,20,30,40,50,60,70,80],
					y:[10,20,30,40,50,60,70,80],
					xName:"测试2X轴（单位）",
					yName:"测试2y轴（单位）" };
				var dataXY2={
					x:[10,20,30],
					y:[30,20,10],
					xName:"测试3X轴（单位）",
					yName:"测试3y轴（单位）" };
				var dataXYArray=[dataXY,dataXY1,dataXY2];
				var json={
						projectname:"项目restful",
						projectType:"early-design",
						projDescription:"本项目为restful集成测试项目",
			            startDate:"2014-12-09",
			            endDate:"2014-12-30",
			            status:"项目开始",
			            languageName:"JAVA",
			            fptosloc:"100",
			            manpermonth:"4000",
			            computefunctionpoint:"1,2,3,1,2,3,1,2,3,1,2,3,1,2,3",
			            clientname:"客户Test",
			            email:"Test@sina.com"
				};
		
			 $.ajax({  
             type: "POST",
             url: "http://localhost:8080/qpmtools/correlation/outputProduct ",
             data:dataXY1,
				dataType: "json",
             error: function(e){
             	   alert(e.status);
					   alert("数据连接错误！");					 
					},
             success: function(json){
					
							     	
             },
          
			 });
    }

   </script> 
  </body>
</html>
