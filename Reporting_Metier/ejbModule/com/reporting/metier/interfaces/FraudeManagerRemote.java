package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.CategoriesFraude;
import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FluxCdr;
import com.reporting.metier.entities.ParametresFraude;


@Remote
public interface FraudeManagerRemote {

	
	public List<CategoriesFraude> getAllCategories();
	public List<FluxCdr> getAllFlux();

	public List<ParametresFraude> getParametresFraude(String Where);
	public List<FiltresFraude> getFilterFiltresFraude(String where);
}
