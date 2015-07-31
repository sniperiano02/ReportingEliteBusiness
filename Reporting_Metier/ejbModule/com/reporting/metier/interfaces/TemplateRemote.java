package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Template;
import com.reporting.metier.entities.TemplateDateIndique;
import com.reporting.metier.entities.TemplateFeuille;
import com.reporting.metier.entities.TemplateSeparateurCode;
import com.reporting.metier.entities.TemplateSeparateurDest;
import com.reporting.metier.entities.TemplateSeparateurPlage;

@Remote
public interface TemplateRemote {
	
	public void AddTempate(Template t);
	
	public List<Template> getAllTemplates();
	public Template getTemplate(int id);
	public void updateTemplate(Template T);
	public void deleteTemplate(Template T);
	
	public void addSeparateurDest(TemplateSeparateurDest t);
	public void addSeparateurPlage(TemplateSeparateurPlage t);
	public void addSeparateurCode(TemplateSeparateurCode t);
	
	public void addFeuille(TemplateFeuille t);
	public void addDateIndique(TemplateDateIndique t);
	public TemplateDateIndique getDateIndiqueByTemplateId(Integer id);
	
	

}
