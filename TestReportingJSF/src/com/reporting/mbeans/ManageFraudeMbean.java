package com.reporting.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FiltresReglesFraude;
import com.reporting.metier.entities.ParametresFraude;
import com.reporting.metier.entities.ParametresReglesFraude;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.ReglesFraude;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.FraudeManagerRemote;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
import com.reporting.metier.interfaces.TypeDestinationRemote;


@ManagedBean(name="manage_fraude")
@ViewScoped
public class ManageFraudeMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FraudeManagerRemote fraude_service;
	
	private List<ReglesFraude> liste_regle_fraude =new ArrayList<>();
	
	private ReglesFraude selected_regle;
	private ParametresReglesFraude selected_parametre;
	private FiltresReglesFraude selected_filtre;
	private ParametresReglesFraude nouveauparametre;
	private FiltresReglesFraude nouveaufiltre;
private List<ParametresFraude> parametres;
private List<ParametresReglesFraude> listparamregle;
private List<FiltresReglesFraude> listfiltreregle;



	
	private List<FiltresFraude> filtres;
	private String choix_param_valeur;
	private String choix_filtre_valeur;
	private Integer param_valeur;
	private String filtre_valeur;
	
	public String getFiltre_valeur() {
		return filtre_valeur;
	}
	public void setFiltre_valeur(String filtre_valeur) {
		this.filtre_valeur = filtre_valeur;
	}

	
	@EJB
	private TypeDestinationRemote type_dest_service;
	
	@EJB
	private PlanTarifaireRemote plan_service;
	private List<PlanTarifaire> plans = new ArrayList<>();
	
	private List<TypeDestination> typeDests = new ArrayList<>();
	
	
	@PostConstruct
	public void init(){
		liste_regle_fraude= fraude_service.getAllFraudes("");
		listfiltreregle= new ArrayList<>();
		listparamregle= new ArrayList<>();
		selected_regle= new ReglesFraude();
		
		selected_filtre= new FiltresReglesFraude();
		selected_parametre= new ParametresReglesFraude();
		parametres= new ArrayList<>();
		filtres = new ArrayList<>();
		nouveaufiltre= new FiltresReglesFraude();
		nouveauparametre= new ParametresReglesFraude();
	}
	

private boolean ListTypeDest=false;
public boolean isListTypeDest() {
	return ListTypeDest;
}
public void setListTypeDest(boolean list) {
	ListTypeDest = list;
}

private boolean ListPlanTarifaire=false;

public boolean isListPlanTarifaire() {
	return ListPlanTarifaire;
}
public void setListPlanTarifaire(boolean isListPlanTarifaire) {
	this.ListPlanTarifaire = isListPlanTarifaire;
}
private boolean text = true;

public boolean isText() {
	return text;
}
public void setText(boolean text) {
	this.text = text;
}
private boolean typeAppel;

public boolean isTypeAppel() {
	return typeAppel;
}
public void setTypeAppel(boolean typeAppel) {
	this.typeAppel = typeAppel;
}
private boolean typelocroa;

