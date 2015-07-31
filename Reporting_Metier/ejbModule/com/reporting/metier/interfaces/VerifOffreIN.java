package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface VerifOffreIN {
	
	public List<Object[]> getStatVerifOffre(String x ,String y,List<String> where,String group);
	public List<Integer> getAllYears();
}
