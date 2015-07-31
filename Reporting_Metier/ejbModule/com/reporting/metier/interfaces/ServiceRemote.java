package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Service;


@Remote
public interface ServiceRemote {

	
	public List<Service> getAllService();
}
