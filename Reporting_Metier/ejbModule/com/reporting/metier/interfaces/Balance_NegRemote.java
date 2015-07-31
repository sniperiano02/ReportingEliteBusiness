package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface Balance_NegRemote {
	public List<Integer> getAllYears() ;
	
	public List<Object[]>   getAllStatBalance(String x,String list_y,String action,String groupby,List<String> Where);


	
	

}
