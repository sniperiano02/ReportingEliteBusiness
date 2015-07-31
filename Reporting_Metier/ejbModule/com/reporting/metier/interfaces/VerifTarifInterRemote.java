package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface VerifTarifInterRemote {
	
	
	public List<Object[]> getStatVerifTarifInter(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatVerifTarifInter(String Where);
	public List<Integer> getAllYears();
}
