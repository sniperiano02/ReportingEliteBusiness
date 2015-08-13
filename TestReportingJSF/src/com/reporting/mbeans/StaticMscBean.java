package com.reporting.mbeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.persistence.MapKey;

import org.apache.commons.collections.ListUtils;
import org.jfree.ui.action.ActionMenuItem;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Period;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.chart.Chart;
import org.primefaces.component.column.Column;
import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.reporting.metier.interfaces.NoeudRemote;
import com.reporting.metier.interfaces.StatMscImplRemote;
import com.reporting.metier.interfaces.TypeDestinationRemote;


@ManagedBean
@ViewScoped
public class StaticMscBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5079033904907400477L;
	
	
	@EJB
	StatMscImplRemote stat_remote;
	private String choix_periode;
	
	private String choix_Voix;
	@EJB
	private TypeDestinationRemote typeDest_remote;
	@EJB 
	private NoeudRemote noeud_remote;
	private String choix_typeDest;
	private String choix_msc;
	 private Date date_Parheure;
	 private Date date_ParJourDeb;
	 private Date date_ParJourFin;
	 private Integer date_year_deb;
	 private Integer date_year_fin;
	 private Date date_mois_fin;
	 private Date date_mois_debut;
	 private Integer date_year; 
	 
	 private boolean chartDisplayed =false;
	 
	 private List<Object[]> staticListStatMsc = new ArrayList<>();
	 
	 
	 public List<Object[]> getStaticListStatMsc() {
		return staticListStatMsc;
	}
	 public void setStaticListStatMsc(List<Object[]> staticListStatMsc) {
		this.staticListStatMsc = staticListStatMsc;
	}
	 
	 public boolean isChartDisplayed() {
		return chartDisplayed;
	}
	 public void setChartDisplayed(boolean chartDisplayed) {
		this.chartDisplayed = chartDisplayed;
	}
	
	
	private List<Integer> liste_desAns = new ArrayList<Integer>();
	private List<Integer> listeMois = new ArrayList<Integer>();
	private List<String> listeVoix = new ArrayList<String>();
	private List<String> listetypeDest = new ArrayList<String>();
private List<String> listeMSC= new ArrayList<String>();

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


private List<Object[]> pie1= new ArrayList<>();
private List<Object[]>  pie2= new ArrayList<>();
private List<String> heures = new ArrayList<>();



public List<Object[]> getPie1() {
	return pie1;
}
public void setPie1(List<Object[]> pie1) {
	this.pie1 = pie1;
}
public List<Object[]> getPie2() {
	return pie2;
}
public void setPie2(List<Object[]> pie2) {
	this.pie2 = pie2;
}
	
	@PostConstruct
public void init(){
	
		listeVoix=stat_remote.getTypeCall();
		listetypeDest=typeDest_remote.getAllStringTypeDest();
		listeMSC = noeud_remote.getNoeudCodes();
		listeMSC.add(0, "");
		listetypeDest.add(0,"");
		listeVoix.add(0, "");
		liste_desAns=stat_remote.getAllYears();
		 heures = new ArrayList<>();
		heures.add("00");
		heures.add("01");
		heures.add("02");
		heures.add("03");
		heures.add("04");
		heures.add("05");
		heures.add("06");
		heures.add("07");
		heures.add("08");
		heures.add("09");
		heures.add("10");
		heures.add("11");
		heures.add("12");
		heures.add("13");
		heures.add("14");
		heures.add("15");
		heures.add("16");
		heures.add("17");
		heures.add("18");
		heures.add("19");
		heures.add("20");
		heures.add("21");
		heures.add("22");
		heures.add("23");
	    
	    
	 
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
				
					
				ValueExpression valjour = createValueExpression("#{staticMscBean.date_ParJourDeb}", Date.class);
				cld_jour_debut.setValueExpression("value", valjour);
			addAjax(cld_jour_debut);
					Calendar cld_jour_fin = new Calendar();
				
					cld_jour_fin.setId("cld_jour_fin");
					
				ValueExpression valjourfin = createValueExpression("#{staticMscBean.date_ParJourFin}", Date.class);
				cld_jour_fin.setValueExpression("value", valjourfin);
				addAjax(cld_jour_fin);
			
					
				
					
					comp.getChildren().add(cld_jour_debut);
					
					comp.getChildren().add(cld_jour_fin);
				
				
				
					
			
			}else if (this.getChoix_periode().equals("Par Heure")){
				Calendar cld_jour = new Calendar();
				
				
				cld_jour.setId("cld_jour");
				
			ValueExpression valjour = createValueExpression("#{staticMscBean.date_Parheure}", Date.class);
			cld_jour.setValueExpression("value", valjour);
			AjaxBehavior ajax = new AjaxBehavior();
		       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
		        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
		            createActionMethodExpression("#{staticMscBean.handlechange1}"),createActionMethodExpression("#{staticMscBean.handlechange1}")));
		        ajax.setUpdate("@form");
		       

			      
	         
	        
