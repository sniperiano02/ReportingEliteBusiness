package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Report;
import com.reporting.metier.entities.Template;
import com.reporting.metier.entities.TemplateDateIndique;
import com.reporting.metier.entities.TemplateFeuille;
import com.reporting.metier.entities.TemplateSeparateurCode;
import com.reporting.metier.entities.TemplateSeparateurDest;
import com.reporting.metier.entities.TemplateSeparateurPlage;
import com.reporting.metier.interfaces.TemplateRemote;

@Stateless
public class TemplateService implements TemplateRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void AddTempate(Template t) {
		
		em.persist(t);
		
	}

	@Override
	public List<Template> getAllTemplates() {
		// TODO Auto-generated method stub
		return em.createQuery("Select t from Template t").getResultList();
	}

	@Override
	public void updateTemplate(Template T) {
		// TODO Auto-generated method stub
		em.merge(T);
		
	}

	@Override
	public void deleteTemplate(Template T) {
		// TODO Auto-generated method stub
		em.remove(T);
	}

	@Override
	public Template getTemplate(int id) {
		// TODO Auto-generated method stub
		return em.find(Template.class, id);
	}

	@Override
	public void addSeparateurDest(TemplateSeparateurDest t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public void addSeparateurPlage(TemplateSeparateurPlage t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public void addSeparateurCode(TemplateSeparateurCode t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public void addFeuille(TemplateFeuille t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public void addDateIndique(TemplateDateIndique t) {
		// TODO Auto-generated method stub
		em.persist(t);
		
	}

	@Override
	public TemplateDateIndique getDateIndiqueByTemplateId(Integer id) {
		// TODO Auto-generated method stub
		return (TemplateDateIndique) em.createQuery("Select t from TemplateDateIndique t where t.nomTemplate").getSingleResult();
	}

}
