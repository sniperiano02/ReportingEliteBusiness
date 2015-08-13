package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.ListAppelant;
import com.reporting.metier.entities.ListAppele;
import com.reporting.metier.entities.ListCellid;
import com.reporting.metier.entities.ListDetailsAppelant;
import com.reporting.metier.entities.ListDetailsAppele;
import com.reporting.metier.entities.ListDetailsCellid;
import com.reporting.metier.entities.ListDetailsImei;
import com.reporting.metier.entities.ListImei;
import com.reporting.metier.interfaces.FraudeHotlistRemote;


@Stateless
public class FraudeHolistService implements FraudeHotlistRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void addCellId(ListCellid cell) {
		// TODO Auto-generated method stub
		em.persist(cell);
	}

	@Override
	public void updateCellId(ListCellid cell) {
		// TODO Auto-generated method stub
		em.merge(cell);
	}

	@Override
	public void deleteCellId(ListCellid cell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAppelant(ListAppelant appelant) {
		// TODO Auto-generated method stub
		em.persist(appelant);
	}

	@Override
	public void updateAppelant(ListAppelant appelant) {
		// TODO Auto-generated method stub
		em.merge(appelant);
	}

	@Override
	public void deleteAppelant(ListAppelant appelant) {
		// TODO Auto-generated method stub
		em.remove(em.contains(appelant) ? appelant : em.merge(appelant));

	}

	@Override
	public void addAppele(ListAppele appele) {
		// TODO Auto-generated method stub
		em.persist(appele);
	}

	@Override
	public void updateAppele(ListAppele appele) {
		// TODO Auto-generated method stub
		em.merge(appele);
	}

	@Override
	public void deleteAppele(ListAppele appele) {
		// TODO Auto-generated method stub
		em.remove(em.contains(appele) ? appele : em.merge(appele));

	}

	@Override
	public void addImei(ListImei imei) {
		// TODO Auto-generated method stub
		em.persist(imei);
	}

	@Override
	public void updateImei(ListImei imei) {
		// TODO Auto-generated method stub
		em.merge(imei);
	}

	@Override
	public void deleteImei(ListImei imei) {
		// TODO Auto-generated method stub
		em.remove(em.contains(imei) ? imei : em.merge(imei));

	}

	@Override
	public List<ListCellid> getAllCellId() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListCellid p").getResultList();
	}

	@Override
	public List<ListAppelant> getAllAppelant() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListAppelant p").getResultList();
	}

	@Override
	public List<ListAppele> getAllAppele() {
		// TODO Auto-generated method stub
	//List<Object[]> lst = em.createNativeQuery("Select * from cdrs_archives.cdrs_in_act_140701 ").getResultList();
		return em.createQuery("Select p From ListAppele p").getResultList();
	}

	@Override
	public List<ListImei> getAllImei() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListImei p").getResultList();
	}

	@Override
	public void addDetailAppelant(ListDetailsAppelant lda) {
		// TODO Auto-generated method stub
		em.persist(lda);
	}

	@Override
	public void addDetailAppele(ListDetailsAppele lda) {
		// TODO Auto-generated method stub
	em.persist(lda);
	}

	@Override
	public List<ListDetailsAppele> getListDetailsAppeles(ListAppele lp) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListDetailsAppele p  where p.appele.id="+lp.getId()).getResultList();
	}

	@Override
	public void updateDetailAppele(ListDetailsAppele lda) {
		// TODO Auto-generated method stub
		em.merge(lda);
	}

	@Override
	public void deleteDetailAppele(ListDetailsAppele lda) {
		// TODO Auto-generated method stub
		em.remove(em.contains(lda) ? lda : em.merge(lda));
		em.flush();
	}

	@Override
	public void addDetailCellId(ListDetailsCellid lda) {
		// TODO Auto-generated method stub
		em.persist(lda);
	}

	@Override
	public void updateDetailCellId(ListDetailsCellid lda) {
		// TODO Auto-generated method stub
		em.merge(lda);
	}

	@Override
	public void deleteDetailCellId(ListDetailsCellid lda) {
		// TODO Auto-generated method stub
		em.remove(em.contains(lda) ? lda : em.merge(lda));
		em.flush();

	}

	@Override
	public List<ListDetailsCellid> getListDetailsCellId(ListCellid lc) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListDetailsCellid p  where p.cellId.id="+lc.getId()).getResultList();
	}

	@Override
	public void updateDetailAppelant(ListDetailsAppelant lda) {
		// TODO Auto-generated method stub
		em.merge(lda);
	}

	@Override
	public void deleteDetailAppelant(ListDetailsAppelant lda) {
		// TODO Auto-generated method stub
		em.remove(em.contains(lda) ? lda : em.merge(lda));
		em.flush();
	}

	@Override
	public List<ListDetailsAppelant> getListDetailsAppelant(ListAppelant lp) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListDetailsAppelant p  where p.appelant.id="+lp.getId()).getResultList();
	}

	@Override
	public void addDetailImei(ListDetailsImei lda) {
		// TODO Auto-generated method stub
		em.persist(lda);
	}

	@Override
	public void updateDetailImei(ListDetailsImei lda) {
		// TODO Auto-generated method stub
		em.merge(lda);
	}

	@Override
	public void deleteDetailImei(ListDetailsImei lda) {
		// TODO Auto-generated method stub
		em.remove(em.contains(lda) ? lda : em.merge(lda));
		em.flush();

	}

	@Override
	public List<ListDetailsImei> getListDetailsImei(ListImei lc) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From ListDetailsImei p  where p.imei.id="+lc.getId()).getResultList();
	}

	
	
}