//			        
	         
		         cld_jour.addClientBehavior("dateSelect", ajax);
		         FacesContext fc = FacesContext.getCurrentInstance();
		         ExpressionFactory ef = fc.getApplication().getExpressionFactory();

		         MethodExpression me = ef.createMethodExpression(fc.getELContext(), "#{staticMscBean.handlechange2}", null, new Class<?>[]{BehaviorEvent.class});
		         AjaxBehavior ajaxBehavior = (AjaxBehavior) fc.getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
		         ajaxBehavior.setProcess("@this");
		      
		         ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(me, me));
		         cld_jour.addClientBehavior("change", ajaxBehavior);
				comp.getChildren().add(cld_jour);
			}else if (this.getChoix_periode().equals("Par An")){
				SelectOneMenu lstDateYears = new SelectOneMenu();
				lstDateYears.setId("lstDateYears");
				 ValueExpression valueExp = createValueExpression("#{staticMscBean.date_year_deb}", Integer.class);
		         UISelectItems list_items0 = new UISelectItems();
				 lstDateYears.setValueExpression("value", valueExp);
		         list_items0.setValue(liste_desAns);
		         lstDateYears.getChildren().add(list_items0);
				
				SelectOneMenu lstDateYearsFin = new SelectOneMenu();
				lstDateYearsFin.setId("lstDateYearsFin");
				 ValueExpression valueExp1 = createValueExpression("#{staticMscBean.date_year_fin}", Integer.class);
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
			
				
			ValueExpression valjour = createValueExpression("#{staticMscBean.date_mois_debut}", Date.class);
			cld_mois_debut.setValueExpression("value", valjour);
			cld_mois_debut.setPattern("MM/yyyy");
			cld_mois_debut.setMask("true");
		
				Calendar cld_mois_fin = new Calendar();
			
				cld_mois_fin.setId("cld_mois_fin");
				cld_mois_fin.setPattern("MM/yyyy");
				cld_mois_fin.setMask("true");
			ValueExpression valjourfin = createValueExpression("#{staticMscBean.date_mois_fin}", Date.class);
			cld_mois_fin.setValueExpression("value", valjourfin);
			
			
				
			
				
				comp.getChildren().add(cld_mois_debut);
				
				comp.getChildren().add(cld_mois_fin);
			
			
			
			}
			
				
				
			
			
			
			
	}
	public void Valider(){
		pie1= new ArrayList<>();
		pie2=new ArrayList<>();
		where_liste = new ArrayList<String>();
		staticListStatMsc = new ArrayList<>();

		
		
		List<Object[]> generateList = new ArrayList<>();
		if(this.getChoix_periode().equals(" ")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
		}
		else{
			
			if(this.getChoix_periode().equals("Par Jour")){
				 DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
					String deb0 = df1.format(this.getDate_ParJourDeb());
					String fin0 = df1.format(this.getDate_ParJourFin());
				DateTime start = DateTime.parse(deb0);
		        System.out.println("Start: " + start);
		  
		        DateTime end = DateTime.parse(fin0);
		        System.out.println("End: " + end);

		        List<DateTime> between = getDateRange(start, end);
		        for (DateTime d : between) {
		        	String s = d+"";
		            
		            System.out.println(" " + s.substring(8,10));
		            Object[] ob = new Object[5];
		            ob[0]=s.substring(0,10);
		            ob[1]=0.0;
		            ob[2]=0.0;
		            ob[3]=0.0;
		            ob[4]=0.0;
		            
		            generateList.add(ob);
		        }
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
					String deb = df.format(this.getDate_ParJourDeb());
					String fin = df.format(this.getDate_ParJourFin());
					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
				SubTitle ="Periode entre "+deb+" et "+fin;
				
				
			}else if(this.getChoix_periode().equals("Par Heure")){
				
				for(int i=0;i<heures.size();i++){
					 Object[] ob = new Object[5];
			            ob[0]=heures.get(i);
			            System.out.println(heures.get(i));
			            ob[1]=0.0;
			            ob[2]=0.0;
			            ob[3]=0.0;
			            ob[4]=0.0;
			            
			            generateList.add(ob);
				}
				System.out.println(generateList.size());
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
				 DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
					String deb0 = df1.format(this.getDate_mois_debut());
					String fin0 = df1.format(this.getDate_mois_fin());
					LocalDate date1 = new LocalDate(deb0);
					 LocalDate date2 = new LocalDate(fin0);
					 date2 = date2.plus(Period.months(1));
					 while(date1.isBefore(date2)){
						 Object[] ob = new Object[5];
				            ob[0]=date1.toString("MM/yyyy");
				            ob[1]=0.0;
				            ob[2]=0.0;
				            ob[3]=0.0;
				            ob[4]=0.0;
				            
				            generateList.add(ob);
					     System.out.println(date1.toString("MM/yyyy"));
					     date1 = date1.plus(Period.months(1));
					    
					 }
		        

		       
				DateFormat df = new SimpleDateFormat("MM-YYYY");
				System.out.println(date_Parheure);
				String deb = df.format(this.getDate_mois_debut());
				String fin = df.format(this.getDate_mois_fin());
				
				where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'MM-YYYY') And to_date("+"'"+fin+"'"+",'MM-YYYY')");
				SubTitle ="Entre le mois "+deb+" et le mois "+fin;
			}
			if(this.getChoix_typeDest()!="" && this.getChoix_typeDest()!=null ){
				where_liste.add(" destination.typeDest="+"'"+this.getChoix_typeDest()+"'");
				SubTitle=SubTitle+"avec "+this.getChoix_typeDest()+" comme destination";
			}
			if(this.getChoix_msc()!="" && this.getChoix_msc()!=null){
				where_liste.add(" msc="+"'"+this.getChoix_msc()+"'");
				SubTitle=SubTitle+"avec "+this.getChoix_msc()+"comme msc";
			}
			if(this.getChoix_Voix()!="" && this.getChoix_Voix()!=null){
				where_liste.add(" typeCall="+"'"+this.getChoix_Voix()+"'");
				SubTitle=SubTitle+"avec "+this.getChoix_Voix()+"comme voix";
			}
			
			
		
			where_liste.add(" typeSubscriber='prp'  ");
	
			
		pie1=stat_remote.getMscByFilters1("destination.typeDest", "duree)","StatMsc", "SUM(", "Group By destination.typeDest ", where_liste);
		pie2=stat_remote.getMscByFilters1("msc", "duree)","StatMsc", "SUM(", "Group By msc ", where_liste);
		 if(this.getChoix_periode().equals("Par Heure")){
			 System.out.println(stat_remote.getAllStatMscStatic("substring(trancheHoraire,1,2)", "SUM(moyenneDuree),SUM(duree),SUM(nbMoyenne),SUM(nbAppel)", "", "", where_liste));
			 staticListStatMsc = removeDuplicate(generateList,stat_remote.getAllStatMscStatic("substring(trancheHoraire,1,2)", "SUM(moyenneDuree),SUM(duree),SUM(nbMoyenne),SUM(nbAppel)", "", "", where_liste));
		
	 		 }else if(this.getChoix_periode().equals("Par An")){
	 			 staticListStatMsc = stat_remote.getAllStatMscStatic("Extract (year from to_date(dateAppel,'YYMMDD'))", "SUM(moyenneDuree),SUM(duree),SUM(nbMoyenne),SUM(nbAppel)", "", "", where_liste);
	 			
	 			
		 		 }else if(this.getChoix_periode().equals("Par Mois")){
		 			 staticListStatMsc = removeDuplicate(generateList, stat_remote.getAllStatMscStatic("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", "SUM(moyenneDuree),SUM(duree),SUM(nbMoyenne),SUM(nbAppel)", "", "", where_liste));

		 		 }else if (this.getChoix_periode().equals("Par Jour")){
		 			
		 			 staticListStatMsc = removeDuplicate(generateList, stat_remote.getAllStatMscStatic("to_char(to_date(dateAppel,'YYMMDD'),'YYYY-MM-DD')", "SUM(moyenneDuree),SUM(duree),SUM(nbMoyenne),SUM(nbAppel)", "", "", where_liste));
		 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
		 		
		 		 }
		
		 if(staticListStatMsc!=null){
			 chartDisplayed=true;
		 }else{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "0 Resultats pour Filters choisi " ) );

		 }
			 		     
			 	



			 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 

			
			
			}		
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
	
	public List<String> getListeMSC() {
		return listeMSC;
	}
	public List<String> getListetypeDest() {
		return listetypeDest;
	}
	public List<String> getListeVoix() {
		return listeVoix;
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


public void handlechange2(ValueChangeEvent event){
	System.out.println("File Date: " + date_Parheure);
    System.out.println("Hello... I am in DateChange");
    UIComponent target = event.getComponent().findComponent("form1:ip1");
 
		chartDisplayed=false;
	}
	public void handlechange1(){
		
		chartDisplayed=false;
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



	public Date getDate_mois_debut() {
		return date_mois_debut;
	}
	public void setDate_mois_debut(Date date_mois_debut) {
		this.date_mois_debut = date_mois_debut;
	}
	public Date getDate_mois_fin() {
		return date_mois_fin;
	}
	public void setDate_mois_fin(Date date_mois_fin) {
		this.date_mois_fin = date_mois_fin;
	}
	public Integer getDate_year() {
		return date_year;
	}



	public void setDate_year(Integer date_year) {
		this.date_year = date_year;
	}



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



	public void setListeMSC(List<String> listeMSC) {
		this.listeMSC = listeMSC;
	}
	public void setListetypeDest(List<String> listetypeDest) {
		this.listetypeDest = listetypeDest;
	}
	public void setListeVoix(List<String> listeVoix) {
		this.listeVoix = listeVoix;
	}
	public String getChoix_msc() {
		return choix_msc;
	}
	public void setChoix_msc(String choix_msc) {
		this.choix_msc = choix_msc;
	}
	public String getChoix_typeDest() {
		return choix_typeDest;
	}
	public void setChoix_typeDest(String choix_typeDest) {
		this.choix_typeDest = choix_typeDest;
	}
	
	public String getChoix_Voix() {
		return choix_Voix;
	}
	public void setChoix_Voix(String choix_Voix) {
		this.choix_Voix = choix_Voix;
	}
	
	
	public String getChoix_periode() {
		return choix_periode;
	}
	public void setChoix_periode(String choix_periode) {
		this.choix_periode = choix_periode;
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
	 
	 public static List<DateTime> getDateRange(DateTime start, DateTime end) {

	        List<DateTime> ret = new ArrayList<DateTime>();
	        DateTime tmp = start;
	        while(tmp.isBefore(end) || tmp.equals(end)) {
	            ret.add(tmp);
	            tmp = tmp.plusDays(1);
	        }
	        return ret;
	    }
	 public List<Object[]> removeDuplicate(List <Object[]> list,List<Object[]> list1) {
		 List<Object[]> liste = new ArrayList<>();
		 int i =0;
		 int j=0;
		 if(list1==null){
			 liste=list;
		 }else if(list1.size()==list.size()){
			 
		   while(j<list.size()){
			   
				   if(list.get(j)[0].toString().equals(list1.get(i)[0].toString())){
					   liste.add(list1.get(i));
				   }else{
					   liste.add(list.get(j));
				   }
				   i++;
					  j++;
			   
		   }
		 }else if(list.size()>list1.size()){
			 System.out.println(list1.size());
			if(list.size()>0){
				for(int nb=0;nb<list.size();nb++){
					
					 boolean trouve = false;
					 int nb1 =0;
					   while((trouve==false) && (nb1<list1.size()) ){
						   System.out.println(trouve);
						  
					   if(list.get(nb)[0].toString().equals(list1.get(nb1)[0].toString())){
						 
						   trouve = true;
						 
					   }else{
						   nb1++;
					   }
						   
						  
					   }
					   if(trouve==false){
						   liste.add(list.get(nb));
					   }else{
						   liste.add(list1.get(nb1));
						   System.out.println(list1.get(nb1)[2]);
					   }
					   
					  
				   
			   }
			}else{
				   liste = list;
			   }
			
		 }
		 
		   return liste;
		  }
	 
	 
	 public  void addAjax(Calendar cld_jour_debut){
		
			AjaxBehavior ajax = new AjaxBehavior();
		       // 
		        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
		            createActionMethodExpression("#{staticMscBean.handlechange1}"),createActionMethodExpression("#{staticMscBean.handlechange1}")));
		        ajax.setUpdate("@form");
		       

			      
	         
	        
//			        
	         
		        cld_jour_debut.addClientBehavior("dateSelect", ajax);
		      
	 }


}
