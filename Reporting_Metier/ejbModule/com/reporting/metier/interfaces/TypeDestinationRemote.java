package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.TypeDestination;

@Remote
public interface TypeDestinationRemote {
	
	public List<TypeDestination> getAllTypeDest();
	public List<String> getAllStringTypeDest();
	
	public void addTypeDest(TypeDestination td);
	public void deleteTypeDest(TypeDestination td);
	public void updateTypeDest(TypeDestination td);

}
