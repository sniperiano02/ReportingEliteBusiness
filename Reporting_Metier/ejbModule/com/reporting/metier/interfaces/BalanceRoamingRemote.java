package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface BalanceRoamingRemote {

	public List<Object[]> getStatBalance(String x,String list_y,List<String> Where);

	public List<Integer> getAllYears(String type_date);
	
	
}
