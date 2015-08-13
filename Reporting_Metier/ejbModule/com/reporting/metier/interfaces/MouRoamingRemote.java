package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;



@Remote
public interface MouRoamingRemote {

	
	public List<Object[]> getStatMou(String x,String list_y,List<String> Where);

	public List<Integer> getAllYears();
}
