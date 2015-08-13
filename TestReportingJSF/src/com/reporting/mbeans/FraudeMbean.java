package com.reporting.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;






import com.reporting.metier.entities.CategoriesFraude;
import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FiltresReglesFraude;
import com.reporting.metier.entities.FluxCdr;
import com.reporting.metier.entities.ParametresFraude;
import com.reporting.metier.entities.ParametresReglesFraude;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.ReglesFraude;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.FraudeManagerRemote;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
import com.reporting.metier.interfaces.TypeDestinationRemote;


@ManagedBean(name="fraude")
@ViewScoped
public class FraudeMbean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EJB
	private FraudeManagerRemote fraude_service;
	
	
	@EJB
	private TypeDestinationRemote type_dest_service;
	
	@EJB
	private PlanTarifaireRemote plan_service;
	

	
	private List<String> filters ;
	
	public List<String> getFilters() {
		return filters;
	}
	public void setFilters(List<String> filters) {
		this.filters = filters;
	}
	
	private Integer id_flux;
	
	public Integer getId_flux() {
		return id_flux;
	}
	public void setId_flux(Integer id_flux) {
		this.id_flux = id_flux;
	}
	private List<PlanTarifaire> plans = new ArrayList<>();
	
	private List<TypeDestination> typeDests = new ArrayList<>();
	
	
	public List<TypeDestination> getTypeDests() {
		return typeDests;
	}

	
	private String choix_Vfiltre;
	
	private ParametresReglesFraude paramter_regle;
	
	public ParametresReglesFraude getParamter_regle() {
		return paramter_regle;
	}
	public void setParamter_regle(ParametresReglesFraude paramter_regle) {
		this.paramter_regle = paramter_regle;
	}
	
	
	public List<PlanTarifaire> getPlans() {
		return plans;
	}
	public void setPlans(List<PlanTarifaire> plans) {
		this.plans = plans;
	}
	public void setTypeDests(List<TypeDestination> typeDests) {
		this.typeDests = typeDests;
	}
	public TypeDestinationRemote getType_dest_service() {
		return type_dest_service;
	}
	
	
	
	private Integer paramtreValeur;
	
	private String choix_param_valeur;
	
	
	
	public String getChoix_Vfiltre() {
		return choix_Vfiltre;
	}
	public void setChoix_Vfiltre(String choix_Vfiltre) {
		this.choix_Vfiltre = choix_Vfiltre;
	}

	
	public String getChoix_param_valeur() {
		return choix_param_valeur;
	}
	public void setChoix_param_valeur(String choix_param_valeur) {
		this.choix_param_valeur = choix_param_valeur;
	}
	
	private FiltresFraude filtre ;
	
	
	public FiltresFraude getFiltre() {
		return filtre;
	}
	public void setFiltre(FiltresFraude filtre) {
		this.filtre = filtre;
	}
	
	
	private String choix_filter_valeur;
	
	
	public String getChoix_filter_valeur() {
		return choix_filter_valeur;
	}
	public void setChoix_filter_valeur(String choix_filter_valeur) {
		this.choix_filter_valeur = choix_filter_valeur;
	}
	
	
	private List<ParametresReglesFraude> liste_parametre_regle = new ArrayList<ParametresReglesFraude>();
	
	private ParametresReglesFraude parametre_regle_fraude;
	
	private List<FiltresReglesFraude> liste_filters_regle = new ArrayList<>();
	
	private  List<FiltresReglesFraude> listeFiltresRegle = new ArrayList<FiltresReglesFraude>();
	
	
	public List<FiltresReglesFraude> getListeFiltresRegle() {
		return listeFiltresRegle;
	}
	public void setListeFiltresRegle(List<FiltresReglesFraude> listeFiltresRegle) {
		this.listeFiltresRegle = listeFiltresRegle;
	}
	public void setListe_parametre_regle(
			List<ParametresReglesFraude> liste_parametre_regle) {
		this.liste_parametre_regle = liste_parametre_regle;
	}
	
	
	private FiltresReglesFraude filtre_regle_fraude;
	
	
	public Integer getParamtreValeur() {
		return paramtreValeur;
	}
	public void setParamtreValeur(Integer paramtreValeur) {
		this.paramtreValeur = paramtreValeur;
	}
	public FiltresReglesFraude getFiltre_regle_fraude() {
		return filtre_regle_fraude;
	}
	public void setFiltre_regle_fraude(FiltresReglesFraude filtre_regle_fraude) {
		this.filtre_regle_fraude = filtre_regle_fraude;
	}
	public void setListe_filters_regle(
			List<FiltresReglesFraude> liste_filters_regle) {
		this.liste_filters_regle = liste_filters_regle;
	}
	public List<FiltresReglesFraude> getListe_filters_regle() {
		return liste_filters_regle;
	}
	
	public List<ParametresReglesFraude> getListe_parametre_regle() {
		return liste_parametre_regle;
	}
	
	public ParametresReglesFraude getParametre_regle_fraude() {
		return parametre_regle_fraude;
	}
	public void setParametre_regle_fraude(
			ParametresReglesFraude parametre_regle_fraude) {
		this.parametre_regle_fraude = parametre_regle_fraude;
	}
	private List<CategoriesFraude> categories;
	private List<FluxCdr> fluxs;
	private List<ParametresFraude> parametres;
	
	private List<FiltresFraude> filtres;
	
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
	private ReglesFraude regle;
	

	private FluxCdr flux;
	
	private CategoriesFraude categorie;
	

	
