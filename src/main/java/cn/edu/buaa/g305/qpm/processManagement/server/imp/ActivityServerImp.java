package cn.edu.buaa.g305.qpm.processManagement.server.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.NoHandlerFoundException;

import cn.edu.buaa.g305.qpm.processManagement.dao.ActivityRepository;
import cn.edu.buaa.g305.qpm.processManagement.domian.Activity;
import cn.edu.buaa.g305.qpm.processManagement.server.ActivitytServer;
@Component
public class ActivityServerImp implements ActivitytServer{
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity getByName(String name) {
		return activityRepository.findByName(name);
	}

	@Override
	public Activity getById(String id) {
		
		return activityRepository.findOne(id);
	}

	@Override
	public void delete(String id) {	
		 activityRepository.delete(id);
	}

	@Override
	public void deleteByName(String name) {
		Activity activity=activityRepository.findByName(name);
		if(activity!=null)
		{
			activityRepository.delete(activity);
		}
		
	}


	@Override
	public Activity save(Activity activity) {
		activity.setId(null);
		return activityRepository.save(activity);
	}

	@Override
	public Activity update(Activity activity, String id) throws NoHandlerFoundException {
        activity.setId(id);
        Activity activityR=activityRepository.findOne(id);
        if(activityR==null)
        {
        	throw new NoHandlerFoundException("post", id, null);
        }
        else {
        	activityR.setFileName(activity.getFileName());
        	activityR.setName(activity.getName());
        	activityR.setDescription(activity.getDescription());
		}
		return activityRepository.save(activity);
	}

	@Override
	public List<Activity> getAllList() {
		return (List<Activity>) activityRepository.findAll();
	}

}
