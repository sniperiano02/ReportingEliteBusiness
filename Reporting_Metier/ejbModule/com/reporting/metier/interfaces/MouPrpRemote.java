package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MouPrpRemote {

	public List<Object[]> getStatMou(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatMou(String Where);
	public List<Integer> getAllYears();
	
	
}
