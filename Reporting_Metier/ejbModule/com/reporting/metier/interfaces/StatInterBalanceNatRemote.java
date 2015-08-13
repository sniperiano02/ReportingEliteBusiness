package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatInterBalanceNatRemote {

	
	public List<Object[]> getStatInterBalanceNat(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatInterBalanceNat(String Where);
	public List<Integer> getAllYears();
}
