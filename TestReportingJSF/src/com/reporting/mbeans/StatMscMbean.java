package com.reporting.mbeans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;



































import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.BehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.component.button.Button;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.chart.Chart;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.confirmdialog.ConfirmDialog;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.dnd.Draggable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectmanymenu.SelectManyMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.spinner.Spinner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.ChartDynamic;
import com.reporting.metier.entities.Datagrid;

import com.reporting.metier.entities.Noeud;
import com.reporting.metier.entities.Report;
import com.reporting.metier.entities.Result;
import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.entities.ToUse;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.GenericReportingRemote;
import com.reporting.metier.interfaces.NoeudRemote;
import com.reporting.metier.interfaces.ReportRemote;
import com.reporting.metier.interfaces.StatMscImplRemote;

@ManagedBean(name="stat_msc")
@ViewScoped
public class StatMscMbean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private  StatMscImplRemote statRemote;
	@EJB
	private ReportRemote reportRemote;
	@EJB
	private NoeudRemote noeudremote;
	
	
	@EJB
	private GenericReportingRemote genericReportRemote;
	
	
	private String libGraphe;
	
	
	public String getLibGraphe() {
		return libGraphe;
	}
	public void setLibGraphe(String libGraphe) {
		this.libGraphe = libGraphe;
	}
	private List<Noeud> liste_noeuds;
	private List<String> liste_noeuds_noms;
	
	private String choix_table;
	
	
	public String getChoix_table() {
		return choix_table;
	}
	public void setChoix_table(String choix_table) {
		this.choix_table = choix_table;
	}
	private boolean choice= false;
	private boolean ChartChoose= false;
	public boolean isChartChoose() {
		return ChartChoose;
	}
	public void setChartChoose(boolean chartChoose) {
		ChartChoose = chartChoose;
	}
	
	public boolean isChoice() {
		return choice;
	}
	private Draggable opet;
	private Draggable opet1;
	private Draggable opet2;
	private Draggable opet3;
	private Date dtTest;
	private BigDecimal duree_filter;
	private ReportConfiguration report_configuration;
	private List<AxeY> fin_AxeYLst;
	
	
	public List<AxeY> getFin_AxeYLst() {
		return fin_AxeYLst;
	}
	public void setFin_AxeYLst(List<AxeY> fin_AxeYLst) {
		this.fin_AxeYLst = fin_AxeYLst;
	}
	private List<AxeY> AxeYOperations;
	public List<AxeY> getAxeYOperations() {
		return AxeYOperations;
	}
	public void setAxeYOperations(List<AxeY> axeYOperations) {
		AxeYOperations = axeYOperations;
	}
	private boolean lineorBar = false;
	
	public boolean isLineorBar() {
		return lineorBar;
	}
	public void setLineorBar(boolean lineorBar) {
		this.lineorBar = lineorBar;
	}

	public ReportConfiguration getReport_configuration() {
		return report_configuration;
	}
	public void setReport_configuration(ReportConfiguration report_configuration) {
		this.report_configuration = report_configuration;
	}
	private String lib_report;
	private List<String> List_Axe_Y = new ArrayList<String>();
	private String choix_noeud_nom;
	
	private List<ChartDynamic> lst_charts = new ArrayList<ChartDynamic>();
	private List<Datagrid> lst_grids = new ArrayList<Datagrid>();
	private int width = 600;
	public String actiontype;
	public List<String> list1 = new ArrayList<String>(); 
 
	private int x =2;
	
	public List<String> getListe_noeuds_noms() {
		return liste_noeuds_noms;
	}
	public void setListe_noeuds_noms(List<String> liste_noeuds_noms) {
		this.liste_noeuds_noms = liste_noeuds_noms;
	}
	private Map<Object, Number> listStatMsc = new HashMap<Object, Number>();
	
	


	
	private List<String> msc_fields= new ArrayList<String>();
	private List<String> msc_fields1= new ArrayList<String>();
	
	private List<String> listParDate = new ArrayList<String>();
	
	private String choixDateFilter;
	protected List<Integer> simpleList;
	
	private List<String> opertions;
	
	
	public List<String> getOpertions() {
		return opertions;
	}
	public void setOpertions(List<String> opertions) {
		this.opertions = opertions;
	}
    
    public List<Integer> getSimpleList() {
        return simpleList;
    }           
	
	private Date date;

	private String typeAxe ;
	private String typeAxeY ;
	private List<Filter> liste_filters = new ArrayList<Filter>();
	private List<String> liste_filters_selected = new ArrayList<String>();
	private String chartType;
	
	
	
	private List<SerieChart> LinesSeries;
	private List<SerieChart> BarSeries;
	private List<List<Object[]>> PieSeries;
	
	public List<SerieChart> getBarSeries() {
		return BarSeries;
	}
	public void setBarSeries(List<SerieChart> barSeries) {
		BarSeries = barSeries;
	}
	public List<SerieChart> getLinesSeries() {
		return LinesSeries;
	}
	public void setLinesSeries(List<SerieChart> linesSeries) {
		LinesSeries = linesSeries;
	}
	public List<List<Object[]>> getPieSeries() {
		return PieSeries;
	}
	public void setPieSeries(List<List<Object[]>> pieSeries) {
		PieSeries = pieSeries;
	}
	
