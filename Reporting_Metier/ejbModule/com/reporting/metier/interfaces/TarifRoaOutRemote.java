package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Tarif;
import com.reporting.metier.entities.TarifsRoaOut;


@Remote
public interface TarifRoaOutRemote {

	

	public List<TarifsRoaOut>  getAllTarifsRoaOut();
	
	public void addTarifRoaOut(TarifsRoaOut u);
	
	public void deleteTarifRoaOut(TarifsRoaOut u);
	
	public void updateTarifRoaOut(TarifsRoaOut u);
}
