package cn.edu.buaa.g305.qpm.system.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AbstractDocument {
	
	@Id
	protected BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

}
