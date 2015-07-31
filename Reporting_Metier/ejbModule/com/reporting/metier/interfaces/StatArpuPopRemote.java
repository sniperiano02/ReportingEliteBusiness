package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatArpuPopRemote {

	

	
	public List<Object[]> getStatArpuPop(String x,String list_y,String groupby,List<String> Where);
	public List<Integer> getAllYears();
}
