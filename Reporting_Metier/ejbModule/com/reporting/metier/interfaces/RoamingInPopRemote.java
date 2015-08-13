package com.reporting.metier.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface RoamingInPopRemote {

	public Map<Object, Number> getConfiguartionChartByCfg(String x,String y,String action,String groupby);
	public Map<String, Number> getConfiguartionChartByCnfg(String x,String y,String action,String groupby);
	public Map<Object,Number> getMscBytranche(String x,String y,String action,String groupby,List<String> Where);
	public Map<Object,Number> getMscByFilters(String x,String y,String action,String groupby,List<String> Where);
public List<Integer> getAllYears();
public List<String> getTypeCall();
public List<String> getAlltrancheHoraire();
public List<Object[]>   getAllStatTrafficStatic(String x,String list_y,String action,String groupby,List<String> Where);


public List<Object[]>   getAllStatLocalIn(String x,List<String> list_y,String action,String groupby,List<String> Where);
	public Map<String, Number> getMscByFilters1(String x, String y,String action, String groupby, List<String> Where);
	
	
}
