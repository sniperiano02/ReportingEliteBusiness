package com.reporting.metier.interfaces;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;






import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.entities.TypeDestination;

@Remote
public interface StatMscImplRemote {

	
	
	
	public Map<Object, Number> getConfiguartionChartByCfg(String x,String y,String action,String groupby);
	public Map<String, Number> getConfiguartionChartByCnfg(String x,String y,String action,String groupby);
	public String getStatMsc();
	public Date getMSCDate();
	public Map<Object,Number> getMscBytranche(String x,String y,String action,String groupby,List<String> Where);
	public Map<Object,Number> getMscByFilters(String x,String y,String action,String groupby,List<String> Where);
public List<Integer> getAllYears();
public List<String> getTypeCall();
public List<String> getTypeSubscriber();
public List<String> getAlltrancheHoraire();
public List<Object[]> getTest1();

public List<Object[]>   getAllStatMsc(String x,List<String> list_y,String action,String groupby,List<String> Where);
	public List<Object[]> getTest() ;
	public Map<String, Number> getMscByFilters1(String x, String y,String action, String groupby, List<String> Where);
	
	
	
	

	
}
