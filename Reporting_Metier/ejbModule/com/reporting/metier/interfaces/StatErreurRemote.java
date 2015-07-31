package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatErreur;

@Remote
public interface StatErreurRemote {
	
	public List<StatErreur> getStatsErreur(String where);

}
