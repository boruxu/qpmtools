package cn.edu.buaa.g305.qpm.spc.server.imp;

import org.springframework.web.servlet.resource.GzipResourceResolver;

import cn.edu.buaa.g305.qpm.spc.domain.SPCXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXROut;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXSIn;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXSOut;
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

	public SPCXSOut computeXS(SPCXSIn spcxsIn) {
		SPCXSOut spcxsOut=new SPCXSOut();
		//样本数
		int n=spcxsIn.getX().length;
		//样本内样品数
		int group_n=spcxsIn.getX()[0].length;
		//样本内X平均值
		double[] xAverage=new double[n];
		//样本内标准差估计
		double[] s=new double[n];
		//样本间X的均值
		double xSumAverage=0;
		//样本间S的均值
		double sAverage=0;
		int i=0;
		//各个X平均值
		for (double[] xSum : spcxsIn.getX()) {

			for (double x : xSum) {				
				xAverage[i]+=x;								
			}
			xAverage[i]=xAverage[i]/group_n;
			xSumAverage+=xAverage[i];
			i++;	
		}
		//计算X总平均值
		xSumAverage=xSumAverage/n;
	    //计算各个s
		for (int m=0;i<n;i++)
		{
			for(int k=0;k<group_n;k++)
			{
				s[m]+=Math.pow(xAverage[k]-xSumAverage,2);
			}
			s[m]=s[m]/(group_n-1);
			sAverage+=s[m];
		}
		//计算s的平均值
		sAverage=sAverage/n;
		//计算X图的UCL和LCL，设置X图输出
		for (double x : xAverage) {
			
			x=precision(x, 4);
			
		}
		for (double sv : s) {
				
				sv=precision(sv, 4);
				
	    }
		if(spcxsIn.getSigma()<0)
		{
			spcxsOut.setX(xAverage);
			spcxsOut.setxCL(precision(xSumAverage, 4));
			spcxsOut.setxUCL(precision(xSumAverage+computeA3(group_n)*sAverage, 4));
			spcxsOut.setxLCL(precision(xSumAverage-computeA3(group_n)*sAverage, 4));
			//计算S图的UCL和LCL，设置S图输出
	      
			spcxsOut.setS(s);
			spcxsOut.setsCL(precision(sAverage, 4));
			spcxsOut.setsUCL(precision(sAverage*computeB4(group_n), 4));
			spcxsOut.setsLCL(precision(sAverage*computeB3(group_n), 4));
			
			spcxsOut.setTime(spcxsIn.getTime());
			return spcxsOut;
		}
		else
		{
			spcxsOut.setX(xAverage);
			spcxsOut.setxCL(precision(xSumAverage, 4));
			spcxsOut.setxUCL(precision(xSumAverage+3*spcxsIn.getSigma(), 4));
			spcxsOut.setxLCL(precision(xSumAverage-3*spcxsIn.getSigma(), 4));
			//计算S图的UCL和LCL，设置S图输出
	      
			spcxsOut.setS(s);
			spcxsOut.setsCL(precision(computeC4(group_n)*spcxsIn.getSigma(), 4));
			spcxsOut.setsUCL(precision(spcxsIn.getSigma()*computeB6(group_n), 4));
			spcxsOut.setsLCL(precision(spcxsIn.getSigma()*computeB5(group_n), 4));
			
			spcxsOut.setTime(spcxsIn.getTime());
			return spcxsOut;
			
		}
		
		
	}
	
	

}
