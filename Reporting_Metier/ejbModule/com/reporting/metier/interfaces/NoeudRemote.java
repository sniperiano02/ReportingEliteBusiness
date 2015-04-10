package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Noeud;

@Remote
public interface NoeudRemote {
	
	public List<Noeud> getNoeudNoms();
	public List<String> getNoeudCodes();
}
