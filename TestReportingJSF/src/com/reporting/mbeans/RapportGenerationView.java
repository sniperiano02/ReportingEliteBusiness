
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





































import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

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
import javax.faces.component.UIGraphic;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
import org.primefaces.component.commandlink.CommandLink;
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

import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.ChartDynamic;
import com.reporting.metier.entities.Datagrid;
import com.reporting.metier.entities.Noeud;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.Report;
import com.reporting.metier.entities.ToUse;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.NoeudRemote;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
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
	private PlanTarifaireRemote planTarifaireService;
	@EJB
	private NoeudRemote noeudremote;
	private Map<String,String> properties1;
	
	
	public Map<String, String> getProperties1() {
		return properties1;
	}
	public void setProperties1(Map<String, String> properties1) {
		this.properties1 = properties1;
	}
	
	private Map<String,List<Object[]>> filter_map;
	
	
	public Map<String, List<Object[]>> getFilter_map() {
		return filter_map;
	}
	
	private List<Object> lst1 = new ArrayList<>();
	
	
	public List<Object> getLst1() {
		return lst1;
	}
	public void setLst1(List<Object> lst1) {
		this.lst1 = lst1;
	}
	
	public void setFilter_map(Map<String, List<Object[]>> filter_map) {
		this.filter_map = filter_map;
	}
	
	private String choix_plan;
	
	public String getChoix_plan() {
		return choix_plan;
	}
	public void setChoix_plan(String choix_plan) {
		this.choix_plan = choix_plan;
	}
	private List<PlanTarifaire> lst_plan = new ArrayList<>();
	
	private List<String> liste = new ArrayList<String>(5);
	
	public List<String> getListe() {
		return liste;
	}
	public void setListe(List<String> liste) {
		this.liste = liste;
	}
	
	private String table;
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public List<PlanTarifaire> getLst_plan() {
		return lst_plan;
	}
	public void setLst_plan(List<PlanTarifaire> lst_plan) {
		this.lst_plan = lst_plan;
	}
	
	private String planFilter;
	 private UIForm form;
	 
	 private Map<String,BigDecimal> highLine;
	 
	 
	 public Map<String, BigDecimal> getHighLine() {
		return highLine;
	}
	 public void setHighLine(Map<String, BigDecimal> highLine) {
		this.highLine = highLine;
	}

	 private List<String> listeTypeDest;
	 
	 private List<Integer> listeMois = new ArrayList<Integer>();
	 
	 private boolean contient = false;
	 
	 
	 public String getPlanFilter() {
		return planFilter;
	}
	 public void setPlanFilter(String planFilter) {
		this.planFilter = planFilter;
	}
	 public boolean isContient() {
		return contient;
	}
	 public void setContient(boolean contient) {
		this.contient = contient;
	}
	 
	 private Map<String, String> properties;
	 private Date date_Parheure;
	 private Date date_ParJourDeb;
	 private Date date_ParJourFin;
	 private Integer date_year_deb;
	 private Integer date_year_fin;
	 private Date date_mois_Fin;
	 private Date date_mois_debut;
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
	private List<SerieChart> linesSeries ;
	private List<SerieChart> barsSeries ;
	private List<List<Object[]>> piesSeries ;
	private String choix_typeCdr;
	
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	
	public String getChoix_typeCdr() {
		return choix_typeCdr;
	}
	public void setChoix_typeCdr(String choix_typeCdr) {
		this.choix_typeCdr = choix_typeCdr;
	}
	
	public List<SerieChart> getBarsSeries() {
		return barsSeries;
	}
	public void setBarsSeries(List<SerieChart> barsSeries) {
		this.barsSeries = barsSeries;
	}
	public List<List<Object[]>> getPiesSeries() {
		return piesSeries;
	}
	public void setPiesSeries(List<List<Object[]>> piesSeries) {
		this.piesSeries = piesSeries;
	}
	public List<SerieChart> getLinesSeries() {
		return linesSeries;
	}
	public void setLinesSeries(List<SerieChart> linesSeries) {
		this.linesSeries = linesSeries;
	}
	
	private List<HighChart> linesMap;
	private List<HighChart> BarsMap;
	
	public List<HighChart> getBarsMap() {
		return BarsMap;
	}
	public List<HighChart> getLinesMap() {
		return linesMap;
	}
	public void setBarsMap(List<HighChart> barsMap) {
		BarsMap = barsMap;
	}
	public void setLinesMap(List<HighChart> linesMap) {
		this.linesMap = linesMap;
	}
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
public Date getDate_mois_debut() {
	return date_mois_debut;
}

