package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.TarifsInter;
import com.reporting.metier.entities.TarifsInterTmp;

@Remote
public interface TarifinterLcrRemote {

	
	public List<TarifsInterTmp> getTarifTempByFichier(String fichier_name);

	
	public List<TarifsInter> getTarifsInterByFichier(String fichier_name);

}
