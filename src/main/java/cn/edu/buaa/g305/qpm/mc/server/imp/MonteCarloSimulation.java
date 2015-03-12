package cn.edu.buaa.g305.qpm.mc.server.imp;

import java.io.IOException;
import java.util.Random;

public class MonteCarloSimulation
{ 
	private static Random r1;
	private static Random r2;
	private static int SimilationNumber=100000; 
	    
	public static int getSimilationNumber() {
		return SimilationNumber;
	}
	public static void setSimilationNumber(int similationNumber) {
		SimilationNumber = similationNumber;
	}
	//�����̬�ֲ������
	public static Double[] normalDistribution(double u,double sigma)throws IOException{
		double s1,s2,s3;
		Double[] s4=new Double[SimilationNumber];
		r1=new Random();
		r2=new Random();
		//FileWriter f=new FileWriter("D://1.txt");
		for(int i=0;i<SimilationNumber;i++)
		{			
			s1=r1.nextDouble();
			s2=r2.nextDouble();
			s3=Math.sqrt((-2)*Math.log(s1))*Math.cos(2*Math.PI*s2);
			s4[i]=s3*sigma+u;
			//f.write(s4[i]+"\r\n");     
			//System.out.println(s4[i]);
		}
		    //f.close();
		    return s4;
	}
	//��ɾ��ȷֲ������
	public static Double[] uniformDistribution(double lowerBound,double upperBound) throws IOException{
		Double[] s1=new Double[SimilationNumber];
		r1=new Random();
		//FileWriter f=new FileWriter("D://2.txt");
		for(int i=0;i<SimilationNumber;i++)
		{
			s1[i]=r1.nextDouble()*(upperBound-lowerBound)+lowerBound;
			//f.write(s1[i]+"\r\n");
			//System.out.println(s1[i]);
		}
		//f.close();
		return s1;
	}
	//�����Ƿֲ������
	public static Double[] triangularDistributionBasic(double min,double mode,double max) throws IOException{
		double c,b;
		c=mode-min;
		b=max-min;
		Double[] s1=new Double[SimilationNumber];
		r1=new Random();
		//FileWriter f=new FileWriter("D://3.txt");
		for(int i=0;i<SimilationNumber;i++)
		{
			double udp;
			udp=r1.nextDouble();
			if(udp<(c/b))
			{
				s1[i]=Math.sqrt(udp*b*c)+min;
			}
			else
			{
				s1[i]=b-Math.sqrt((1-udp)*b*(b-c))+min;
			}
			//f.write(s1[i]+"\r\n");
		    //System.out.println(s1[i]);          
		}
		//f.close();
		return s1;
	}   
 /*public static void main(String[] args) throws IOException 
 { 
	 MonteCarloSimulation ww=new MonteCarloSimulation();
	 Double[] T1=new Double[SimilationNumber];
	 Double[] T2=new Double[SimilationNumber];
	 Double[] T3=new Double[SimilationNumber];
	 Double[] DD=new Double[SimilationNumber];
	 T1=ww.triangularDistributionBasic(0.15,0.2,0.45);
	 T2=ww.triangularDistributionBasic(15,20,30);
	 T3=ww.triangularDistributionBasic(2,7,8);
	 FileWriter f=new FileWriter("D://4.txt");
	 for(int i=0;i<SimilationNumber;i++)
	 {
		 DD[i]=(389+2.12*T1[i]+5.32*T2[i]-24.1*T3[i])/1000;
		 f.write(DD[i]+","+"\r\n");
	 }
	 f.close();	 
  }
*/
}