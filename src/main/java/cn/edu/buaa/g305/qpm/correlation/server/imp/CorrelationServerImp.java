package cn.edu.buaa.g305.qpm.correlation.server.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.correlation.dao.CorrelationInRepository;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationInXYArray;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.Correlation;
import cn.edu.buaa.g305.qpm.correlation.server.CorrelationServer;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@Component
public class CorrelationServerImp implements CorrelationServer{

	@Autowired
	private Correlation correlation;
	@Autowired
	private CorrelationInRepository correlationInRepository;
	@Autowired
	private SystemFind systemFind;
	

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

	public void saveCorrelationIn(CorrelationIn correlationIn) {
	   
		correlationIn.setProject(
				systemFind.findProductAffiliation(correlationIn.getProject()));
		if(correlationInRepository.findByName(correlationIn.getName())==null)
		{
			correlationInRepository.save(correlationIn);
		}
		else
		{
			System.out.println("重复键！");
		}
		    	
	}

	public CorrelationIn getCorrelationInByName(String name) {
		
	    CorrelationIn correlationIn=correlationInRepository.findByName(name);
		if(correlationIn==null)
		{
			System.out.println("为空");
			return null;
		}
		else {
			return correlationIn;
		}	
	}
	

}
