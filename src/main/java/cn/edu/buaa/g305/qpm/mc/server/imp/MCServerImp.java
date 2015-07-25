package cn.edu.buaa.g305.qpm.mc.server.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import cn.edu.buaa.g305.qpm.mc.dao.MCRepository;
import cn.edu.buaa.g305.qpm.mc.domain.MC;
import cn.edu.buaa.g305.qpm.mc.server.MCServer;
import cn.edu.buaa.g305.qpm.system.domain.Project;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

@Component
public class MCServerImp implements MCServer{
	
	@Autowired
	private MCRepository mcRepository;
	@Autowired
	private SystemFind systemFind;

	@Override
	public MC getByName(String name) {
		MC mc=mcRepository.findByName(name);
		if(mc==null)
		{
			mc=new MC("名为"+name+"mc资源不存在！");
		}
		else {
			mc=compute(mc);			
		}
		return mc;
	}

	@Override
	public MC getById(String id) {
		
		MC mc=mcRepository.findOne(id);
		if(mc==null)
		{
			mc=new MC("ID为"+id+"mc资源不存在！");
		}
		else {
			mc=compute(mc);			
		}
		return mc;
	}

	@Override
	public MC save(MC mc) {
		
		mc.setId(null);
		mc.setProject(systemFind.findProductAffiliation(mc.getProject().getName()));
		try{
			mc=mcRepository.save(mc);
		}
		catch(DuplicateKeyException e)
		{
			mc=new MC("名重复");
			return mc;
		}
		
		mc=compute(mc);
		return mc;
		
	}

	@Override
	public MC update(MC mc, String id) {
		
		mc.setId(id);
		mc.setProject(systemFind.findProductAffiliation(mc.getProject().getName()));
		
		try{
			mc=mcRepository.save(mc);
		}
		catch(DuplicateKeyException e)
		{
			mc=new MC("名重复");
			return mc;
		}
		
		mc=compute(mc);
		return mc;
				
	}
	@Override
	public MC delete(String id) {
		MC mc=mcRepository.findOne(id);
		if(mc!=null)
		{
			mcRepository.delete(id);
			return null;
		}
		else {
			mc=new MC("id："+id+"的资源不存在！");
			return mc;			
		}
		
	}

	@Override
	public MC deleteByName(String name) {
		MC mc=mcRepository.findByName(name);
		if(mc!=null)
		{
			mcRepository.delete(mc);
			return null;
		}
		else {
			mc=new MC("name："+name+"的资源不存在！");
			return mc;		
		}
	}

	@Override
	public List<MC> getAllList() {
		
		return (List<MC>) mcRepository.findAll();
	}

	@Override
	public List<MC> getListByProject(String name) {
		Project project=systemFind.findProjectByName(name);
		if(project==null)
		{
			return null;
		}
		else {
			return mcRepository.findByProject(project);
		}
	}

	
	
	private MC compute(MC mc)
	{
		try{
			
	   	 	mc.setResult(FormulaParse.compute(mc.getMcParam(),
	   	 			mc.getMcParam().getPredictionValue()));
	   	 	return mc;
			
		}
		catch(Exception e)
		{
			mc=new MC("计算参数出错");
			return mc;
		}
			
	}

	
	

}
