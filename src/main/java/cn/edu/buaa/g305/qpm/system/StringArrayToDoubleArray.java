package cn.edu.buaa.g305.qpm.system;

public class StringArrayToDoubleArray {
	
	//后续添加出错处理
	public static double toDouble(String string)
	{
		return Double.parseDouble(string); 
	}
	
	public static double[] toDouble(String[] string)
	{
		int n=string.length;
		double[] doubleV=new double[n];
		for(int i=0;i<n;i++)
		{
			doubleV[i]=toDouble(string[i]);
		}
		return doubleV; 
	}
	
	public static double[][] toDouble(String[][] string)
	{
		int n=string.length;
		double[][] doubleV=new double[n][];
		for(int i=0;i<n;i++)
		{
			doubleV[i]=toDouble(string[i]);
		}
		return doubleV; 
	}


}
