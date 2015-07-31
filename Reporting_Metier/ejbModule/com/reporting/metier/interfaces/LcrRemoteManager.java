package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.FichierLcr;
import com.reporting.metier.entities.FichierLcr2;
import com.reporting.metier.entities.FichierLcrProcess;



@Remote
public interface LcrRemoteManager {

	
	
	public void addFichierLcr(FichierLcr f_lcr);
	public void addFichierLcr2(FichierLcr2 f_lcr2);

	public void updateFichierLcrProcess(FichierLcrProcess fich);
	
	public void truncateTmp();
	
	public List<FichierLcrProcess> getAllLcrProcess();
	
	public void deleteOffre(FichierLcrProcess fich);
	public void LaunchProc(String fichier_name);
	
	public void addFichierLcrProcess(FichierLcrProcess f_lcr_process);
	
	
	
	public FichierLcrProcess findFichierByName(String Name);
}
