package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface VerifBilingRemote {
	
	
	public List<Object[]> getStatVerifBiling(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatVerifBiling(String Where);
	public List<Integer> getAllYears();
}
