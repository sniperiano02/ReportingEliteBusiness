package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Trunck;


@Remote
public interface TrunckRemote {
	
	public List<Trunck> getAllTruncks();
	
	public void addTrunck(Trunck t);
	public void DeleteTrunck(Trunck t);
	public void UpdateTrunck(Trunck t);

	

}
