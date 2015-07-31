package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface StatParcActivePopRemote {

	
	public List<Object[]> getStatParcActivePop(String date,List<String> where);
}
