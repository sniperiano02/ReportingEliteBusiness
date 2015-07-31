package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatErreurTarifIn;


@Remote
public interface ErreurTarifINRemote {
	
	
	
	public List<Object[]> getStatErreurTarif(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatErreurTarif(String Where);
	public List<Integer> getAllYears();
}
