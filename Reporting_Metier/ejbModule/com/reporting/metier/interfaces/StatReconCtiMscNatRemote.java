package com.reporting.metier.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatReconMscIn;



@Remote
public interface StatReconCtiMscNatRemote {
	
	
	public List<Object[]> getAllStatRecon(String x, String y,
			String action, String groupby, List<String> Where);
	

	
	
	public List<Object[]> getAllStatReconCtiMscNat(String x,String where);
	public List<Object[]> getAllStatReconCDRIN(String x,String y,List<String> where);
	
	public List<Object[]> getDetailsStatReconCtiMscNat(String x,String y,String where);
	
	public List<Object[]> getDetailsDestinationStatReconCtiMscNat(String x,String where);
	
	
	public List<Integer> getAllYears();

}
