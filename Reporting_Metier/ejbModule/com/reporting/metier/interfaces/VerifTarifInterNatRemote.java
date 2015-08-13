package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface VerifTarifInterNatRemote {
	
	
	public List<Object[]> getStatVerifTarifInterNat(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatVerifTarifInterNat(String Where);
	public List<Integer> getAllYears();
}
