package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ParcActiveRemote {
	
	public List<Object[]> getStatParcActive(String date,List<String> where);

}
