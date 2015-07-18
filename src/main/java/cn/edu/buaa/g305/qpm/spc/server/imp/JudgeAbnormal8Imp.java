package cn.edu.buaa.g305.qpm.spc.server.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;
import cn.edu.buaa.g305.qpm.spc.server.JudgeAbnormal8;

public class JudgeAbnormal8Imp implements JudgeAbnormal8{

	@Override
	public AbnormalPoint JudgeAbnormalPoints(double[] x,double average,double sigma,AbnormalPoint abnormalPoint) {
        boolean[] check=abnormalPoint.getCheck();
        //后面的k使用int强制转换
        double[] k=abnormalPoint.getK();
        //初始化8条准则数组
        abnormalPoint.setAbnormalPoint(new String[8][]);
		for(int i=0;i<check.length;i++)
		{
			if(check[i])
			{
				switch (i) {
				case 0:
				    {
				    	abnormalPoint.getAbnormalPoint()[i]=Judge_0(x,average,sigma,k[i]);
				    	break;
				    }					
				case 1:
			    	{
			    		abnormalPoint.getAbnormalPoint()[i]=Judge_1(x,average,(int) k[i]);
			    		break;
			    	}	
				case 2:
			    	{
			    		abnormalPoint.getAbnormalPoint()[i]=Judge_2(x,(int) k[i]);
			    		break;
			    	}	
				case 3:
			    	{
			    		abnormalPoint.getAbnormalPoint()[i]=Judge_3(x,(int) k[i]);
			    		break;
			    	}	
				case 4:
			    	{
			    		abnormalPoint.getAbnormalPoint()[i]=Judge_4(x,average,sigma,(int)k[i]);
			    		break;
			    	}	
				case 5:
			    	{
			    		abnormalPoint.getAbnormalPoint()[i]=Judge_5(x,average,sigma,(int)k[i]);
			    		break;
			    	}	
				case 6:
			    	{
			    		abnormalPoint.getAbnormalPoint()[i]=Judge_6(x,average,sigma,(int)k[i]);
			    		break;
			    	}
				case 7:
		    	{
		    		abnormalPoint.getAbnormalPoint()[i]=Judge_7(x,average,sigma,(int)k[i]);
		    		break;
		    	}
				default:
			    	{
			    		
			    	}	
				}
			}
		}
		return abnormalPoint;
	}
	//1个点，距离中心线大于K个标准差
	private String[] Judge_0(double[] x,double average,double sigma,double k){
        List<String> outList=new ArrayList<String>();
        double lineUp=average+sigma*k;
        double lineDown=average-sigma*k;
        for(int i=0;i<x.length;i++)
        {
        	if(x[i]>lineUp||x[i]<lineDown)
        	{
        		outList.add(i+"");
        	}
        }         
		return listToArray(outList);
	}
	//连续K点在中心线同一侧
	private String[] Judge_1(double[] x,double average,int k){
		List<String> ks=new ArrayList<String>();
		double[] xSub=new double[k]; 
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int up=0;
			int down=0;

			for(int j=0;j<k;j++)
			{
				if((xSub[j]-average)>0)
				{
					up+=1;
					if(down>0)
					{
						break;
					}
				}
				else if((xSub[j]-average)<0)
				{
					down+=1;
					if(up>0)
					{
						break;
					}
				}

				
				if(up==k||down==k)
				{
					ks.add(i+k-1+"");
				}
			}
		}
		
