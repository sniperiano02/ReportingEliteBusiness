package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.PackageRoaming;
import com.reporting.metier.entities.RoaminginParameter;
import com.reporting.metier.interfaces.PackageRoamingRemote;


@Stateless
public class PackageRoamingService implements PackageRoamingRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<PackageRoaming> getAllPackage() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From PackageRoaming p").getResultList();
	}

	@Override
	public void addPackageRomaing(PackageRoaming pr) {
		// TODO Auto-generated method stub
		em.persist(pr);
	}

	@Override
	public void deletePackageRomaing(PackageRoaming or) {
		// TODO Auto-generated method stub
		em.remove(em.contains(or) ? or : em.merge(or));
	}

	@Override
	public void updatePackageRomaing(PackageRoaming pr) {
		// TODO Auto-generated method stub
		em.merge(pr);
	}

	@Override
	public void priseEncomptePackageRomaing(RoaminginParameter rp) {
		// TODO Auto-generated method stub
		em.persist(rp);
	}
	
	
	
	
}
