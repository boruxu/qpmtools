package cn.edu.buaa.g305.qpm.spc.server.imp;

import cn.edu.buaa.g305.qpm.spc.domain.SPCXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXROut;
import cn.edu.buaa.g305.qpm.spc.server.SPC;
import static cn.edu.buaa.g305.qpm.spc.system.VariableControlChartsFactor.*;
import static cn.edu.buaa.g305.qpm.system.DoublePrecision.*;

public class SPCImp implements SPC {

	public SPCXROut computeXR(SPCXRIn spcxrIn) {
		
		SPCXROut spcxrOut=new SPCXROut();
		//样本数
		int n=spcxrIn.getX().length;
		//样本内样品数
		int group_n=spcxrIn.getX()[0].length;
		//样本内X平均值
		double[] xAverage=new double[n];
		//样本内极差
		double[] r=new double[n];
		//样本间X的均值
		double xSumAverage=0;
		//样本间R的均值
		double rAverage=0;
		int i=0;
		//计算X总平均值和R的平均值
		for (double[] xSum : spcxrIn.getX()) {
			
			double min=xSum[0];
			double max=xSum[0];
			
			for (double x : xSum) {
				
				if(x<min)
				{
					min=x;
				}
				else if(x>max)
				{
					max=x;
				}
				xAverage[i]+=x;
								
			}
			xAverage[i]=xAverage[i]/group_n;
			xSumAverage+=xAverage[i];
			r[i]=max-min;
			rAverage+=r[i];
			i++;	
		}
		
		xSumAverage=xSumAverage/n;
		rAverage=rAverage/n;
		//计算X图的UCL和LCL，设置X图输出
		for (double x : xAverage) {
			
			x=precision(x, 4);
			
		}
		spcxrOut.setX(xAverage);
		spcxrOut.setxCL(precision(xSumAverage, 4));
		spcxrOut.setxUCL(precision(xSumAverage+computeA2(group_n)*rAverage, 4));
		spcxrOut.setxLCL(precision(xSumAverage-computeA2(group_n)*rAverage, 4));
		
		//计算R图的UCL和LCL，设置R图输出
        for (double rv : r) {
			
			rv=precision(rv, 4);
			
		}
		spcxrOut.setR(r);
		spcxrOut.setrCL(precision(rAverage, 4));
		spcxrOut.setrUCL(precision(rAverage*computeD4(group_n), 4));
		spcxrOut.setrLCL(precision(rAverage*computeD3(group_n), 4));
		
		spcxrOut.setTime(spcxrIn.getTime());
		return spcxrOut;
	}
	
	

}
