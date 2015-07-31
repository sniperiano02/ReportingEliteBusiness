package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.TypePalier;


@Remote
public interface TypePalierRemote {

	
	public List<TypePalier> getAllTypePaliers();
}
