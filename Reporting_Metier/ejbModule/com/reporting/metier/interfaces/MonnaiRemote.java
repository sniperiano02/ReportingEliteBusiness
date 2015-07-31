package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Monnaie;



@Remote
public interface MonnaiRemote {

	public List<Monnaie> getAllMonnaies();
	
	
	
}
