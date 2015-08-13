package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatVerifTarifRoaRemote {

	public List<Object[]> getStatVerifTarifRoa(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatVerifTarifRoa(String Where);
	public List<Integer> getAllYears();
	
}
