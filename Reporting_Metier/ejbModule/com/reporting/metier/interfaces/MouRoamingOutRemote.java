package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;



@Remote
public interface MouRoamingOutRemote {

	
	public List<Object[]> getStatMouOut(String x,String list_y,List<String> Where);

	public List<Integer> getAllYears();
}
