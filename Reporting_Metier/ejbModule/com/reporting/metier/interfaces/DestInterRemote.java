package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.InterDest;



@Remote
public interface DestInterRemote {

	public List<InterDest> getAllInterDests();
	
	public void addInterDest(InterDest i);
	public void DeleteInterDest(InterDest i);
	public void UpdateInterDest(InterDest i);
	
}
