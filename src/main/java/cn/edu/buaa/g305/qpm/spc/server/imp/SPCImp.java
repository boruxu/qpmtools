package cn.edu.buaa.g305.qpm.spc.server.imp;

import cn.edu.buaa.g305.qpm.spc.domain.SPCXRIn;
import cn.edu.buaa.g305.qpm.spc.domain.SPCXROut;
import cn.edu.buaa.g305.qpm.spc.server.SPC;

public class SPCImp implements SPC {

	public SPCXROut computeXR(SPCXRIn spcxrIn) {
		
		double xSumAverage=0;
		double xAverage=0;
		double rSum=0;
		double rAverage=0;
		//计算X总平均值、极差R的平均值
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
						
				xAverage+=x;				
			}
			
			rSum+=max-min;			
			xAverage=xAverage/xSum.length;
			xSumAverage+=xAverage;			
		}
		rAverage=rSum/spcxrIn.getX().length;
		xSumAverage=xSumAverage/spcxrIn.getX().length;
		
		//计算控制限
		
		
		return null;
	}
	
	

}
