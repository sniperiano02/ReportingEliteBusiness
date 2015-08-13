package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Cell;
import com.reporting.metier.interfaces.CellRemote;


@Stateless
public class CellService implements CellRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Cell> getAllCell() {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select c from Cell c ").getResultList();
		
	}

	@Override
	public void addCell(Cell cell) {
		// TODO Auto-generated method stub
		em.persist(cell);
	}

	@Override
	public void updateCell(Cell cell) {
		// TODO Auto-generated method stub
		em.merge(cell);
	}

	@Override
	public void deleteCell(Cell cell) {
		// TODO Auto-generated method stub
		em.remove(cell);
	}
	
	
}
