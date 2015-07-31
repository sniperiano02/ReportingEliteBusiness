package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Monnaie;
import com.reporting.metier.entities.TauxChange;



@Remote
public interface TauxChangeRemote {

	public List<Monnaie> getAllMonnaie();
	public List<TauxChange> getAllTaux();
	public void addTaux(TauxChange t);
	public void updateTaux(TauxChange t);
	public void deleteTaux(TauxChange t);
}
