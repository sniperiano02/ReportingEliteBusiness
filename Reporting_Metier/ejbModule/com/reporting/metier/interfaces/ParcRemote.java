package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ParcRemote {

	
	
	public List<Object[]> getStatParc();
	
}
