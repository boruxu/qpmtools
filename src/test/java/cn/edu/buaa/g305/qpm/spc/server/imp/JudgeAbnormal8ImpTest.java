package cn.edu.buaa.g305.qpm.spc.server.imp;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class JudgeAbnormal8ImpTest {
	
	 private JudgeAbnormal8Imp judgeAbnormal8ImpTest;
	 private Class<? extends JudgeAbnormal8Imp> jclass;
	 @Before
	 public void setUp() {
		 judgeAbnormal8ImpTest=new JudgeAbnormal8Imp();
		 jclass= judgeAbnormal8ImpTest.getClass();  // 获取被测试类的反射

	 }
	 
	//测试第一条准则
	@Test
	public void test_1() {
		//private String[] Judge_0(double[] x,double average,double sigma,double k)
		
	      try {	            
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,double.class,double.class,double.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_0", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={4.5,3.1,3,2.9,1,0,2,1,-3,-5,-3,-2.9,-3.1};
                double average=0;
                double sigma=1;
                double k=3;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,average,sigma,k); // 调用被测类的方法	            
	            String[] expecteds = {"0","1","9","12"};              
	            assertArrayEquals(expecteds, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }
	}
	//测试第2条准则
		@Test
		public void test_2() {
			//连续K点在中心线同一侧
			//private String[] Judge_1(double[] x,double average,int k){
			
		      try {  
		            @SuppressWarnings("rawtypes")
					Class[] args = new Class[]{double[].class,double.class,int.class}; // 建立参数
		            Method method = jclass.getDeclaredMethod("Judge_1", args); // 获取私有方法和他的参数
		            method.setAccessible(true); // 允许访问
		            
	                double[] x={-1,-2,-3,-4,-5,-6,-7,-8,-9,0,3,-14,0,1,0,3,-5
	                		,1,2,3,4,5,6,7,8,9,0,-4,-5,-6,-7,-8,-9,-8,-9};
	                double average=0;
	                int k=9;
		            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,average,k); // 调用被测类的方法	            
		            String[] expecteds = {"8","25"};              
		            assertArrayEquals(expecteds, actuals);
		            double[] y={1,2,3};
		            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,average,k); // 调用被测类的方法	            
		            String[] expecteds2 ={};             
		            assertArrayEquals(expecteds2, actuals);
		            
		            
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	fail("反射出错："+e.getMessage());
		        }
		}
		//测试第3条准则
		@Test
		public void test_3() {
			
			try {  
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,int.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_2", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={0,1,2,3,4,5,6,6,4,3,2,1,1,3,5,4,3,2,1,0,-2,-2};
                int k=6;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,k); // 调用被测类的方法
	            System.out.println(Arrays.deepToString(actuals));
	            String[] expecteds = {"6","20"};              
	            assertArrayEquals(expecteds, actuals);
	            double[] y={1,2,3};
	            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,k); // 调用被测类的方法	            
	            String[] expecteds2 ={};             
	            assertArrayEquals(expecteds2, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }			
		}//测试第4条准则
		@Test
		public void test_4() {
			
			try {  
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,int.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_3", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,2};
                int k=14;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,k); // 调用被测类的方法
	            System.out.println(Arrays.deepToString(actuals));
	            String[] expecteds = {"14","15"};              
	            assertArrayEquals(expecteds, actuals);
	            double[] y={1,2,3};
	            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,k); // 调用被测类的方法	            
	            String[] expecteds2 ={};             
	            assertArrayEquals(expecteds2, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }			
		}@Test
		//测试第5条准则
		public void test_5() {
			
			try {  
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,double.class,double.class,int.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_4", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={3,2,3,-3,-3,2,1,2,3,3,3,3,1,2,2};
                double average=0;
                double sigma=1;
                int k=2;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,average,sigma,k); // 调用被测类的方法
	            System.out.println(Arrays.deepToString(actuals));
	          	String[] expecteds = {"2","4","9","10","11"};              
	            assertArrayEquals(expecteds, actuals);
	            double[] y={3,3,3,1};
	            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,average,sigma,k); // 调用被测类的方法	            
	            String[] expecteds2 ={"1","2"};             
	            assertArrayEquals(expecteds2, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }			
		}
		@Test
		//测试第6条准则
		public void test_6() {
			
			try {  
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,double.class,double.class,int.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_5", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={2,1,2,-2,-2,1,0,1,2,2,2,2,0,1,1};
                double average=0;
                double sigma=1;
                int k=2;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,average,sigma,k); // 调用被测类的方法
	            System.out.println(Arrays.deepToString(actuals));
	          	String[] expecteds = {"2","4","9","10","11"};              
	            assertArrayEquals(expecteds, actuals);
	            double[] y={3,3,3,1};
	            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,average,sigma,k); // 调用被测类的方法	            
	            String[] expecteds2 ={"1","2"};             
	            assertArrayEquals(expecteds2, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }			
		}
		@Test
		//测试第7条准则
		public void test_7() {
			
			try {  
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,double.class,double.class,int.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_6", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,1.0,1.1};
                double average=0;
                double sigma=1;
                int k=15;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,average,sigma,k); // 调用被测类的方法
	            System.out.println(Arrays.deepToString(actuals));
	          	String[] expecteds = {"14","15","16"};              
	            assertArrayEquals(expecteds, actuals);
	            double[] y={3,3,3,1};
	            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,average,sigma,k); // 调用被测类的方法	            
	            String[] expecteds2 ={};             
	            assertArrayEquals(expecteds2, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }			
		}@Test
		//测试第8条准则
		public void test_8() {
			
			try {  
	            @SuppressWarnings("rawtypes")
				Class[] args = new Class[]{double[].class,double.class,double.class,int.class}; // 建立参数
	            Method method = jclass.getDeclaredMethod("Judge_7", args); // 获取私有方法和他的参数
	            method.setAccessible(true); // 允许访问
	            
                double[] x={2,2,2,2,2,2,2,-2,2,1};
                double average=0;
                double sigma=1;
                int k=8;
	            String[] actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,x,average,sigma,k); // 调用被测类的方法
	            System.out.println(Arrays.deepToString(actuals));
	          	String[] expecteds = {"7","8"};              
	            assertArrayEquals(expecteds, actuals);
	            double[] y={3,3,3,1};
	            actuals =  (String[]) method.invoke(judgeAbnormal8ImpTest,y,average,sigma,k); // 调用被测类的方法	            
	            String[] expecteds2 ={};             
	            assertArrayEquals(expecteds2, actuals);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	fail("反射出错："+e.getMessage());
	        }			
		}						

}
