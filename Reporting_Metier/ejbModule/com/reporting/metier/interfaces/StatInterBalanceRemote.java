package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatInterBalanceRemote {

	
	public List<Object[]> getStatInterBalance(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatInterBalance(String Where);
	public List<Integer> getAllYears();
}
