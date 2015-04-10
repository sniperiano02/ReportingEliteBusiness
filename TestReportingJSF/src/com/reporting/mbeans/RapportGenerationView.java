package com.reporting.mbeans;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;










import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.persistence.PostLoad;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.blockui.BlockUI;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.chart.Chart;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.filedownload.FileDownloadActionListener;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.layout.Layout;
import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.progressbar.ProgressBar;
import org.primefaces.component.selectmanymenu.SelectManyMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.separator.Separator;
import org.primefaces.component.slider.Slider;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.ApplicationContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.reporting.metier.entities.ChartDynamic;
import com.reporting.metier.entities.Datagrid;
import com.reporting.metier.entities.Noeud;
import com.reporting.metier.entities.Report;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.NoeudRemote;
import com.reporting.metier.interfaces.ReportRemote;
import com.reporting.metier.interfaces.StatMscImplRemote;
import com.reporting.metier.interfaces.TypeDestinationRemote;
import com.sun.faces.taglib.jsf_core.SetPropertyActionListenerImpl;


@ManagedBean(name="rp_gene")
@ViewScoped
public class RapportGenerationView implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -557072870602513676L;
	/**
	 * 
	 */
	
	
	
	@EJB
	private StatMscImplRemote statRemote;
	@EJB
	private TypeDestinationRemote typdestRemote;
	@EJB
	private ReportRemote reportremote;
	
	@EJB
	private NoeudRemote noeudremote;

	
	 private UIForm form;

	 private List<String> listeTypeDest;
	 
	 private List<Integer> listeMois = new ArrayList<Integer>();
	 private Date date_Parheure;
	 private Date date_ParJourDeb;
	 private Date date_ParJourFin;
	 private Integer date_year_deb;
	 private Integer date_year_fin;
	 private Integer date_mois_Fin;
	 private Integer date_mois_debut;
	 private Report rapport;
	 private Integer date_year;
	private List<String> listTranche = new ArrayList<String>();
	private List<String> listeTypeCall = new ArrayList<String>();
	private List<String> listeTypeSubscriber = new ArrayList<String>(); 
	private String TypeCall;
	private List<String> listePeriode = new ArrayList<String>();
	private String tranche_horaire;
	private String TypeSubscriber;
	private BigDecimal duree_filter;
	private List<Noeud> liste_noeuds;
	private List<String> liste_noeuds_noms;
	private List<Integer> liste_desAns = new ArrayList<Integer>();
	
	private String typeDestFilter;
	
public Report getRapport() {
	return rapport;
}
public void setRapport(Report rapport) {
	this.rapport = rapport;
}
	public String getTranche_horaire() {
		return tranche_horaire;
	}
	public void setTranche_horaire(String tranche_horaire) {
		this.tranche_horaire = tranche_horaire;
	}
	
	public List<String> getListeTypeCall() {
		return listeTypeCall;
	}
	public void setListeTypeCall(List<String> listeTypeCall) {
		this.listeTypeCall = listeTypeCall;
	}
	public List<String> getListeTypeSubscriber() {
		return listeTypeSubscriber;
	}
	public void setListeTypeSubscriber(List<String> listeTypeSubscriber) {
		this.listeTypeSubscriber = listeTypeSubscriber;
	}
	public String getTypeCall() {
		return TypeCall;
	}
	public void setTypeCall(String typeCall) {
		TypeCall = typeCall;
	}
	public String getTypeSubscriber() {
		return TypeSubscriber;
	}
	public void setTypeSubscriber(String typeSubscriber) {
		TypeSubscriber = typeSubscriber;
	}
	public Integer getDate_mois_debut() {
		return date_mois_debut;
	}
	public Integer getDate_mois_Fin() {
		return date_mois_Fin;
	}
	public void setDate_mois_debut(Integer date_mois_debut) {
		this.date_mois_debut = date_mois_debut;
	}
	public void setDate_mois_Fin(Integer date_mois_Fin) {
		this.date_mois_Fin = date_mois_Fin;
	}
	public List<Integer> getListe_desAns() {
		return liste_desAns;
	}
	public void setListe_desAns(List<Integer> liste_desAns) {
		this.liste_desAns = liste_desAns;
	}
	public Integer getDate_year_deb() {
		return date_year_deb;
	}
	public Integer getDate_year_fin() {
		return date_year_fin;
	}
	public void setDate_year_deb(Integer date_year_deb) {
		this.date_year_deb = date_year_deb;
	}
	public void setDate_year_fin(Integer date_year_fin) {
		this.date_year_fin = date_year_fin;
	}
public String getTypeDestFilter() {
	return typeDestFilter;
}
public void setTypeDestFilter(String typeDestFilter) {
	this.typeDestFilter = typeDestFilter;
}
	
	private String id;

	
	private List<String> where_liste = new ArrayList<String>();
	
	private ChartDynamic chartdynamic ;
	private List<String> where = new ArrayList<String>();
	private Datagrid datagrid;
	private Map<Object,Number> data = new HashMap<Object, Number>();
	private String choix_filter_date;
	private String choix_noeud_nom;
	

	
	
	public List<String> getListeTypeDest() {
		return listeTypeDest;
	}
	public void setListeTypeDest(List<String> listeTypeDest) {
		this.listeTypeDest = listeTypeDest;
	}
	public Date getDate_ParJourDeb() {
		return date_ParJourDeb;
	}
	public Date getDate_ParJourFin() {
		return date_ParJourFin;
	}
	public void setDate_ParJourDeb(Date date_ParJourDeb) {
		this.date_ParJourDeb = date_ParJourDeb;
	}
	public void setDate_ParJourFin(Date date_ParJourFin) {
		this.date_ParJourFin = date_ParJourFin;
	}
	public Date getDate_Parheure() {
		return date_Parheure;
	}
	public void setDate_Parheure(Date date_Parheure) {
		this.date_Parheure = date_Parheure;
	}
	
