package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.TarifsRoaPop;

@Remote
public interface TarifPopRoamRemote {
	
	
	public void createTarifPop(TarifsRoaPop p);
	public void deleteTarifPop(TarifsRoaPop p);
	public void updateTarifPop(TarifsRoaPop p);
	
	public TarifsRoaPop getPlanByName(String name);
	public List<TarifsRoaPop> getAllTarifPop();

}
