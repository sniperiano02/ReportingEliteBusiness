package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.PackageZone;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.interfaces.ZoneRemote;

@Stateless
public class ZoneService implements ZoneRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Zone> getAllZones() {
		// TODO Auto-generated method stub
		return em.createQuery("Select z From Zone z").getResultList();
	}

	@Override
	public void addZone(Zone z) {
		em.persist(z);
		
		
	}

	@Override
	public void updateZone(Zone z) {
		// TODO Auto-generated method stub
		em.merge(z);
	}

	@Override
	public void deleteZone(Zone z) {
		// TODO Auto-generated method stub
		
		em.remove(em.contains(z) ? z : em.merge(z));
	}

	@Override
	public List<Zone> getZonesByPackage(PackageZone pack) {
		// TODO Auto-generated method stub
		return em.createQuery("Select z From Zone z where packagezone.id="+pack.getId()).getResultList();
	}

	@Override
	public List<Zone> getZonesByIdPackage(Integer id_package) {
		// TODO Auto-generated method stub
		return null;
	}
}
