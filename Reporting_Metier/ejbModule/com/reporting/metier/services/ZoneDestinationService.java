package com.reporting.metier.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.entities.ZonesDestination;
import com.reporting.metier.interfaces.ZoneDestinationRemote;


@Stateless
public class ZoneDestinationService implements ZoneDestinationRemote {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public List<ZonesDestination> getAllZonesDestinationByZone(Zone z) {
		// TODO Auto-generated method stub
		return em.createQuery("Select z From ZonesDestination z where z.zone.id="+z.getId()).getResultList();
	}

	@Override
	public void addZonesDestination(ZonesDestination dest) {
		// TODO Auto-generated method stub
		em.persist(dest);
	}

	@Override
	public void deleteZonesDestination(ZonesDestination dest) {
		// TODO Auto-generated method stub
		em.remove(em.contains(dest) ? dest : em.merge(dest));
	}

	@Override
	public void updateZonesDestination(ZonesDestination dest) {
		em.merge(dest);
	
	}

	@Override
	public List<Destination> getDestinationByDate(String dt, Integer id_zone) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT distinct d  FROM Destination d  WHERE d.id not in ( select zd.destination.id from ZonesDestination zd where zd.dateDebutValidite = to_date('"+dt+"','DD/MM/YYYY') and zd.zone.id in ( SELECT z.id from Zone z where z.packagezone.id = (SELECT max(z.packagezone.id) from Zone z WHERE z.id ="+id_zone+")))").getResultList();
	}

}