		return listToArray(ks);
	}
	//连续K个点，全部递增或全部递减
	private String[] Judge_2(double[] x,int k){
		k=k+1;
		List<String> ks=new ArrayList<String>();
		double[] xSub=new double[k]; 
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int up=0;
			int down=0;
			for(int j=1;j<k;j++)
			{
				if((xSub[j]-xSub[j-1])>0)
				{
					up+=1;
					if(down>0)
					{
						break;
					}
				}
				else if((xSub[j]-xSub[j-1])<0)
				{
					down+=1;
					if(up>0)
					{
						break;
					}
				}
		
				
				if(up==(k-1)||down==(k-1))
				{
					ks.add(i+k-1+"");
				}
			}
		}
		
		return listToArray(ks);
	}
	//连续K个点，上下交错
	private String[] Judge_3(double[] x,int k){
		k=k+1;
		List<String> ks=new ArrayList<String>();
		double[] xSub=new double[k]; 
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int upDown=0;
			int number=0;
			for(int j=1;j<k;j++)
			{
				if((xSub[j]-xSub[j-1])>0)
				{
					upDown+=1;
					if(upDown>=2)
					{
						break;
					}
					else {
						number+=1;
					}
				}
				else if((xSub[j]-xSub[j-1])<0)
				{
					upDown-=1;
					if(upDown<=-2)
					{
						break;
					}
					else {
						number+=1;
					}
				}
				else{
					break;
				}
				
				
				if(number==(k-1))
				{
					ks.add(i+k-1+"");
				}
			}
		}
		
		return listToArray(ks);
	}
	//K+1个点中有K个点距离中心线（同侧）大于2个标准差
	private String[] Judge_4(double[] x,double average, double sigma,int k){
		k=k+1;
		Set<String> ks=new LinkedHashSet<String>();
		double[] xSub=new double[k]; 
		double lineUp=average+2*sigma;
		double lineDown=average-2*sigma;
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int up=0;
			int down=0;
			for(int j=0;j<k;j++)
			{
				if(xSub[j]>lineUp)
				{
					up+=1;
					if(up>=(k-1))
					{
						ks.add(i+j+"");			     
					}
				}
				else if(xSub[j]<lineDown)
				{
					down+=1;
					if(down>=(k-1))
					{
						 ks.add(i+j+"");
					}
				}				
			}

		}
		return SetToArray(ks);

	}

	//K+1个点中有K个点距离中心线（同侧）大于1个标准差
	private String[] Judge_5(double[] x,double average, double sigma,int k){
		k=k+1;
		Set<String> ks=new LinkedHashSet<String>();
		double[] xSub=new double[k]; 
		double lineUp=average+sigma;
		double lineDown=average-sigma;
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int up=0;
			int down=0;
			for(int j=0;j<k;j++)
			{
				if(xSub[j]>lineUp)
				{
					up+=1;
					if(up>=(k-1))
					{
						ks.add(i+j+"");		     
					}
				}
				else if(xSub[j]<lineDown)
				{
					down+=1;
					if(down>=(k-1))
					{
						 ks.add(i+j+"");
					}
				}				
			}

		}
		return SetToArray(ks);
	}
	//连续K个点，距离中心线（任一侧）1个标准差以内
	private String[] Judge_6(double[] x,double average, double sigma,int k){
		List<String> ks=new ArrayList<String>();
		double[] xSub=new double[k]; 
		double lineUp=average+sigma;
		double lineDown=average-sigma;
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int number=0;
			for(int j=0;j<k;j++)
			{
				if(xSub[j]<=lineUp&&xSub[j]>=lineDown)
				{
					number+=1;
				}
				else{
					break;
				}

				
				if(number==k)
				{
					ks.add(i+k-1+"");
				}
			}
		}
		
		return listToArray(ks);
	}
	//连续K个点，距离中心线（任一侧）大于1个标准差
	private String[] Judge_7(double[] x,double average, double sigma,int k){
		List<String> ks=new ArrayList<String>();
		double[] xSub=new double[k]; 
		double lineUp=average+sigma;
		double lineDown=average-sigma;
		for(int i=0;i<=x.length-k;i++)
		{			
			xSub=Arrays.copyOfRange(x, i, i+k);
			int number=0;
			for(int j=0;j<k;j++)
			{
				if(xSub[j]>lineUp||xSub[j]<lineDown)
				{
					number+=1;
				}
				else {
					break;
				}
				
				if(number==k)
				{
					ks.add(i+k-1+"");
				}
			}
		}
		
		return listToArray(ks);
	}
	
	private String[] listToArray(List<String> list)
	{
		String[] string=new String[list.size()];
		for(int i=0;i<list.size();i++)
		{
			string[i]=list.get(i);
		}
		return string;
	}
	private String[] SetToArray(Set<String> ks) {
		String[] string=new String[ks.size()];
		int i=0;
		for(String s:ks)
		{
			string[i]=s;
			i++;
		}
		return string;
	}

	

}
