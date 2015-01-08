package qpmtest.correlation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
  

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration("classpath:spring.xml")  
 

public class CorrelationTest { 
	


  @Autowired
  private WebApplicationContext wac;
  
  public void setWac(WebApplicationContext wac) {
	this.wac = wac;
  }
  private MockMvc mockMvc;  

  @Before  
  public void setUp() {  
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
  }
  @Test
  public void correlationJsonTest() throws Exception
  {
	  String requestBody = "{\"correlationIn\":[{\"x\":[45,59,33,81,77,26,19,55,50,34,99,61,38,72,59,25],"
			  +"\"y\":[25,36,19,45,42,23,16,38,32,22,48,42,29,36,29,17],\"xName\":\"测试1X轴（单位）\""
		      +",\"yName\":\"测试1y轴（单位）\",\"p\":0,\"r\":0,\"alpha\":0.05},"
		      +"{\"x\":[10,20,30,40,50,60,70,80],\"y\":[10,20,30,40,50,60,70,80],"
			  +"\"xName\":\"测试2X轴（单位）\",\"yName\":\"测试2y轴（单位）\",\"p\":0,\"r\":0,\"alpha\":0.05},"
			  +"{\"x\":[10,20,30],\"y\":[30,20,10],\"xName\":\"测试3X轴（单位）\",\"yName\":\"测试3y轴（单位）\","
			  +"\"p\":0,\"r\":0,\"alpha\":0.05}]};";

	  mockMvc.perform(post("/correlation/outputProduct")
			          .contentType(MediaType.APPLICATION_JSON)
			          .content(requestBody)  
	                  .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk())
	         .andExpect(content().contentType("application/json;charset=UTF-8")); 
	  mockMvc.perform(get("/home"))
             .andExpect(status().isOk())
             .andReturn(); 
  }

} 
