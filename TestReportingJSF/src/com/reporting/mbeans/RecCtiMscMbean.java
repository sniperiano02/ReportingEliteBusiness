package com.reporting.mbeans;

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
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.ListUtils;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.reporting.metier.interfaces.LocalInRemote;
import com.reporting.metier.interfaces.StatReconCtiMscRemote;
import com.reporting.metier.interfaces.StatReconRemote;


@ManagedBean(name="rec_cti_msc")
@ViewScoped
public class RecCtiMscMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	@EJB
	private StatReconCtiMscRemote statRecon;
	
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

private HighChart lineChart1 ;

private HighChart lineChartDetails;


public HighChart getLineChartDetails() {
	return lineChartDetails;
}
public void setLineChartDetails(HighChart lineChartDetails) {
	this.lineChartDetails = lineChartDetails;
}

private HighChart pieChartDetails;

public HighChart getPieChartDetails() {
	return pieChartDetails;
}
public void setPieChartDetails(HighChart pieChartDetails) {
	this.pieChartDetails = pieChartDetails;
}

private boolean DetailDisplayed = false;

public boolean isDetailDisplayed() {
	return DetailDisplayed;
}
public void setDetailDisplayed(boolean detailDisplayed) {
	DetailDisplayed = detailDisplayed;
}


