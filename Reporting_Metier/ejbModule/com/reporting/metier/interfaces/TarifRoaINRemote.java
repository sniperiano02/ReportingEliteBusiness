package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Tarif;
import com.reporting.metier.entities.TarifsRoaIn;
import com.reporting.metier.entities.TarifsRoaOut;


@Remote
public interface TarifRoaINRemote {

	

	public List<TarifsRoaIn>  getAllTarifsRoaOut();
	
	public void addTarifRoaOut(TarifsRoaIn u);
	
	public void deleteTarifRoaOut(TarifsRoaIn u);
	
	public void updateTarifRoaOut(TarifsRoaIn u);
}
