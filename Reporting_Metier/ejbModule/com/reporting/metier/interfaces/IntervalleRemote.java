package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.IntervalHeure;


@Remote
public interface IntervalleRemote {

	
	public List<IntervalHeure> getAllIntervalHeures();
	
	public void addIntervalle(IntervalHeure ih);
	public void DeleteIntervalle(IntervalHeure ih);
	public void UpdateIntervalle(IntervalHeure ih);
}