public HighChart getLineChart1() {
	return lineChart1;
}
public void setLineChart1(HighChart lineChart1) {
	this.lineChart1 = lineChart1;
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
	 private Date date_Parheure;
	 private Date date_ParJourDeb;
	 private Date date_ParJourFin;
	 private Integer date_year_deb;
	 private Integer date_year_fin;
	 private Integer date_mois_Fin;
	 private Integer date_mois_debut;
	 private Integer date_year; 
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
	public Integer getDate_mois_Fin() {
		return date_mois_Fin;
	}
	public void setDate_mois_Fin(Integer date_mois_Fin) {
		this.date_mois_Fin = date_mois_Fin;
	}
	public Integer getDate_mois_debut() {
		return date_mois_debut;
	}
	public void setDate_mois_debut(Integer date_mois_debut) {
		this.date_mois_debut = date_mois_debut;
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
		    lineChartDetails = new HighChart();
	}
	
	public void clicked() {
		choixSelect=false;  
	    FacesMessage m = new FacesMessage("You clicked " + selectedPoint );
        FacesContext.getCurrentInstance().addMessage("", m);  
		System.out.println(selectedPoint);
		System.out.println(selectedRecon[0].toString());
		lineChartDetails = new HighChart();
		
		SerieChart sc1 = new SerieChart();
		SerieChart sc2 = new SerieChart();
		
	
	
		String yDetail=null;
		String where = null;
//		String deb = df.format(selectedRecon.toString());
//		System.out.println(deb);
	List<Object[]> result1 = new ArrayList<Object[]>();
		
	
		if(this.getChoix_periode().equals("Par Jour")){
			
			
			
		
			where="  to_date(dateAppel,'YYMMDD') = to_date("+"'"+selectedRecon[0].toString()+"'"+",'yyyy-MM-dd') ";
		
			
			xDetail="CAST(trancheHoraire AS integer)  ";
			yDetail = ",SUM(dureeAppelMsc),SUM(dureeAppelMsc)";
			///resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconMscIn("to_date(dateAppel,'YYMMDD')", where);
			Subtitle1="Par tranche Horaire"+selectedRecon[0].toString();
			for(int i=0;i<24;i++){
				Object[] obj = new Object[3];
				obj[0]=i;
				obj[1]=0;
				obj[2]=0;
						result1.add(obj);
			}
		}else if(this.getChoix_periode().equals("Par An")){
	
		
		
		
		 where =" Extract(year from to_date(dateAppel,'YYMMDD')) = "+Integer.valueOf(selectedRecon[0].toString())+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= 01 And Extract(month from to_date(dateAppel,'YYMMDD')) <= 12";
		xDetail="Extract(month from to_date(dateAppel,'YYMMDD')) ";
		yDetail = ",SUM(dureeAppelMsc),SUM(dureeAppelMsc)";
		//resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconMscIn(xDetail, where);
		
		Subtitle1="Par Mois en "+selectedRecon[0].toString();
		for(int i=1;i<=12;i++){
			Object[] obj = new Object[3];
			obj[0]=i;
			obj[1]=0;
			obj[2]=0;
					result1.add(obj);
		}
	}else if(this.getChoix_periode().equals("Par Mois")){
		Integer year = this.getDate_year();
		Integer deb = this.getDate_mois_debut();
		Integer fin =this.getDate_mois_Fin();
		System.out.println(year+""+deb+""+fin);
		Integer mois = Integer.valueOf(selectedRecon[0].toString());
 where="Extract(year from to_date(dateAppel,'YYMMDD')) ="+year+" And Extract(month from to_date(dateAppel,'YYMMDD')) ="+mois+" And codeMsc='"+selectedPoint+"'";
xDetail="Extract(day from to_date(dateAppel,'YYMMDD'))  ";
yDetail = ",SUM(dureeAppelMsc),SUM(dureeAppelMsc)";
//resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconMscIn(xDetail, where);
Subtitle1="Par Jour en "+selectedRecon[0].toString();
for(int i=1;i<=31;i++){
	Object[] obj = new Object[3];
	obj[0]=i;
	obj[1]=0;
	obj[2]=0;
			result1.add(obj);
}
	}
		
		List<Object[]> Details = statRecon.getDetailsStatReconCtiMsc(xDetail,yDetail, where);
	
		List<Object[]> res = ListUtils.union(result1, Details);
		System.out.println(res.size());
	
		
		
		sc1.setMap(Details);
		sc2.setMap(Details);
		lineChartDetails.setName("Par "+selectedPoint);
		sc1.setName(" Duree IN");
		sc2.setName("Duree KPI");
		
		System.out.println(sc1.getMap().isEmpty());
		List<SerieChart> series = new ArrayList<>();
		series.add(sc1);
		series.add(sc2);
		//List<SerieChart> series1 = new ArrayList<>();
		
		lineChartDetails.setSeries(series);
		
		
		//System.out.println(lineChartDetails.getSeries().get(0).getName()+":"+lineChartDetails.getSeries().get(0).getMap().entrySet().size());
		choixSelect=true;       
    }
	
	
	
	public void handlechange(AjaxBehaviorEvent event){
		chartDisplayed= false;

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
			UIComponent compSLMois =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:lstMois");
			UIComponent compSLMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:lstMoisFin");
		
			if(compJourOutput0!=null){
				
				comp.getChildren().remove(compJourOutput0);
				comp.getChildren().remove(compJourOutput1);
			}
			if(compSLMois!=null){
				comp.getChildren().remove(compSLYearMois);
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
				
					
				ValueExpression valjour = createValueExpression("#{rec_cti_msc.date_ParJourDeb}", Date.class);
				cld_jour_debut.setValueExpression("value", valjour);
					
					Calendar cld_jour_fin = new Calendar();
				
					cld_jour_fin.setId("cld_jour_fin");
					
				ValueExpression valjourfin = createValueExpression("#{rec_cti_msc.date_ParJourFin}", Date.class);
				cld_jour_fin.setValueExpression("value", valjourfin);
				
				
					
				
					
					comp.getChildren().add(cld_jour_debut);
					
					comp.getChildren().add(cld_jour_fin);
				
				
				
					
			
			}else if (this.getChoix_periode().equals("Par Heure")){
				Calendar cld_jour = new Calendar();
				
				
				cld_jour.setId("cld_jour");
				
			ValueExpression valjour = createValueExpression("#{rec_cti_msc.date_Parheure}", Date.class);
			cld_jour.setValueExpression("value", valjour);
			
				comp.getChildren().add(cld_jour);
			}else if (this.getChoix_periode().equals("Par An")){
				SelectOneMenu lstDateYears = new SelectOneMenu();
				lstDateYears.setId("lstDateYears");
				 ValueExpression valueExp = createValueExpression("#{rec_cti_msc.date_year_deb}", Integer.class);
		         UISelectItems list_items0 = new UISelectItems();
				 lstDateYears.setValueExpression("value", valueExp);
		         list_items0.setValue(liste_desAns);
		         lstDateYears.getChildren().add(list_items0);
				
				SelectOneMenu lstDateYearsFin = new SelectOneMenu();
				lstDateYearsFin.setId("lstDateYearsFin");
				 ValueExpression valueExp1 = createValueExpression("#{rec_cti_msc.date_year_fin}", Integer.class);
		         UISelectItems list_items = new UISelectItems();
				 lstDateYearsFin.setValueExpression("value", valueExp1);
		         list_items.setValue(liste_desAns);
		         lstDateYearsFin.getChildren().add(list_items);
			
		
			comp.getChildren().add(lstDateYears);
			
			comp.getChildren().add(lstDateYearsFin);
			}else if (this.getChoix_periode().equals("Par Mois")){
				SelectOneMenu lstDateYearsMois = new SelectOneMenu();
				lstDateYearsMois.setId("lstDateYearsMois");
				 ValueExpression valueExp = createValueExpression("#{rec_cti_msc.date_year}", Integer.class);
		         UISelectItems list_items0 = new UISelectItems();
		         lstDateYearsMois.setValueExpression("value", valueExp);
		         list_items0.setValue(liste_desAns);
		         lstDateYearsMois.getChildren().add(list_items0);
				
				SelectOneMenu lstMois = new SelectOneMenu();
				lstMois.setId("lstMois");
				 ValueExpression valueExp1 = createValueExpression("#{rec_cti_msc.date_mois_debut}", Integer.class);
		         UISelectItems list_items = new UISelectItems();
		         lstMois.setValueExpression("value", valueExp1);
		         list_items.setValue(listeMois);
		         lstMois.getChildren().add(list_items);
				
				SelectOneMenu lstMoisFin = new SelectOneMenu();
				lstMoisFin.setId("lstMoisFin");
				 ValueExpression valueExp2 = createValueExpression("#{rec_cti_msc.date_mois_Fin}", Integer.class);
		         UISelectItems list_items2 = new UISelectItems();
		         lstMoisFin.setValueExpression("value", valueExp2);
		         list_items2.setValue(listeMois);
		         lstMoisFin.getChildren().add(list_items2);
				
				comp.getChildren().add(lstDateYearsMois);
		
				comp.getChildren().add(lstMois);
			
				comp.getChildren().add(lstMoisFin);
			}
			lineChart1= new HighChart();
			
		
			
			
	}
	
	public void Valider(){
		
		where_liste = new ArrayList<String>();
		if(this.getChoix_periode().equals(" ")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
		}
		else{
			where_liste.add(" ");
			List<Object[]> result1 = new ArrayList<Object[]>();
			
			if(this.getChoix_periode().equals("Par Jour")){
				
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
					
					String deb = df.format(this.getDate_ParJourDeb());
					String fin = df.format(this.getDate_ParJourFin());
					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
					String where="  to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')";
					resultCDRInexistant= statRecon.getAllStatReconCtiMsc("to_char(to_date(dateAppel,'YYMMDD'),'YYYY-MM-DD')",where);
				
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
				resultCDRInexistant= statRecon.getAllStatReconCtiMsc("Extract (year from to_date(dateAppel,'YYMMDD'))",where);
				
			}else if(this.getChoix_periode().equals("Par Mois")){
				Integer year = this.getDate_year();
				Integer deb = this.getDate_mois_debut();
				Integer fin =this.getDate_mois_Fin();
				System.out.println(year+""+deb+""+fin);
				where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) = "+year+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(month from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
		String where="  Extract(year from to_date(dateAppel,'YYMMDD')) = "+year+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(month from to_date(dateAppel,'YYMMDD')) <= "+fin+"";
		resultCDRInexistant= statRecon.getAllStatReconCtiMsc("Extract (month from to_date(dateAppel,'YYMMDD'))",where);
		SubTitle ="Periode entre au "+year+"entre les mois"+deb+" et "+fin;
		
			}
		//String kfdf=	(String) resultCDRInexistant.get(0)[0].t;
			lineChart1 = new HighChart();
			SerieChart sc1 = new SerieChart();
			SerieChart sc2 = new SerieChart();
			SerieChart sc3 = new SerieChart();
		
			 Map<Object,Number> map = new HashMap<>();
 			 Map<Object,Number> map1= new HashMap<>();
 			 Map<Object,Number> map2 = new HashMap<>();
 			List<Object[]>  result = new ArrayList<>();
			//pie1=statRecon.getAllStatRecon1("s.destination.typeDest", "duree)", "SUM(", "Group By s.destination.typeDest ", where_liste);
		 if(this.getChoix_periode().equals("Par An")){
			
			 result = statRecon.getAllStatReconCDRIN("Extract (year from to_date(dateAppel,'YYMMDD'))", "SUM(dureeAppelCti),SUM(dureeAppelMsc),SUM(dureeRoaOut)", where_liste);
				
 			 System.out.println(result.size());
			
			
		 		 }else if(this.getChoix_periode().equals("Par Mois")){
		 		
		 			 
		 			result = statRecon.getAllStatReconCDRIN("Extract (month from to_date(dateAppel,'YYMMDD'))", "SUM(dureeAppelCti),SUM(dureeAppelMsc),SUM(dureeRoaOut)", where_liste);
					
					 
		 		 }
		 
		 sc1.setMap(result);
			sc2.setMap(result);
			sc3.setMap(result);
		 sc1.setName("Duree Cti ");
		 sc2.setName("Duree MSC");
		 sc3.setName("Duree Roa Out");
		
		List<SerieChart> series= new ArrayList<>();
		series.add(sc1);
		series.add(sc2);
		
		series.add(sc3);
		
		lineChart1.setSeries(series);
	
			 			
			 		     
			 	



			 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
chartDisplayed=true;
				
				
			
			
			
		}
			
	}
	
	
	public void getDetail(){
		choixSelect=false;
		DetailDisplayed=true;
		System.out.println(selectedRecon[0].toString());
		
		pieChartDetails= new HighChart();
	
		SerieChart spie = new SerieChart();
		
		
		String yDetail=null;
		String where = null;
//		String deb = df.format(selectedRecon.toString());
//		System.out.println(deb);
	List<Object[]> result1 = new ArrayList<Object[]>();
		
	
		if(this.getChoix_periode().equals("Par Jour")){
			
			
			
		
			where=" to_date(dateAppel,'YYMMDD') = to_date("+"'"+selectedRecon[0].toString()+"'"+",'yyyy-MM-dd') ";
		
			
			xDetail="CAST(trancheHoraire AS integer)  ";
			yDetail = ",SUM(dureeTotalIn),SUM(kpiDuree)";
			resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconCtiMsc("to_date(dateAppel,'YYMMDD')", where);
			Subtitle1="Par tranche Horaire"+selectedRecon[0].toString();
		
		}else if(this.getChoix_periode().equals("Par An")){
	
		
		
		
		 where =" Extract(year from to_date(dateAppel,'YYMMDD')) = "+Integer.valueOf(selectedRecon[0].toString())+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= 01 And Extract(month from to_date(dateAppel,'YYMMDD')) <= 12";
		xDetail="Extract(month from to_date(dateAppel,'YYMMDD')) ";
		yDetail = ",SUM(dureeAppelMsc),SUM(dureeAppelMsc)";
		resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconCtiMsc(xDetail, where);
		
		Subtitle1="Par Mois en "+selectedRecon[0].toString();
		
	}else if(this.getChoix_periode().equals("Par Mois")){
		Integer year = this.getDate_year();
		Integer deb = this.getDate_mois_debut();
		Integer fin =this.getDate_mois_Fin();
		System.out.println(year+""+deb+""+fin);
		Integer mois = Integer.valueOf(selectedRecon[0].toString());
 where=" Extract(year from to_date(dateAppel,'YYMMDD')) ="+year+" And Extract(month from to_date(dateAppel,'YYMMDD')) ="+mois;
xDetail="Extract(day from to_date(dateAppel,'YYMMDD'))  ";
yDetail = ",SUM(dureeAppelMsc),SUM(dureeAppelMsc)";
resultCDRDetailsInexistant = statRecon.getDetailsDestinationStatReconCtiMsc(xDetail, where);
Subtitle1="Par Jour en "+selectedRecon[0].toString();
for(int i=1;i<=31;i++){
	Object[] obj = new Object[3];
	obj[0]=i;
	obj[1]=0;
	obj[2]=0;
			result1.add(obj);
}
	}
		
		List<Object[]> Details = statRecon.getDetailsStatReconCtiMsc(xDetail,yDetail, where);
	
		List<Object[]> res = ListUtils.union(result1, Details);
		System.out.println(res.size());
		List<Object[]> DetailsMSC = statRecon.getDetailsStatReconCtiMsc("codeMsc",yDetail, where);
		
		
	spie.setMap(DetailsMSC);
		
		spie.setName("Repartition Par MSc");
		//System.out.println(sc1.getMap().isEmpty());
		List<SerieChart> series = new ArrayList<>();
		
		List<SerieChart> series1 = new ArrayList<>();
		series1.add(spie);
		
		pieChartDetails.setSeries(series1);
		pieChartDetails.setName("Repartition par MSC");
		
		//System.out.println(lineChartDetails.getSeries().get(0).getName()+":"+lineChartDetails.getSeries().get(0).getMap().entrySet().size());
		
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
