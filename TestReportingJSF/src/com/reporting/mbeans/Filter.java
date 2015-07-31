package com.reporting.mbeans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



public class Filter implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
	
	
	
	private String Label;
	
	private String value;
	
	public void setLabel(String label) {
		Label = label;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return Label;
	}
	public String getValue() {
		return value;
	}
	
	

}
