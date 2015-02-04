package cn.edu.buaa.g305.qpm.spc.dao;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import cn.edu.buaa.g305.qpm.spc.domain.SpcXR;


public interface SPCXRRepository extends CrudRepository<SpcXR
, BigInteger> {
	
	SpcXR findByName(String name);

}