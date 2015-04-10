package com.reporting.mbeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

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
	 private Integer date_mois_Fin;
	 private Integer date_mois_debut;
	 private Integer date_year; 
	 private LineChartModel lineModel1;
	    private LineChartModel lineModel2;
	    private PieChartModel pieModel1;
	    private PieChartModel pieModel2;
	
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


	public PieChartModel getPieModel1() {
		return pieModel1;
	}
	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}
	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}
	public void setLineModel2(LineChartModel lineModel2) {
		this.lineModel2 = lineModel2;
	}
	public PieChartModel getPieModel2() {
		return pieModel2;
	}
public LineChartModel getLineModel1() {
	return lineModel1;
}
public LineChartModel getLineModel2() {
	return lineModel2;
}

	
	@PostConstruct
public void init(){
		lineModel1= new LineChartModel();
		lineModel2= new LineChartModel();
		listeVoix=stat_remote.getTypeCall();
		listetypeDest=typeDest_remote.getAllStringTypeDest();
		listeMSC = noeud_remote.getNoeudCodes();
		listeMSC.add(0, "");
		listetypeDest.add(0,"");
		listeVoix.add(0, "");
		liste_desAns=stat_remote.getAllYears();
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
	    lineModel1 = new LineChartModel();
	}
	
	
	
	public void handlechange(AjaxBehaviorEvent event){
	

	

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
				
					
				ValueExpression valjour = createValueExpression("#{staticMscBean.date_ParJourDeb}", Date.class);
				cld_jour_debut.setValueExpression("value", valjour);
					
					Calendar cld_jour_fin = new Calendar();
				
					cld_jour_fin.setId("cld_jour_fin");
					
				ValueExpression valjourfin = createValueExpression("#{staticMscBean.date_ParJourFin}", Date.class);
				cld_jour_fin.setValueExpression("value", valjourfin);
				
				
					
				
					
					comp.getChildren().add(cld_jour_debut);
					
					comp.getChildren().add(cld_jour_fin);
				
				
				
					
			
			}else if (this.getChoix_periode().equals("Par Heure")){
				Calendar cld_jour = new Calendar();
				
				
				cld_jour.setId("cld_jour");
				
			ValueExpression valjour = createValueExpression("#{staticMscBean.date_Parheure}", Date.class);
			cld_jour.setValueExpression("value", valjour);
			
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
				SelectOneMenu lstDateYearsMois = new SelectOneMenu();
				lstDateYearsMois.setId("lstDateYearsMois");
				 ValueExpression valueExp = createValueExpression("#{staticMscBean.date_year}", Integer.class);
		         UISelectItems list_items0 = new UISelectItems();
		         lstDateYearsMois.setValueExpression("value", valueExp);
		         list_items0.setValue(liste_desAns);
		         lstDateYearsMois.getChildren().add(list_items0);
				
				SelectOneMenu lstMois = new SelectOneMenu();
				lstMois.setId("lstMois");
				 ValueExpression valueExp1 = createValueExpression("#{staticMscBean.date_mois_debut}", Integer.class);
		         UISelectItems list_items = new UISelectItems();
		         lstMois.setValueExpression("value", valueExp1);
		         list_items.setValue(listeMois);
		         lstMois.getChildren().add(list_items);
				
				SelectOneMenu lstMoisFin = new SelectOneMenu();
				lstMoisFin.setId("lstMoisFin");
				 ValueExpression valueExp2 = createValueExpression("#{staticMscBean.date_mois_Fin}", Integer.class);
		         UISelectItems list_items2 = new UISelectItems();
		         lstMoisFin.setValueExpression("value", valueExp2);
		         list_items2.setValue(listeMois);
		         lstMoisFin.getChildren().add(list_items2);
				
				comp.getChildren().add(lstDateYearsMois);
		
				comp.getChildren().add(lstMois);
			
				comp.getChildren().add(lstMoisFin);
			}
			
				
				
			
			
			
			
	}
	public void Valider(){
		where_liste = new ArrayList<String>();
		if(this.getChoix_periode().equals(" ")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
		}
		else{
			if(this.getChoix_periode().equals("Par Jour")){
				
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
					String deb = df.format(this.getDate_ParJourDeb());
					String fin = df.format(this.getDate_ParJourFin());
					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
				
				
				
			}else if(this.getChoix_periode().equals("Par Heure")){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(date_Parheure);
				String deb = df.format(this.getDate_Parheure());
				System.out.println(deb);
				where_liste.add(" to_date(dateAppel,'YYMMDD')= to_date("+"'"+deb+"'"+",'yyyy-MM-dd')");
				
			}else if(this.getChoix_periode().equals("Par An")){
			
				Integer deb = this.getDate_year_deb();
				Integer fin =this.getDate_year_fin();
				where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(year from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
			}else if(this.getChoix_periode().equals("Par Mois")){
				Integer year = this.getDate_year();
				Integer deb = this.getDate_mois_debut();
				Integer fin =this.getDate_mois_Fin();
				System.out.println(year+""+deb+""+fin);
				where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) = "+year+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(month from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
			}
			if(this.getChoix_typeDest()!="" && this.getChoix_typeDest()!=null ){
				where_liste.add(" s.destination.typeDest="+"'"+this.getChoix_typeDest()+"'");
			}
			if(this.getChoix_msc()!="" && this.getChoix_msc()!=null){
				where_liste.add(" msc="+"'"+this.getChoix_msc()+"'");
			}
			if(this.getChoix_Voix()!="" && this.getChoix_Voix()!=null){
				where_liste.add(" typeCall="+"'"+this.getChoix_Voix()+"'");
			}
			
			lineModel1= new LineChartModel();
			lineModel2= new LineChartModel();
			pieModel1= new PieChartModel();
			pieModel2 = new PieChartModel();
		
		LineChartSeries duree = new LineChartSeries();
		LineChartSeries moy_duree = new LineChartSeries();
		LineChartSeries nbAppel = new LineChartSeries();
		LineChartSeries moy_nbAppel = new LineChartSeries();
				
		pieModel1.setData(stat_remote.getMscByFilters1("s.destination.typeDest", "duree)", "SUM(", "Group By s.destination.typeDest ", where_liste));
		pieModel2.setData(stat_remote.getMscByFilters1("msc", "duree)", "SUM(", "Group By msc ", where_liste));
		 if(this.getChoix_periode().equals("Par Heure")){
			
			 moy_duree.setData(stat_remote.getMscBytranche("trancheHoraire", "duree)", "AVG(", "", where_liste));
	 			duree.setData(stat_remote.getMscBytranche("trancheHoraire", "duree)", "SUM(", "", where_liste));
	 			moy_nbAppel.setData(stat_remote.getMscBytranche("trancheHoraire", "nbAppel)", "AVG(", "", where_liste));
	 			nbAppel.setData(stat_remote.getMscBytranche("trancheHoraire", "nbAppel)", "SUM(", "", where_liste));
	 		 }else if(this.getChoix_periode().equals("Par An")){
	 			moy_duree.setData(stat_remote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))", "duree)", "AVG(", "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste));	
	 			duree.setData(stat_remote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))", "duree)", "SUM(", "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste));	
	 			moy_nbAppel.setData(stat_remote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))", "nbAppel)", "AVG(", "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste));	
	 			nbAppel.setData(stat_remote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))", "nbAppel)", "SUM(", "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste));	
	 			
		 		 }else if(this.getChoix_periode().equals("Par Mois")){
			 			moy_duree.setData(stat_remote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))", "duree)", "AVG(", "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste));	

		 			duree.setData(stat_remote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))", "duree)", "SUM(", "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste));	
		 			moy_nbAppel.setData(stat_remote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))", "nbAppel)", "AVG(", "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste));	

		 			nbAppel.setData(stat_remote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))", "nbAppel)", "SUM(", "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste));	
		 			 
		 		 }else if (this.getChoix_periode().equals("Par Jour")){
		 			moy_duree.setData(stat_remote.getMscByFilters("extract(day from  to_date(dateAppel,'YYMMDD'))", "duree)", "AVG(", "Group By  extract(day from  to_date(dateAppel,'YYMMDD'))", where_liste));	
			 			duree.setData(stat_remote.getMscByFilters("extract(day from  to_date(dateAppel,'YYMMDD'))", "duree)", "SUM(", "Group By  extract(day from  to_date(dateAppel,'YYMMDD'))", where_liste));	
			 			moy_nbAppel.setData(stat_remote.getMscByFilters("extract(day from  to_date(dateAppel,'YYMMDD'))", "nbAppel)", "AVG(", "Group By extract(day from  to_date(dateAppel,'YYMMDD'))", where_liste));	
			 			nbAppel.setData(stat_remote.getMscByFilters("extract(day from  to_date(dateAppel,'YYMMDD'))", "nbAppel)", "SUM(", "Group By  extract(day from  to_date(dateAppel,'YYMMDD')) ", where_liste));	

		 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
		 		 }
		 
		
		
			 			 lineModel1.addSeries(duree);
			 			 lineModel1.addSeries(moy_duree);
			 			 lineModel2.addSeries(moy_nbAppel);
			 			 lineModel2.addSeries(nbAppel);
			 			  lineModel1.setTitle("Linear Chart");
			 		        lineModel1.setLegendPosition("e");
			 		       Axis xAxis = new CategoryAxis("Date");
			 			   xAxis.setTickAngle(-50);
			 			       lineModel1.getAxes().put(AxisType.X, xAxis);
			 			      Axis  yAxis =  lineModel1.getAxis(AxisType.Y);
			 			      yAxis.setMin(0);
			 			      lineModel1.setMouseoverHighlight(true);
			 			      //yAxis.setLabel(rapport.getLst_chart().get(i).getAxe_y());
			 			     lineModel1.setAnimate(true);
			 			   
			 		      
			 		       lineModel2.setTitle("Category Chart");
			 		        lineModel2.setLegendPosition("e");
			 		        lineModel2.setShowPointLabels(true);
			 		        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
			 		        yAxis = lineModel2.getAxis(AxisType.Y);
			 		        yAxis.setLabel("Births");
			 		       
			 		PanelGrid col = (PanelGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ColLineChart");
			 		
			 		System.out.println(col.getChildCount());
			 		col.getChildren().clear();
			 		PanelGrid col1 = (PanelGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ColLineChart1");
col1.getChildren().clear();


pieModel1.setTitle("R. Traffic par Destination(Durée) ");
pieModel1.setLegendPosition("e");
pieModel1.setShowDataLabels(true);
//lineModel3.setTitle(chd.getAxe_y()+" Par  "+chd.getAxe_x());
pieModel1.setFill(true);
pieModel1.setShadow(true);
pieModel1.setMouseoverHighlight(true);
pieModel2.setTitle("R. Traffic par MSC(Durée) ");
pieModel2.setLegendPosition("e");
pieModel2.setShowDataLabels(true);
//lineModel3.setTitle(chd.getAxe_y()+" Par  "+chd.getAxe_x());
pieModel2.setFill(true);
pieModel2.setShadow(true);
pieModel2.setMouseoverHighlight(true);

Chart ch2 = new Chart();
ch2.setType("pie");
ch2.setModel(pieModel1);
ch2.setStyle("width:200px;height:200px;");
Chart ch3 = new Chart();
ch3.setType("pie");
ch3.setModel(pieModel2);
ch3.setStyle("width:200px;height:200px;");
			 		Chart ch = new Chart();
ch.setStyle("width:600px;height:200px;");
ch.setType("line");
ch.setModel(lineModel1);
col.getChildren().add(ch);
col.getChildren().add(ch2);
Chart ch1 = new Chart();
ch1.setType("line");
ch1.setModel(lineModel2);
ch1.setStyle("width:600px;height:200px;");
col1.getChildren().add(ch1);
col1.getChildren().add(ch3);
RequestContext.getCurrentInstance().update("form1:ColLineChart");
RequestContext.getCurrentInstance().update("form1:ColLineChart1");
System.out.println(col.getChildCount());
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

}
