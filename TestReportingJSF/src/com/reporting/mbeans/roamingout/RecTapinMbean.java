package com.reporting.mbeans.roamingout;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;

import org.apache.commons.collections.ListUtils;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.reporting.metier.interfaces.LocalInRemote;
import com.reporting.metier.interfaces.StatReconCtiMscNatRemote;
import com.reporting.metier.interfaces.StatReconTapinRemote;




@ManagedBean(name="rec_tapin")
@ViewScoped
public class RecTapinMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	@EJB
	private StatReconTapinRemote statRecon;
	
	private String selectedPoint;

	public String getSelectedPoint() {
		return selectedPoint;
	}
	public void setSelectedPoint(String selectedPoint) {
		this.selectedPoint = selectedPoint;
	}
	private boolean choixSelect = false;
	
	public boolean isChoixSelect() {
		return choixSelect;
	}
	
	public void setChoixSelect(boolean choixSelect) {
		this.choixSelect = choixSelect;
	}
	
	
	private String choix_call;
	
	private String Subtitle1;
	
	public String getSubtitle1() {
		return Subtitle1;
	}
	public void setSubtitle1(String subtitle1) {
		Subtitle1 = subtitle1;
	}
	
private String choix_periode;
	
	
private boolean chartDisplayed =false;

public boolean isChartDisplayed() {
	return chartDisplayed;
}
public void setChartDisplayed(boolean chartDisplayed) {
	this.chartDisplayed = chartDisplayed;
}






private boolean DetailDisplayed = false;

public boolean isDetailDisplayed() {
	return DetailDisplayed;
}
public void setDetailDisplayed(boolean detailDisplayed) {
	DetailDisplayed = detailDisplayed;
}

private Object[] selectedRecon;


public Object[] getSelectedRecon() {
	return selectedRecon;
}
public void setSelectedRecon(Object[] selectedRecon) {
	this.selectedRecon = selectedRecon;
}
private String SubTitle;

