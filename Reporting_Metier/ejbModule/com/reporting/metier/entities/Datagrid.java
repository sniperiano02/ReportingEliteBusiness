package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(schema="stat")
public class Datagrid implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operationD;
	private String Daxe_x;
	private String Daxe_y;
	public String getOperationD() {
		return operationD;
	}
	public void setOperationD(String operationD) {
		this.operationD = operationD;
	}
	public String getDaxe_x() {
		return Daxe_x;
	}
	public void setDaxe_x(String daxe_x) {
		Daxe_x = daxe_x;
	}
	public String getDaxe_y() {
		return Daxe_y;
	}
	public void setDaxe_y(String daxe_y) {
		Daxe_y = daxe_y;
	}
	
	
	

}
