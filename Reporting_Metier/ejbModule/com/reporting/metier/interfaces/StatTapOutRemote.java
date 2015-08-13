package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatTapOutRemote {

	
	public List<Object[]> getStatTapOutByFilters(String x,String y,List<String> Where);
	public List<Integer> getAllYears();

}
