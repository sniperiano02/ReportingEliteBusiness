package com.reporting.mbeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.itextpdf.text.pdf.PRAcroForm;
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
public class FraudeMbean {

	
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
	
	private List<PlanTarifaire> plans = new ArrayList<>();
	
	private List<TypeDestination> typeDests = new ArrayList<>();
	
	
	public List<TypeDestination> getTypeDests() {
		return typeDests;
	}

	
	private String choix_Vfiltre;
	
	
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
	
	
	
	private Long paramtreValeur;
	
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
	
	
	private List<ParametresReglesFraude> liste_parametre_regle;
	
	private ParametresReglesFraude parametre_regle_fraude;
	
	private List<FiltresReglesFraude> liste_filters_regle = new ArrayList<>();
	
	private List<FiltresReglesFraude> listeFiltresRegle = new ArrayList<>();
	
	
	public List<FiltresReglesFraude> getListeFiltresRegle() {
		return listeFiltresRegle;
	}
	public void setListeFiltresRegle(List<FiltresReglesFraude> listeFiltresRegle) {
		this.listeFiltresRegle = listeFiltresRegle;
	}
	
	private FiltresReglesFraude filtre_regle_fraude;
	
	
	public Long getParamtreValeur() {
		return paramtreValeur;
	}
	public void setParamtreValeur(Long paramtreValeur) {
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
	public void setListe_parametre_regle(
			List<ParametresReglesFraude> liste_parametre_regle) {
		this.liste_parametre_regle = liste_parametre_regle;
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

	@PostConstruct
	public void init(){
	categories=fraude_service.getAllCategories();
	filters = new ArrayList<>();
	fluxs=fraude_service.getAllFlux();
	parametres= new ArrayList<>();
	filtres= new ArrayList<>();
	regle= new ReglesFraude();
	flux= new FluxCdr();
	filtre= new FiltresFraude();
	categorie= new CategoriesFraude();
	parametre = new ParametresFraude();
	parametre_regle_fraude = new ParametresReglesFraude();
	filtre_regle_fraude = new FiltresReglesFraude();
	liste_filters_regle= new ArrayList<>();
	listeFiltresRegle = new ArrayList<FiltresReglesFraude>();
	liste_parametre_regle= new ArrayList<ParametresReglesFraude>();
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

public void selectChange(){
	System.out.println("test");
	System.out.println(this.getFiltre().getFiltre());
	if(this.getFiltre().getFiltre().equals("type destination")){
		typeDests= type_dest_service.getAllTypeDest();
		ListTypeDest = true;
		
		ListPlanTarifaire= false;
		text=false;
		
	}else if(this.getFiltre().getFiltre().equals("plan tarifaire")){
		plans = plan_service.getAllPlanTarifaires();
	ListTypeDest = false;
		
		ListPlanTarifaire= true;
		text=false;
	}else{
	ListTypeDest = false;
		
		ListPlanTarifaire= false;
		text=true;
	}
	
}
public void setParametre(ParametresFraude parametre) {
	this.parametre = parametre;
}
	public void selectChangeFiltres(){
		String where = "p.flux.id="+flux.getId();
		parametres=fraude_service.getParametresFraude(where);
		
		 where = "f.flux.id="+flux.getId();
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
		System.out.println("1");
		regle.setFlux(flux);
		parametre_regle_fraude = new ParametresReglesFraude();
		System.out.println(parametre.getNomParametre());
		System.out.println(regle.getNom());
		System.out.println(regle.getNom());
		parametre_regle_fraude.setParametreFraude(parametre);

		parametre_regle_fraude.setRegle(regle);
	
		liste_parametre_regle.add(parametre_regle_fraude);
	}
	public void addFiltre(){
		System.out.println("1");
		String val = "";
		filtre_regle_fraude = new FiltresReglesFraude();
		
		System.out.println(filtre.getFiltre());
		System.out.println(regle.getNom());
		System.out.println(regle.getNom());
		filtre_regle_fraude.setFiltreFraude(filtre);
		filtre_regle_fraude.setRegle(regle);
		switch (choix_filter_valeur) {
		case "egale":
			filtre_regle_fraude.setVegal(choix_Vfiltre);
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike("-1");
			val="=";
			break;
		case "like":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike(choix_Vfiltre);
			filtre_regle_fraude.setVnotlike("-1");
			val=choix_filter_valeur;
			break;
		case "not like":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike(choix_Vfiltre);
			val=choix_filter_valeur;
			break;
		case "different":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef(choix_Vfiltre);
			filtre_regle_fraude.setInegal("-1");
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike("-1");
			val="<>";
			break;
		case "in":
			filtre_regle_fraude.setVegal("-1");
			filtre_regle_fraude.setVdef("-1");
			filtre_regle_fraude.setInegal(choix_Vfiltre);
			filtre_regle_fraude.setVlike("-1");
			filtre_regle_fraude.setVnotlike("-1");
			val="inegal";
			break;

		default:
			break;
		}


	
		listeFiltresRegle.add(filtre_regle_fraude);
		System.out.println(liste_filters_regle.size());
	}
	
	
	public void valider(){
		
		for(int i=0;i<listeFiltresRegle.size();i++){
			System.out.println(listeFiltresRegle.get(i).getFiltreFraude().getFiltre()+"="+listeFiltresRegle.get(i).getVegal()+""+listeFiltresRegle.get(i));
		}
		for(int i=0;i<liste_parametre_regle.size();i++){
			System.out.println(liste_parametre_regle.get(i).getParametreFraude().getNomParametre());
		}
	}
	
}
