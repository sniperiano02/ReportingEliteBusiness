package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatParcRemote {

	
	public List<Object[]> getEvolutionParc(String x,String list_y,String groupby,List<String> Where);
}
