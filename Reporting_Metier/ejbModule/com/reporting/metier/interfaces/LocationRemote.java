package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Cell;
import com.reporting.metier.entities.Location;
import com.reporting.metier.entities.ParcGptoAct;

@Remote
public interface LocationRemote {

	
	
	public List<Location> getFraudeurLocations(String fraudeur);
	
	
	
}
