package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.StatLcr;


@Remote
public interface StatLcrRemote {

	
	public List<StatLcr> getAllStatLcr();
}
