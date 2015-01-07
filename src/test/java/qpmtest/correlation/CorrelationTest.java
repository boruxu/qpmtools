package qpmtest.correlation;

import junit.textui.ResultPrinter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  


//XML���  
@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration(value = "src/main/java/spring.xml")  
 

public class CorrelationTest { 
	

  @Autowired  
  private WebApplicationContext wac;  
  private MockMvc mockMvc;  

  @Before  
  public void setUp() {  
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
  }
  @Test
  public void correlationJsonTest() throws Exception
  {
	  String requestBody = "{correlationIn:[{x:"
	  		+ "[45,59,33,81,77,26,19,55,50,34,99,61,38,72,59,25],"
	  		+ "y:[25,36,19,45,42,23,16,38,32,22,48,42,29,36,29,17],"
	  		+ "xName:\"����1X�ᣨ��λ��\","
	  		+ "yName:\"����1y�ᣨ��λ��\","
	  		+ "p:0,"
	  		+ "r:0,"
	  		+ "alpha:0.05},"
	  		+ "{x:[10,20,30,40,50,60,70,80],"
	  		+ "y:[10,20,30,40,50,60,70,80],"
	  		+ "xName:\"����2X�ᣨ��λ��\","
	  		+ "yName:\"����2y�ᣨ��λ��\","
	  		+ "p:0,"
	  		+ "r:0,"
	  		+ "alpha:0.05},"
	  		+ "{x:[10,20,30],y:[30,20,10],xName:\"����3X�ᣨ��λ��\","
	  		+ "yName:\"����3y�ᣨ��λ��\" ,p:0,r:0,alpha:0.05}]"
	  		+ "}";

	  mockMvc.perform(post("/correltion/outputProduct")
			          .contentType(MediaType.APPLICATION_JSON)
			          .content(requestBody)  
	                  .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk())
	         .andExpect(content().contentType(MediaType.APPLICATION_JSON)); 
	          
	          //.andDo()
	         // .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //��֤��ӦcontentType  
	         // .andExpect(jsonPath("$.id").value(1));  
  }

} 
