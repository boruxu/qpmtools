package cn.edu.buaa.g305.qpm.spc.dao;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.spcxs.SpcXS;

public interface SpcXSRepository extends CrudRepository<SpcXS
, String> {
	
	SpcXS findByName(String name);

}