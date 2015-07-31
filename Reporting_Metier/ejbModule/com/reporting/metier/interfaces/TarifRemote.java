package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Tarif;


@Remote
public interface TarifRemote {

	

	public List<Tarif>  getAllTarifs();
	
	public void addTarif(Tarif u);
	
	public void deleteTarif(Tarif u);
	
	public void updateTarif(Tarif u);
}
