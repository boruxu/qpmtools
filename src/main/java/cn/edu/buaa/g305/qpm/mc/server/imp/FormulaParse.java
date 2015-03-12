package cn.edu.buaa.g305.qpm.mc.server.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import cn.edu.buaa.g305.qpm.mc.domain.MCAssumptionParam;
import cn.edu.buaa.g305.qpm.mc.domain.MCNormalParam;
import cn.edu.buaa.g305.qpm.mc.domain.MCParam;

public class FormulaParse {
	
	 public static String ADD="+";
	 public static String SUB="-";
	 public static String MUL="*";
	 public static String DIV="/";
	 public static String LEFT_BRACKET="(";
	 public static String RIGHT_BRACKET=")";

	 public static Map<String,Double[]> verificationParseAndSetvalue(MCParam mcparam) throws IOException 
	 {
		 //设置模拟次数
		 int num=mcparam.getSimulationNumber();
		 Map<String,Double[]> variableNameValue=new HashMap<String,Double[]>();
		 
		 //提取公式
		 String formula=mcparam.getPredictionValue();
		 //前缀表达式
		 List<String> infixExpression=ParseToken(formula);
		 
		 //设置操作数、数字
		 Map<String,Integer> number=new HashMap<String,Integer>();
		 Map<String,Integer> operator=new HashMap<String,Integer>();
		 
		 for(String n:infixExpression)
		 {
			 if(isOperator(n) || isBracket(n))
			 {
				 operator.put(n, infixExpression.indexOf(n));
			 }
			 else
			 {
				 number.put(n, infixExpression.indexOf(n)); 
			 }
		 }
		 		 
		 //设置常量
		 for( MCNormalParam m: mcparam.getMcNormalParamList())
	     {
			 Double[] temp=new Double[num];
			 String name=m.getName();
			 Double value=Double.valueOf(m.getValue());
			 for(int i=0;i<num;i++)
			 {
				 temp[i]=value;
			 }
			 variableNameValue.put(name, temp);
	      }
		  //设置假设变量
		  for (MCAssumptionParam m : mcparam.getMcAssumptionParamList())  
		  {
			  
			  String name=m.getName();
			  MonteCarloSimulation.setSimilationNumber(num);
			  if(m.getType().equals("0"))
			  {
				  Double[] temp=new Double[num];
				  temp=MonteCarloSimulation.uniformDistribution(m.getDistributionParam().get(0), m.getDistributionParam().get(1));
				  variableNameValue.put(name,temp);
			  }
			  else if(m.getType().equals("1"))
			  {
				  Double[] temp=new Double[num];
				  temp=MonteCarloSimulation.normalDistribution(m.getDistributionParam().get(0), m.getDistributionParam().get(1));
				  variableNameValue.put(name,temp);			
			  }
			  else
			  {
				  Double[] temp=new Double[num];
				  temp=MonteCarloSimulation.triangularDistributionBasic(m.getDistributionParam().get(0), m.getDistributionParam().get(1), m.getDistributionParam().get(2));
				  variableNameValue.put(name,temp);				
			  }

		    }
			return variableNameValue;		
	 }

	 public static Double[] compute(MCParam mcparam,String formula) throws IOException
	 {
		
		 Map<String,Double[]> variableNameValue=verificationParseAndSetvalue(mcparam);
		 List<String> infixExpression=ParseToken(formula);
		 for(String n:infixExpression)
		 {
			 System.out.print(n);
		 }
		 System.out.println();
		 List<String> postfixExpression=toPostfixExpression(infixExpression);
		 for(String n:postfixExpression)
		 {
			 System.out.print(n);
		 }
		 System.out.println();
		 
		 return computePostfixExpression(postfixExpression,variableNameValue);
	 }
	 
	 public static List<String> ParseToken(String inputFormula){
		 
		 
		 List<String> token = new ArrayList<String>();
		 
		 String arrays[] = inputFormula.split(" ");
		 
		  for(int i=0;i<arrays.length;i++)
		  {
			  
			  if(!arrays[i].equals(""))
			  {
				  token.add(arrays[i]);
			  }	    
		  }
		  return token;		 
	 }