public List<String> getWhere() {
	return where;
}
public void setWhere(List<String> where) {
	this.where = where;
}
	
	
	public ChartDynamic getChartdynamic() {
		return chartdynamic;
	}
	public void setChartdynamic(ChartDynamic chartdynamic) {
		this.chartdynamic = chartdynamic;
	}
	
	@PostConstruct
	public void init(){
		FacesContext facesCtx = FacesContext.getCurrentInstance();
	    ELContext elContext = facesCtx.getELContext();
	    Application app = facesCtx.getApplication();
		 ExpressionFactory elFactory = app.getExpressionFactory();
	    ExternalContext externalContext = facesCtx.getExternalContext();
	    Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
	    //System.out.println(requestParameterMap.get("rapport_id"));
	    System.out.println(requestParameterMap.containsKey("rapport_id"));
	    if (requestParameterMap.containsKey("rapport_id")) {
	       id=requestParameterMap.get("rapport_id");
	       System.out.println(id);
	    }
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
	    
	    listeTypeCall= statRemote.getTypeCall();
	    listeTypeCall.add(0, "");
	   
	    listeTypeSubscriber=statRemote.getTypeSubscriber();
	    listeTypeSubscriber.add(0, "");
	    liste_noeuds = noeudremote.getNoeudNoms();
		liste_noeuds_noms = new ArrayList<String>();
		liste_noeuds_noms.add("");
		for(int noeud_nb=0;noeud_nb<liste_noeuds.size();noeud_nb++){
			liste_noeuds_noms.add(liste_noeuds.get(noeud_nb).getCodeNoeud());
			System.out.println(liste_noeuds.get(noeud_nb).getCodeNoeud());
		}
		liste_desAns = statRemote.getAllYears();
		listTranche.add(" ");
		listTranche.add("Par heure");
		listTranche.add("Par Jour");
		listTranche.add("Par mois");
		listTranche.add("Par An");
		listePeriode=statRemote.getAlltrancheHoraire();
		listePeriode.add(0, "");
	List<TypeDestination> lst = new ArrayList<TypeDestination>(typdestRemote.getAllTypeDest());
			listeTypeDest= new ArrayList<String>();
		
		listeTypeDest.add("");
		for(int j=0;j<lst.size();j++){
			listeTypeDest.add(lst.get(j).getTypeDest());
		}
	}


public void createForm(){
	
	 form.getChildren().clear();
	FacesContext facesCtx = FacesContext.getCurrentInstance();
    ELContext elContext = facesCtx.getELContext();
    Application app = facesCtx.getApplication();
	 ExpressionFactory elFactory = app.getExpressionFactory();
    ExternalContext externalContext = facesCtx.getExternalContext();
    Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
    //System.out.println(requestParameterMap.get("rapport_id"));
    System.out.println(id);
    if (id!=null) {
      
    
      rapport = reportremote.getReportById(Integer.valueOf(id));
      
      Layout rapport_layout = new Layout();
      rapport_layout.setId("rapport_layout");
      LayoutUnit filter_layout = new LayoutUnit();
      filter_layout.setHeader("Filters");
      filter_layout.setPosition("west");
      rapport_layout.setStyle("background-color:#FFFFFF;");
      rapport_layout.getChildren().add(filter_layout);
     LayoutUnit  main_layout = new LayoutUnit();
      main_layout.setStyleClass("layoutUnitCenter");
      main_layout.setPosition("center");
      main_layout.setId("main_layout");
      rapport_layout.getChildren().add(main_layout);
    // rapport_layout.setFullPage(true);
 	 
 	 
 	rapport_layout.setStyle("height:600px;");
 
// rapport_layout.set
	   List<String> liste_filters = rapport.getFilters_report();
	   PanelGrid pgdate = new PanelGrid();
	   pgdate.setId("pgdate");
		   pgdate.setColumns(2);
	   for(int nb=0;nb<liste_filters.size();nb++){
		   if(liste_filters.get(nb).equals("dateAppel")){
	 		   
	 			  SelectOneMenu lstDate = new SelectOneMenu();
	 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.choix_filter_date}",Object.class); 
		         UISelectItems list_items5 = new UISelectItems();
		         lstDate.setValueExpression("value", valueExp);
		         list_items5.setValue(listTranche);
		         lstDate.getChildren().add(list_items5);
		         
		       
		OutputLabel filter_per = new OutputLabel();
		filter_per.setValue("Periode");
		
	
		
		
		AjaxBehavior ajax = new AjaxBehavior();
	       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
	        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
	            createActionMethodExpression("#{rp_gene.handlechange}"),createActionMethodExpression("#{rp_gene.handlechange}")));
	        ajax.setUpdate("@form");
	       

		      
         
        
//		        
         
	         lstDate.addClientBehavior("change", ajax);
	         
	         
		pgdate.getChildren().add(filter_per);
		pgdate.getChildren().add(lstDate);
	
		
		
	 		   }else if(liste_filters.get(nb).equals("duree")){
	 			  OutputLabel lb_duree = new OutputLabel();
		    		lb_duree.setValue("Duree ");
		    	
		    		Spinner sp_duree = new Spinner();
		    		
		    		
//			         ji.addAjaxBehaviorListener( new AjaxBehaviorListenerImpl( me,me ) );
//			         ji.setProcess("@this");
//			        
//			         lstDate.addClientBehavior("change", ji);
pgdate.getChildren().add(lb_duree);
pgdate.getChildren().add(sp_duree);
				       
				       
				        
	 		   }else if(liste_filters.get(nb).equals("trancheHoraire")){
		 			  SelectOneMenu lstlmsc = new SelectOneMenu();
			 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.tranche_horaire}",Object.class); 
				         UISelectItems list_items5 = new UISelectItems();
				         lstlmsc.setValueExpression("value", valueExp);
				         list_items5.setValue(listePeriode);
				         lstlmsc.getChildren().add(list_items5);
				         OutputLabel lb_noeud = new OutputLabel();
				         lb_noeud.setValue("Tranche Horaire");
				         AjaxBehavior ajax = new AjaxBehavior();
					       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
					        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
					            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
					        ajax.setUpdate("@form");
					       

						      
				         
				        
