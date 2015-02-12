qpmtools
========

本项目使用MAVEN，Eclipse开发，eclipse设置默认编码格式UTF-8以保证git上可浏览中文注释
为支持URI中文
tomcat设置默认编码为UTF-8（使用的浏览器发送URI编码也为UTF-8）
在tomcat中server.xml中 设置 
  <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" URIEncoding="UTF-8" />
参考网站：
超媒体即应用状态引擎 spring-hateoas示例程序 http://spring.io/guides/gs/rest-hateoas/  
其应用在本项目中，生成动态的http资源地址

测试
1、JUnit
2、web端Restful API调试工具火狐Poster插件，https://addons.mozilla.org/zh-CN/firefox/addon/poster/
   （使用火狐浏览器，启动工具调试工具Ctrl + Alt + P）