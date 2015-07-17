package cn.edu.buaa.g305.qpm.spc.server;

import cn.edu.buaa.g305.qpm.spc.domain.AbnormalPoint;

public interface JudgeAbnormal8 {
	
	AbnormalPoint JudgeAbnormalPoints(double[] x,double average,double sigma,AbnormalPoint abnormalPoint);
	
}