//						        
				         
					         lstlmsc.addClientBehavior("change", ajax);
				         pgdate.getChildren().add(lb_noeud);
				         pgdate.getChildren().add(lstlmsc);
				       
				         
		 		   }
	 		   else if(liste_filters.get(nb).equals("msc")){
	 			  SelectOneMenu lstlmsc = new SelectOneMenu();
		 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.choix_noeud_nom}",Object.class); 
			         UISelectItems list_items5 = new UISelectItems();
			         lstlmsc.setValueExpression("value", valueExp);
			         list_items5.setValue(liste_noeuds_noms);
			         lstlmsc.getChildren().add(list_items5);
			         OutputLabel lb_noeud = new OutputLabel();
			         lb_noeud.setValue("MSC");
			         AjaxBehavior ajax = new AjaxBehavior();
				       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
				        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
				            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
				        ajax.setUpdate("@form");
				       

					      
			         
			        
//					        
			         
				         lstlmsc.addClientBehavior("change", ajax);
			         pgdate.getChildren().add(lb_noeud);
			         pgdate.getChildren().add(lstlmsc);
			       
			         
	 		   }else if(liste_filters.get(nb).equals("typeDest")){
		 			  SelectOneMenu lstlmsc = new SelectOneMenu();
			 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.typeDestFilter}",Object.class); 
				         UISelectItems list_items5 = new UISelectItems();
				         lstlmsc.setValueExpression("value", valueExp);
				         list_items5.setValue(listeTypeDest);
				         lstlmsc.getChildren().add(list_items5);
				         OutputLabel lb_noeud = new OutputLabel();
				         lb_noeud.setValue("Type Destination");
				         pgdate.getChildren().add(lb_noeud);
				         pgdate.getChildren().add(lstlmsc);
				         AjaxBehavior ajax = new AjaxBehavior();
					       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
					        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
					            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
					        ajax.setUpdate("@form");
					       

						      
				         
				        
//						        
				         
					         lstlmsc.addClientBehavior("change", ajax);
				       
				         
		 		   }
	 		
	 		  else if(liste_filters.get(nb).equals("typeCall")){
	 			  SelectOneMenu lstlmsc = new SelectOneMenu();
		 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.typeCall}",Object.class); 
			         UISelectItems list_items5 = new UISelectItems();
			         lstlmsc.setValueExpression("value", valueExp);
			         list_items5.setValue(listeTypeCall);
			         lstlmsc.getChildren().add(list_items5);
			         OutputLabel lb_noeud = new OutputLabel();
			         lb_noeud.setValue("Voix");
			         pgdate.getChildren().add(lb_noeud);
			         pgdate.getChildren().add(lstlmsc);
			         AjaxBehavior ajax = new AjaxBehavior();
				       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
				        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
				            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
				        ajax.setUpdate("@form");
				       

					      
			         
			        
//					        
			         
				         lstlmsc.addClientBehavior("change", ajax);
			       
			         
	 		   }
	 		  else if(liste_filters.get(nb).equals("typeSubscriber")){
	 			  SelectOneMenu lstlmsc = new SelectOneMenu();
		 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.typeSubscriber}",Object.class); 
			         UISelectItems list_items5 = new UISelectItems();
			         lstlmsc.setValueExpression("value", valueExp);
			         list_items5.setValue(listeTypeSubscriber);
			         lstlmsc.getChildren().add(list_items5);
			         OutputLabel lb_noeud = new OutputLabel();
			         lb_noeud.setValue("Type Inscris");
			         pgdate.getChildren().add(lb_noeud);
			         pgdate.getChildren().add(lstlmsc);
			         AjaxBehavior ajax = new AjaxBehavior();
				       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
				        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
				            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
				        ajax.setUpdate("@form");
				       

					      
			         
			        