public boolean isTypelocroa() {
	return typelocroa;
}
public void setTypelocroa(boolean typelocroa) {
	this.typelocroa = typelocroa;
}
public void selectChange(){


	if(this.getNouveaufiltre().getFiltreFraude().getFiltre().equals("type destination")){
		typeDests= type_dest_service.getAllTypeDest();
		ListTypeDest = true;
		
		ListPlanTarifaire= false;
		text=false;
		typeAppel=false;
		typelocroa=false;
		
	}else if(this.getNouveaufiltre().getFiltreFraude().getFiltre().equals("plan tarifaire")){
		plans = plan_service.getAllPlanTarifaires();
	ListTypeDest = false;
		
		ListPlanTarifaire= true;
		text=false;
		typeAppel=false;
		typelocroa=false;
	}else if(this.getNouveaufiltre().getFiltreFraude().getFiltre().equals("Type Appel")){
ListTypeDest = false;
		
		ListPlanTarifaire= false;
		text=false;
		typeAppel=true;
		typelocroa=false;
	
}else if(this.getNouveaufiltre().getFiltreFraude().getFiltre().equals("Type msisdn")){
	ListTypeDest = false;
	
	ListPlanTarifaire= false;
	text=false;
	typeAppel=false;
	typelocroa=true;
}else{
	ListTypeDest = false;
		
		ListPlanTarifaire= false;
		text=true;
		typeAppel=false;
		typelocroa=false;
	}
	
}
public void selectChange1(){


	if(this.getSelected_filtre().getFiltreFraude().getFiltre().equals("type destination")){
		typeDests= type_dest_service.getAllTypeDest();
		ListTypeDest = true;
		
		ListPlanTarifaire= false;
		text=false;
		typeAppel=false;
		typelocroa=false;
		
	}else if(this.getSelected_filtre().getFiltreFraude().getFiltre().equals("plan tarifaire")){
		plans = plan_service.getAllPlanTarifaires();
	ListTypeDest = false;
		
		ListPlanTarifaire= true;
		text=false;
		typeAppel=false;
		typelocroa=false;
	}else if(this.getSelected_filtre().getFiltreFraude().getFiltre().equals("Type Appel")){
ListTypeDest = false;
		
		ListPlanTarifaire= false;
		text=false;
		typeAppel=true;
		typelocroa=false;
	
}else if(this.getSelected_filtre().getFiltreFraude().getFiltre().equals("Type msisdn")){
	ListTypeDest = false;
	
	ListPlanTarifaire= false;
	text=false;
	typeAppel=false;
	typelocroa=true;
}else{
	ListTypeDest = false;
		
		ListPlanTarifaire= false;
		text=true;
		typeAppel=false;
		typelocroa=false;
	}
	
}
	
	public void ajouter_filtre(){
		
		switch (this.getChoix_filtre_valeur()) {
		case "egale":
			nouveaufiltre.setVegal(filtre_valeur);
			nouveaufiltre.setVdef("-1");
			nouveaufiltre.setInegal("-1");
			nouveaufiltre.setVlike("-1");
			nouveaufiltre.setVnotlike("-1");
			
			break;
		case "like":
			nouveaufiltre.setVegal("-1");
			nouveaufiltre.setVdef("-1");
			nouveaufiltre.setInegal("-1");
			nouveaufiltre.setVlike(filtre_valeur);
			nouveaufiltre.setVnotlike("-1");
			
			break;
		case "not like":
			nouveaufiltre.setVegal("-1");
			nouveaufiltre.setVdef("-1");
			nouveaufiltre.setInegal("-1");
			nouveaufiltre.setVlike("-1");
			nouveaufiltre.setVnotlike(this.getFiltre_valeur());
			
			break;
		case "different":
			nouveaufiltre.setVegal("-1");
			nouveaufiltre.setVdef(this.getFiltre_valeur());
			nouveaufiltre.setInegal("-1");
			nouveaufiltre.setVlike("-1");
			nouveaufiltre.setVnotlike("-1");
		
			break;
		case "in":
			nouveaufiltre.setVegal("-1");
			nouveaufiltre.setVdef("-1");
			nouveaufiltre.setInegal(this.getFiltre_valeur());
			nouveaufiltre.setVlike("-1");
			nouveaufiltre.setVnotlike("-1");
			
			
			break;

		default:
			break;
		}

				nouveaufiltre.setRegle(selected_regle);
			fraude_service.addFiltre(nouveaufiltre);
		listfiltreregle= fraude_service.getFiltresReglesFraudeByRegle(selected_regle);
		
		nouveaufiltre= new FiltresReglesFraude();
		choix_filtre_valeur = "";
		filtre_valeur="";
		
				//fraude_service.addParametre(nouveauparametre);
	//listparamregle = fraude_service.getParametresReglesFraudeByRegle(selected_regle);
		//selected_regle.setListe_parameters(selected_regle.getListe_parameters());
		
		
	}
