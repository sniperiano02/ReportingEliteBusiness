package com.reporting.metier.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiTabsGroup;
import com.reporting.metier.interfaces.GroupRemote;

@Stateless
public class GroupService implements GroupRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<GuiGroup> getAllGroup() {
		// TODO Auto-generated method stub
		return em.createQuery("Select g from GuiGroup g").getResultList();
		
	}

	@Override
	public List<GuiTabsGroup> getAllTab() {
		// TODO Auto-generated method stub
		return em.createQuery("Select g from GuiTabsGroup g").getResultList();
	}

	@Override
	public void addGroup(GuiGroup g) {
	
		em.persist(g);
		
	}
	
	@Override
	public GuiTabsGroup getTabByName(String name) {
		GuiTabsGroup found = null;
		Query query = em.createQuery("select g from GuiTabsGroup g where g.tabName=:x");
		query.setParameter("x", name);
		try{
			found = (GuiTabsGroup) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no Group with name="+name);
		}
		return found;
	}

	@Override
	public void deleteGroup(GuiGroup g) {
		em.remove(em.contains(g) ? g : em.merge(g));
		
	}

	@Override
	public void updateGroup(GuiGroup g) {
		em.merge(g);
		
	}
}
