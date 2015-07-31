package com.reporting.mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SerieChart implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private List<Object[]> map;
	
	
	public String getName() {
		return name;
	}
	public List<Object[]> getMap() {
		return map;
	}
	public void setMap(List<Object[]> map) {
		this.map = map;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