//					        
			         
				         lstlmsc.addClientBehavior("change", ajax);
			       
			         
	 		   }
	 		   else if(liste_filters.get(nb).equals("moyenneDuree")){
	 			  OutputLabel lb_moyduree = new OutputLabel();
	 			 lb_moyduree.setValue("Moyenne Duree ");
		    	
		    		Spinner sp_moyduree = new Spinner();
		    		
		    		
//			         ji.addAjaxBehaviorListener( new AjaxBehaviorListenerImpl( me,me ) );
//			         ji.setProcess("@this");
//			        
//			         lstDate.addClientBehavior("change", ji);
pgdate.getChildren().add(lb_moyduree);
pgdate.getChildren().add(sp_moyduree);
	 			   
	 		   }else if(liste_filters.get(nb).equals("nbAppel")){
	 			  OutputLabel lb_nbAppel = new OutputLabel();
	 			 lb_nbAppel.setValue("Nb Appel ");
			    	
			    		Spinner sp_nbAppel = new Spinner();
			    		
			    		
//				         ji.addAjaxBehaviorListener( new AjaxBehaviorListenerImpl( me,me ) );
//				         ji.setProcess("@this");
//				        
//				         lstDate.addClientBehavior("change", ji);
	pgdate.getChildren().add(lb_nbAppel);
	pgdate.getChildren().add(sp_nbAppel);
	 		   }
		         
	   }
	 
	   filter_layout.getChildren().add(pgdate);
	   
	 //  colFilters.getChildren().add(panel_filters);
	   CommandButton update_chart = new CommandButton();
	   update_chart.setId("BTNUpdate");
	   update_chart.setValue("Valider");
	   update_chart.setStyle("margin-left:70px;");
	   filter_layout.getChildren().add(update_chart);
	   

	   BlockUI bl = new BlockUI();
		bl.setBlock("mainForm:main_layout");
		bl.setTrigger("mainForm:BTNUpdate");
		HtmlOutputText loading = new HtmlOutputText();
		loading.setValue("Loading");
		GraphicImage img = new GraphicImage();
		img.setName("img/ajaxloadingbar.gif");
		bl.getChildren().add(loading);
		bl.getChildren().add(img);

	   
 	update_chart.setUpdate("@form");
 	MethodExpression methode = createActionMethodExpression("#{rp_gene.update_rapport}");
 	update_chart.setActionExpression(methode);
 	 form.getChildren().add(rapport_layout);
 	form.getChildren().add(bl);
    }
	
	
	
}
public void  toPdf(){
	try {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ex= context.getExternalContext();
		ex.getSessionMap().put("where", where_liste);
		ex.getSessionMap().put("choix_date", choix_filter_date);
		ex.getSessionMap().put("rapport", rapport);
		
				ex.redirect("/TestReportingJSF/PdfReportServlet");
		//context.responseComplete();
				
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
}

public void update_rapport(){
	where_liste = new ArrayList<String>();
	//String text1 = df.format(jour_choisi_fin);
	//System.out.println(text+"  "+text1);


	if(this.getChoix_filter_date().equals(" ")){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
	}
	else{
		if(this.getChoix_filter_date().equals("Par Jour")){
			
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
				String deb = df.format(this.getDate_ParJourDeb());
				String fin = df.format(this.getDate_ParJourFin());
				where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
			
			
			
		}else if(this.getChoix_filter_date().equals("Par heure")){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
			String deb = df.format(this.getDate_Parheure());
			where_liste.add(" to_date(dateAppel,'YYMMDD')= to_date("+"'"+deb+"'"+",'yyyy-MM-dd')");
			
		}else if(this.getChoix_filter_date().equals("Par An")){
		
			Integer deb = this.getDate_year_deb();
			Integer fin =this.getDate_year_fin();
			where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(year from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
		}else if(this.getChoix_filter_date().equals("Par mois")){
			Integer year = this.getDate_year();
			Integer deb = this.getDate_mois_debut();
			Integer fin =this.getDate_mois_Fin();
			System.out.println(year+""+deb+""+fin);
			where_liste.add(" Extract(year from to_date(dateAppel,'YYMMDD')) = "+year+"  And Extract(month from to_date(dateAppel,'YYMMDD')) >= "+deb+" And Extract(month from to_date(dateAppel,'YYMMDD')) <= "+fin+"");
		}
		if(this.getTypeDestFilter()!="" && this.getTypeDestFilter()!=null ){
			where_liste.add(" s.destination.typeDest="+"'"+this.getTypeDestFilter()+"'");
		}
		if(this.getChoix_noeud_nom()!="" && this.getChoix_noeud_nom()!=null){
			where_liste.add(" msc="+"'"+this.getChoix_noeud_nom()+"'");
		}
		if(this.getTypeCall()!="" && this.getTypeCall()!=null){
			where_liste.add(" typeCall="+"'"+this.getTypeCall()+"'");
		}
		if(this.getTranche_horaire()!="" && this.getTranche_horaire()!=null){
			where_liste.add(" trancheHoraire="+"'"+this.getTranche_horaire()+"'");
		}
		if(this.getTypeSubscriber()!="" && this.getTypeSubscriber()!=null){
			where_liste.add(" typeSubscriber="+"'"+this.getTypeSubscriber()+"'");
		}	
	//System.out.println(where_liste.size());
		FacesContext facesCtx = FacesContext.getCurrentInstance();
	    ELContext elContext = facesCtx.getELContext();
	    Application app = facesCtx.getApplication();
		 ExpressionFactory elFactory = app.getExpressionFactory();
	    ExternalContext externalContext = facesCtx.getExternalContext();
		Report rapport = reportremote.getReportById(Integer.valueOf(id));
		
		LayoutUnit mainLayout = (LayoutUnit) facesCtx.getViewRoot().findComponent("mainForm:main_layout");
		
		
		System.out.println(mainLayout.getChildCount());

	mainLayout.getChildren().clear();
	CommandButton toPdf = new CommandButton();
	   toPdf.setValue("ToPdf");
	   toPdf.setStyle("margin-left:70px;");
	

	   MethodExpression methode1 = createActionMethodExpression("#{rp_gene.toPdf}");
	   toPdf.setActionExpression(methode1);
//	   FileDownloadActionListener fl = new FileDownloadActionListener();
//	  
//	toPdf.addActionListener(fl);
//	
	   mainLayout.getChildren().add(toPdf);
	 for(int i = 0;i<rapport.getLst_chart().size();i++){
 		 chartdynamic= rapport.getLst_chart().get(i);
 		Chart ch = new Chart();
 		 switch (rapport.getLst_chart().get(i).getType_chart()) {
 		 case "line":
 			 if( createLineChart(chartdynamic)!=null){
 				 ch= createLineChart(chartdynamic);
 				mainLayout.getChildren().add(ch);
 			 }else{
 				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Graphe Linéaire non Affiché!", "0 Resultats pour ces choix de filters  "));
 			 }
		    		
 		 	
 		 	break;
 		 case "bar":
 			 if(createBarChart(chartdynamic)!=null){
 				 ch= createBarChart(chartdynamic);
 			      mainLayout.getChildren().add(ch);
 			 }else{
 				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Graphe Bar non Affiché!", "0 Resultats pour ces choix de filters  "));
 			 }
 			
 		 	
 		 	break;
 		 case "pie":
 			 if(createPieChart(chartdynamic)!=null){
 			ch= createPieChart(chartdynamic);
		      mainLayout.getChildren().add(ch);
 			 }else{
 				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Graphe Pie non Affiché!", "0 Resultats pour ces choix de filters  "));
 			 }
 		       
 		 	break;
 		 	
 		 default:
 		 	break;
 		 }
 		 		
 	 }
 	if(rapport.getLst_datagrid().size()>0){
		for (int j=0;j<rapport.getLst_datagrid().size();j++){
			datagrid = rapport.getLst_datagrid().get(j);
			 Map<Object, Number> data1 = new HashMap<Object,Number>();
			 if(datagrid.getDaxe_x().equals("typeDest")){
		 			data1 =statRemote.getMscByFilters("s.destination.typeDest",datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By s.destination.typeDest ", where_liste);
		 		 }
			 else if(this.getChoix_filter_date().equals("Par An")&&datagrid.getDaxe_x().equals("dateAppel")){
			 			data1 =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By Extract (year from  to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&datagrid.getDaxe_x().equals("dateAppel")){
				 			data1 =statRemote.getMscByFilters(" extract(day from  to_date(dateAppel,'YYMMDD'))",datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By extract(day from  to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par mois")&&datagrid.getDaxe_x().equals("dateAppel")){
					 			data1 =statRemote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))",datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
					 		 }
				 		else if(this.getChoix_filter_date().equals("Par mois")&&datagrid.getDaxe_x().equals("dateAppel")){
				 			data1 =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par heure")&&datagrid.getDaxe_x().equals("dateAppel")){
					 			data1 =statRemote.getMscByFilters("CAST(trancheHoraire AS integer)",datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By CAST(trancheHoraire AS integer)  ", where_liste);
					 			
					 		 }
	 		 else{
	 			data1 =statRemote.getMscByFilters(datagrid.getDaxe_x(),datagrid.getDaxe_y()+")", datagrid.getOperationD()+"(","Group By "+datagrid.getDaxe_x(), where_liste);
	 		 }
			Object[] liste = data1.entrySet().toArray();
			DataTable table = (DataTable) app.createComponent(DataTable.COMPONENT_TYPE);
			
		//table.setId("Dbgrid"+j);
			 table.setValue(liste);
			 table.setVar("exam");
			 table.setWidgetVar("examTable");
			 table.setEmptyMessage("aucun résultat trouvé pour votre recherche");

			 table.setPaginator(true);
			 table.setPaginatorPosition("bottom");
			 table.setRows(25);
			 HtmlOutputLabel htmlOutputLabelObj = new HtmlOutputLabel();
			 htmlOutputLabelObj.setValue(rapport.getLst_datagrid().get(j).getDaxe_y()+ "  Par "+rapport.getLst_datagrid().get(j).getDaxe_x());
			 table.getFacets().put("header", htmlOutputLabelObj);
			 table.setPaginatorTemplate("{CurrentPageReport} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}");
			 table.setRowsPerPageTemplate("10,25,50,100");
				 
			 
			Column indexColumn = (Column) app.createComponent(Column.COMPONENT_TYPE);
			 UIOutput indexColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
			 indexColumnTitle.setValue(datagrid.getDaxe_x());
			 indexColumn.getFacets().put("header", indexColumnTitle);
			 //table.getColumns().add(column);
			 table.getChildren().add(indexColumn);

			 ValueExpression indexValueExp = elFactory.createValueExpression(elContext, "#{exam.key}", Object.class);
			 HtmlOutputText indexOutput = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
			 indexOutput.setValueExpression("value", indexValueExp);
			 indexColumn.getChildren().add(indexOutput);

			 //Name Column
			 Column nameColumn = (Column) app.createComponent(Column.COMPONENT_TYPE);
			 UIOutput nameColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
			 nameColumnTitle.setValue(datagrid.getDaxe_y());
			 nameColumn.getFacets().put("header", nameColumnTitle);
			 table.getChildren().add(nameColumn);

			 ValueExpression nameValueExp = elFactory.createValueExpression(elContext, "#{exam.value}", Object.class);
			 HtmlOutputText nameOutput = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
			 nameOutput.setValueExpression("value", nameValueExp);
			 nameColumn.getChildren().add(nameOutput);
		
			 
			mainLayout.getChildren().add(table);
			 
		}
		}
 	
//	   CommandButton toXls = new CommandButton();
//	   toXls.setValue("ToXls");
//	   toXls.setStyle("margin-left:70px;");
//	   mainLayout.getChildren().add(toXls);
//
//	   MethodExpression methode2 = createActionMethodExpression("#{rp_gene.toXls}");
//	   toXls.setActionExpression(methode2);
	
	
	}
	
	
}


public Chart createLineChart(ChartDynamic chd){
	Chart ch = new Chart();
	String exist = "" ;
	String title = "Graphe Linéaire Representant : ";
	if(chd.getOperation()=="SUM"){
		title= title+" La Somme";
	}else{
		title= title+" La Moyenne";
	}
	//Report rapport = reportremote.getReportById(Integer.valueOf(id));
	LineChartModel lineModel1 = new LineChartModel();
	 	for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
	 		title=title+"("+chd.getList_axe_y().get(nb_y)+")";
	 		LineChartSeries boys = new LineChartSeries();
	 		 boys.setLabel(chd.getAxe_x()+" Par"+chd.getList_axe_y().get(nb_y));
	 		 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
	 			data =statRemote.getMscBytranche(chd.getAxe_x(),chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By "+chd.getAxe_x(), where_liste);
	    		
	 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
		 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }
	 		 else if(chd.getAxe_x().equals("typeDest")){
			 			data =statRemote.getMscByFilters("s.destination.typeDest",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By s.destination.typeDest ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			data =statRemote.getMscByFilters(" extract(day from  to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  extract(day from  to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }
	 		 else{
	 			data =statRemote.getMscByFilters(chd.getAxe_x(),chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By "+chd.getAxe_x(), where_liste);
	 		 }
		     
 		 if(data!=null){
 			 boys.setData(data);
 		    lineModel1.addSeries(boys); 
 		    
 		  
 		 }else{
 			return null;
 		 }
		 	
	 	}
	 	 title=title+" Par "+chd.getAxe_x();
	 		 lineModel1.setTitle(title);
		      lineModel1.setLegendPosition("e");
		   //lineModel1.setShowPointLabels(true);
		   //lineModel1.setZoom(true);
		   Axis xAxis = new CategoryAxis(chd.getAxe_x());
		   xAxis.setTickAngle(-50);
		       lineModel1.getAxes().put(AxisType.X, xAxis);
		      Axis  yAxis =  lineModel1.getAxis(AxisType.Y);
		      yAxis.setMin(0);
		      lineModel1.setMouseoverHighlight(true);
		      //yAxis.setLabel(rapport.getLst_chart().get(i).getAxe_y());
		     lineModel1.setAnimate(true);
		      ch.setModel(lineModel1);
		      //ch.setId("line"+i);
		     // elements_to_update= elements_to_update+ch.getId();
		   
		      
		 	ch.setType(chd.getType_chart());
		 	return ch;
	 	
	 	
}
public Chart createBarChart(ChartDynamic chd ){
	Chart ch = new Chart();
	String title = "Graphe Bar Representant : ";
	if(chd.getOperation().equals("SUM")){
		title= title+" La Somme";
	}else{
		title= title+" La Moyenne";
	}
	
	BarChartModel lineModel2 = new BarChartModel();
	ch.setStyle("height:600px;");
		for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
	 		BarChartSeries boys = new BarChartSeries();
	 		title=title+"("+chd.getList_axe_y().get(nb_y)+")/";
	 		 boys.setLabel(chd.getAxe_x()+" Par "+chd.getList_axe_y().get(nb_y));
	 		 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			data =statRemote.getMscBytranche(chd.getAxe_x(),chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By "+chd.getAxe_x(), where_liste);
		 			ch.setStyle("height:600px;");
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(chd.getAxe_x().equals("typeDest")){
		 			data =statRemote.getMscByFilters("s.destination.typeDest",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By s.destination.typeDest ", where_liste);
			 		}else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters("Extract (month from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }
			 		 else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters(" extract(day from  to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  extract(day from  to_date(dateAppel,'YYMMDD')) ", where_liste);
			 			
			 		 }
	 		 else{
	 			data =statRemote.getMscByFilters(chd.getAxe_x(),chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By "+chd.getAxe_x(), where_liste);
	 		 }
	 		if(data!=null){
	 		
	 			 boys.setData(data);
			     lineModel2.addSeries(boys);
			    
			     
	 		}else{
	 			return null;
	 		}
		    
		}
		title=title+" Par "+chd.getAxe_x();
	 		 lineModel2.setTitle(title);
		     lineModel2.setLegendPosition("e");
		     lineModel2.setShowPointLabels(true);
		     lineModel2.setShadow(true);
		    // lineModel2.setShowDatatip(true);
		     
		     lineModel2.setAnimate(true);
		     //lineModel2.setZoom(true);
		     lineModel2.setMouseoverHighlight(true);
		     Axis xAxis = new CategoryAxis(chd.getAxe_x());
			   xAxis.setTickAngle(-50);
			       lineModel2.getAxes().put(AxisType.X, xAxis);
			      Axis  yAxis =  lineModel2.getAxis(AxisType.Y);
			      yAxis.setMin(0);
		     yAxis.setLabel("Valeurs");
		    
		     ch.setModel(lineModel2);
		     
		 	ch.setType(chd.getType_chart());
		return ch;
	 	
	    
}
public Chart createPieChart(ChartDynamic chd){
	Chart ch = new Chart();
	ChartSeries boys = new ChartSeries();
String title="Graphe Pie Representant";
	 	PieChartModel lineModel3 = new PieChartModel();
	 	 boys.setLabel(chd.getAxe_x()+" Par "+chd.getAxe_y());
	 	 Map<String, Number> data1 = new HashMap<String,Number>();
	 	if(chd.getAxe_x().equals("typeDest")){
	 			data1 =statRemote.getMscByFilters1("s.destination.typeDest",chd.getAxe_y()+")", chd.getOperation()+"(","Group By s.destination.typeDest ", where_liste);
	 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			data1 =statRemote.getMscByFilters1("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getAxe_y()+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
		 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
			 			data1 =statRemote.getMscByFilters1(" to_date(dateAppel,'YYMMDD')",chd.getAxe_y()+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
				 			data1 =statRemote.getMscByFilters1("Extract (month from to_date(dateAppel,'YYMMDD'))",chd.getAxe_y()+")", chd.getOperation()+"(","Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
				 			data1 =statRemote.getMscByFilters1("trancheHoraire",chd.getAxe_y()+")", chd.getOperation()+"(","Group By trancheHoraire  ", where_liste);
				 			
				 		 }
 		 else{
 			data1 =statRemote.getMscByFilters1(chd.getAxe_x(),chd.getAxe_y()+")", chd.getOperation()+"(","Group By "+chd.getAxe_x(), where_liste);
 		 }
	 	if(data1!=null && data1.size()>0){
	 		System.out.println(data1.size());
	 		 lineModel3.setData(data1);
	 		ch.setStyle("height:600px;");
		     
		}else{
			return null;
		}
	 	if(chd.getOperation().equals("SUM")){
			title= title+" La Somme("+chd.getAxe_y()+") Par "+chd.getAxe_x();
		}else{
			title= title+" La Moyenne("+chd.getAxe_y()+") Par "+chd.getAxe_x();
		}
	   
	     lineModel3.setTitle(title);
	     lineModel3.setLegendPosition("e");
	     lineModel3.setShowDataLabels(true);
	     //lineModel3.setTitle(chd.getAxe_y()+" Par  "+chd.getAxe_x());
	  lineModel3.setFill(true);
	  lineModel3.setShadow(true);
	  lineModel3.setMouseoverHighlight(true);
	  
	   
	    
	     ch.setModel(lineModel3);
	   
	 	ch.setType(chd.getType_chart());
	 	return ch;
	
}
public void handlechange(){
	LayoutUnit mainLayout = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:main_layout");
	System.out.println(mainLayout.getChildCount());

mainLayout.getChildren().clear();

	FacesContext facesCtx = FacesContext.getCurrentInstance();
    ELContext elContext = facesCtx.getELContext();
    Application app = facesCtx.getApplication();
	 ExpressionFactory elFactory = app.getExpressionFactory();
		PanelGrid comp = (PanelGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:pgdate");
		System.out.println(comp.getId());
		System.out.println(comp.getChildCount());
		UIComponent compJourOutput0 =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:cld_jour_debut");
		UIComponent compJourOutput1 =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:cld_jour_fin");
		UIComponent compJourLB0 =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_jour_debut");
		UIComponent compJourLB1 =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_jour_fin");
		UIComponent compJourLBHeure =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_jour");
		UIComponent compJourOutputHeure =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:cld_jour");
		UIComponent compJourOutputYear=FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lstDateYears");
		UIComponent compJourOutputYearFin=FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lstDateYearsFin");
		UIComponent compJourLBY =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_year_debut");
		UIComponent compJourLBYF =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_year_fin");
		UIComponent compSLYearMois =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lstDateYearsMois");
		UIComponent compSLMois =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lstMois");
		UIComponent compSLMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lstMoisFin");
		UIComponent compLBMoisDeb =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_mois_debut");
		UIComponent compLBMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_mois_fin");
		UIComponent compLBMoisYear =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_year");
		if(compJourLB0!=null){
			comp.getChildren().remove(compJourLB1);
			comp.getChildren().remove(compJourLB0);
			comp.getChildren().remove(compJourOutput0);
			comp.getChildren().remove(compJourOutput1);
		}
		if(compSLMois!=null){
			comp.getChildren().remove(compSLYearMois);
			comp.getChildren().remove(compSLMois);
			comp.getChildren().remove(compSLMoisFin);
			comp.getChildren().remove(compLBMoisDeb);
			comp.getChildren().remove(compLBMoisFin);
			comp.getChildren().remove(compLBMoisYear);
			
		}
		if(compJourLBY!=null){
			comp.getChildren().remove(compJourLBY);
			comp.getChildren().remove(compJourLBYF);
			comp.getChildren().remove(compJourOutputYear);
			comp.getChildren().remove(compJourOutputYearFin);
		}
	if(compJourLBHeure!=null){
		comp.getChildren().remove(compJourLBHeure);
		comp.getChildren().remove(compJourOutputHeure);
	}
		
		if(this.getChoix_filter_date().equals("Par Jour")){
			
			
		
				Calendar cld_jour_debut = new Calendar();
				cld_jour_debut.setId("cld_jour_debut");
				cld_jour_debut.setRequired(true);
				OutputLabel lb_jour_debut= new OutputLabel();
				lb_jour_debut.setId("lb_jour_debut");
				cld_jour_debut.setRequiredMessage("Veuillez choisir la date debut");
			ValueExpression valjour = createValueExpression("#{rp_gene.date_ParJourDeb}", Date.class);
			cld_jour_debut.setValueExpression("value", valjour);
				lb_jour_debut.setValue("Choisir Debut");
				Calendar cld_jour_fin = new Calendar();
				cld_jour_fin.setRequired(true);
				cld_jour_fin.setRequiredMessage("Veuillez choisir la date fin");
				cld_jour_fin.setId("cld_jour_fin");
				OutputLabel lb_jour_fin= new OutputLabel();
				lb_jour_fin.setId("lb_jour_fin");
			ValueExpression valjourfin = createValueExpression("#{rp_gene.date_ParJourFin}", Date.class);
			cld_jour_fin.setValueExpression("value", valjourfin);
			lb_jour_fin.setValue("Choisir Fin");
			
				
			
				comp.getChildren().add(lb_jour_debut);
				comp.getChildren().add(cld_jour_debut);
				comp.getChildren().add(lb_jour_fin);
				comp.getChildren().add(cld_jour_fin);
			
			
			
				
		
		}else if (this.getChoix_filter_date().equals("Par heure")){
			Calendar cld_jour = new Calendar();
			OutputLabel lb_jour= new OutputLabel();
			cld_jour.setId("cld_jour");
			lb_jour.setId("lb_jour");
		ValueExpression valjour = createValueExpression("#{rp_gene.date_Parheure}", Date.class);
		cld_jour.setValueExpression("value", valjour);
		lb_jour.setValue("Choisir Date");
			comp.getChildren().add(lb_jour);
			comp.getChildren().add(cld_jour);
		}else if (this.getChoix_filter_date().equals("Par An")){
			SelectOneMenu lstDateYears = new SelectOneMenu();
			lstDateYears.setId("lstDateYears");
			 ValueExpression valueExp = createValueExpression("#{rp_gene.date_year_deb}", Integer.class);
	         UISelectItems list_items0 = new UISelectItems();
			 lstDateYears.setValueExpression("value", valueExp);
	         list_items0.setValue(liste_desAns);
	         lstDateYears.getChildren().add(list_items0);
			OutputLabel lb_year_debut= new OutputLabel();
			lb_year_debut.setId("lb_year_debut");
			SelectOneMenu lstDateYearsFin = new SelectOneMenu();
			lstDateYearsFin.setId("lstDateYearsFin");
			 ValueExpression valueExp1 = createValueExpression("#{rp_gene.date_year_fin}", Integer.class);
	         UISelectItems list_items = new UISelectItems();
			 lstDateYearsFin.setValueExpression("value", valueExp1);
	         list_items.setValue(liste_desAns);
	         lstDateYearsFin.getChildren().add(list_items);
		lb_year_debut.setValue("Choisir Debut");
			
			
			OutputLabel lb_year_fin= new OutputLabel();
			lb_year_fin.setId("lb_year_fin");
		
		lb_year_fin.setValue("Choisir Fin");
		comp.getChildren().add(lb_year_debut);
		comp.getChildren().add(lstDateYears);
		comp.getChildren().add(lb_year_fin);
		comp.getChildren().add(lstDateYearsFin);
		}else if (this.getChoix_filter_date().equals("Par mois")){
			SelectOneMenu lstDateYearsMois = new SelectOneMenu();
			lstDateYearsMois.setId("lstDateYearsMois");
			 ValueExpression valueExp = createValueExpression("#{rp_gene.date_year}", Integer.class);
	         UISelectItems list_items0 = new UISelectItems();
	         lstDateYearsMois.setValueExpression("value", valueExp);
	         list_items0.setValue(liste_desAns);
	         lstDateYearsMois.getChildren().add(list_items0);
			OutputLabel lb_year_debut= new OutputLabel();
			lb_year_debut.setValue("Choisir Année");
			lb_year_debut.setId("lb_year");
			SelectOneMenu lstMois = new SelectOneMenu();
			lstMois.setId("lstMois");
			 ValueExpression valueExp1 = createValueExpression("#{rp_gene.date_mois_debut}", Integer.class);
	         UISelectItems list_items = new UISelectItems();
	         lstMois.setValueExpression("value", valueExp1);
	         list_items.setValue(listeMois);
	         lstMois.getChildren().add(list_items);
			OutputLabel lb_mois_debut= new OutputLabel();
			lb_mois_debut.setValue("Choisir Mois Debut");
			lb_mois_debut.setId("lb_mois_debut");
			SelectOneMenu lstMoisFin = new SelectOneMenu();
			lstMoisFin.setId("lstMoisFin");
			 ValueExpression valueExp2 = createValueExpression("#{rp_gene.date_mois_Fin}", Integer.class);
	         UISelectItems list_items2 = new UISelectItems();
	         lstMoisFin.setValueExpression("value", valueExp2);
	         list_items2.setValue(listeMois);
	         lstMoisFin.getChildren().add(list_items2);
			OutputLabel lb_mois_fin= new OutputLabel();
			lb_mois_fin.setValue("Choisir Mois Fin");
			lb_mois_fin.setId("lb_mois_fin");
			comp.getChildren().add(lb_year_debut);
			comp.getChildren().add(lstDateYearsMois);
			comp.getChildren().add(lb_mois_debut);
			comp.getChildren().add(lstMois);
			comp.getChildren().add(lb_mois_fin);
			comp.getChildren().add(lstMoisFin);
		}
		
			
			
		
		
		
		
}


       
        
         
    
	
		
		
			 
		
		
	    
		  
		 
		   
		
	 //System.out.println(comp2.getChildCount());

	    
		 
	





public void handlechange1(){
	LayoutUnit mainLayout = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:main_layout");
	System.out.println(mainLayout.getChildCount());

mainLayout.getChildren().clear();
}

public void testid(){
	FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
    System.out.println(requestParameterMap.get("rapport_id"));
}
	public List<String> getListTranche() {
		return listTranche;
	}








	public void setListTranche(List<String> listTranche) {
		this.listTranche = listTranche;
	}



	public String getChoix_filter_date() {
		return choix_filter_date;
	}



	public void setChoix_filter_date(String choix_filter_date) {
		this.choix_filter_date = choix_filter_date;
	}



	public BigDecimal getDuree_filter() {
		return duree_filter;
	}



	public void setDuree_filter(BigDecimal duree_filter) {
		this.duree_filter = duree_filter;
	}
	public void updateChart(ActionEvent event){
		System.out.println(event.getComponent().getId());
		
		
		
	}



	public Map<Object,Number> getData() {
		return data;
	}



	public void setData(Map<Object,Number> data) {
		this.data = data;
	}




	public List<String> getListe_noeuds_noms() {
		return liste_noeuds_noms;
	}
	public void setListe_noeuds_noms(List<String> liste_noeuds_noms) {
		this.liste_noeuds_noms = liste_noeuds_noms;
	}
	public String getChoix_noeud_nom() {
		return choix_noeud_nom;
	}
	public void setChoix_noeud_nom(String choix_noeud_nom) {
		this.choix_noeud_nom = choix_noeud_nom;
	}
	public Datagrid getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(Datagrid datagrid) {
		this.datagrid = datagrid;
	}
	public UIForm getForm() {
		return form;
	}
	public void setForm(UIForm form) {
		this.form = form;
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	
		public List<String> getWhere_liste() {
			return where_liste;
		}
		public void setWhere_liste(List<String> where_liste) {
			this.where_liste = where_liste;
		}
		public Integer getDate_year() {
			return date_year;
		}
		public void setDate_year(Integer date_year) {
			this.date_year = date_year;
		}
		public List<String> getListePeriode() {
			return listePeriode;
		}
		public void setListePeriode(List<String> listePeriode) {
			this.listePeriode = listePeriode;
		}
		
		
		
}
