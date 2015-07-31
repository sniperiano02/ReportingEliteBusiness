package com.reporting.metier.interfaces;

import java.util.List;

import com.reporting.metier.entities.Proccontrole;
import com.reporting.metier.entities.Stproc;

public interface StProcRemote {

	public List<Stproc> getAllStProc();
	
	public void arretProc(Stproc proc);
	public void activerProc(Stproc proc);
	public void changerCycle(Stproc proc);
	
	public List<Proccontrole> getDetailsByName(String name);
	
}
