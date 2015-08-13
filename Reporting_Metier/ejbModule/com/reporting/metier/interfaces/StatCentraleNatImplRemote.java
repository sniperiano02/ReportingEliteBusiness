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
public interface StatCentraleNatImplRemote {

	
	
	
	public List<Object[]> getConfiguartionChartByCfg(String x,String y,String table,String action,String groupby);
	public List<Object[]> getConfiguartionChartByCnfg(String x,String y,String table,String action,String groupby);
	
	public Map<Object, Number> getConfiguartionChartGeneric(String x,String y,String table,String action,String groupby);

	public String getStatCentral();
	public Date getCentralDate();
	public List<Object[]> getCentralBytranche(String x,String y,String table,String action,String groupby,List<String> Where);
	public List<Object[]> getCentralByFilters(String x,String y,String table,String action,String groupby,List<String> Where);
	
public List<Integer> getAllYears();
public List<String> getAlltrancheHoraire();

public List<Object> getFilterDitinct(String x,String table);

public List<Object[]> getFilter(String x ,String table);
public List<Object[]> getTest1();



public List<Object[]>   getAllStatCentralStatic(String x,String list_y,String action,String groupby,List<String> Where);



public List<Object[]>   getAllStatCentral(String x,List<String> list_y,String table,String action,String groupby,List<String> Where);
public List<Object[]>   getAllStatCentral1(String x,List<AxeY> list_y,String table,String action,String groupby,List<String> Where);

public List<Object[]> getTest() ;
	public List<Object[]> getCtiByFilters1(String x, String y,String table,String action, String groupby, List<String> Where);
	
	
	
	

	
}
