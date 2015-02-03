package cn.edu.buaa.g305.qpm.system;

import java.math.BigDecimal;

public class DoublePrecisonArrayToStringArray {
	
	public static String toStringPrecision(double doubleV,int precision)
	{	
		BigDecimal doubleBigDecimal=new BigDecimal(doubleV);
		String doubleS=doubleBigDecimal.setScale(precision,BigDecimal.ROUND_HALF_UP).toString();
	    return doubleS;	
	}
	
	public static String[] toStringPrecision(double[] doubleV,int precision)
	{
		int n=doubleV.length;
		String[] string=new String[n];
		for(int i=0;i<n;i++)
		{
			string[i]=toStringPrecision(doubleV[i], precision);
		}
		return string;		
	}
	public static String[][] toStringPrecision(double[][] doubleV,int precision)
	{
		int n=doubleV.length;
		String[][] string=new String[n][];
		for(int i=0;i<n;i++)
		{
			string[i]=toStringPrecision(doubleV[i], precision);
		}
		return string;		
	}
	

}