private ParametresFraude parametre;


private FiltresReglesFraude filtre_regle;

public FiltresReglesFraude getFiltre_regle() {
	return filtre_regle;
}
public void setFiltre_regle(FiltresReglesFraude filtre_regle) {
	this.filtre_regle = filtre_regle;
}

	@PostConstruct
public void init(){
	categories=fraude_service.getAllCategories();
	filters = new ArrayList<>();
	fluxs=fraude_service.getAllFlux();
	parametres= new ArrayList<>();
	choix_param_valeur="egale";
	choix_filter_valeur = "egale";
	regle= new ReglesFraude();
	flux= new FluxCdr();
	filtre= new FiltresFraude();
	categorie= new CategoriesFraude();
	parametre = new ParametresFraude();
	parametre_regle_fraude = new ParametresReglesFraude();
	filtre_regle_fraude = new FiltresReglesFraude();
	liste_filters_regle= new ArrayList<>();
	paramter_regle = new ParametresReglesFraude();
	filtre_regle = new FiltresReglesFraude();

	}
	
public ParametresFraude getParametre() {
	return parametre;
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
private Integer id_filter;

public Integer getId_filter() {
	return id_filter;
}
public void setId_filter(Integer id_filter) {
	this.id_filter = id_filter;
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

private boolean iscellid =false;

public boolean isIscellid() {
	return iscellid;
}
public void setIscellid(boolean iscellid) {
	this.iscellid = iscellid;
}
public void selectChange(){

	System.out.println(this.getFiltre().getFiltre());
	if(this.getFiltre_regle_fraude().getFiltreFraude().getFiltre().equals("type destination")){
		typeDests= type_dest_service.getAllTypeDest();
		ListTypeDest = true;
		
		ListPlanTarifaire= false;
		text=false;
		typeAppel=false;
		typelocroa=false;
		
	}else if(this.getFiltre_regle_fraude().getFiltreFraude().getFiltre().equals("plan tarifaire")){
		plans = plan_service.getAllPlanTarifaires();
	ListTypeDest = false;
		
		ListPlanTarifaire= true;
		text=false;
		typeAppel=false;
		typelocroa=false;
	}else if(this.getFiltre_regle_fraude().getFiltreFraude().getFiltre().equals("Type Appel")){
ListTypeDest = false;
		
		ListPlanTarifaire= false;
		text=false;
		typeAppel=true;
		typelocroa=false;
	
}else if(this.getFiltre_regle_fraude().getFiltreFraude().getFiltre().equals("Type msisdn")){
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
public void setParametre(ParametresFraude parametre) {
	this.parametre = parametre;
}
	
public void selectChangeFiltres(){
		
		String where = "p.flux.id="+this.getRegle().getFlux().getId();
		parametres=fraude_service.getParametresFraude(where);
		
		 where = "f.flux.id="+this.getRegle().getFlux().getId();
		filtres=fraude_service.getFilterFiltresFraude(where);
		
		
		
	}
	public CategoriesFraude getCategorie() {
		return categorie;
	}
	public FluxCdr getFlux() {
		return flux;
	}
	public void setCategorie(CategoriesFraude categorie) {
		this.categorie = categorie;
	}
	public void setFlux(FluxCdr flux) {
		this.flux = flux;
	}
	public ReglesFraude getRegle() {
		return regle;
	}
	public void setRegle(ReglesFraude regle) {
		this.regle = regle;
	}

	
	public List<CategoriesFraude> getCategories() {
		return categories;
	}
	public List<FluxCdr> getFluxs() {
		return fluxs;
	}
	public void setCategories(List<CategoriesFraude> categories) {
		this.categories = categories;
	}
	public void setFluxs(List<FluxCdr> fluxs) {
		this.fluxs = fluxs;
	}
	
	public void addParametre(){
		
	
	
switch (this.getChoix_param_valeur()) {
case "egale":
	parametre_regle_fraude.setVegal(paramtreValeur);
	parametre_regle_fraude.setVmax(-1);
	parametre_regle_fraude.setVmin(-1);
	
	
	break;
case "superieur":
	parametre_regle_fraude.setVegal(-1);
	parametre_regle_fraude.setVmax(-1);
	parametre_regle_fraude.setVmin(paramtreValeur);
	
	
	break;
case "inferieur":
	parametre_regle_fraude.setVegal(-1);
	parametre_regle_fraude.setVmax(paramtreValeur);
	parametre_regle_fraude.setVmin(-1);
	
	
	break;

default:
	break;
}
parametre_regle_fraude.setNomUtilisateur(Util.getUserName());

		parametre_regle_fraude.setRegle(regle);
	
		this.liste_parametre_regle.add(parametre_regle_fraude);
		parametre_regle_fraude = new ParametresReglesFraude();
		
	}
	
	public void addFiltreRegleFraude(){
		FiltresFraude f= new FiltresFraude();
		f.setFiltre("test");
		filtre_regle_fraude.setFiltreFraude(f);
		listeFiltresRegle.add(filtre_regle_fraude);
		filtre_regle_fraude= new FiltresReglesFraude();
	}
	public void addFiltre(){
		
	
	
	
		switch (this.getChoix_filter_valeur()) {
		case "egale":
			filtre_regle_fraude.setVegal(this.getChoix_Vfiltre());
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike("-1");
			
			break;
		case "like":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike(this.getChoix_Vfiltre());
			filtre_regle_fraude.setVnotlike("-1");
			
			break;
		case "not like":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike(this.getChoix_Vfiltre());
			
			break;
		case "different":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef(this.getChoix_Vfiltre());
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike("-1");
		
			break;
		case "in":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal(this.getChoix_Vfiltre());
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike("-1");
			
			
			break;

		default:
			break;
		}
		filtre_regle_fraude.setNomUtlisateur(Util.getUserName());
filtre_regle_fraude.setRegle(regle);
	
		listeFiltresRegle.add(filtre_regle_fraude);
		System.out.println(listeFiltresRegle.size());
		filtre_regle_fraude= new FiltresReglesFraude();
	
	}
	
	public void deletefiltre(){
		listeFiltresRegle.remove(filtre_regle);
		
		
	}
	
	public void deleteParametre(){
		liste_parametre_regle.remove(paramter_regle);
	}
	
	public void valider(){
		try {
		for(int i=0;i<listeFiltresRegle.size();i++){
			System.out.println(listeFiltresRegle.get(i).getFiltreFraude().getFiltre()+"="+listeFiltresRegle.get(i).getVegal()+""+listeFiltresRegle.get(i));
		}
		for(int i=0;i<liste_parametre_regle.size();i++){
			System.out.println(liste_parametre_regle.get(i).getParametreFraude().getNomParametre());
		
		}
		
		regle.setListe_filters(listeFiltresRegle);
		regle.setListe_parameters(liste_parametre_regle);
		regle.setEtat("D");
		fraude_service.addRegle(regle);
		
	
			FacesContext.getCurrentInstance().getExternalContext().redirect("/TestReportingJSF/pages/fraude/manage_regles.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
