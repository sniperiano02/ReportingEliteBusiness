package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.OperateurInterco;


@Remote
public interface OperateurIntercoRemote {
	
	public List<OperateurInterco> getAllOperateurs(String where);
	
	public void addOperateur(OperateurInterco op);
	public void UpdateOperateur(OperateurInterco op);
	public void DeleteOperateur(OperateurInterco op);

}
