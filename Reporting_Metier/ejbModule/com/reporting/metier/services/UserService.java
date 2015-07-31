package com.reporting.metier.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.interfaces.userRemote;

@Stateless
public class UserService implements userRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<GuiUser> getAllUsers() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s from GuiUser s").getResultList();
	}

	@Override
	public void addUser(GuiUser u) {
		em.persist(u);
		
	}

	@Override
	public void deleteUser(GuiUser u) {
		em.remove(em.contains(u) ? u : em.merge(u));
		
	}

	@Override
	public void updateUser(GuiUser u) {
		em.merge(u);
		
	}
	@Override
	public GuiGroup getGroupByName(String name) {
		GuiGroup found = null;
		Query query = em.createQuery("select g from GuiGroup g where g.gName=:x");
		query.setParameter("x", name);
		try{
			found = (GuiGroup) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no Group with name="+name);
		}
		return found;
	}

	@Override
	public List<GuiGroup> getAllGroup() {
		// TODO Auto-generated method stub
		return em.createQuery("Select g from GuiGroup g").getResultList();
	}

	@Override
	public GuiUser authenticate(String login ,String password) {
		GuiUser found = null;
		String jpql = "select u from GuiUser u where u.uLogin=:login and u.uPwd=:password";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try{
			found = (GuiUser) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(UserService.class.getName()).log(Level.WARNING, "authentication attempt failure with login="+login+" and password="+password);
		}
		return found;
	}
	

}
