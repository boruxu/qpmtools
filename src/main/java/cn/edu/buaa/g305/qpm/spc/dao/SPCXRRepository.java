package cn.edu.buaa.g305.qpm.spc.dao;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.SpcXR;


public interface SPCXRRepository extends CrudRepository<SpcXR
, String> {
	
	SpcXR findByName(String name);

}