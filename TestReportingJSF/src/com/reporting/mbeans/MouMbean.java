package com.reporting.mbeans;

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

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.reporting.metier.interfaces.MouPrpRemote;


@ManagedBean(name="mou_prp")
@ViewScoped
public class MouMbean {
	
	
	@EJB
	private MouPrpRemote mou_service;
	private List<Object[]> listeDetailsVerifTransfert;
	public List<Object[]> getListeDetailsVerifTransfert() {
		return listeDetailsVerifTransfert;
	}
	public void setListeDetailsVerifTransfert(
			List<Object[]> listeDetailsVerifTransfert) {
		this.listeDetailsVerifTransfert = listeDetailsVerifTransfert;
	}
 
	private String choix_impact;
	
	public String getChoix_impact() {
		return choix_impact;
	}
	public void setChoix_impact(String choix_impact) {
		this.choix_impact = choix_impact;
	}
	
	private Object[] selectedRecon;
	
	public Object[] getSelectedRecon() {
		return selectedRecon;
	}
	public void setSelectedRecon(Object[] selectedRecon) {
		this.selectedRecon = selectedRecon;
	}
	
	private List<Object[]> listeArpu;

	public List<Object[]> getListeArpu() {
		return listeArpu;
	}
	public void setListeArpu(List<Object[]> listeArpu) {
		this.listeArpu = listeArpu;
	}
	private String choix_periode;
	public String getChoix_periode() {
		return choix_periode;
	}
	public void setChoix_periode(String choix_periode) {
		this.choix_periode = choix_periode;
	}
	private List<Integer> liste_desAns = new ArrayList<Integer>();
	private List<Integer> listeMois = new ArrayList<Integer>();

	public List<Integer> getListe_desAns() {
		return liste_desAns;
	}
	public void setListe_desAns(List<Integer> liste_desAns) {
		this.liste_desAns = liste_desAns;
	}
	public List<Integer> getListeMois() {
		return listeMois;
	}
	public void setListeMois(List<Integer> listeMois) {
		this.listeMois = listeMois;
	}
	
	private List<String> where_liste ;

	public List<String> getWhere_liste() {
		return where_liste;
	}
	public void setWhere_liste(List<String> where_liste) {
		this.where_liste = where_liste;
	}
	private boolean chartDisplayed =false;
	 
	 public boolean isChartDisplayed() {
		return chartDisplayed;
	}
	 public void setChartDisplayed(boolean chartDisplayed) {
		this.chartDisplayed = chartDisplayed;
	}

	 private String SubTitle;

	 public String getSubTitle() {
	 	return SubTitle;
	 }
	 public void setSubTitle(String subTitle) {
	 	SubTitle = subTitle;
	 }
	 
	 private boolean detail_displayed=false;
	 
	 public boolean isDetail_displayed() {
		return detail_displayed;
	}
	 public void setDetail_displayed(boolean detail_displayed) {
		this.detail_displayed = detail_displayed;
	}
	
		
	
