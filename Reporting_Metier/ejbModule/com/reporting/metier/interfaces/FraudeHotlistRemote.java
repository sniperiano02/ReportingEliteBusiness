package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.InterDest;
import com.reporting.metier.entities.ListAppelant;
import com.reporting.metier.entities.ListAppele;
import com.reporting.metier.entities.ListCellid;
import com.reporting.metier.entities.ListDetailsAppelant;
import com.reporting.metier.entities.ListDetailsAppele;
import com.reporting.metier.entities.ListDetailsCellid;
import com.reporting.metier.entities.ListDetailsImei;
import com.reporting.metier.entities.ListImei;


@Remote
public interface FraudeHotlistRemote {

	public List<ListCellid> getAllCellId();
	public List<ListAppelant> getAllAppelant();
	public List<ListAppele> getAllAppele();
	public List<ListImei> getAllImei();
	public void addCellId(ListCellid cell);
	public void updateCellId(ListCellid cell);
	public void deleteCellId(ListCellid cell);
	public void addDetailCellId(ListDetailsCellid lda);
	public void updateDetailCellId(ListDetailsCellid lda);
	public void deleteDetailCellId(ListDetailsCellid lda);
	public List<ListDetailsCellid> getListDetailsCellId(ListCellid lc);
	
	public void addAppelant(ListAppelant appelant);
	public void updateAppelant(ListAppelant appelant);
	public void deleteAppelant(ListAppelant appelant);
	public void addDetailAppelant(ListDetailsAppelant lda);
	public void updateDetailAppelant(ListDetailsAppelant lda);
	public void deleteDetailAppelant(ListDetailsAppelant lda);
	public List<ListDetailsAppelant> getListDetailsAppelant(ListAppelant lp);
	
	
	
	public void addAppele(ListAppele appele);
	public void updateAppele(ListAppele appele);
	public void deleteAppele(ListAppele appele);
	public void addDetailAppele(ListDetailsAppele lda);
	public void updateDetailAppele(ListDetailsAppele lda);
	public void deleteDetailAppele(ListDetailsAppele lda);
	public List<ListDetailsAppele> getListDetailsAppeles(ListAppele lp);
	
	public void addImei(ListImei imei);
	public void updateImei(ListImei imei);
	public void deleteImei(ListImei imei);
	public void addDetailImei(ListDetailsImei lda);
	public void updateDetailImei(ListDetailsImei lda);
	public void deleteDetailImei(ListDetailsImei lda);
	public List<ListDetailsImei> getListDetailsImei(ListImei lc);
	

	
}