public String getSubTitle() {
	return SubTitle;
}
public void setSubTitle(String subTitle) {
	SubTitle = subTitle;
}

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
	public List<Integer> getListeMois() {
		return listeMois;
	}
	
	private String xDetail;
	
	public String getxDetail() {
		return xDetail;
	}
	public void setxDetail(String xDetail) {
		this.xDetail = xDetail;
	}
	private List<Object[]> Details = new ArrayList<>();
	 private Date date_Parheure;
	 private Date date_ParJourDeb;
	 private Date date_ParJourFin;
	 private Integer date_year_deb;
	 private Integer date_year_fin;
	 private Date date_mois_fin;
	 private Date date_mois_debut;
	 private Integer date_year; 
	 
	 
	 
	 public List<Object[]> getDetails() {
		return Details;
	}
	 public void setDetails(List<Object[]> details) {
		Details = details;
	}
		private List<String> where_liste ;

		public List<String> getWhere_liste() {
			return where_liste;
		}
		public void setWhere_liste(List<String> where_liste) {
			this.where_liste = where_liste;
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
public Date getDate_mois_debut() {
	return date_mois_debut;
}
public void setDate_mois_debut(Date date_mois_debut) {
	this.date_mois_debut = date_mois_debut;
}public Date getDate_mois_fin() {
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
	private List<Object[]> resultCDRInexistant;
	private List<Object[]> resultCDRDetailsInexistant;
	
	public List<Object[]> getResultCDRDetailsInexistant() {
		return resultCDRDetailsInexistant;
	}
	public void setResultCDRDetailsInexistant(
			List<Object[]> resultCDRDetailsInexistant) {
		this.resultCDRDetailsInexistant = resultCDRDetailsInexistant;
	}
	
	public List<Object[]> getResultCDRInexistant() {
		return resultCDRInexistant;
	}
	public void setResultCDRInexistant(List<Object[]> resultCDRInexistant) {
		this.resultCDRInexistant = resultCDRInexistant;
	}
	@PostConstruct
	public void init(){
			resultCDRInexistant= new ArrayList<>();
			resultCDRDetailsInexistant = new ArrayList<>();
			liste_desAns=statRecon.getAllYears();
		    listeMois.add(1);
		    listeMois.add(2);
		    listeMois.add(3);
		    listeMois.add(4);
		    listeMois.add(5);
		    listeMois.add(6);
		    listeMois.add(7);
		    listeMois.add(8);
		    listeMois.add(9);
		    listeMois.add(10);
		    listeMois.add(11);
		    listeMois.add(12);
		
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
						
							
						ValueExpression valjour = createValueExpression("#{rec_tapin.date_ParJourDeb}", Date.class);
						cld_jour_debut.setValueExpression("value", valjour);
							
							Calendar cld_jour_fin = new Calendar();
						
							cld_jour_fin.setId("cld_jour_fin");
							
						ValueExpression valjourfin = createValueExpression("#{rec_tapin.date_ParJourFin}", Date.class);
						cld_jour_fin.setValueExpression("value", valjourfin);
						
						
							
						
							
							comp.getChildren().add(cld_jour_debut);
							
							comp.getChildren().add(cld_jour_fin);
						
						
						
							
					
					}else if (this.getChoix_periode().equals("Par Heure")){
						Calendar cld_jour = new Calendar();
						
						
						cld_jour.setId("cld_jour");
						
					ValueExpression valjour = createValueExpression("#{rec_tapin.date_Parheure}", Date.class);
					cld_jour.setValueExpression("value", valjour);
					AjaxBehavior ajax = new AjaxBehavior();
				       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
				        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
				            createActionMethodExpression("#{rec_tapin.handlechange1}"),createActionMethodExpression("#{rec_tapin.handlechange1}")));
				        ajax.setUpdate("@form");
				       

					      
			         
			        
//					        
			         
				         cld_jour.addClientBehavior("dateSelect", ajax);
				         FacesContext fc = FacesContext.getCurrentInstance();
				         ExpressionFactory ef = fc.getApplication().getExpressionFactory();

				         MethodExpression me = ef.createMethodExpression(fc.getELContext(), "#{rec_tapin.handlechange2}", null, new Class<?>[]{BehaviorEvent.class});
				         AjaxBehavior ajaxBehavior = (AjaxBehavior) fc.getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
				         ajaxBehavior.setProcess("@this");
				      
				         ajaxBehavior.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(me, me));
				         cld_jour.addClientBehavior("change", ajaxBehavior);
						comp.getChildren().add(cld_jour);
					}else if (this.getChoix_periode().equals("Par An")){
						SelectOneMenu lstDateYears = new SelectOneMenu();
						lstDateYears.setId("lstDateYears");
						 ValueExpression valueExp = createValueExpression("#{rec_tapin.date_year_deb}", Integer.class);
				         UISelectItems list_items0 = new UISelectItems();
						 lstDateYears.setValueExpression("value", valueExp);
				         list_items0.setValue(liste_desAns);
				         lstDateYears.getChildren().add(list_items0);
						
						SelectOneMenu lstDateYearsFin = new SelectOneMenu();
						lstDateYearsFin.setId("lstDateYearsFin");
						 ValueExpression valueExp1 = createValueExpression("#{rec_tapin.date_year_fin}", Integer.class);
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
					
						
					ValueExpression valjour = createValueExpression("#{rec_tapin.date_mois_debut}", Date.class);
					cld_mois_debut.setValueExpression("value", valjour);
					cld_mois_debut.setPattern("MM/yyyy");
					cld_mois_debut.setMask("true");
				
						Calendar cld_mois_fin = new Calendar();
					
						cld_mois_fin.setId("cld_mois_fin");
						cld_mois_fin.setPattern("MM/yyyy");
						cld_mois_fin.setMask("true");
					ValueExpression valjourfin = createValueExpression("#{rec_tapin.date_mois_fin}", Date.class);
					cld_mois_fin.setValueExpression("value", valjourfin);
					
					
						
					
						
						comp.getChildren().add(cld_mois_debut);
						
						comp.getChildren().add(cld_mois_fin);
					
					
					
					}
					
						
						
					
					
					
					
			}
	
	public void Valider(){
		
		where_liste = new ArrayList<String>();
		if(this.getChoix_periode().equals(" ")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
		}
		else{
		
			List<Object[]> result1 = new ArrayList<Object[]>();
			
			if(this.getChoix_periode().equals("Par Jour")){
				
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
					
					String deb = df.format(this.getDate_ParJourDeb());
					String fin = df.format(this.getDate_ParJourFin());
					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
					String where="  to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')";
					resultCDRInexistant= statRecon.getAllStatReconTapin("to_char(to_date(dateAppel,'YYMMDD'),'YYYY-MM-DD')",where);
				
					SubTitle ="Periode entre "+deb+" et "+fin;
					List<Date> dates = new ArrayList<Date>(25);
					java.util.Calendar cal = java.util.Calendar.getInstance();
					cal.setTime(date_ParJourDeb);
					while (cal.getTime().before(date_ParJourFin)) {
					    cal.add(java.util.Calendar.DATE, 1);
					    dates.add(cal.getTime());
					}
				
					
			}else if(this.getChoix_periode().equals("Par An")){
			
				Integer deb = this.getDate_year_deb();
				Integer fin =this.getDate_year_fin();
				where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(year from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
				String where ="Extract(year from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(year from to_date(dateAppel,'YYMMDD')) <= "+fin;
				SubTitle ="Par An entre "+deb+" et "+fin;
				resultCDRInexistant= statRecon.getAllStatReconTapin("Extract (year from to_date(dateAppel,'YYMMDD'))",where);
				
			}else if(this.getChoix_periode().equals("Par Mois")){
				DateFormat df = new SimpleDateFormat("MM-YYYY");
				System.out.println(date_Parheure);
				String deb = df.format(this.getDate_mois_debut());
				String fin = df.format(this.getDate_mois_fin());
				where_liste.add("to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'MM-YYYY') And to_date("+"'"+fin+"'"+",'MM-YYYY')");
		String where="  to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'MM-YYYY') And to_date("+"'"+fin+"'"+",'MM-YYYY')";
		resultCDRInexistant= statRecon.getAllStatReconTapin("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')",where);
		SubTitle ="Periode  entre les mois"+deb+" et "+fin;
		
			}
		//String kfdf=	(String) resultCDRInexistant.get(0)[0].t;
			
			if(this.getChoix_call()!="" && this.getChoix_call()!=null){
					where_liste.add(" typeCall like "+"'"+this.getChoix_call()+"'");
			} 		     
			 	



			 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
chartDisplayed=true;
				
				
			
			
			
		}
			
	}
	
	
	public void getDetail(){
	
		DetailDisplayed=true;
		System.out.println(selectedRecon[0].toString());
		
		
	
	
		
		String yDetail=null;
		String where = null;
//		String deb = df.format(selectedRecon.toString());
//		System.out.println(deb);
	List<Object[]> result1 = new ArrayList<Object[]>();
		
	
		if(this.getChoix_periode().equals("Par Jour")){
			
			
			
		
			where=" to_date(dateAppel,'YYMMDD') = to_date("+"'"+selectedRecon[0].toString()+"'"+",'yyyy-MM-dd') ";
		
			
			xDetail="CAST(trancheHoraire AS integer)  ";
			yDetail = ",SUM(nbPrepaye),SUM(dureePrepaye),SUM(revenuePrepaye),SUM(nbTapin),SUM(dureeTapin),SUM(charge),SUM(nbPop),SUM(dureePop),SUm(revenuePop)";
			resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconTapin("to_date(dateAppel,'YYMMDD')", where);
			Subtitle1="Par tranche Horaire"+selectedRecon[0].toString();
		
		}else if(this.getChoix_periode().equals("Par An")){
	
		
		
		
		 where =" Extract(year from to_date(dateAppel,'YYMMDD')) = "+Integer.valueOf(selectedRecon[0].toString())+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= 01 And Extract(month from to_date(dateAppel,'YYMMDD')) <= 12";
		xDetail="Extract(month from to_date(dateAppel,'YYMMDD')) ";
		yDetail = ",SUM(nbPrepaye),SUM(dureePrepaye),SUM(revenuePrepaye),SUM(nbTapin),SUM(dureeTapin),SUM(charge),SUM(nbPop),SUM(dureePop),SUm(revenuePop)";
		resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconTapin(xDetail, where);
		
		Subtitle1="Par Mois en "+selectedRecon[0].toString();
		
	}else if(this.getChoix_periode().equals("Par Mois")){
		DateFormat df = new SimpleDateFormat("MM-YYYY");
		System.out.println(date_Parheure);
		String deb = df.format(this.getDate_mois_debut());
		String fin = df.format(this.getDate_mois_fin());
		String mois = "01/"+selectedRecon[0].toString();
		String mois1 = "31/"+selectedRecon[0].toString();
 where="to_date(dateAppel,'YYMMDD') BETWEEN to_date("+"'"+mois+"'"+",'DD/MM/YYYY') AND to_date("+"'"+mois1+"'"+",'DD/MM/YYYY') ";
xDetail="Extract(day from to_date(dateAppel,'YYMMDD'))  ";
yDetail = ",SUM(nbPrepaye),SUM(dureePrepaye),SUM(revenuePrepaye),SUM(nbTapin),SUM(dureeTapin),SUM(charge),SUM(nbPop),SUM(dureePop),SUm(revenuePop)";
resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconTapin(xDetail, where);
Subtitle1="Par Jour en "+selectedRecon[0].toString();

	}
		
		if(this.getChoix_call()!="" && this.getChoix_call()!=null){
				where_liste.add(" typeStat like "+"'"+this.getChoix_call()+"'");
			}
		Details = statRecon.getDetailsStatReconTapin(xDetail,yDetail, where);
	
		
		
		
		//System.out.println(lineChartDetails.getSeries().get(0).getName()+":"+lineChartDetails.getSeries().get(0).getMap().entrySet().size());
		
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
	public String getChoix_call() {
		return choix_call;
	}
	public void setChoix_call(String choix_call) {
		this.choix_call = choix_call;
	}


	

}
