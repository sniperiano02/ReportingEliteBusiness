package com.reporting.mbeans.interco;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.reporting.metier.entities.OperateurInterco;
import com.reporting.metier.interfaces.OperateurIntercoRemote;
import com.reporting.metier.interfaces.StatIntercoInterRemote;



@ManagedBean(name="mou_entrant")
@ViewScoped
public class MouEntrant {

	@EJB
	private StatIntercoInterRemote interco_service;
	
	@EJB
	private OperateurIntercoRemote operateur_service;
	
	
	private List<OperateurInterco> lstOperateurs = new ArrayList<>();
	
	private String choix_periode;
	
	private List<Object[]> resultatmou_entrant;
	
	private String choix_operateur;
	
	
	 private Date date_Parheure;
	 private Date date_ParJourDeb;
	 private Date date_ParJourFin;
	 private Integer date_year_deb;
	 private Integer date_year_fin;
	 private Date date_mois_fin;
	 private Date date_mois_debut;
	 private Integer date_year; 
	 private boolean chartDisplayed=false;
	 private List<String> where_liste ;

	 public List<String> getWhere_liste() {
	 	return where_liste;
	 }
	 public void setWhere_liste(List<String> where_liste) {
	 	this.where_liste = where_liste;
	 }
	 private String SubTitle;

	 public String getSubTitle() {
	 	return SubTitle;
	 }
	 public void setSubTitle(String subTitle) {
	 	SubTitle = subTitle;
	 }


	 public boolean isChartDisplayed() {
		return chartDisplayed;
	}
	 public void setChartDisplayed(boolean chartDisplayed) {
		this.chartDisplayed = chartDisplayed;
	}
public String getChoix_operateur() {
	return choix_operateur;
}
public void setChoix_operateur(String choix_operateur) {
	this.choix_operateur = choix_operateur;
}

	public List<OperateurInterco> getLstOperateurs() {
		return lstOperateurs;
	}
	public void setLstOperateurs(List<OperateurInterco> lstOperateurs) {
		this.lstOperateurs = lstOperateurs;
	}
	
	public String getChoix_periode() {
		return choix_periode;
	}
	public void setChoix_periode(String choix_periode) {
		this.choix_periode = choix_periode;
	}
	public List<Object[]> getResultatmou_entrant() {
		return resultatmou_entrant;
	}
	public void setResultatmou_entrant(List<Object[]> resultatmou_entrant) {
		this.resultatmou_entrant = resultatmou_entrant;
	}
	
	private List<Integer> liste_desAns = new ArrayList<Integer>();
	
	public List<Integer> getListe_desAns() {
		return liste_desAns;
	}
	public void setListe_desAns(List<Integer> liste_desAns) {
		this.liste_desAns = liste_desAns;
	}
	
	
	
