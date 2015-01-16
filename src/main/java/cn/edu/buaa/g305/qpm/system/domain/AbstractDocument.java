package cn.edu.buaa.g305.qpm.system.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AbstractDocument {
	
	@Id
	private BigInteger idBigInteger;

	public BigInteger getIdBigInteger() {
		return idBigInteger;
	}

	public void setIdBigInteger(BigInteger idBigInteger) {
		this.idBigInteger = idBigInteger;
	}
	
}
