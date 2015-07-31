package com.reporting.metier.services;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import com.reporting.metier.entities.FichierLcr;
import com.reporting.metier.entities.FichierLcr2;
import com.reporting.metier.entities.FichierLcrProcess;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.entities.TarifsInterTmp;
import com.reporting.metier.interfaces.LcrRemoteManager;


@Stateless
public class LcrManager implements LcrRemoteManager {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public void addFichierLcr(FichierLcr f_lcr) {
		// TODO Auto-generated method stub
		em.persist(f_lcr);
	}

	@Override
	public void addFichierLcr2(FichierLcr2 f_lcr2) {
		// TODO Auto-generated method stub
		em.persist(f_lcr2);
	}

	@Override
	public void addFichierLcrProcess(FichierLcrProcess f_lcr_process) {
		// TODO Auto-generated method stub
		em.persist(f_lcr_process);
	}

	@Override
	public FichierLcrProcess findFichierByName(String Name) {
		// TODO Auto-generated method stub
		FichierLcrProcess found = null;
		//Query q = em.createQuery("Select f from FichierLcrProcess f where f.nomFichier='"+Name+"'");
		String jpql = "Select f from FichierLcrProcess f where f.nomFichier=:name";
		Query query = em.createQuery(jpql);
		query.setParameter("name", Name);
		try{
			found = (FichierLcrProcess) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(LcrManager.class.getName()).log(Level.WARNING, "No Fichier avec nom="+Name+"");
		}
		return found;
		
	}

	@Override
	public void LaunchProc(String fichier_name) {
		// TODO Auto-generated method stub
		String jpql = "Select tables.procedure_fichier_lcr('"+fichier_name+"');";
		Query query = em.createNativeQuery(jpql);
		
		try{
			 query.executeUpdate();
		}catch(Exception ex){
			Logger.getLogger(LcrManager.class.getName()).log(Level.WARNING, "working="+fichier_name+"");
		}
			}

	@Override
	public void deleteOffre(FichierLcrProcess fich) {
		// TODO Auto-generated method stub
		em.remove(em.contains(fich) ? fich : em.merge(fich));
		
	}

	@Override
	public List<FichierLcrProcess> getAllLcrProcess() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from FichierLcrProcess p").getResultList();
	}

	@Override
	public void updateFichierLcrProcess(FichierLcrProcess fich) {
		em.merge(fich);
		
	}

	@Override
	public void truncateTmp() throws org.hibernate.exception.GenericJDBCException {
		// TODO Auto-generated method stub
		try{
		em.createNativeQuery("insert into tables.tarifs_inter (destination,code_dest,tarifs,date_validite,qualite,operateur,nom_fichier,id_pays,group_destination)"
				+ " select destination,code_dest,tarifs,date_validite,qualite,operateur,nom_fichier,id_pays,group_destination from tables.tarifs_inter_tmp").executeUpdate();
		em.createNativeQuery("truncate Table tables.tarifs_inter_tmp").executeUpdate();
	
		
		
		}catch(Exception ex){
			Logger.getLogger(LcrManager.class.getName()).log(Level.WARNING, "No working=");
			System.out.println("Finish");
		}
		
	}

}
