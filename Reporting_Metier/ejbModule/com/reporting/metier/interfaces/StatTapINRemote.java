package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatTapINRemote {

	
	public List<Object[]> getStatTapOutByFilters(String x,String y,List<String> Where);
	public List<Integer> getAllYears();
	public List<String> getTypeCall();
	public List<Object[]> getAllStatTarifMoy(String x,List<String> Where);

}
