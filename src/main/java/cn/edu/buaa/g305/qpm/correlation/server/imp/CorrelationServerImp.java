package cn.edu.buaa.g305.qpm.correlation.server.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationInXYArray;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.Correlation;
import cn.edu.buaa.g305.qpm.correlation.server.CorrelationServer;

@Component
public class CorrelationServerImp implements CorrelationServer{

	@Autowired
	private Correlation correlation;

	public CorrelationOut[] computeRAndP(CorrelationIn correlationIn) {
		CorrelationOut[] correlationOuts=new CorrelationOut[correlationIn.getCorrelationIn().length];
		double[] xArray,yArray;
		int i=0;
		for (CorrelationInXYArray xyArray : correlationIn.getCorrelationIn()) {
			xArray=xyArray.getX();
			yArray=xyArray.getY();
			correlationOuts[i]=correlation.computeCorrelationAndPValue(xArray, yArray);
			i++;
		}
		return correlationOuts;
	}

}
