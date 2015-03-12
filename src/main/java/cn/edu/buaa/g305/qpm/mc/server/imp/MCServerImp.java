package cn.edu.buaa.g305.qpm.mc.server.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import cn.edu.buaa.g305.qpm.mc.dao.MCRepository;
import cn.edu.buaa.g305.qpm.mc.domain.MC;
import cn.edu.buaa.g305.qpm.mc.domain.MCParam;
import cn.edu.buaa.g305.qpm.mc.server.MCServer;
import cn.edu.buaa.g305.qpm.system.server.SystemFind;

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
			compute(mc);			
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
			compute(mc);			
		}
		return mc;
	}

	@Override
	public MC save(MC mc) {
		
		mc.setId(null);
		try{
			mc=mcRepository.save(mc);
		}
		catch(DuplicateKeyException e)
		{
			mc=new MC("名重复");
			return mc;
		}
		
		compute(mc);
		return mc;
		
	}

	@Override
	public MC update(MC mc, String id) {
		
		mc.setId(id);
		try{
			mc=mcRepository.save(mc);
		}
		catch(DuplicateKeyException e)
		{
			mc=new MC("名重复");
			return mc;
		}
		
		compute(mc);
		return mc;
				
	}

	@Override
	public List<MC> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MC> getListByProject(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNameListByProject(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void compute(MC mc)
	{
		try{
			
	   	 	mc.setResult(FormulaParse.compute(mc.getMcParam(),
	   	 			mc.getMcParam().getPredictionValue()));
			
		}
		catch(Exception e)
		{
			mc=new MC("计算参数出错");
		}
			
	}
	

}
