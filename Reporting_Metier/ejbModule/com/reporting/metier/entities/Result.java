package com.reporting.metier.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1928194058428685091L;
	
	private Object[] resValue;
	private Object resX;
	
	public Object getResX() {
		return resX;
	}
	
	
	
	
	public Object[] getResValue() {
		return resValue;
	}
	
	public void setResValue(Object[] resValue) {
		this.resValue = resValue;
	}
	
	public Result(Object x,Object... str) {
		this.resValue=str;
		
	}
	public Result() {
		// TODO Auto-generated constructor stub
	}

}