	 public static boolean isOperator(String tokenString)
	 {
		 if(tokenString.equals(ADD)||tokenString.equals(SUB)||tokenString.equals(MUL)||tokenString.equals(DIV))
			 return true;
		 else
			 return false;
	 }

	 public static boolean isBracket(String tokenString)
	 {
		 if(tokenString.equals(LEFT_BRACKET)||tokenString.equals(RIGHT_BRACKET))
			 return true;
		 else
			 return false;
	 }
	
	 public static boolean judgeOperationPriority(String string1,String string2)
	 {
		 if(string1.equals(ADD)||string1.equals(SUB)||string2.equals(DIV)||string2.equals(MUL))
		 {
			 return false;
		 }
		 else
		 {
			 return true;
		 }
	 }
	
	 public static List<String> toPostfixExpression(List<String> infixExpression)
	 {
	
		 Stack<String> operatorStack = new Stack<String>();
	
		 Stack<String> resultStack = new Stack<String>();
	
		 List<String> postfixExpression = new ArrayList<String>();
		 
		 for(String n:infixExpression)
		 {
		
			 if(isOperator(n))
			 {
				 while(n!=null)
				 {
					 if(operatorStack.isEmpty()||operatorStack.peek().equals(LEFT_BRACKET))
					 {
						 operatorStack.push(n);
						 n=null;						 
					 }
					 else if(judgeOperationPriority(n,operatorStack.peek()))
					 {
						 operatorStack.push(n);
						 n=null;
					 }
					 else
					 {
						 resultStack.push(operatorStack.pop());
					 }					 
				 }
                 			 
			 }

			 else if(isBracket(n))
			 {
				 if(n.equals(LEFT_BRACKET))
					 operatorStack.push(n);
				 else
				 {
					 while(!operatorStack.peek().equals(LEFT_BRACKET))
						 resultStack.push(operatorStack.pop());
					 operatorStack.pop();
				 }				 
			 }
			
			 else
			 {
				 resultStack.push(n);
			 }
		 }
	
		 while(!operatorStack.isEmpty())
		 {
			 resultStack.push(operatorStack.pop());
		 }
		 while(!resultStack.isEmpty())
		 {
			 operatorStack.push(resultStack.pop());
		 }
		 while(!operatorStack.isEmpty())
		 {
			 postfixExpression.add(operatorStack.pop());
		 }
		 return postfixExpression;
	 }
 
	 public static Double[] computePostfixExpression(List<String> postfixExpression,Map<String,Double[]> variableNameValue)
	 {
		
		 Stack<Double[]> resultStack = new Stack<Double[]>();
		 
		 for(String n:postfixExpression)
		 {
			 if(isOperator(n))
			 {
				 Double[] s1=resultStack.pop();
				 Double[] s2=resultStack.pop();
				 resultStack.push(arrayCompute(s1,s2,n));
			 }
			 else
			 {
				 resultStack.push(variableNameValue.get(n));
				
			 }		 
		 }
		
		 return resultStack.pop();
		 
	 }

	 public static Double[] arrayCompute(Double[] stacktop,Double[] stacktopnext,String operator)
	 {
		 Double[] resultArray=new Double[stacktop.length];
		 if(operator.equals(ADD))
		 {
			 for(int i=0;i<stacktop.length;i++)
			 {
				 resultArray[i]=stacktopnext[i]+stacktop[i];
				 
			 }
		 }
	     else if(operator.equals(SUB))
	     {
			 for(int i=0;i<stacktop.length;i++)
			 {
				 resultArray[i]=stacktopnext[i]-stacktop[i];
				 
			 }
		 }	    	
	     else if(operator.equals(MUL))
	     {
			 for(int i=0;i<stacktop.length;i++)
			 {
				 resultArray[i]=stacktopnext[i]*stacktop[i];
				 
			 }
		 }    	 
	     else
	     {
			 for(int i=0;i<stacktop.length;i++)
			 {
				 resultArray[i]=stacktopnext[i]/stacktop[i];			 
			 }
		 }	    	 
		 return resultArray;
	 }

}
