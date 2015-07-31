package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatRechargeRemote {

	
	
	public List<Object[]> getAllStatRecharge(String x, String y,List<String> Where);
	public List<Integer> getAllYears() ;
	
	public List<Object[]> getStatRechargeByType(List<String> Where);
}
