package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.PlanTarifaire;

@Remote
public interface PlanTarifaireRemote {
	
	
	public void createPlanT(PlanTarifaire p);
	public void deletePlanT(PlanTarifaire p);
	public void updatePlanT(PlanTarifaire p);
	
	public PlanTarifaire getPlanByName(String name);
	public List<PlanTarifaire> getAllPlanTarifaires();

}
