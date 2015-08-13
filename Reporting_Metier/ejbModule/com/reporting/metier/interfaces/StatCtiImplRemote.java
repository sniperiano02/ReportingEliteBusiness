package com.reporting.metier.interfaces;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;










import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.Result;
import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.entities.TypeDestination;

@Remote
public interface StatCtiImplRemote {

	
	
	
	public List<Object[]> getConfiguartionChartByCfg(String x,String y,String table,String action,String groupby);
	public List<Object[]> getConfiguartionChartByCnfg(String x,String y,String table,String action,String groupby);
	
	public Map<Object, Number> getConfiguartionChartGeneric(String x,String y,String table,String action,String groupby);

	public String getStatCti();
	public Date getCtiDate();
	public List<Object[]> getCtiBytranche(String x,String y,String table,String action,String groupby,List<String> Where);
	public List<Object[]> getCtiByFilters(String x,String y,String table,String action,String groupby,List<String> Where);
	
public List<Integer> getAllYears();
public List<String> getAlltrancheHoraire();

public List<Object> getFilterDitinct(String x,String table);

public List<Object[]> getFilter(String x ,String table);
public List<Object[]> getTest1();



public List<Object[]>   getAllStatCtiStatic(String x,String list_y,String action,String groupby,List<String> Where);


public List<Object[]>   getAllQualite(String x,String list_y,String action,String groupby,List<String> Where);
public List<Object[]> getAllStatQualite(String x,List<String> Where);

public List<Object[]>   getAllStatCti(String x,List<String> list_y,String table,String action,String groupby,List<String> Where);
public List<Object[]>   getAllStatCti1(String x,List<AxeY> list_y,String table,String action,String groupby,List<String> Where);

public List<Object[]> getTest() ;
	public List<Object[]> getCtiByFilters1(String x, String y,String table,String action, String groupby, List<String> Where);
	
	
	
	

	
}