public void update_filtre(){
		
	switch (this.getChoix_filtre_valeur()) {
	case "egale":
		selected_filtre.setVegal(this.getFiltre_valeur());
		selected_filtre.setVdef("-1");
		selected_filtre.setInegal("-1");
		selected_filtre.setVlike("-1");
		selected_filtre.setVnotlike("-1");
		
		break;
	case "like":
		selected_filtre.setVegal("-1");
		selected_filtre.setVdef("-1");
		selected_filtre.setInegal("-1");
		selected_filtre.setVlike(filtre_valeur);
		selected_filtre.setVnotlike("-1");
		
		break;
	case "not like":
		selected_filtre.setVegal("-1");
		selected_filtre.setVdef("-1");
		selected_filtre.setInegal("-1");
		selected_filtre.setVlike("-1");
		selected_filtre.setVnotlike(filtre_valeur);
		
		break;
	case "different":
		selected_filtre.setVegal("-1");
		selected_filtre.setVdef(filtre_valeur);
		selected_filtre.setInegal("-1");
		selected_filtre.setVlike("-1");
		selected_filtre.setVnotlike("-1");
	
		break;
	case "in":
		selected_filtre.setVegal("-1");
		selected_filtre.setVdef("-1");
		selected_filtre.setInegal(filtre_valeur);
		selected_filtre.setVlike("-1");
		selected_filtre.setVnotlike("-1");
		
		
		break;

	default:
		break;
	}

			selected_filtre.setRegle(this.getSelected_regle());
			
			fraude_service.updateFiltre(this.getSelected_filtre());
			listfiltreregle= fraude_service.getFiltresReglesFraudeByRegle(selected_regle);
			selected_filtre= new FiltresReglesFraude();
			choix_filtre_valeur="";
			filtre_valeur="";
		//fraude_service.updateRegle(this.getSelected_regle());
			//fraude_service.addParametre(nouveauparametre);
//listparamregle = fraude_service.getParametresReglesFraudeByRegle(selected_regle);
	//selected_regle.setListe_parameters(selected_regle.getListe_parameters());
		}


	public void ajoutParam(){
		System.out.println("marche");
		switch (choix_param_valeur) {
		case "egale":
			nouveauparametre.setVegal(this.getParam_valeur());
			nouveauparametre.setVmax(-1);
			nouveauparametre.setVmin(-1);
			
			
			break;
		case "superieur":
			nouveauparametre.setVegal(-1);
			nouveauparametre.setVmax(-1);
			nouveauparametre.setVmin(this.getParam_valeur());
			
			
			break;
		case "inferieur":
			nouveauparametre.setVegal(-1);
			nouveauparametre.setVmax(this.getParam_valeur());
			nouveauparametre.setVmin(-1);
			
			
			break;

		default:
			break;
		}
	

				nouveauparametre.setRegle(this.getSelected_regle());
				fraude_service.addParametre(nouveauparametre);
			listparamregle= fraude_service.getParametresReglesFraudeByRegle(selected_regle);
			nouveauparametre = new ParametresReglesFraude();
			choix_param_valeur = "";
			param_valeur=0;
				//fraude_service.addParametre(nouveauparametre);
	//listparamregle = fraude_service.getParametresReglesFraudeByRegle(selected_regle);
		//selected_regle.setListe_parameters(selected_regle.getListe_parameters());
		
		
	}
public void update_param(){
		
		switch (this.getChoix_param_valeur()) {
		case "egale":
			selected_parametre.setVegal(param_valeur);
			selected_parametre.setVmax(-1);
			selected_parametre.setVmin(-1);
			
			
			break;
		case "superieur":
			selected_parametre.setVegal(-1);
			selected_parametre.setVmax(-1);
			selected_parametre.setVmin(param_valeur);
			
			
			break;
		case "inferieur":
			selected_parametre.setVegal(-1);
			selected_parametre.setVmax(param_valeur);
			selected_parametre.setVmin(-1);
			
			
			break;

		default:
			break;
		}
		

		selected_parametre.setRegle(selected_regle);
		
		
		//selected_regle.setListe_parameters(selected_regle.getListe_parameters());
		selected_regle.setListe_parameters(selected_regle.getListe_parameters());
		fraude_service.updateParametre(selected_parametre);
		listparamregle= fraude_service.getParametresReglesFraudeByRegle(selected_regle);
		selected_parametre= new ParametresReglesFraude();
		//listparamregle= fraude_service.getParametresReglesFraudeByRegle(selected_regle);
	}