private List<HighChart> BarMapList;

private List<HighChart> LineMapList;

private List<List<Map<String,Number>>> PieMapList;


public List<List<Map<String, Number>>> getPieMapList() {
	return PieMapList;
}

public void setPieMapList(List<List<Map<String, Number>>> pieMapList) {
	PieMapList = pieMapList;
}
public List<HighChart> getBarMapList() {
	return BarMapList;
}
public List<HighChart> getLineMapList() {
	return LineMapList;
}
public void setBarMapList(List<HighChart> barMapList) {
	BarMapList = barMapList;
}
public void setLineMapList(List<HighChart> lineMapList) {
	LineMapList = lineMapList;
}

	
	private Map<String, String> listeAxeY;
	private Map<String, String> OperationsY;
	private Map<String, Map<String, Boolean>> AxeOperations;
	
	public Map<String, Map<String, Boolean>> getAxeOperations() {
		return AxeOperations;
	}
	public Map<String, String> getListeAxeY() {
		return listeAxeY;
	}
	public Map<String, String> getOperationsY() {
		return OperationsY;
	}
	public void setAxeOperations(Map<String, Map<String, Boolean>> axeOperations) {
		AxeOperations = axeOperations;
	}
	public void setListeAxeY(Map<String, String> listeAxeY) {
		this.listeAxeY = listeAxeY;
	}
	public void setOperationsY(Map<String, String> operationsY) {
		OperationsY = operationsY;
	}

	private Report p = new Report();
	
	

	
	public StatMscMbean() {
		// TODO Auto-generated constructor stub
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
public String getActiontype() {
	return actiontype;
}
public void setActiontype(String actiontype) {
	this.actiontype = actiontype;
}
public List<String> getMsc_fields() {
	return msc_fields;
}
public void setMsc_fields(List<String> msc_fields) {
	this.msc_fields = msc_fields;
}

public void handle_tableChoice() throws SecurityException, ClassNotFoundException{
	msc_fields = new ArrayList<>();
	msc_fields1 = new ArrayList<>();
	liste_filters = new ArrayList<>();
	
	Class c = Class.forName(choix_table);
	for(int j=2; j<c.getDeclaredFields().length;j++){
		if(c.getDeclaredFields()[j].getGenericType()==BigDecimal.class){
			System.out.println(c.getDeclaredFields()[j].getGenericType());
			//System.out.println(StatMsc.class.getDeclaredFields()[j].getType());
			msc_fields1.add(c.getDeclaredFields()[j].getName());
		}else if(c.getDeclaredFields()[j].isAnnotationPresent(ManyToOne.class)==false) {
			
			msc_fields.add(c.getDeclaredFields()[j].getName());
		}else if(c.getDeclaredFields()[j].isAnnotationPresent(ManyToOne.class)){
			Class co = c.getDeclaredFields()[j].getType();
			for(int i=0;i<co.getDeclaredFields().length;i++){
				if(co.getDeclaredFields()[i].isAnnotationPresent(ToUse.class)){
					msc_fields.add(c.getDeclaredFields()[j].getName()+"."+co.getDeclaredFields()[i].getName());
				}
			}
			
		}
		
	}
	for(int j=2; j<c.getDeclaredFields().length;j++){
		if(c.getDeclaredFields()[j].getGenericType()==String.class){
			Filter f =new Filter();
			f.setLabel(c.getDeclaredFields()[j].getName());
			f.setValue(c.getDeclaredFields()[j].getName());
			liste_filters.add(f);
		}else if(c.getDeclaredFields()[j].isAnnotationPresent(ManyToOne.class)){
			Field[] fields= c.getDeclaredFields()[j].getType().getDeclaredFields();
			
					 Filter f =new Filter();
						f.setLabel(c.getDeclaredFields()[j].getName().toString());
						f.setValue(c.getDeclaredFields()[j].getName().toString());
						System.out.println(c.getDeclaredFields()[j].getType().getName());
						liste_filters.add(f);
			
			
		}
		listeAxeY = new LinkedHashMap<>();
		for(int k=0;k<msc_fields1.size();k++){
			
			listeAxeY.put( msc_fields1.get(k), msc_fields1.get(k));
		}
		OperationsY = new LinkedHashMap<>();
		OperationsY.put("SUM", "SUM");
		OperationsY.put("AVG", "AVG");
		OperationsY.put("Count", "Count");
		AxeOperations = new HashMap<>();
			    for (String office : listeAxeY.keySet()) {
			    	AxeOperations.put(office, new HashMap<String, Boolean>());
			    }
			    
			    LineMapList = new ArrayList<>();
			    BarMapList = new ArrayList<>();
			    PieSeries = new ArrayList<>();
	}
	LayoutUnit colFilters = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:colFilters");
	colFilters.getChildren().clear(); 
	SelectManyMenu menu_filters = (SelectManyMenu) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:filters_menu");
	   FacesContext facesCtx = FacesContext.getCurrentInstance();
       ELContext elContext = facesCtx.getELContext();
       Application app = facesCtx.getApplication();
       ExpressionFactory elFactory = app.getExpressionFactory();
       LayoutUnit component = (LayoutUnit) facesCtx.getViewRoot().findComponent("form1:myPanelGrid");
	    
		  if(menu_filters==null){
			  menu_filters = new SelectManyMenu();
	    	   menu_filters.setId("filters_menu");
	    	   ValueExpression valueExp5 = elFactory.createValueExpression(elContext, "#{stat_msc.liste_filters_selected}",List.class);
	    	   ValueExpression valueExp6 = elFactory.createValueExpression(elContext, "filter",Object.class);
	    	   ValueExpression valueExp7 = elFactory.createValueExpression(elContext, "#{filter.label}",Object.class);
	    	   ValueExpression valueExp8 = elFactory.createValueExpression(elContext, "#{filter.value}",Object.class);
	    	   UISelectItems list_items3 = new UISelectItems();
	    	   
	    	   menu_filters.setValueExpression("value", valueExp5);
	    	   list_items3.setValue(liste_filters);
	    	   list_items3.setValueExpression("var", valueExp6);
	    	   list_items3.setValueExpression("itemLabel", valueExp7);
	    	   list_items3.setValueExpression("itemValue", valueExp8);
	    	   menu_filters.setShowCheckbox(true);
	    	   menu_filters.getChildren().add(list_items3);
	    	 
	    	   
	    	   colFilters.getChildren().add(menu_filters);
		  }
		  create_Lib_Save();
 	  
	choice=true;
}
	
	@PostConstruct
	public void init() throws ParseException, SecurityException, ClassNotFoundException{
		
	
	dtTest=statRemote.getMSCDate();
	System.out.println(dtTest);
			list1.add("Aucun");
			list1.add("SUM");
			list1.add("AVG");
			list1.add("COUNT");
			report_configuration = new ReportConfiguration();
			//simpleList = new ArrayList<>();
			report_configuration.getListeActions().add("Aucun");
			report_configuration.getListeActions().add("SUM");
			report_configuration.getListeActions().add("AVG");
			report_configuration.getListeActions().add("COUNT");
	        //Random r = new Random();
	        
			
			
		listParDate.add("Par heure");
		listParDate.add("Par Jour");
		listParDate.add("Par mois");
		listParDate.add("Par An");
		liste_noeuds = noeudremote.getNoeudNoms();
		liste_noeuds_noms = new ArrayList<String>();
		for(int noeud_nb=0;noeud_nb<liste_noeuds.size();noeud_nb++){
			liste_noeuds_noms.add(liste_noeuds.get(noeud_nb).getCodeNoeud());
			System.out.println(liste_noeuds.get(noeud_nb).getCodeNoeud());
		}
		
		
		

		 
		
		
	
	}
	public List<String> getMsc_fields1() {
		return msc_fields1;
	}
	
	
	
	
	
	public Map<Object,Number> getListStatMsc() {
		return listStatMsc;
	}
	public void setListStatMsc(Map<Object,Number> listStatMsc) {
		this.listStatMsc = listStatMsc;
	}
	
	
 
	
	 
	 public void deleteDialog(){
		 UIComponent component1 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
		 UIComponent comp2 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:configuration_dialog");
		 component1.getChildren().remove(comp2);
		 RequestContext.getCurrentInstance().update("form1:myPanelGrid");
	 }
	 
	
	 
	 public void create_Lib_Save(){
		 
		 FacesContext facesCtx = FacesContext.getCurrentInstance();
	       ELContext elContext = facesCtx.getELContext();
	       Application app = facesCtx.getApplication();
	       ExpressionFactory elFactory = app.getExpressionFactory();
	       LayoutUnit component = (LayoutUnit) facesCtx.getViewRoot().findComponent("form1:myPanelGrid");
	       LayoutUnit sauvegard = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:sauvegard");
	   	
			 if (component != null) {
		 UIComponent componentLib = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tx_rapport");
	        UIComponent componentBtn = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:btnsave");
	       
	       
	    	 
	    	   //PanelGrid pgall = (PanelGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:pgall");
	    	  // pgall.setStyle("border-style: hidden;");
	    	 
	    	   
		    	   LayoutUnit ColChart = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ChartCol");
		    	 
		   
	    	   
	    	   
	       
	       if (componentLib == null) {
	        	
	        	//pgGrid.setStyle("margin-left:500px;");
	        	OutputLabel lb_rapport = new OutputLabel();
			   	 InputText tx_rapport = new InputText();
			   	lb_rapport.setValue("Libelle Rapport:");
				 lb_rapport.setFor("tx_rapport");
				// tx_rapport.setStyle("width:100px;");
				 tx_rapport.setId("tx_rapport");
				 
				ValueExpression value_input = elFactory.createValueExpression(elContext,"#{stat_msc.lib_report}",Object.class);
				 tx_rapport.setValueExpression("value", value_input);
				 
				 sauvegard.getChildren().add(lb_rapport);
				 sauvegard.getChildren().add(tx_rapport);
				 
				 
				
				 
	        
	        if (componentBtn == null) {
	        	 CommandButton btnsave = new CommandButton();
			        btnsave.setId("btnsave");
			        btnsave.setValue("Sauvegarder Rapport");
			   
			        MethodExpression methodExpression1 =null;
	  methodExpression1 = elFactory.createMethodExpression(elContext,"#{stat_msc.createRapport}",null, new Class[]{});
	  btnsave.setActionExpression(methodExpression1);
	  sauvegard.getChildren().add(btnsave);
	        }
	       
		  
	        
	        //component.getChildren().add(pgall);
			 }
			 }	 
		 
	 }
	 
	
	 

	 
	 public void ReportConfiguration(DragDropEvent event){
		System.out.println(event.getDragId());
			if(event.getDragId().equals("form1:line_chart")){
				 LayoutUnit component = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    setLineorBar(true);
				 if (component != null) {
					 
					 chartType="line";
					// report_configuration.createDialog(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.list_Axe_Y}");
				      
				       
				       
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				     
				        opet = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet);
				        requestContext.update("form1:availablePlayers");
				   
				}
			}else if(event.getDragId().equals("form1:pie")){
				 LayoutUnit component = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    setLineorBar(false);
				 if (component != null) {
					 chartType="pie";
					// report_configuration.createDialog1(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.typeAxeY}");
				       
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet2 = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet2);
				        requestContext.update("form1:availablePlayers");
				   
				}
			}else if(event.getDragId().equals("form1:bar")){
				 LayoutUnit component = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    setLineorBar(true);
				 if (component != null) {
					 chartType="bar";
					// report_configuration.createDialog(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.list_Axe_Y}");
				       
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet1 = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet1);
				        requestContext.update("form1:availablePlayers");
				   
				}
				 
			}else if(event.getDragId().equals("form1:grid")){
				 LayoutUnit component = (LayoutUnit) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    setLineorBar(true);
				 if (component != null) {
					 chartType="grid";
					// report_configuration.createDialog1(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.typeAxeY}");
					
				    
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet1 = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet1);
				        requestContext.update("form1:availablePlayers");
				   
				}
			}
		
		 
	 }

	 
	
