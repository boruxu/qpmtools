package cn.edu.buaa.g305.qpm.correlation.server.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.correlation.dao.CorrelationInRepository;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationIn;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationInXYArray;
import cn.edu.buaa.g305.qpm.correlation.domain.CorrelationOut;
import cn.edu.buaa.g305.qpm.correlation.server.Correlation;
import cn.edu.buaa.g305.qpm.correlation.server.CorrelationServer;
import cn.edu.buaa.g305.qpm.system.dao.ProjectRepository;
import cn.edu.buaa.g305.qpm.system.domain.Project;

@Component
public class CorrelationServerImp implements CorrelationServer{

	@Autowired
	private Correlation correlation;
	@Autowired
	private CorrelationInRepository correlationInRepository;
	@Autowired
	private ProjectRepository projectRepository;

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

	public void saveCorrelationI(CorrelationIn correlationIn) {
	    correlationIn.setProject(findProject(correlationIn));
		correlationInRepository.save(correlationIn);
		
	}

	public Project findProject(CorrelationIn correlationIn) {
		
		String name=correlationIn.getProject().getName();
		List<Project> projects=projectRepository.findByName(name);
		return projects.get(0);
	}

}
