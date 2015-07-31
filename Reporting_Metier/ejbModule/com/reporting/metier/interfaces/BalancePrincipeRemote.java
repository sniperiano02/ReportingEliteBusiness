package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface BalancePrincipeRemote {

	
	public List<Object[]> getBalance(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsBalance(String Where);
	public List<Integer> getAllYears();
}
