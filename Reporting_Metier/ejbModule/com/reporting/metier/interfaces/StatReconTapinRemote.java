package com.reporting.metier.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatReconMscIn;



@Remote
public interface StatReconTapinRemote {
	
	
	public List<Object[]> getAllStatReconTapin(String x, String y,
			String action, String groupby, List<String> Where);
	

	
	
	public List<Object[]> getAllStatReconTapin(String x,String where);
	public List<Object[]> getAllStatReconCDRIN(String x,String y,List<String> where);
	
	public List<Object[]> getDetailsStatReconTapin(String x,String y,String where);
	
	public List<Object[]> getDetailsDestinationStatReconTapin(String x,String where);
	
	
	public List<Integer> getAllYears();

}
