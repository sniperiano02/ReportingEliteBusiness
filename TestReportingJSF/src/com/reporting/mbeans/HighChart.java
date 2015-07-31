package com.reporting.mbeans;

import java.io.Serializable;
import java.util.List;

public class HighChart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private List<SerieChart> series;
	
	
	public String getName() {
		return name;
	}
	public List<SerieChart> getSeries() {
		return series;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSeries(List<SerieChart> series) {
		this.series = series;
	}

}