	public Date getDate_Parheure() {
		return date_Parheure;
	}
	public void setDate_Parheure(Date date_Parheure) {
		this.date_Parheure = date_Parheure;
	}
	public Date getDate_ParJourDeb() {
		return date_ParJourDeb;
	}
	public void setDate_ParJourDeb(Date date_ParJourDeb) {
		this.date_ParJourDeb = date_ParJourDeb;
	}
	public Date getDate_ParJourFin() {
		return date_ParJourFin;
	}
	public void setDate_ParJourFin(Date date_ParJourFin) {
		this.date_ParJourFin = date_ParJourFin;
	}
	public Integer getDate_year_deb() {
		return date_year_deb;
	}
	public void setDate_year_deb(Integer date_year_deb) {
		this.date_year_deb = date_year_deb;
	}
	public Integer getDate_year_fin() {
		return date_year_fin;
	}
	public void setDate_year_fin(Integer date_year_fin) {
		this.date_year_fin = date_year_fin;
	}
	public Date getDate_mois_fin() {
		return date_mois_fin;
	}
	public void setDate_mois_fin(Date date_mois_fin) {
		this.date_mois_fin = date_mois_fin;
	}
	public Date getDate_mois_debut() {
		return date_mois_debut;
	}
	public void setDate_mois_debut(Date date_mois_debut) {
		this.date_mois_debut = date_mois_debut;
	}
	public Integer getDate_year() {
		return date_year;
	}
	public void setDate_year(Integer date_year) {
		this.date_year = date_year;
	}
	@PostConstruct
	public void init(){
		lstOperateurs= operateur_service.getAllOperateurs();
		
		liste_desAns=interco_service.getAllYears();
	}
	public void handlechange(AjaxBehaviorEvent event){
		
		chartDisplayed = false;
			

				FacesContext facesCtx = FacesContext.getCurrentInstance();
			    ELContext elContext = facesCtx.getELContext();
			    Application app = facesCtx.getApplication();
				 ExpressionFactory elFactory = app.getExpressionFactory();
					PanelGrid comp = (PanelGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:PanelPeriode");
					System.out.println(comp.getId());
					System.out.println(comp.getChildCount());
					UIComponent compJourOutput0 =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_jour_debut");
					UIComponent compJourOutput1 =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_jour_fin");
				
					UIComponent compJourOutputHeure =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_jour");
					UIComponent compJourOutputYear=FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:lstDateYears");
					UIComponent compJourOutputYearFin=FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:lstDateYearsFin");
				
					UIComponent compSLYearMois =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:lstDateYearsMois");
					UIComponent compSLMois =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_mois_debut");
					UIComponent compSLMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_mois_fin");
				
					if(compJourOutput0!=null){
						
						comp.getChildren().remove(compJourOutput0);
						comp.getChildren().remove(compJourOutput1);
					}
					if(compSLMois!=null){

						comp.getChildren().remove(compSLMois);
						comp.getChildren().remove(compSLMoisFin);
						
						
					}
					if(compJourOutputYear!=null){
						
						comp.getChildren().remove(compJourOutputYear);
						comp.getChildren().remove(compJourOutputYearFin);
					}
				if(compJourOutputHeure!=null){
				
					comp.getChildren().remove(compJourOutputHeure);
				}
					
					if(this.getChoix_periode().equals("Par Jour")){
						
						
					
							Calendar cld_jour_debut = new Calendar();
							cld_jour_debut.setId("cld_jour_debut");
							cld_jour_debut.setRequired(true);
						
							
						ValueExpression valjour = createValueExpression("#{mou_entrant.date_ParJourDeb}", Date.class);
						cld_jour_debut.setValueExpression("value", valjour);
							
							Calendar cld_jour_fin = new Calendar();
						
							cld_jour_fin.setId("cld_jour_fin");
							
						ValueExpression valjourfin = createValueExpression("#{mou_entrant.date_ParJourFin}", Date.class);
						cld_jour_fin.setValueExpression("value", valjourfin);
						
						
							
						
							
							comp.getChildren().add(cld_jour_debut);
							
							comp.getChildren().add(cld_jour_fin);
						
						
						
							
					
					}else if (this.getChoix_periode().equals("Par Heure")){
						Calendar cld_jour = new Calendar();
						
						
						cld_jour.setId("cld_jour");
						
					ValueExpression valjour = createValueExpression("#{mou_entrant.date_Parheure}", Date.class);
					cld_jour.setValueExpression("value", valjour);
					AjaxBehavior ajax = new AjaxBehavior();
				       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
				        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
				            createActionMethodExpression("#{mou_entrant.handlechange1}"),createActionMethodExpression("#{mou_entrant.handlechange1}")));
				        ajax.setUpdate("@form");
				       

					      
			         
			        
//					        
			         
				         cld_jour.addClientBehavior("dateSelect", ajax);
				         FacesContext fc = FacesContext.getCurrentInstance();
				         ExpressionFactory ef = fc.getApplication().getExpressionFactory();

				         MethodExpression me = ef.createMethodExpression(fc.getELContext(), "#{mou_entrant.handlechange2}", null, new Class<?>[]{BehaviorEvent.class});
				         AjaxBehavior ajaxBehavior = (AjaxBehavior) fc.getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
				         ajaxBehavior.setProcess("@this");
				      
				         ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(me, me));
				         cld_jour.addClientBehavior("change", ajaxBehavior);
						comp.getChildren().add(cld_jour);
					}else if (this.getChoix_periode().equals("Par An")){
						SelectOneMenu lstDateYears = new SelectOneMenu();
						lstDateYears.setId("lstDateYears");
						 ValueExpression valueExp = createValueExpression("#{mou_entrant.date_year_deb}", Integer.class);
				         UISelectItems list_items0 = new UISelectItems();
						 lstDateYears.setValueExpression("value", valueExp);
				         list_items0.setValue(liste_desAns);
				         lstDateYears.getChildren().add(list_items0);
						
						SelectOneMenu lstDateYearsFin = new SelectOneMenu();
						lstDateYearsFin.setId("lstDateYearsFin");
						 ValueExpression valueExp1 = createValueExpression("#{mou_entrant.date_year_fin}", Integer.class);
				         UISelectItems list_items = new UISelectItems();
						 lstDateYearsFin.setValueExpression("value", valueExp1);
				         list_items.setValue(liste_desAns);
				         lstDateYearsFin.getChildren().add(list_items);
					
				
					comp.getChildren().add(lstDateYears);
					
					comp.getChildren().add(lstDateYearsFin);
					
					
					
					}else if (this.getChoix_periode().equals("Par Mois")){
						Calendar cld_mois_debut = new Calendar();
						cld_mois_debut.setId("cld_mois_debut");
						cld_mois_debut.setRequired(true);
					
						
					ValueExpression valjour = createValueExpression("#{mou_entrant.date_mois_debut}", Date.class);
					cld_mois_debut.setValueExpression("value", valjour);
					cld_mois_debut.setPattern("MM/yyyy");
					cld_mois_debut.setMask("true");
				
						Calendar cld_mois_fin = new Calendar();
					
						cld_mois_fin.setId("cld_mois_fin");
						cld_mois_fin.setPattern("MM/yyyy");
						cld_mois_fin.setMask("true");
					ValueExpression valjourfin = createValueExpression("#{mou_entrant.date_mois_fin}", Date.class);
					cld_mois_fin.setValueExpression("value", valjourfin);
					
					
						
					
						
						comp.getChildren().add(cld_mois_debut);
						
						comp.getChildren().add(cld_mois_fin);
					
					
					}	
					
			}
	
	public void Valider(){

		where_liste = new ArrayList<String>();
		resultatmou_entrant = new ArrayList<>();
		if(this.getChoix_periode().equals(" ")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
		}
		else{
			where_liste.add("typeStat='charge'");
			if(this.getChoix_periode().equals("Par Jour")){
				
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
					String deb = df.format(this.getDate_ParJourDeb());
					String fin = df.format(this.getDate_ParJourFin());
					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
				SubTitle ="Periode entre "+deb+" et "+fin;
				
				
			}else if(this.getChoix_periode().equals("Par Heure")){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(date_Parheure);
				String deb = df.format(this.getDate_Parheure());
				System.out.println(deb);
				where_liste.add(" to_date(dateAppel,'YYMMDD')= to_date("+"'"+deb+"'"+",'yyyy-MM-dd')");
				SubTitle ="Par tranche horaire à la date "+deb;
				
			}else if(this.getChoix_periode().equals("Par An")){
			
				Integer deb = this.getDate_year_deb();
				Integer fin =this.getDate_year_fin();
				where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(year from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
				SubTitle ="Entre  "+deb+" et "+fin;
			}else if(this.getChoix_periode().equals("Par Mois")){
				DateFormat df = new SimpleDateFormat("MM-YYYY");
				System.out.println(date_Parheure);
				String deb = df.format(this.getDate_mois_debut());
				String fin = df.format(this.getDate_mois_fin());
				
				where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'MM-YYYY') And to_date("+"'"+fin+"'"+",'MM-YYYY')");
				SubTitle ="Entre le mois "+deb+" et le mois "+fin;
			}
			
			
			
			
		
			if(!this.getChoix_operateur().equals("")&& this.getChoix_operateur()!=null){
				where_liste.add(" codeOperateur="+"'"+this.getChoix_operateur()+"'");
				SubTitle=SubTitle+"avec "+this.getChoix_operateur()+" comme operateur";
			}
			
	
				
		if(this.getChoix_periode().equals("Par Heure")){
			 
			 resultatmou_entrant = interco_service.getStatInterco("substring(trancheHoraire,1,2)", "SUM(dureeTotal/60)/SUM(nombreTotal)", "", where_liste);
		
	 		 }else if(this.getChoix_periode().equals("Par An")){
	 			 resultatmou_entrant = interco_service.getStatInterco("Extract (year from to_date(dateAppel,'YYMMDD'))", "SUM(dureeTotal/60)/SUM(nombreTotal)", "", where_liste);
	 			
	 			
		 		 }else if(this.getChoix_periode().equals("Par Mois")){
		 			 resultatmou_entrant = interco_service.getStatInterco("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", "SUM(dureeTotal/60)/SUM(nombreTotal)", "",  where_liste);

		 		 }else if (this.getChoix_periode().equals("Par Jour")){
		 			 resultatmou_entrant = interco_service.getStatInterco("to_char(to_date(dateAppel,'YYMMDD'),'DD')", "SUM(dureeTotal/60)/SUM(nombreTotal)", "",  where_liste);

		 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
		 		 }
		
		 if(resultatmou_entrant!=null){
			 chartDisplayed=true;
		 }else{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "0 Resultats pour Filters choisi " ) );

		 }
			 		     
			 	



			 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 

			
			
			}		
	}
	public void handlechange2(AjaxBehaviorEvent event){
		System.out.println("File Date: " + date_Parheure);
	    System.out.println("Hello... I am in DateChange");
	    UIComponent target = event.getComponent().findComponent("form1:ip1");
	    FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(target.getClientId());  
	    
			chartDisplayed=false;
		}
		public void handlechange1(){
			
			chartDisplayed=false;
		}

	
	
	 public MethodExpression createActionMethodExpression(String name) {
	        FacesContext facesCtx = FacesContext.getCurrentInstance();
	        ELContext elContext = facesCtx.getELContext();
	        return facesCtx
	            .getApplication()
	            .getExpressionFactory()
	            .createMethodExpression(elContext, name, String.class,
	                new Class[]{});
	    }
	   public ValueExpression createValueExpression(String valueExpression,
		        Class<?> valueType) {
		        FacesContext context = FacesContext.getCurrentInstance();
		        return context
		            .getApplication()
		            .getExpressionFactory()
		            .createValueExpression(context.getELContext(), valueExpression,
		                valueType);
		    }
	
	
	
}
