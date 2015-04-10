package com.reporting.mbeans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.reporting.metier.entities.ChartDynamic;
import com.reporting.metier.entities.Datagrid;
import com.reporting.metier.entities.Noeud;
import com.reporting.metier.entities.Report;
import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.entities.TypeDestination;
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
	private List<Noeud> liste_noeuds;
	private List<String> liste_noeuds_noms;
	private Draggable opet;
	private Draggable opet1;
	private Draggable opet2;
	private Draggable opet3;
	private Date dtTest;
	private BigDecimal duree_filter;
	private ReportConfiguration report_configuration;

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
    
    public List<Integer> getSimpleList() {
        return simpleList;
    }           
	
	private Date date;

	private String typeAxe ;
	private String typeAxeY ;
	private List<String> liste_filters = new ArrayList<String>();
	private List<String> liste_filters_selected = new ArrayList<String>();
	private String chartType;

	private Report p;

	
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
	
	@PostConstruct
	public void init() throws ParseException{
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
	        
			for(int j=3; j<StatMsc.class.getDeclaredFields().length;j++){
				if(StatMsc.class.getDeclaredFields()[j].getGenericType()==BigDecimal.class){
					System.out.println(StatMsc.class.getDeclaredFields()[j].getGenericType());
					//System.out.println(StatMsc.class.getDeclaredFields()[j].getType());
					msc_fields1.add(StatMsc.class.getDeclaredFields()[j].getName());
				}else {
					msc_fields.add(StatMsc.class.getDeclaredFields()[j].getName());
				}
				
			}
			for(int j=3; j<StatMsc.class.getDeclaredFields().length;j++){
				if(StatMsc.class.getDeclaredFields()[j].getGenericType()==String.class){
					liste_filters.add(StatMsc.class.getDeclaredFields()[j].getName());
				}
				liste_filters.add("typeDest");
			}
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
	       UIComponent component = facesCtx.getViewRoot().findComponent("form1:myPanelGrid");
		    
			 if (component != null) {
		 UIComponent componentLib = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tx_rapport");
	        UIComponent componentBtn = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:btnsave");
	        UIComponent componentpg = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:pgall");
	       if(componentpg==null){
	    	  
	    	   PanelGrid pgall = new PanelGrid();
	    	   pgall.setStyle("border-style: hidden;");
	    	   pgall.setId("pgall");
	    	   Column colFilters = new Column();
	    	   Panel panel_filters = new Panel();
	    	   panel_filters.setStyle("height:580px;overflow:hidden;width:225;");
	    	   panel_filters.setHeader("Choisir Filtres");
	    	   SelectManyMenu menu_filters = new SelectManyMenu();
	    	   ValueExpression valueExp5 = elFactory.createValueExpression(elContext, "#{stat_msc.liste_filters_selected}",Object.class); 
	    	   UISelectItems list_items3 = new UISelectItems();
	    	   menu_filters.setValueExpression("value", valueExp5);
	    	   list_items3.setValue(liste_filters);
	    	   menu_filters.setShowCheckbox(true);
	    	   menu_filters.getChildren().add(list_items3);
	    	   panel_filters.getChildren().add(menu_filters);
	    	   
	    	   colFilters.getChildren().add(panel_filters);
	    	   Column ColChart = new Column();
	    	   ColChart.setId("ChartCol");
	       pgall.getChildren().add(colFilters);
	       pgall.getChildren().add(ColChart);
	       
	       if (componentLib == null) {
	        	PanelGrid pgGrid = new PanelGrid();
	        	//pgGrid.setStyle("margin-left:500px;");
	        	OutputLabel lb_rapport = new OutputLabel();
			   	 InputText tx_rapport = new InputText();
			   	lb_rapport.setValue("Libelle Rapport");
				 lb_rapport.setFor("tx_rapport");
				// tx_rapport.setStyle("width:100px;");
				 tx_rapport.setId("tx_rapport");
				ValueExpression value_input = elFactory.createValueExpression(elContext,"#{stat_msc.lib_report}",Object.class);
				 tx_rapport.setValueExpression("value", value_input);
				 
				 pgGrid.getChildren().add(lb_rapport);
				 pgGrid.getChildren().add(tx_rapport);
				 
				 component.getChildren().add(pgGrid);
				
				 
	        }
	        if (componentBtn == null) {
	        	 CommandButton btnsave = new CommandButton();
			        btnsave.setId("btnsave");
			        btnsave.setValue("Sauvegarder Rapport");
			        btnsave.setStyle("margin-left:900px;");
			        MethodExpression methodExpression1 =null;
	  methodExpression1 = elFactory.createMethodExpression(elContext,"#{stat_msc.createRapport}",null, new Class[]{});
	  btnsave.setActionExpression(methodExpression1);
	  component.getChildren().add(btnsave);
	        }
	       
		  
	        
	        component.getChildren().add(pgall);
			 }
			 }	 
		 
	 }
	 
	
	 

	 
	 public void ReportConfiguration(DragDropEvent event){
		
			if(event.getDragId().equals("form1:availablePlayers:0:line_chart")){
				 UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    
				 if (component != null) {
					 chartType="line";
					 report_configuration.createDialog(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.list_Axe_Y}");
				      
				       
				       create_Lib_Save();
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet);
				        requestContext.update("form1:availablePlayers");
				   
				}
			}else if(event.getDragId().equals("form1:availablePlayers:0:pie")){
				 UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    
				 if (component != null) {
					 chartType="pie";
					 report_configuration.createDialog1(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.typeAxeY}");
				       create_Lib_Save();
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet2 = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet2);
				        requestContext.update("form1:availablePlayers");
				   
				}
			}else if(event.getDragId().equals("form1:availablePlayers:0:bar")){
				 UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    
				 if (component != null) {
					 chartType="bar";
					 report_configuration.createDialog(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.list_Axe_Y}");
				       create_Lib_Save();
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet1 = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet1);
				        requestContext.update("form1:availablePlayers");
				   
				}
				 
			}else if(event.getDragId().equals("form1:availablePlayers:0:grid")){
				 UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
				    
				 if (component != null) {
					 chartType="grid";
					 report_configuration.createDialog1(component, msc_fields, msc_fields1, "#{stat_msc.createChart}", "#{stat_msc.deleteDialog}", "#{stat_msc.actiontype}", "#{stat_msc.typeAxe}", "#{stat_msc.typeAxeY}");
					
				       create_Lib_Save();
				        
				        RequestContext requestContext = RequestContext.getCurrentInstance();
				        requestContext.update("form1:myPanelGrid");
				        opet1 = new Draggable();
				        FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:availablePlayers").getChildren().add(opet1);
				        requestContext.update("form1:availablePlayers");
				   
				}
			}
		
		 
	 }

	 
	