private List<Object[]> initGridModel(){
	String action;
	String groupby;
	Datagrid grid = new Datagrid();
	List<Object[]> list = new ArrayList<>(); ;
	int endIndex = choix_table.lastIndexOf(".")+1;
	String table1 = choix_table.substring(endIndex, choix_table.length());
	 AxeYOperations = new ArrayList<>();
	 for (Entry<String, Map<String, Boolean>> entry : AxeOperations.entrySet()) {
		 AxeY axeY =  new AxeY();
		 opertions = new ArrayList<>();
	        String office = entry.getKey();
	        
	        Map<String, Boolean> services = entry.getValue();
	        for(Entry e: services.entrySet()){
	        	if((boolean) e.getValue()==true){
	        		opertions.add(e.getKey().toString());
	        		System.out.println(e.getKey().toString());
	        	}
	        }
	        if(opertions.size()>0){
	        	axeY.setAxey(office);
	        	axeY.setOperations(opertions);
	        	 axeY.setGridD(grid);
		        AxeYOperations.add(axeY);
		       

	        }
	        
	    }
	 List<String> where = new ArrayList<>();
	 if(AxeYOperations.isEmpty()==false){
		 if(typeAxe.contains("date")){
			 list=statRemote.getAllStatMsc1("to_date("+typeAxe+",'YYMMDD')", AxeYOperations, table1, "", "", where);
		 }else{
			 list=statRemote.getAllStatMsc1(typeAxe, AxeYOperations, table1, "", "", where);
		 }
		
			FacesContext facesCtx = FacesContext.getCurrentInstance();
		    ELContext elContext = facesCtx.getELContext();
		    Application app = facesCtx.getApplication();
			 ExpressionFactory elFactory = app.getExpressionFactory();
			DataTable table = (DataTable) app.createComponent(DataTable.COMPONENT_TYPE);
			
			
			 table.setValue(list);
			 table.setVar("exam");
			 table.setWidgetVar("examTable");
			 table.setEmptyMessage("aucun résultat trouvé pour votre recherche");

			 table.setPaginator(true);
			 table.setPaginatorPosition("bottom");
			 table.setRows(5);
			 UIOutput HeaderColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
			 HeaderColumnTitle.setValue(libGraphe);
			 table.setHeader(HeaderColumnTitle);
			 table.setPaginatorTemplate("{CurrentPageReport} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}");
			 table.setRowsPerPageTemplate("10,25,50,100");

			 
			Column indexColumn = (Column) app.createComponent(Column.COMPONENT_TYPE);
			 UIOutput indexColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
			 if(typeAxe.contains(".")){
				 int b = typeAxe.lastIndexOf(".");
				 String s = typeAxe.substring(0,b);
				 indexColumnTitle.setValue(s);
			 }else{
				 indexColumnTitle.setValue(typeAxe);
			 }
		
			 indexColumn.getFacets().put("header", indexColumnTitle);
			 //table.getColumns().add(column);
			 table.getChildren().add(indexColumn);
		 ValueExpression indexValueExp = elFactory.createValueExpression(elContext, "#{exam[0]}", Object.class);
			 HtmlOutputText indexOutput = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
			 indexOutput.setValueExpression("value", indexValueExp);
			 indexColumn.getChildren().add(indexOutput);
			 int nbr=0;
			 for(int nb=0;nb<AxeYOperations.size();nb++){
				 nbr=nbr+AxeYOperations.get(nb).getOperations().size();
			 }
			 System.out.println(nbr);
			 int nb=1;
			 for(int k=0;k<AxeYOperations.size();k++){
				 String name = AxeYOperations.get(k).getAxey();
				 for (int p=0;p<AxeYOperations.get(k).getOperations().size();p++){
					
					 Column nameColumn = new Column();
					 UIOutput nameColumnTitle = (UIOutput) app.createComponent(UIOutput.COMPONENT_TYPE);
					 nameColumnTitle.setValue(AxeYOperations.get(k).getOperations().get(p)+"("+name+")");
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
			
			 UIComponent component1 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
			// UIComponent comp2 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:configuration_dialog");
			 UIComponent compColChart = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ChartCol");
			 component1.getChildren().add(table);
			 
			 grid.setDaxe_x(typeAxe);
			 grid.setList_axe_y(AxeYOperations);
			 grid.setReport_grid(p);
			   //dg.setList_axe_y(AxeYOperations);
			   //dg.setOperationD(actiontype);
			   lst_grids.add(grid);
			 //  component1.getChildren().remove(comp2);
			
				//component1.getChildren()
				 
			    RequestContext requestContext = RequestContext.getCurrentInstance();
				 requestContext.update("form1:myPanelGrid");
		 
	 }

   
    
     
    return list;

}

public void submit(){
	
	
	
	 Report p = new Report();
	 ChartDynamic cd= new ChartDynamic();
	 cd.setAxe_x("dateAppel");
	 cd.setList_axe_y(AxeYOperations);
		int endIndex = choix_table.lastIndexOf(".")+1;
		String table = choix_table.substring(endIndex, choix_table.length());
	 p.setTable_name(table);
	 List<ChartDynamic> cdh = new ArrayList<>();
	 cdh.add(cd);
	 p.setLst_chart(cdh);
	
	 reportRemote.addReport(p);
}

	private void initLineModel() {
		
		int endIndex = choix_table.lastIndexOf(".")+1;
		String table = choix_table.substring(endIndex, choix_table.length());
			String action;
			String groupby;
			//ChartDynamic chartdynamique = new ChartDynamic();
			
			ChartDynamic chd = new ChartDynamic();
		
			 AxeYOperations = new ArrayList<>();
			 for (Entry<String, Map<String, Boolean>> entry : AxeOperations.entrySet()) {
				 AxeY axeY =  new AxeY();
				 opertions = new ArrayList<>();
			        String office = entry.getKey();
			        
			        Map<String, Boolean> services = entry.getValue();
			        for(Entry e: services.entrySet()){
			        	if((boolean) e.getValue()==true){
			        		opertions.add(e.getKey().toString());
			        		System.out.println(e.getKey().toString());
			        	}
			        }
			        if(opertions.size()>0){
			        	axeY.setAxey(office);
			        	axeY.setOperations(opertions);
			        	 axeY.setChartD(chd);
				        AxeYOperations.add(axeY);
				  
			        }
			        
			    }
			 LinesSeries = new ArrayList<>();
			 
			 for(AxeY a: AxeYOperations){
				
				
				
					 for(String bs: a.getOperations()){
						
							
								
								
								 groupby= "Group By ";
								
									SerieChart sch = new SerieChart();
									
									sch.setMap(statRemote.getConfiguartionChartByCfg(typeAxe, a.getAxey()+")",table, bs+"(",groupby+typeAxe));
									System.out.println(sch.getMap().size());
									sch.setName(bs+"("+a.getAxey()+")");
									
									
								        LinesSeries.add(sch);
								 
								
							      
							        
							
					 }
				 
				
			 }
HighChart hichart = new HighChart();
hichart.setName(libGraphe);
hichart.setSeries(LinesSeries);
			 LineMapList.add(hichart);
			 System.out.println(LineMapList.size());
			 
			 chd.setAxe_x(typeAxe);
			 chd.setList_axe_y(AxeYOperations);
			 chd.setType_chart("line");
			 chd.setReport_chart(p);
			 lst_charts.add(chd);
			 ChartChoose = true;
	      
		
		
    }
	private void initPieModel() {
		ChartChoose = true;
		int endIndex = choix_table.lastIndexOf(".")+1;
		String table = choix_table.substring(endIndex, choix_table.length());
		String action;
		String groupby;
		ChartDynamic chartdynamique = new ChartDynamic();

		
		
				switch (actiontype) {
				case "SUM":
					action="SUM(";
					
					 groupby= "Group By ";
				    // System.out.println(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")", action,groupby+typeAxe).size());
				        PieSeries.add(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")",table, action,groupby+typeAxe));
				      
				        
				        
					break;
				case "AVG":
					action="AVG(";
				
					groupby= "Group By ";
					 PieSeries.add(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")",table, action,groupby+typeAxe));
					break;
				case "COUNT":
					action="COUNT(";
				
					groupby= "Group By ";
					 PieSeries.add(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")",table, action,groupby+typeAxe));
					break;
				

				default:
					break;
				}
				
				chartdynamique.setAxe_x(typeAxe);
				chartdynamique.setAxe_y(typeAxeY);
				chartdynamique.setOperation(actiontype);
				chartdynamique.setType_chart("pie");
				chartdynamique.setReport_chart(p);
				lst_charts.add(chartdynamique);
				
		       
		 }
		
        
	
