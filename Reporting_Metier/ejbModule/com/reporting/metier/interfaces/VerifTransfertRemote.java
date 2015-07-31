package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface VerifTransfertRemote {
	
	
	public List<Object[]> getStatVerifTransfert(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatVerifTransfert(String Where);
	public List<Integer> getAllYears();
}