public void deleteParam(){
	fraude_service.deleteParametre(selected_parametre);
	listparamregle=fraude_service.getParametresReglesFraudeByRegle(selected_regle);
}
public void deleteFiltre(){
	fraude_service.deleteFiltre(selected_filtre);
	listfiltreregle.remove(selected_filtre);
	
	listfiltreregle = fraude_service.getFiltresReglesFraudeByRegle(selected_regle);
}
	
	public void loadparametres(SelectEvent event){
		listfiltreregle= fraude_service.getFiltresReglesFraudeByRegle(selected_regle);
		listparamregle = fraude_service.getParametresReglesFraudeByRegle(selected_regle);
		String where = "p.flux.id="+selected_regle.getFlux().getId();
		parametres=fraude_service.getParametresFraude(where);
		
		 where = "f.flux.id="+selected_regle.getFlux().getId();
		filtres=fraude_service.getFilterFiltresFraude(where);
		System.out.println(filtres.size());
		System.out.println(parametres.size());
		
	}
	
	public void desactiverRegle(){
		
		selected_regle.setEtat("D");
		fraude_service.updateRegle(selected_regle);
		liste_regle_fraude= fraude_service.getAllFraudes("");
		
	}
public void activerRegle(){
		
		selected_regle.setEtat("A");
		fraude_service.updateRegle(selected_regle);
		liste_regle_fraude= fraude_service.getAllFraudes("");
		
	}
public Integer getParam_valeur() {
	return param_valeur;
}
public void setParam_valeur(Integer param_valeur) {
	this.param_valeur = param_valeur;
}

public String getChoix_filtre_valeur() {
	return choix_filtre_valeur;
}
public void setChoix_filtre_valeur(String choix_filtre_valeur) {
	this.choix_filtre_valeur = choix_filtre_valeur;
}

public String getChoix_param_valeur() {
	return choix_param_valeur;
}
public void setChoix_param_valeur(String choix_param_valeur) {
	this.choix_param_valeur = choix_param_valeur;
}
public FiltresReglesFraude getNouveaufiltre() {
return nouveaufiltre;
}
public void setNouveaufiltre(FiltresReglesFraude nouveaufiltre) {
this.nouveaufiltre = nouveaufiltre;
}
public ParametresReglesFraude getNouveauparametre() {
return nouveauparametre;
}
public void setNouveauparametre(ParametresReglesFraude nouveauparametre) {
this.nouveauparametre = nouveauparametre;
}


public List<ReglesFraude> getListe_regle_fraude() {
	return liste_regle_fraude;
}





public void setListe_regle_fraude(List<ReglesFraude> liste_regle_fraude) {
	this.liste_regle_fraude = liste_regle_fraude;
}





public ReglesFraude getSelected_regle() {
	return selected_regle;
}





public void setSelected_regle(ReglesFraude selected_regle) {
	this.selected_regle = selected_regle;
}





public ParametresReglesFraude getSelected_parametre() {
	return selected_parametre;
}





public void setSelected_parametre(ParametresReglesFraude selected_parametre) {
	this.selected_parametre = selected_parametre;
}





public FiltresReglesFraude getSelected_filtre() {
	return selected_filtre;
}





public void setSelected_filtre(FiltresReglesFraude selected_filtre) {
	this.selected_filtre = selected_filtre;
}

public List<FiltresFraude> getFiltres() {
	return filtres;
}
public void setFiltres(List<FiltresFraude> filtres) {
	this.filtres = filtres;
}
public List<ParametresFraude> getParametres() {
	return parametres;
}
public void setParametres(List<ParametresFraude> parametres) {
	this.parametres = parametres;
}

public List<FiltresReglesFraude> getListfiltreregle() {
	return listfiltreregle;
}
public void setListfiltreregle(List<FiltresReglesFraude> listfiltreregle) {
	this.listfiltreregle = listfiltreregle;
}public List<ParametresReglesFraude> getListparamregle() {
	return listparamregle;
}
public void setListparamregle(List<ParametresReglesFraude> listparamregle) {
	this.listparamregle = listparamregle;
}
	
	
	
	
	
	public List<TypeDestination> getTypeDests() {
		return typeDests;
	}
	public void setPlans(List<PlanTarifaire> plans) {
		this.plans = plans;
	}
	public void setTypeDests(List<TypeDestination> typeDests) {
		this.typeDests = typeDests;
	}
	public List<PlanTarifaire> getPlans() {
		return plans;
	}
	
	
	

}
