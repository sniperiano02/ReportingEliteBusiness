package com.reporting.metier.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.interfaces.PlanTarifaireRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class PlanTarifaireImpl
 */
@Stateless
public class PlanTarifaireImpl implements PlanTarifaireRemote {

    /**
     * Default constructor. 
     */
    public PlanTarifaireImpl() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
    private EntityManager em;

	@Override
	public void createPlanT(PlanTarifaire p) {
		em.persist(p);
		
	}

	@Override
	public void deletePlanT(PlanTarifaire p) {
		em.remove(em.contains(p) ? p : em.merge(p));
	}

	@Override
	public void updatePlanT(PlanTarifaire p) {
		em.merge(p);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PlanTarifaire> getAllPlanTarifaires() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from PlanTarifaire p").getResultList();
	}

	@Override
	public PlanTarifaire getPlanByName(String name) {
		PlanTarifaire found = null;
		Query query = em.createQuery("select p from PlanTarifaire c where p.planTarifaire=:x");
		query.setParameter("x", name);
		try{
			found = (PlanTarifaire) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no plan with name="+name);
		}
		return found;
	}

}