private void createGrid(){

	   
}

	private void initBarModel() {
		
		int endIndex = choix_table.lastIndexOf(".")+1;
		String table = choix_table.substring(endIndex, choix_table.length());
		String action;
		String groupby;
		//ChartDynamic chartdynamique = new ChartDynamic();
		
		ChartDynamic chd = new ChartDynamic();
	
		 AxeYOperations = new ArrayList<>();
		 for (Entry<String, Map<String, Boolean>> entry : AxeOperations.entrySet()) {
			 AxeY axeY =  new AxeY();
			 opertions = new ArrayList<>();
		        String office = entry.getKey();
		        
		        Map<String, Boolean> services = entry.getValue();
		        for(Entry e: services.entrySet()){
		        	if((boolean) e.getValue()==true){
		        		opertions.add(e.getKey().toString());
		        		System.out.println(e.getKey().toString());
		        	}
		        }
		        if(opertions.size()>0){
		        	axeY.setAxey(office);
		        	axeY.setOperations(opertions);
		        	 axeY.setChartD(chd);
			        AxeYOperations.add(axeY);
			       

		        }
		        
		    }
		 if(AxeYOperations.isEmpty()==false){
			 BarSeries = new ArrayList<>();
			 for(AxeY a: AxeYOperations){
				
			
				
					 for(String bs: a.getOperations()){
						 
								
								 groupby= "Group By ";

								 SerieChart sch = new SerieChart();
								 sch.setMap(statRemote.getConfiguartionChartByCfg(typeAxe, a.getAxey()+")",table, bs+"(",groupby+typeAxe));
								 sch.setName(bs+"("+a.getAxey()+")");
								BarSeries.add(sch);
							      
							        
							
					 }
					
				 
				
			 }
			 HighChart hichart = new HighChart();
			 hichart.setName(libGraphe);
			 hichart.setSeries(BarSeries);
			 BarMapList.add(hichart);
			 
			 
			 chd.setAxe_x(typeAxe);
			 chd.setList_axe_y(AxeYOperations);
			 chd.setType_chart("bar");
			 chd.setReport_chart(p);
			 lst_charts.add(chd);
			 ChartChoose = true;
		 }else{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez Choisir Operations" ) );
		 }
			
		
}
	
	
	
	
public void createChart(){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ceci est un simple Preview"));
	if(chartType=="line"){
		initLineModel();
		
	}else if(chartType=="bar"){
		initBarModel();
	}else if(chartType=="pie"){
		initPieModel();
	}else{
		initGridModel();
	}

	
	 
}

	
	

	public int getWidth() {
		return width;
	}
 



	public void setWidth(int width) {
		this.width = width;
	}
	public String getTypeAxe() {
		return typeAxe;
	}
	public void setTypeAxe(String typeAxe) {
		this.typeAxe = typeAxe;
	}
	public String getTypeAxeY() {
		return typeAxeY;
	}
	public void setTypeAxeY(String typeAxeY) {
		this.typeAxeY = typeAxeY;
	}
	public List<Filter> getListe_filters() {
		return liste_filters;
	}
	public void setListe_filters_selected(List<String> liste_filters_selected) {
		this.liste_filters_selected = liste_filters_selected;
	}
	public void setListe_filters(List<Filter> liste_filters) {
		this.liste_filters = liste_filters;
	}
	public List<String> getListe_filters_selected() {
		return liste_filters_selected;
	}
	public List<String> getListParDate() {
		return listParDate;
	}
	public void setListParDate(List<String> listParDate) {
		this.listParDate = listParDate;
	}
	public String getChoixDateFilter() {
		return choixDateFilter;
	}
	public void setChoixDateFilter(String choixDateFilter) {
		this.choixDateFilter = choixDateFilter;
	}
	public void changeLs(){
		System.out.println("work");
	}
	public Report getP() {
		return p;
	}
	public void setP(Report p) {
		this.p = p;
	}
	public void createRapport() throws IOException{
	
		p.setLib_rapport(lib_report);
		int endIndex = choix_table.lastIndexOf(".")+1;
		String table = choix_table.substring(endIndex, choix_table.length());
		p.setTable_name(table);
	p.setLst_chart(lst_charts);
	  p.setFilters_report(liste_filters_selected);
	  p.setLst_datagrid(lst_grids);
	
	reportRemote.addReport(p);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("rapport.jsf?");
		//statRemote.getTest();
	}
	public List<ChartDynamic> getLst_charts() {
		return lst_charts;
	}
	

	public void setLst_charts(List<ChartDynamic> lst_charts) {
		this.lst_charts = lst_charts;
	}
	public Draggable getOpet() {
		return opet;
	}
	public void setOpet(Draggable opet) {
		this.opet = opet;
	}
	public Draggable getOpet1() {
		return opet1;
	}
	public void setOpet1(Draggable opet1) {
		this.opet1 = opet1;
	}
	public String getLib_report() {
		return lib_report;
	}
	public void setLib_report(String lib_report) {
		this.lib_report = lib_report;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getDuree_filter() {
		return duree_filter;
	}
	public void setDuree_filter(BigDecimal duree_filter) {
		this.duree_filter = duree_filter;
	}
	public Draggable getOpet2() {
		return opet2;
	}
	public void setOpet2(Draggable opet2) {
		this.opet2 = opet2;
	}
	public String getChoix_noeud_nom() {
		return choix_noeud_nom;
	}
	public void setChoix_noeud_nom(String choix_noeud_nom) {
		this.choix_noeud_nom = choix_noeud_nom;
	}
	public Draggable getOpet3() {
		return opet3;
	}
	public void setOpet3(Draggable opet3) {
		this.opet3 = opet3;
	}
	public List<String> getList_Axe_Y() {
		return List_Axe_Y;
	}
	public void setList_Axe_Y(List<String> list_Axe_Y) {
		List_Axe_Y = list_Axe_Y;
	}
	public Date getDtTest() {
		return dtTest;
	}
	public void setDtTest(Date dtTest) {
		this.dtTest = dtTest;
	}
	

}