public Date getDate_mois_Fin() {
	return date_mois_Fin;
}
public void setDate_mois_debut(Date date_mois_debut) {
	this.date_mois_debut = date_mois_debut;
}
public void setDate_mois_Fin(Date date_mois_Fin) {
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
private SelectOneMenu lstDate ;

public SelectOneMenu getLstDate() {
	return lstDate;
}
public void setLstDate(SelectOneMenu lstDate) {
	this.lstDate = lstDate;
}
	
	private List<String> where_liste = new ArrayList<String>();
	
	private ChartDynamic chartdynamic ;
	private List<String> where = new ArrayList<String>();
	private Datagrid datagrid;
	private List<Object[]> data = new ArrayList<>();
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
	public void init() {
		properties= new HashMap<>();
		properties1= new HashMap<>();
//		 ClassPool pool = ClassPool.getDefault();
//		    
//		    ClassClassPath ccpath = new ClassClassPath(RapportGenerationView.class);
//		    pool.insertClassPath(ccpath);
//		   
//		    CtClass classe = pool.get("com.reporting.mbeans.RapportGenerationView");
//		    if(classe.getField("testInt")==null){
//		    	classe.defrost();
//				   CtField f = new CtField(CtClass.intType, "testInt", classe);
//				   CtMethod mthd = CtNewMethod.make("public Integer getTestInt() { return 4; }", classe);
//				   classe.addMethod(mthd);
//				   classe.addField(f);
//				   CtField f1 = new CtField(CtClass.intType, "testInte", classe);
//				   CtMethod mthd1 = CtNewMethod.make("public Integer getTestInte() { return 4; }", classe);
//				   classe.addMethod(mthd1);
//				   classe.addField(f1);
//				  
//				   for(int i=0;i<classe.getDeclaredFields().length;i++){
//					   System.out.println(classe.getDeclaredFields()[i].getName());
//				   }
//		    }
		    
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
		System.out.println(id);
		linesMap = new ArrayList<>();
		BarsMap = new ArrayList<>();
		piesSeries = new ArrayList<>();
		 filter_map = new HashMap<>();
		
	}


public void createForm() throws ClassNotFoundException, NotFoundException, NoSuchFieldException, SecurityException{
	

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
      
//      Layout rapport_layout = new Layout();
//      rapport_layout.setId("rapport_layout");
//      LayoutUnit filter_layout = new LayoutUnit();
//      filter_layout.setHeader("Filters");
//      filter_layout.setPosition("west");
//      rapport_layout.setStyle("background-color:#FFFFFF;");
//      rapport_layout.getChildren().add(filter_layout);
//     LayoutUnit  main_layout = new LayoutUnit();
//      main_layout.setStyleClass("layoutUnitCenter");
//      main_layout.setPosition("center");
//      main_layout.setId("main_layout");
//      rapport_layout.getChildren().add(main_layout);
//    // rapport_layout.setFullPage(true);
// 	 
// 	 
// 	rapport_layout.setStyle("height:600px;");
      
      LayoutUnit main_layout = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:main_layout");
      LayoutUnit filter_layout = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:filter_layout");
 
// rapport_layout.set
	   List<String> liste_filters = rapport.getFilters_report();
	    table = rapport.getTable_name();
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	 
	   
	   Class c = Class.forName("com.reporting.metier.entities."+table);
	
	   for(int verif=0;verif<c.getDeclaredFields().length;verif++){
		   if(c.getDeclaredFields()[verif].getName().contains("tranche")){
			   listTranche.add("Par heure");
		   }
	   }
	   PanelGrid pgdate = new PanelGrid();
	   pgdate.setId("pgdate");
		   pgdate.setColumns(2);
		   int nbf=0;
	   for(int nb=0;nb<liste_filters.size();nb++){
		   
		   if(liste_filters.get(nb).contains("dateAppel")){
			   
			
	 			 lstDate = new SelectOneMenu();
	 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.choix_filter_date}",Object.class); 
		         UISelectItems list_items5 = new UISelectItems();
		         lstDate.setValueExpression("value", valueExp);
		         list_items5.setValue(listTranche);
		         lstDate.getChildren().add(list_items5);
		         
	 	//		 ValueExpression valueExp1 = elFactory.createValueExpression(elContext, "#{rp_gene.testInte}",Object.class); 

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
	
		
		
	 		   }else if(c.getDeclaredField(liste_filters.get(nb)).isAnnotationPresent(ManyToOne.class)) {
	 			   
	 			System.out.println(liste_filters.get(nb)); 
	 		
	 		List<Object[]> lst = new ArrayList<>();
	 		int endIndex = c.getDeclaredField(liste_filters.get(nb)).getType().getName().lastIndexOf(".")+1;
	 		String table2 = c.getDeclaredField(liste_filters.get(nb)).getType().getName().substring(endIndex, c.getDeclaredField(liste_filters.get(nb)).getType().getName().length());
	 		lst = statRemote.getFilter(" p ", table2+" p");
	 		
	 		Class cl = c.getDeclaredField(liste_filters.get(nb)).getType();
	 		 String label = null ;
	 		 String value = null;
	 		 
	 		 
	 		
	 		 for(int i=0;i<cl.getDeclaredFields().length;i++){
	 			 if(cl.getDeclaredFields()[i].isAnnotationPresent(ToUse.class)){
	 				 label = cl.getDeclaredFields()[i].getName();
	 				 System.out.println(cl.getDeclaredFields()[i].getName());
	 			 }
	 			 
	 			if(cl.getDeclaredFields()[i].isAnnotationPresent(Id.class)){
	 				String p =null;
	 				 value = cl.getDeclaredFields()[i].getName();
	 				 properties1.put(liste_filters.get(nb)+"."+value, p);
	 				
	 				 System.out.println(cl.getDeclaredFields()[i].getName());
	 			 }

	 		 }
	 		
	 		 
	 		
	 		 
	 	
	 			  SelectOneMenu lstlmsc = new SelectOneMenu();
		 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.properties1['"+liste_filters.get(nb)+"."+value+"']}",Object.class); 
		 			 UISelectItem selectitem = new UISelectItem();
		 			 selectitem.setItemLabel("");
		 			 selectitem.setItemValue(null);	
    UISelectItems list_items5 = new UISelectItems();
  ValueExpression valueExp6 = elFactory.createValueExpression(elContext, "plan",Object.class);
 
 
			    	   ValueExpression valueExp7 = elFactory.createValueExpression(elContext, "#{plan."+label+"}",Object.class);
			    	   ValueExpression valueExp8 = elFactory.createValueExpression(elContext, "#{plan."+value+"}",Object.class);
			         lstlmsc.setValueExpression("value", valueExp);
			         list_items5.setValueExpression("var", valueExp6);
			         list_items5.setValueExpression("itemLabel", valueExp7);
			         list_items5.setValueExpression("itemValue", valueExp8);
			         list_items5.setValue(lst);
			         lstlmsc.getChildren().add(selectitem);
			         lstlmsc.getChildren().add(list_items5);
			         OutputLabel lb_noeud = new OutputLabel();
			         lb_noeud.setValue(table2);
			         AjaxBehavior ajax = new AjaxBehavior();
				       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
				        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
				            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
				        ajax.setUpdate("@form");
				       

					      
			         
			        
				        
			         
				         lstlmsc.addClientBehavior("change", ajax);
			         pgdate.getChildren().add(lb_noeud);
			         pgdate.getChildren().add(lstlmsc);
		 			 nbf = nbf+1;
	 		   }else {
	 			   System.out.println(liste_filters.get(nb)); 
	 			  List<Object> lst1 = new ArrayList<>();
	 			 	
			 		lst1 = statRemote.getFilterDitinct(liste_filters.get(nb), table);
			 		
			 		 String label = null ;
			 		 String value = null;
			 		
			 		 UISelectItem selectitem = new UISelectItem();
		 			 selectitem.setItemLabel("");
		 			 selectitem.setItemValue(null);	
			 	String s=null;
			 		 
			 		
			 		 properties.put(liste_filters.get(nb), s);
			 	
			 			  SelectOneMenu lstlmsc = new SelectOneMenu();
				 			 ValueExpression valueExp = elFactory.createValueExpression(elContext, "#{rp_gene.properties['"+liste_filters.get(nb)+"']}",Object.class); 
		    UISelectItems list_items5 = new UISelectItems();
		  ValueExpression valueExp6 = elFactory.createValueExpression(elContext, "plan",Object.class);
		  
		 
					    	   ValueExpression valueExp7 = elFactory.createValueExpression(elContext, "#{plan}",Object.class);
					    	   ValueExpression valueExp8 = elFactory.createValueExpression(elContext, "#{plan}",Object.class);
					         lstlmsc.setValueExpression("value", valueExp);
					         list_items5.setValueExpression("var", valueExp6);
					         list_items5.setValueExpression("itemLabel", valueExp7);
					         list_items5.setValueExpression("itemValue", valueExp8);
					         list_items5.setValue(lst1);
					        lstlmsc.getChildren().add(selectitem);
					         lstlmsc.getChildren().add(list_items5);
					         OutputLabel lb_noeud = new OutputLabel();
					         lb_noeud.setValue(liste_filters.get(nb));
					         AjaxBehavior ajax = new AjaxBehavior();
						       // MethodExpression me = elFactory.createMethodExpression(elContext, "#{sampleMBean.processAction}", null, new Class<?>[]{BehaviorEvent.class});
						        ajax.addAjaxBehaviorListener(new AjaxBehaviorListenerImpl(
						            createActionMethodExpression("#{rp_gene.handlechange1}"),createActionMethodExpression("#{rp_gene.handlechange1}")));
						        ajax.setUpdate("@form");
						       

							      
					         
					        
						        
					         
						         lstlmsc.addClientBehavior("change", ajax);
					         pgdate.getChildren().add(lb_noeud);
					         pgdate.getChildren().add(lstlmsc);
	 		   }
		 		
		 		
			 			
		         
	   }
	 
	   filter_layout.getChildren().add(pgdate);
	   
	 //  colFilters.getChildren().add(panel_filters);
	   CommandButton update_chart = new CommandButton();
	   update_chart.setId("BTNUpdate");
	   update_chart.setValue("Valider");
	   update_chart.setStyle("margin-left:70px;");
	   filter_layout.getChildren().add(update_chart);
	  // RequestContext.getCurrentInstance().update("mainForm:filter_layout");

	   BlockUI bl = new BlockUI();
		bl.setBlock("mainForm:main_layout");
		bl.setTrigger("mainForm:BTNUpdate");
		HtmlOutputText loading = new HtmlOutputText();
		loading.setValue("Loading");
	HtmlGraphicImage img = new HtmlGraphicImage();
	
		img.setValue("/resources/img/ajaxloadingbar.gif");
		bl.getChildren().add(loading);
		bl.getChildren().add(img);

	   
 	update_chart.setUpdate("@form");
 	MethodExpression methode = createActionMethodExpression("#{rp_gene.update_rapport}");
 	update_chart.setActionExpression(methode);
 	 
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
contient = true;
	where_liste = new ArrayList<String>();
	//String text1 = df.format(jour_choisi_fin);
	//System.out.println(text+"  "+text1);

 linesMap = new ArrayList<>();
 BarsMap = new ArrayList<>();
 piesSeries = new ArrayList<>();
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
			DateFormat df = new SimpleDateFormat("MM/YYYY");
			System.out.println(date_Parheure);
			String deb = df.format(this.getDate_mois_debut());
			String fin = df.format(this.getDate_mois_Fin());
			
			where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'MM/YYYY') And to_date("+"'"+fin+"'"+",'MM/YYYY')");}
		System.out.println(properties.size());
		System.out.println(properties1.size());
	for(Entry e: properties1.entrySet()){
		System.out.println(e);
	}
	for(Map.Entry<String, String>  e: properties.entrySet()){
		System.out.println(e);
	}
	if(!properties.isEmpty()){
		for(Map.Entry<String, String>  e:properties.entrySet()){
			if(properties.get(e.getKey())!="" && properties.get(e.getKey())!=null){
				where_liste.add("Cast("+e.getKey()+" as text) = '"+properties.get(e.getKey())+"'");
			}
		}
	}
	if(!properties1.isEmpty()){
		for(Entry e:properties1.entrySet()){
			if(properties1.get(e.getKey()) != "" && properties1.get(e.getKey())!=null){
				where_liste.add("Cast("+e.getKey()+" as text) = '"+properties1.get(e.getKey())+"'");
			}
		}
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
	
	Panel export_panel = new Panel();
	export_panel.setHeader("Exporter");
	HtmlCommandLink toPdf = new HtmlCommandLink();
	GraphicImage img1 = new GraphicImage();
	img1.setLibrary("img");
	img1.setName("pdf.png");
	toPdf.getChildren().add(img1);
	
	   
	HtmlCommandLink toXls = new HtmlCommandLink();
	GraphicImage img2 = new GraphicImage();
	img2.setLibrary("img");
	img2.setName("excel.png");
	toXls.getChildren().add(img2);

	   MethodExpression methode1 = createActionMethodExpression("#{rp_gene.toPdf}");
	   toPdf.setActionExpression(methode1);
	   export_panel.getChildren().add(toPdf);
	   export_panel.getChildren().add(toXls);
//	   FileDownloadActionListener fl = new FileDownloadActionListener();
//	  
//	toPdf.addActionListener(fl);
//	
	   mainLayout.getChildren().add(export_panel);
	 for(int i = 0;i<rapport.getLst_chart().size();i++){
 		 chartdynamic= rapport.getLst_chart().get(i);
 		Chart ch = new Chart();
 		 switch (rapport.getLst_chart().get(i).getType_chart()) {
 		 case "line":
 			 createLineChart(chartdynamic);
 			 if(linesSeries.isEmpty()==false){
 				System.out.println(chartdynamic.getList_axe_y().get(0).getAxey()) ;
				 
				 HighChart hichart = new HighChart();
				 hichart.setName(chartdynamic.getNom_chart());
				 hichart.setSeries(linesSeries);
				 linesMap.add(hichart);
 			 }else{
 				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Graphe Linéaire non Affiché!", "0 Resultats pour ces choix de filters  ")); 
 			 }
 			
 				//mainLayout.getChildren().add(ch);
 			 
		    		
 		 	
 		 	break;
 		 case "bar":
 		 createBarChart(chartdynamic);
 			 if(barsSeries.isEmpty()==false){
 				
 				 HighChart hichart = new HighChart();
 				 hichart.setName(chartdynamic.getNom_chart());
 				 hichart.setSeries(barsSeries);
 				 BarsMap.add(hichart);
 			     // mainLayout.getChildren().add(ch);
 			 }else{
 				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Graphe Bar non Affiché!", "0 Resultats pour ces choix de filters  "));
 			 }
 			
 		 	
 		 	break;
 		 case "pie":
 			createPieChart(chartdynamic);
 			 if(piesSeries.isEmpty()==false){
 			
 				
		      //mainLayout.getChildren().add(ch);
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
			List<Object[]> liste = new ArrayList<>();
			 
			 if(datagrid.getDaxe_x().contains("typeDest")){
		 			liste =statRemote.getAllStatMsc1("destination.typeDest",datagrid.getList_axe_y(),table, "","Group By destination.typeDest ", where_liste);
		 		 }
			 else if(this.getChoix_filter_date().equals("Par An")&&datagrid.getDaxe_x().contains("dateAppel")){
				 liste =statRemote.getAllStatMsc1("Extract (year from to_date(dateAppel,'YYMMDD'))",datagrid.getList_axe_y(),table, "","Group By Extract (year from  to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&datagrid.getDaxe_x().equals("dateAppel")){
			 			liste =statRemote.getAllStatMsc1(" extract(day from  to_date(dateAppel,'YYMMDD'))",datagrid.getList_axe_y(),table, "","Group By extract(day from  to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par mois")&&datagrid.getDaxe_x().equals("dateAppel")){
				 			liste =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')",datagrid.getList_axe_y(),table, "","Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
					 		 }
				 		else if(this.getChoix_filter_date().equals("Par mois")&&datagrid.getDaxe_x().equals("dateAppel")){
				 			liste =statRemote.getAllStatMsc1(" to_date(dateAppel,'YYMMDD')",datagrid.getList_axe_y(),table, "","Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par heure")&&datagrid.getDaxe_x().equals("dateAppel")){
				 			liste =statRemote.getAllStatMsc1("CAST(trancheHoraire AS integer)",datagrid.getList_axe_y(),table, "","Group By CAST(trancheHoraire AS integer)  ", where_liste);
					 			
					 		 }
	 		 else{
	 			liste =statRemote.getAllStatMsc1(datagrid.getDaxe_x(),datagrid.getList_axe_y(),table, "","Group By "+datagrid.getDaxe_x(), where_liste);
	 		 }
			
			DataTable table = (DataTable) app.createComponent(DataTable.COMPONENT_TYPE);
			
		//table.setId("Dbgrid"+j);
			 table.setValue(liste);
			 table.setVar("exam");
			 table.setWidgetVar("examTable");
			 table.setEmptyMessage("aucun résultat trouvé pour votre recherche");

			 table.setPaginator(true);
			 table.setPaginatorPosition("bottom");
			 table.setRows(25);
			 
			 table.setPaginatorTemplate("{CurrentPageReport} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}");
			 table.setRowsPerPageTemplate("10,25,50,100");
				 
			 
			Column indexColumn = (Column) app.createComponent(Column.COMPONENT_TYPE);
			 UIOutput indexColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
			 if(datagrid.getDaxe_x().contains(".")){
				 int endIndex = datagrid.getDaxe_x().lastIndexOf(".");
				 String s= datagrid.getDaxe_x().substring(0,endIndex);
				 indexColumnTitle.setValue(s);
			 }else{
				 
				 indexColumnTitle.setValue(datagrid.getDaxe_x());
			 }
			
			 indexColumn.getFacets().put("header", indexColumnTitle);
			 //table.getColumns().add(column);
			 table.getChildren().add(indexColumn);

			 ValueExpression indexValueExp = elFactory.createValueExpression(elContext, "#{exam[0]}", Object.class);
			 HtmlOutputText indexOutput = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
			 indexOutput.setValueExpression("value", indexValueExp);
			 indexColumn.getChildren().add(indexOutput);

			 //Name Column
			 int nb=1;
			 for(int k=0;k<datagrid.getList_axe_y().size();k++){
				 String name = datagrid.getList_axe_y().get(k).getAxey();
				 for (int p=0;p<datagrid.getList_axe_y().get(k).getOperations().size();p++){
					
					 Column nameColumn = new Column();
					 UIOutput nameColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
					 nameColumnTitle.setValue(datagrid.getList_axe_y().get(k).getOperations().get(p)+"("+name+")");
					 nameColumn.getFacets().put("header", nameColumnTitle);
					 table.getChildren().add(nameColumn);

					 ValueExpression nameValueExp = elFactory.createValueExpression(elContext, "#{exam["+nb+"]}", Object.class);
					 nb++;
					 HtmlOutputText nameOutput = new HtmlOutputText();
					 nameOutput.setValueExpression("value", nameValueExp);
					 nameColumn.getChildren().add(nameOutput);

				 }
				 
			 }
			 
			 //Name Column
			
		
			 
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


public void createLineChart(ChartDynamic chd){

	String exist = "" ;
	String title = "Graphe Linéaire Representant : ";
	
	//Report rapport = reportremote.getReportById(Integer.valueOf(id));
	LineChartModel lineModel1 = new LineChartModel();
	 linesSeries = new ArrayList<>();
	 
	 	for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
	 		
	 		for(int nb_op=0;nb_op<chd.getList_axe_y().get(nb_y).getOperations().size();nb_op++){
	 			System.out.println(chd.getList_axe_y().get(nb_y).getAxey()+":"+chd.getList_axe_y().get(nb_y).getOperations().get(nb_op));
	 			String operation = chd.getList_axe_y().get(nb_y).getOperations().get(nb_op);
	 		
		 		 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			data =statRemote.getMscBytranche(chd.getAxe_x(),chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By "+chd.getAxe_x(), where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y).getAxey()+")",table,operation+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
				 			data =statRemote.getMscByFilters("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')",chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
				 			data =statRemote.getMscByFilters("destination.typeDest",chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
					 			data =statRemote.getMscByFilters(" extract(day from  to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By  extract(day from  to_date(dateAppel,'YYMMDD')) ", where_liste);
					 		 }
		 		 else{
		 			data =statRemote.getMscByFilters(chd.getAxe_x(),chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By "+chd.getAxe_x(), where_liste);
		 		 }
			     
	 		 if(data!=null){
	 			SerieChart sch = new SerieChart();
	 			sch.setMap(data);
	 			sch.setName(operation+"("+chd.getList_axe_y().get(nb_y).getAxey()+")");
	 		linesSeries.add(sch);
	 		
	 		  
	 		 
	 		}
	 		}
	 	}
	 		
		 	
	 	
	
	 	
	 	
}
public void createBarChart(ChartDynamic chd ){

	barsSeries = new ArrayList<>();
	
	String name;
 	for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
 		for(int nb_op=0;nb_op<chd.getList_axe_y().get(nb_y).getOperations().size();nb_op++){
 			String operation = chd.getList_axe_y().get(nb_y).getOperations().get(nb_op);
	 		
	 		 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			data =statRemote.getMscBytranche(chd.getAxe_x(),chd.getList_axe_y().get(nb_y).getAxey()+")",table,operation+"(","Group By "+chd.getAxe_x(), where_liste);
		 		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y).getAxey()+")",table,operation+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(chd.getAxe_x().equals("typeDest")){
		 			data =statRemote.getMscByFilters("destination.typeDest",chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By destination.typeDest ", where_liste);
			 		}else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')",chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
			 		 }
			 		 else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
			 			data =statRemote.getMscByFilters(" extract(day from  to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By    to_date(dateAppel,'YYMMDD')", where_liste);
			 			
			 		 }
	 		 else{
	 			data =statRemote.getMscByFilters(chd.getAxe_x(),chd.getList_axe_y().get(nb_y).getAxey()+")",table, operation+"(","Group By "+chd.getAxe_x(), where_liste);
	 		 }
	 		if(data!=null){
	 			SerieChart sch = new SerieChart();
	 			sch.setMap(data);
	 			sch.setName(operation+"("+chd.getList_axe_y().get(nb_y).getAxey()+")");
	 		barsSeries.add(sch);
	 		
			    
			     
	 		}
 		}
		    
		}
	
	 	
	    
}
public void createPieChart(ChartDynamic chd){

	 	 List<Object[]> datapie = new ArrayList<>();
	 	if(chd.getAxe_x().equals("typeDest")){
	 		datapie =statRemote.getMscByFilters1("destination.typeDest",chd.getAxe_y()+")",table, chd.getOperation()+"(","Group By destination.typeDest ", where_liste);
	 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
	 			datapie =statRemote.getMscByFilters1("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getAxe_y()+")",table, chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
		 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
		 			datapie =statRemote.getMscByFilters1(" to_date(dateAppel,'YYMMDD')",chd.getAxe_y()+")",table, chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			datapie =statRemote.getMscByFilters1("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')",chd.getAxe_y()+")",table, chd.getOperation()+"(","Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
				 			datapie =statRemote.getMscByFilters1("trancheHoraire",chd.getAxe_y()+")",table, chd.getOperation()+"(","Group By trancheHoraire  ", where_liste);
				 			
				 		 }
 		 else{
 			datapie =statRemote.getMscByFilters1(chd.getAxe_x(),chd.getAxe_y()+")",table, chd.getOperation()+"(","Group By "+chd.getAxe_x(), where_liste);
 		 }
	 	if(datapie!=null && datapie.size()>0){
	 		System.out.println(datapie.size());
	 		piesSeries.add(datapie);
	 	
		     
		}
}
public void handlechange(){
	contient = true;
	LayoutUnit mainLayout = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:main_layout");
	System.out.println(mainLayout.getChildCount());

mainLayout.getChildren().clear();
BarsMap = new ArrayList<>();
linesMap= new ArrayList<>();
piesSeries = new ArrayList<>();
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
		

		UIComponent compLBMoisDeb =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_mois_debut");
		UIComponent compLBMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:lb_mois_fin");
		UIComponent compMoisDeb =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:cld_mois_debut");
		UIComponent compMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:cld_mois_fin");
		
		if(compJourLB0!=null){
			comp.getChildren().remove(compJourLB1);
			comp.getChildren().remove(compJourLB0);
			comp.getChildren().remove(compJourOutput0);
			comp.getChildren().remove(compJourOutput1);
		}
		if(compMoisDeb!=null){
	
		
			comp.getChildren().remove(compLBMoisDeb);
			comp.getChildren().remove(compLBMoisFin);
			comp.getChildren().remove(compMoisDeb);
			comp.getChildren().remove(compMoisFin);
			
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
			

			Calendar cld_mois_debut = new Calendar();
			cld_mois_debut.setId("cld_mois_debut");
			cld_mois_debut.setRequired(true);
			OutputLabel lb_mois_debut= new OutputLabel();
			lb_mois_debut.setId("lb_mois_debut");
			cld_mois_debut.setRequiredMessage("Veuillez choisir la date debut");
		ValueExpression valjour = createValueExpression("#{rp_gene.date_mois_debut}", Date.class);
		cld_mois_debut.setValueExpression("value", valjour);
		lb_mois_debut.setValue("Choisir Debut");
			Calendar cld_mois_fin = new Calendar();
			cld_mois_fin.setPattern("MM/yyyy");
			cld_mois_fin.setMask("true");
			cld_mois_debut.setPattern("MM/yyyy");
			cld_mois_debut.setMask("true");
			cld_mois_fin.setRequired(true);
			cld_mois_fin.setRequiredMessage("Veuillez choisir la date fin");
			cld_mois_fin.setId("cld_mois_fin");
			OutputLabel lb_mois_fin= new OutputLabel();
			lb_mois_fin.setId("lb_mois_fin");
		ValueExpression valjourfin = createValueExpression("#{rp_gene.date_mois_Fin}", Date.class);
		cld_mois_fin.setValueExpression("value", valjourfin);
		lb_mois_fin.setValue("Choisir Fin");
		
			
		
			comp.getChildren().add(lb_mois_debut);
			comp.getChildren().add(cld_mois_debut);
			comp.getChildren().add(lb_mois_fin);
			comp.getChildren().add(cld_mois_fin);
		
			
			/////////////////////////////
			
		}
		
			
			
		
		
		
		
}


       
        
         
    
	
		
		
			 
		
		
	    
		  
		 
		   
		
	 //System.out.println(comp2.getChildCount());

	    
		 
	





public void handlechange1(){
	LayoutUnit mainLayout = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("mainForm:main_layout");
	System.out.println(mainLayout.getChildCount());
System.out.println(choix_plan);
mainLayout.getChildren().clear();

BarsMap = new ArrayList<>();
linesMap= new ArrayList<>();
piesSeries = new ArrayList<>();
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


public List<Object[]> getData() {
	return data;
}
public void setData(List<Object[]> data) {
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

