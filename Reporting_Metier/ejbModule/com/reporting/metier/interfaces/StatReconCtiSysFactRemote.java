package com.reporting.metier.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatReconMscIn;



@Remote
public interface StatReconCtiSysFactRemote {
	
	
	public List<Object[]> getAllStatRecon(String x, String y,
			String action, String groupby, List<String> Where);
	
	public Map<Object,Number> getMscBytranche(String x,String y,String action,String groupby,List<String> Where);
	
	
	public List<Object[]> getAllStatReconCtiSysFact(String x,String where);
	public List<Object[]> getAllStatReconCDRIN(String x,String y,List<String> where);
	
	public List<Object[]> getDetailsStatReconCtiSysFact(String x,String y,String where);
	
	public List<Object[]> getDetailsDestinationStatReconCtiSysFact(String x,String where);
	
	
	public List<Integer> getAllYears();

}