	public String getChoix_typeCall() {
		return choix_typeCall;
	}
	public void setChoix_typeCall(String choix_typeCall) {
		this.choix_typeCall = choix_typeCall;
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
		private String choix_typeCall;
		 private Date date_Parheure;
		 private Date date_ParJourDeb;
		 private Date date_ParJourFin;
		 private Integer date_year_deb;
		 private Integer date_year_fin;
		 private Date date_mois_fin;
		 private Date date_mois_debut;
		 private Integer date_year;  
		 
		 @PostConstruct
		 public void init(){
			 liste_desAns = mou_service.getAllYears();
			 listeArpu = new ArrayList<>();
			 
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
			 				
			 					
			 				ValueExpression valjour = createValueExpression("#{mou_prp.date_ParJourDeb}", Date.class);
			 				cld_jour_debut.setValueExpression("value", valjour);
			 					
			 					Calendar cld_jour_fin = new Calendar();
			 				
			 					cld_jour_fin.setId("cld_jour_fin");
			 					
			 				ValueExpression valjourfin = createValueExpression("#{mou_prp.date_ParJourFin}", Date.class);
			 				cld_jour_fin.setValueExpression("value", valjourfin);
			 				
			 				
			 					
			 				
			 					
			 					comp.getChildren().add(cld_jour_debut);
			 					
			 					comp.getChildren().add(cld_jour_fin);
			 				
			 				
			 				
			 					
			 			
			 			}else if (this.getChoix_periode().equals("Par An")){
			 				SelectOneMenu lstDateYears = new SelectOneMenu();
			 				lstDateYears.setId("lstDateYears");
			 				 ValueExpression valueExp = createValueExpression("#{mou_prp.date_year_deb}", Integer.class);
			 		         UISelectItems list_items0 = new UISelectItems();
			 				 lstDateYears.setValueExpression("value", valueExp);
			 		         list_items0.setValue(liste_desAns);
			 		         lstDateYears.getChildren().add(list_items0);
			 				
			 				SelectOneMenu lstDateYearsFin = new SelectOneMenu();
			 				lstDateYearsFin.setId("lstDateYearsFin");
			 				 ValueExpression valueExp1 = createValueExpression("#{mou_prp.date_year_fin}", Integer.class);
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
			 			
			 				
			 			ValueExpression valjour = createValueExpression("#{mou_prp.date_mois_debut}", Date.class);
			 			cld_mois_debut.setValueExpression("value", valjour);
			 			cld_mois_debut.setPattern("MM/yyyy");
			 			cld_mois_debut.setMask("true");
			 		
			 				Calendar cld_mois_fin = new Calendar();
			 			
			 				cld_mois_fin.setId("cld_mois_fin");
			 				cld_mois_fin.setPattern("MM/yyyy");
			 				cld_mois_fin.setMask("true");
			 			ValueExpression valjourfin = createValueExpression("#{mou_prp.date_mois_fin}", Date.class);
			 			cld_mois_fin.setValueExpression("value", valjourfin);
			 			
			 			
			 				
			 			
			 				
			 				comp.getChildren().add(cld_mois_debut);
			 				
			 				comp.getChildren().add(cld_mois_fin);
			 			
			 			
			 			
			 			}
			 			
			 				
			 				
			 			
			 			
			 			
			 			
			 	}
		
			 	public void Valider(){
			 		
			 		where_liste = new ArrayList<String>();
			 		listeArpu = new ArrayList<>();
			 		if(this.getChoix_periode().equals(" ")){
			 			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
			 		}
			 		else{
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
			 			
			 			
			 			
			 			
			 		

			 	
			 				
			 	 if(this.getChoix_periode().equals("Par An")){
			 	 			 listeArpu = mou_service.getStatMou("Extract (year from to_date(dateAppel,'YYMMDD'))", "AVG(nbMinutes),AVG(parcPrp),(AVG(nbMinutes)/AVG(parcPrp))",  "", where_liste);
			 	 			
			 	 			
			 		 		 }else if(this.getChoix_periode().equals("Par Mois")){
			 		 			 listeArpu = mou_service.getStatMou("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", "AVG(nbMinutes),AVG(parcPrp),(AVG(nbMinutes) / AVG(parcPrp))",  "", where_liste);

			 		 		 }else if (this.getChoix_periode().equals("Par Jour")){
			 		 			 listeArpu = mou_service.getStatMou("to_char(to_date(dateAppel,'YYMMDD'),'DD/MM/YYYY')", "AVG(nbMinutes),AVG(parcPrp),(AVG(nbMinutes) / AVG(parcPrp)) ", "", where_liste);

			 		 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
			 		 		 }
			 		
			 		
			 			 chartDisplayed=true;
			 			     
			 			 	



			 			 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 

			 			
			 			
			 			}		
			 	}
			 	
			
			 	public void getDetail(){
			 		detail_displayed=true;
			 		
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