private Object[] initGridModel(){
	String action;
	String groupby;
	Object[] list = null ;
	switch (actiontype) {
	case "SUM":
		action="SUM(";
		
		 groupby= "Group By ";
	 
	       list= statRemote.getConfiguartionChartByCfg(typeAxe, typeAxeY+")", action,groupby+typeAxe).entrySet().toArray();
	      
	        
	       
		break;
	case "AVG":
		action="AVG(";
	
		groupby= "Group By ";
		 list= statRemote.getConfiguartionChartByCfg(typeAxe, typeAxeY+")", action,groupby+typeAxe).entrySet().toArray();
		break;
	case "Aucun":
		action=" ";
	
		groupby= "Group By ";
		 list= statRemote.getConfiguartionChartByCfg(typeAxe, typeAxeY+")", action,groupby+typeAxe).entrySet().toArray();
		break;

	default:
		break;
	}
   
    
     
    return list;

}

	private LineChartModel initLineModel() {
	
			String action;
			String groupby;
			//ChartDynamic chartdynamique = new ChartDynamic();
			
			 LineChartModel modelchart = new LineChartModel();
			
			switch (actiontype) {
			case "SUM":
				action="SUM(";
				
				 groupby= "Group By ";
				for(int k =0;k<List_Axe_Y.size();k++){
					 ChartSeries boys = new ChartSeries();
					 boys.setLabel(typeAxe+" Par"+typeAxeY);
				        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(k)+")", action,groupby+typeAxe));
				      
				        
				        modelchart.addSeries(boys);
				}
				 
				
			      
			        
				break;
			case "AVG":
				action="AVG(";
			
				groupby= "Group By ";
				for(int k =0;k<List_Axe_Y.size();k++){
					 ChartSeries boys = new ChartSeries();
					 boys.setLabel(typeAxe+" Par "+List_Axe_Y.get(k));
				        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(k)+")", action,groupby+typeAxe));
				      
				        
				        modelchart.addSeries(boys);
				}
				break;
			case "Aucun":
				action=" ";
			
				groupby= "Group By ";
				for(int k =0;k<List_Axe_Y.size();k++){
					 ChartSeries boys = new ChartSeries();
					 boys.setLabel(typeAxe+" Par "+List_Axe_Y.get(k));
				        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(k)+")", action,groupby+typeAxe));
				      
				        
				        modelchart.addSeries(boys);
				}
				break;
			case "COUNT":
				action="COUNT(";
				
				 groupby= "Group By ";
				 for(int nb=0;nb<List_Axe_Y.size();nb++){
					 ChartSeries boys = new ChartSeries();
				        boys.setLabel(typeAxe+" Par"+" "+List_Axe_Y.get(nb));
				        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(nb)+")", action,groupby+typeAxe));
				      
				       
				        modelchart.addSeries(boys);
				 }
				
				break;

			default:
				break;
			}
	       
	        
	         
	        return modelchart;
		
		
    }
	private PieChartModel initPieModel() {
		
		String action;
		String groupby;
		//ChartDynamic chartdynamique = new ChartDynamic();
		
		 PieChartModel modelchart = new PieChartModel();
		  ChartSeries boys = new ChartSeries();
		switch (actiontype) {
		case "SUM":
			action="SUM(";
			
			 groupby= "Group By ";
		     System.out.println(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")", action,groupby+typeAxe).size());
		        modelchart.setData(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")", action,groupby+typeAxe));
		      
		        
		        
			break;
		case "AVG":
			action="AVG(";
		
			groupby= "Group By ";
			modelchart.setData(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")", action,groupby+typeAxe));
			break;
		case "COUNT":
			action="COUNT(";
		
			groupby= "Group By ";
			modelchart.setData(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY+")", action,groupby+typeAxe));
			break;
		case "Aucun":
			action="";
		
			groupby= "Group By ";
			modelchart.setData(statRemote.getConfiguartionChartByCnfg(typeAxe, typeAxeY, action,groupby+typeAxe));
			break;	
			

		default:
			break;
		}
       
        
         
        return modelchart;
	
	
}
	
