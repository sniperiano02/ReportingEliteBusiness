package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.DetailsDestination;


@Remote
public interface DestinationRemote  {

	
	public List<Destination> getAllDestination();
	
	public void addDestination(Destination dest);
	public void deleteDestination(Destination dest);
	
	public void updateDestination(Destination dest);
	
	public void deleteDetail(DetailsDestination det,Destination dest);
	public void addDetail(DetailsDestination det,Destination dest);
	public Destination getDestinationByName(String name);
	}
