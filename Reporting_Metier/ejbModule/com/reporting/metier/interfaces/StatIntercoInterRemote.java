package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatIntercoInterRemote {

	
	public List<Object[]>   getStatInterco(String x,String list_y,String groupby,List<String> Where);
	public List<Integer> getAllYears();
	
}
