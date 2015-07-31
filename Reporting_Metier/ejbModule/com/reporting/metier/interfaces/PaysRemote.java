package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Pay;


@Remote
public interface PaysRemote {

	public List<Pay> getAllPays();
	
	public void addPays(Pay p);
	public void DeletePays(Pay p);
	public void UpdatePays(Pay p);
	
}
