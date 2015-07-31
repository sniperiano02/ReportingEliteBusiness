package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatArpuRemote {

	
	public List<Object[]> getStatArpu(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatArpu(String Where);
	public List<Integer> getAllYears();
}
