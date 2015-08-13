package com.reporting.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.OperateurInterco;
import com.reporting.metier.entities.Template;
import com.reporting.metier.entities.TemplateDateIndique;
import com.reporting.metier.entities.TemplateFeuille;
import com.reporting.metier.entities.TemplateSeparateurCode;
import com.reporting.metier.entities.TemplateSeparateurDest;
import com.reporting.metier.entities.TemplateSeparateurPlage;
import com.reporting.metier.interfaces.OperateurIntercoRemote;
import com.reporting.metier.interfaces.TemplateRemote;

@ManagedBean(name="lcr")
@ViewScoped
public class LcrMbean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private OperateurIntercoRemote operateurService;
	
	@EJB
	private TemplateRemote template_service;
	
	private TemplateDateIndique dateindique = new TemplateDateIndique();
	
	private TemplateSeparateurDest separateurDest = new TemplateSeparateurDest();
	
	
	private TemplateSeparateurCode separateurCode = new TemplateSeparateurCode();
	
	
	public TemplateSeparateurCode getSeparateurCode() {
		return separateurCode;
	}
	public void setSeparateurCode(TemplateSeparateurCode separateurCode) {
		this.separateurCode = separateurCode;
	}
	
	private TemplateSeparateurPlage separateurPlage = new TemplateSeparateurPlage();
	
	
	public TemplateSeparateurPlage getSeparateurPlage() {
		return separateurPlage;
	}
	public void setSeparateurPlage(TemplateSeparateurPlage separateurPlage) {
		this.separateurPlage = separateurPlage;
	}
	private TemplateFeuille template_feuille = new  TemplateFeuille();
	private TemplateFeuille template_feuille1 = new  TemplateFeuille();
	
	
	public TemplateFeuille getTemplate_feuille1() {
		return template_feuille1;
	}
	public void setTemplate_feuille1(TemplateFeuille template_feuille1) {
		this.template_feuille1 = template_feuille1;
	}
	
	public TemplateFeuille getTemplate_feuille() {
		return template_feuille;
	}
	public void setTemplate_feuille(TemplateFeuille template_feuille) {
		this.template_feuille = template_feuille;
	}
	public TemplateSeparateurDest getSeparateurDest() {
		return separateurDest;
	}
	public void setSeparateurDest(TemplateSeparateurDest separateurDest) {
		this.separateurDest = separateurDest;
	}
	
	public TemplateDateIndique getDateindique() {
		return dateindique;
	}
	public void setDateindique(TemplateDateIndique dateindique) {
		this.dateindique = dateindique;
	}
	  
	
	private OperateurInterco operateur;
	private List<OperateurInterco> lst_operateurs = new ArrayList<>();
	
	private Template template;
	public Template getTemplate() {
		return template;
	}
	
	public void setTemplate(Template template) {
		this.template = template;
	}
	
	@PostConstruct
	public void init(){
		
		lst_operateurs=operateurService.getAllOperateurs("I");
		operateur= new OperateurInterco();
		template= new Template();
		
	}
	
	
	public void saveTemplate(){
		List<TemplateFeuille> lst = new ArrayList<>();
		
		System.out.println(template.getNbFeuille());
		if(template.getNbFeuille()==2){
			template_feuille.setNomTemplate(template.getNomTemplate());
			template_feuille1.setNomTemplate(template.getNomTemplate());
		
			lst.add(template_feuille);
			lst.add(template_feuille1);
		}else if(template.getNbFeuille()==1){
			template_feuille.setNomTemplate(template.getNomTemplate());
			template_feuille.setTemplate(template);
			lst.add(template_feuille);
		}
		
		System.out.println("Begin");
		
template.setLsttemplateFeuille(lst);

		template_service.AddTempate(template);
		
		System.out.println("End");
		if(template.getDateIndique().equals("Oui")){
			dateindique.setNomTemplate(template.getNomTemplate());
			template_service.addDateIndique(dateindique);
			
		}
		
		if(template.getCodePays().equals("Oui")){
			separateurCode.setNomTemplate(template.getNomTemplate());
			template_service.addSeparateurCode(separateurCode);
		}
		
		if(template.getDestination().equals("Non")){
			separateurDest.setNomTemplate(template.getNomTemplate());
			template_service.addSeparateurDest(separateurDest);
		}
		if(template.getSeparateurPlage().equals("Oui")){
			separateurPlage.setNomTemplate(template.getNomTemplate());
			template_service.addSeparateurPlage(separateurPlage);
		}
		
	}
	
	
	
	
	





	public OperateurInterco getOperateur() {
		return operateur;
	}







	public void setOperateur(OperateurInterco operateur) {
		this.operateur = operateur;
	}







	public List<OperateurInterco> getLst_operateurs() {
		return lst_operateurs;
	}







	public void setLst_operateurs(List<OperateurInterco> lst_operateurs) {
		this.lst_operateurs = lst_operateurs;
	}












}
