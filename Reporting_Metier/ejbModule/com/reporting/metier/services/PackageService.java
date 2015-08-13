package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.PackageJour;
import com.reporting.metier.entities.PackageZone;
import com.reporting.metier.interfaces.PackageJourRemote;
import com.reporting.metier.interfaces.PackageZoneRemote;

@Stateless
public class PackageService implements PackageZoneRemote,PackageJourRemote {

	@PersistenceContext
	EntityManager em;
	@Override
	public List<PackageJour> getAllPackagesJour() {
		// TODO Auto-generated method stub
		return em.createQuery("Select pj From PackageJour pj").getResultList();
	}

	@Override
	public void addPackage(PackageJour pj) {
		// TODO Auto-generated method stub
		em.persist(pj);
	}

	@Override
	public void DeletePackage(PackageJour pj) {
		// TODO Auto-generated method stub
		em.remove(em.contains(pj) ? pj : em.merge(pj));
	}

	@Override
	public void updatePackage(PackageJour pj) {
		// TODO Auto-generated method stub
		em.merge(pj);
	}

	@Override
	public List<PackageZone> getAllPackageZone() {
		// TODO Auto-generated method stub
		return em.createQuery("Select pj From PackageZone pj").getResultList();
	}

	@Override
	public void addPackageZone(PackageZone pz) {
		// TODO Auto-generated method stub
		em.persist(pz);
	}

	@Override
	public void DeletePackageZone(PackageZone pz) {
		// TODO Auto-generated method stub
		em.remove(em.contains(pz) ? pz : em.merge(pz));
	}

	@Override
	public void updatePackageZone(PackageZone pz) {
		// TODO Auto-generated method stub
		em.merge(pz);
		em.flush();
	}

	@Override
	public List<PackageZone> getAllPackageZoneRoa() {
		// TODO Auto-generated method stub
		return em.createQuery("Select pj From PackageZone pj where pj.codePackage='pack_ROA_OUT'").getResultList();
	}

}