private void createGrid(){
	FacesContext facesCtx = FacesContext.getCurrentInstance();
    ELContext elContext = facesCtx.getELContext();
    Application app = facesCtx.getApplication();
	 ExpressionFactory elFactory = app.getExpressionFactory();
	DataTable table = (DataTable) app.createComponent(DataTable.COMPONENT_TYPE);
	
	 Object[] liste = initGridModel();
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
	 indexColumnTitle.setValue("Index");
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
	 nameColumnTitle.setValue("Name");
	 nameColumn.getFacets().put("header", nameColumnTitle);
	 table.getChildren().add(nameColumn);

	 ValueExpression nameValueExp = elFactory.createValueExpression(elContext, "#{exam.value}", Object.class);
	 HtmlOutputText nameOutput = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
	 nameOutput.setValueExpression("value", nameValueExp);
	 nameColumn.getChildren().add(nameOutput);
	 UIComponent component1 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
	 UIComponent comp2 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:configuration_dialog");
	 UIComponent compColChart = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ChartCol");
	 compColChart.getChildren().add(table);
	 Datagrid dg = new Datagrid();
	 dg.setDaxe_x(typeAxe);
	   dg.setDaxe_y(typeAxeY);
	   dg.setOperationD(actiontype);
	   lst_grids.add(dg);
	   component1.getChildren().remove(comp2);
		 Panel comp = (Panel) component1;
		 width = width+400;
		 comp.setStyle("height:"+width+"px;");
		//component1.getChildren()
		 
	    RequestContext requestContext = RequestContext.getCurrentInstance();
		 requestContext.update("form1:myPanelGrid");
	   
}

	private BarChartModel initBarModel() {
		
		String action;
		String groupby;
		//ChartDynamic chartdynamique = new ChartDynamic();
		
		 BarChartModel modelchart = new BarChartModel();
		 
		switch (actiontype) {
		case "SUM":
			action="SUM(";
			
			 groupby= "Group By ";
			 for(int nb=0;nb<List_Axe_Y.size();nb++){
				 ChartSeries boys = new ChartSeries();
			        boys.setLabel(typeAxe+" Par"+" "+List_Axe_Y.get(nb));
			        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(nb)+")", action,groupby+typeAxe));
			      
			       
			        modelchart.addSeries(boys);
			 }
			
			break;
		case "COUNT":
			action="COUNT(";
			
			 groupby= "Group By ";
			 for(int nb=0;nb<List_Axe_Y.size();nb++){
				 ChartSeries boys = new ChartSeries();
			        boys.setLabel(typeAxe+" Par"+" "+List_Axe_Y.get(nb));
			        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(nb)+")", action,groupby+typeAxe));
			      
			       
			        modelchart.addSeries(boys);
			 }
			
			break;
		case "AVG":
			action="AVG(";
		
			groupby= "Group By ";
			for(int nb=0;nb<List_Axe_Y.size();nb++){
				 ChartSeries boys = new ChartSeries();
			        boys.setLabel(typeAxe+" Par"+" "+List_Axe_Y.get(nb));
			        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(nb)+")", action,groupby+typeAxe));
			      
			        
			        modelchart.addSeries(boys);
			 }
			break;
		case "Aucun":
			action="";
		
			groupby= "Group By ";
			for(int nb=0;nb<List_Axe_Y.size();nb++){
				 ChartSeries boys = new ChartSeries();
			        boys.setLabel(typeAxe+" Par"+" "+List_Axe_Y.get(nb));
			        boys.setData(statRemote.getConfiguartionChartByCfg(typeAxe, List_Axe_Y.get(nb)+")", action,groupby+typeAxe));
			      
			        
			        modelchart.addSeries(boys);
			 }
			break;

		default:
			break;
		}
       
        
         
        return modelchart;
	
	
}
	
	
	
	public void CreateLineChart(){
		Chart ch = new Chart();

		
		LineChartModel lineModel1 =  initLineModel();
	
	
    lineModel1.setTitle("Graphe Linéaire");
    lineModel1.setLegendPosition("e");
    lineModel1.setShowPointLabels(true);
   if(typeAxe.equals("dateAppel")){
	   DateAxis axis = new DateAxis("Dates");
       axis.setTickAngle(-50);
       //axis.setMax("2015-08-01");
       axis.setTickFormat("%b %#d, %y");
       lineModel1.getAxes().put(AxisType.X, axis);
   }else{
	   Axis xAxis = new CategoryAxis(typeAxe);
	   xAxis.setTickAngle(-50);
	   lineModel1.getAxes().put(AxisType.X,xAxis );
   }
    	
    
    Axis  yAxis = lineModel1.getAxis(AxisType.Y);
    yAxis.setLabel("Valeurs");
   
    ch.setModel(lineModel1);
    ch.setStyle("width:500px;height:400px;");
	ch.setType("line");
	 UIComponent component1 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
	 UIComponent comp2 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:configuration_dialog");
	 UIComponent compColChart = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ChartCol");
	 compColChart.getChildren().add(ch);
	   
	   
	   
	   
	   
	   ChartDynamic chd = new ChartDynamic();
	   chd.setAxe_x(typeAxe);
	   chd.setList_axe_y(List_Axe_Y);
	   chd.setOperation(actiontype);
	   chd.setType_chart("line");
	   
	   lst_charts.add(chd);
	   
	 
//System.out.println(comp2.getChildCount());

    component1.getChildren().remove(comp2);
	 Panel comp = (Panel) component1;
	 width = width+400;
	 comp.setStyle("height:"+width+"px;");
	//component1.getChildren()
	 
    RequestContext requestContext = RequestContext.getCurrentInstance();
	 requestContext.update("form1:myPanelGrid");
		
	}
	public void CreatePieChart(){
		FacesContext facesCtx = FacesContext.getCurrentInstance();
	    ELContext elContext = facesCtx.getELContext();
	    Application app = facesCtx.getApplication();
		 ExpressionFactory elFactory = app.getExpressionFactory();
		Chart ch = new Chart();
		
		PieChartModel lineModel1 =  initPieModel();
	lineModel1.setMouseoverHighlight(true);
	lineModel1.setShowDataLabels(true);
	
    lineModel1.setTitle("Pie Graphe ");
    lineModel1.setLegendPosition("w");
    
   
    ch.setModel(lineModel1);
    ch.setStyle("width:500px;height:400px;");
	ch.setType("pie");
	 UIComponent component1 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
	 UIComponent comp2 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:configuration_dialog");
	 UIComponent compColChart = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ChartCol");
	 compColChart.getChildren().add(ch);
	
       //MethodExpression methodExpression =null;
	  //  methodExpression = elFactory.createMethodExpression(elContext,"#{stat_msc.createRapport}",null, new Class[]{});
	   
	   
	  
	   ChartDynamic chd = new ChartDynamic();
	   chd.setAxe_x(typeAxe);
	   chd.setAxe_y(typeAxeY);
	   chd.setOperation(actiontype);
	   chd.setType_chart("pie");
	 
	   lst_charts.add(chd);
	   
	
    component1.getChildren().remove(comp2);
	 Panel comp = (Panel) component1;
	 width = width+400;
	 comp.setStyle("height:"+width+"px;");
	//component1.getChildren()
	 
    RequestContext requestContext = RequestContext.getCurrentInstance();
	 requestContext.update("form1:myPanelGrid");
		
	}
	public void CreateBarChart(){
		Chart ch = new Chart();

		
		BarChartModel lineModel1 =  initBarModel();
	
	
    lineModel1.setTitle("Graphe ");
    lineModel1.setLegendPosition("e");
    lineModel1.setShowPointLabels(true);
    if(typeAxe.equals("dateAppel")){
    	 Axis xAxis = new CategoryAxis("Date");
  	   xAxis.setTickAngle(-50);
  	   lineModel1.getAxes().put(AxisType.X,xAxis );
    }else{
    	 Axis xAxis = new CategoryAxis(typeAxe);
  	   xAxis.setTickAngle(-50);
  	   lineModel1.getAxes().put(AxisType.X,xAxis );
    }
  
    Axis  yAxis = lineModel1.getAxis(AxisType.Y);
    yAxis.setLabel("Valeurs");
   
    ch.setModel(lineModel1);
    ch.setStyle("width:500px;height:400px;");
	ch.setType("bar");
	 UIComponent component1 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:myPanelGrid");
	 UIComponent comp2 = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:configuration_dialog");
	 UIComponent compColChart = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:ChartCol");
	 compColChart.getChildren().add(ch);
	   ChartDynamic chd = new ChartDynamic();
	   chd.setAxe_x(typeAxe);
	   chd.setList_axe_y(List_Axe_Y);
	   chd.setOperation(actiontype);
	   chd.setType_chart("bar");
	 
	   lst_charts.add(chd);
	   
	
    component1.getChildren().remove(comp2);
	 Panel comp = (Panel) component1;
	 width = width+400;
	 comp.setStyle("height:"+width+"px;");
	//component1.getChildren()
	 
    RequestContext requestContext = RequestContext.getCurrentInstance();
	 requestContext.update("form1:myPanelGrid");
		
	}

public void createChart(){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ceci est un simple Preview"));
	if(chartType=="line"){
		CreateLineChart();
	}else if(chartType=="bar"){
		CreateBarChart();
	}else if(chartType=="pie"){
		CreatePieChart();
	}else{
		createGrid();
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
	public List<String> getListe_filters() {
		return liste_filters;
	}
	public void setListe_filters(List<String> liste_filters) {
		this.liste_filters = liste_filters;
	}
	public List<String> getListe_filters_selected() {
		return liste_filters_selected;
	}
	public void setListe_filters_selected(List<String> liste_filters_selected) {
		this.liste_filters_selected = liste_filters_selected;
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
		p = new Report();
		
		p.setLib_rapport(lib_report);
		p.setTable_name("StatMsc");
	p.setLst_chart(lst_charts);
	  p.setFilters_report(liste_filters_selected);
	  p.setLst_datagrid(lst_grids);
	
	reportRemote.addReport(p);
		FacesContext.getCurrentInstance().getExternalContext().redirect("rapport.jsf?");
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
