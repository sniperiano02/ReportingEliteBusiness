package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatTaxeTransfert {

	

	public List<Object[]> getStatTaxePrp(String x,String list_y,String groupby,List<String> Where);
	public List<Object[]> getDetailsStatTaxe(String Where);
	public List<Integer> getAllYears();
}
