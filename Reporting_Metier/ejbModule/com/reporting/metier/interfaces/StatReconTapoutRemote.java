package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatReconTapoutRemote {
	
	
public List<Object[]> getAllStatReconTapout(String x,String y,List<String> where);
	
	public List<Object[]> getDetailsStatReconTapout(String x,String y,String where);
	
	
	public List<Object[]> getDetailsOperateurStatReconTapout(String x,String where);
	
	
}
