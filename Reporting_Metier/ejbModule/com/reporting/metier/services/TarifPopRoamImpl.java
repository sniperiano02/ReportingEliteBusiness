package com.reporting.metier.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



import com.reporting.metier.entities.TarifsRoaPop;
import com.reporting.metier.interfaces.TarifPopRoamRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class PlanTarifaireImpl
 */
@Stateless
public class TarifPopRoamImpl implements TarifPopRoamRemote {

    /**
     * Default constructor. 
     */
    public TarifPopRoamImpl() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
    private EntityManager em;

	@Override
	public void createTarifPop(TarifsRoaPop p) {
		em.persist(p);
		
	}

	@Override
	public void deleteTarifPop(TarifsRoaPop p) {
		em.remove(em.contains(p) ? p : em.merge(p));
	}

	@Override
	public void updateTarifPop(TarifsRoaPop p) {
		em.merge(p);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TarifsRoaPop> getAllTarifPop() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from TarifsRoaPop p").getResultList();
	}

	@Override
	public TarifsRoaPop getPlanByName(String name) {
		TarifsRoaPop found = null;
		Query query = em.createQuery("select p from TarifRoaPop c where p.dateValidite=:x");
		query.setParameter("x", name);
		try{
			found = (TarifsRoaPop) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no plan with name="+name);
		}
		return found;
	}

}
