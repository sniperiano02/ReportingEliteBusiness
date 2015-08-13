package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.RoamingOperator;


@Remote
public interface OperateurRoamingRemote {

	
	public List<RoamingOperator> getAllOperateurs();
	
	public void addOperator(RoamingOperator or);
	public void deleteOperator(RoamingOperator or);
	public void updateOperator(RoamingOperator or);
}
