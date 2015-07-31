package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatActivation;

@Remote
public interface StatActivationRemote {

	public List<Integer> getAllYears() ;
	public List<Object[]> getAllStatActivation(String x,String y,String action,String groupby,List<String> Where);
}
