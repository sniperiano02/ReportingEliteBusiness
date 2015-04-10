package com.reporting.metier.services;

import java.util.List;

import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.interfaces.PlanTarifaireRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		em.remove(p);
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

}
