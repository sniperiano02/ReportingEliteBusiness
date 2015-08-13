package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface VerifBilingNatRemote {
	
	
	public List<Object[]> getStatVerifBilingNat(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatVerifBilingNat(String Where);
	public List<Integer> getAllYears();
}
