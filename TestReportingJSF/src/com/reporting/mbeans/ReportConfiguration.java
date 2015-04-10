package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.dnd.Draggable;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;

public  class ReportConfiguration {
	
	
	private List<String> ListeActions= new ArrayList<String>();
	
	
	
	public  void createDialog(UIComponent MainComponent,List<String> axeX,List<String> axeY,String actionYes,String actionNo,String typeAction,String choixAxeX,String choixAxeY ){
		
		Dialog configuration_dialog = new Dialog();
		 configuration_dialog.setVisible(true);
		 configuration_dialog.setId("configuration_dialog");
		 configuration_dialog.setHeader("Configuration");
		 configuration_dialog.setClosable(false);
		 CommandButton b1 = new CommandButton();
	       b1.setValue("Valider");
	       CommandButton b2 = new CommandButton();
	       b2.setValue("Annuler");	
	       FacesContext facesCtx = FacesContext.getCurrentInstance();
	       ELContext elContext = facesCtx.getELContext();
	       Application app = facesCtx.getApplication();
	       ExpressionFactory elFactory = app.getExpressionFactory();
	       MethodExpression methodExpression =null;
	       methodExpression = elFactory.createMethodExpression(elContext,actionYes,null, new Class[]{});
	       b1.setActionExpression(methodExpression);
	       MethodExpression methodExpression1 = elFactory.createMethodExpression(elContext,actionNo,null, new Class[]{});
	       b2.setActionExpression(methodExpression1);
	       //****************************************************************************
	       
	  	 
	  	 OutputLabel lb_action = new OutputLabel();
	  	 OutputLabel lb_x = new OutputLabel();
	  	 lb_x.setValue("Axe X");
	  	 lb_x.setFor("lst_X");
	  	 OutputLabel lb_y = new OutputLabel();
	  	 lb_y.setValue("Axe Y");
	  	 lb_y.setFor("lst_Y");
	  	 SelectOneMenu lst_action = new SelectOneMenu();
	  	 SelectOneMenu lst_X = new SelectOneMenu();
	  	 SelectCheckboxMenu lst_Y = new SelectCheckboxMenu();
	  	
	  	
	  	 
	  	 lb_action.setValue("Type Action");
	  	 lb_action.setFor("lst_action");
	  	 UISelectItems list_items = new UISelectItems();
	  	 UISelectItems list_items1 = new UISelectItems();
	  	 UISelectItems list_items2 = new UISelectItems();
	  	 
	       ValueExpression valueExp1 = elFactory.createValueExpression(elContext, typeAction,Object.class); 
	       ValueExpression valueExp2 = elFactory.createValueExpression(elContext, choixAxeX,Object.class); 
	       ValueExpression valueExp3 = elFactory.createValueExpression(elContext, choixAxeY,Object.class); 
	     
	       lst_action.setValueExpression("value",valueExp1);
	       lst_X.setValueExpression("value",valueExp2);
	       lst_Y.setValueExpression("value",valueExp3);
	    
	      list_items.setValue(ListeActions);
	      list_items1.setValue(axeX);
	      list_items2.setValue(axeY);
	      
	      lst_action.getChildren().add(list_items); 
	      lst_X.getChildren().add(list_items1);
	      lst_Y.getChildren().add(list_items2);
	      
	      lst_action.setId("lst_action");  
	      lst_X.setId("lst_X");
	      lst_Y.setId("lst_Y");
	    
	      PanelGrid pg = new PanelGrid();
	      pg.setColumns(2);
	      

	     
	      pg.getChildren().add(lb_action);
	      pg.getChildren().add(lst_action);
	      pg.getChildren().add(lb_x);
	      pg.getChildren().add(lst_X);
	      pg.getChildren().add(lb_y);
	      pg.getChildren().add(lst_Y);
	     
	      configuration_dialog.getChildren().add(pg);
	       //****************************************************************
	       configuration_dialog.getChildren().add(b1);
	       configuration_dialog.getChildren().add(b2);
	       MainComponent.getChildren().add(configuration_dialog);
	}
	
	public void CreateLibSave(){
		
	}
public  void createDialog1(UIComponent MainComponent,List<String> axeX,List<String> axeY,String actionYes,String actionNo,String typeAction,String choixAxeX,String choixAxeY ){
		
	Dialog configuration_dialog = new Dialog();
	 configuration_dialog.setVisible(true);
	 configuration_dialog.setId("configuration_dialog");
	 configuration_dialog.setHeader("Configuration");
	 configuration_dialog.setClosable(false);
	 CommandButton b1 = new CommandButton();
      b1.setValue("Yes");
      CommandButton b2 = new CommandButton();
      b2.setValue("No");	
      FacesContext facesCtx = FacesContext.getCurrentInstance();
      ELContext elContext = facesCtx.getELContext();
      Application app = facesCtx.getApplication();
      ExpressionFactory elFactory = app.getExpressionFactory();
      MethodExpression methodExpression =null;
      methodExpression = elFactory.createMethodExpression(elContext,actionYes,null, new Class[]{});
      b1.setActionExpression(methodExpression);
     
      MethodExpression methodExpression1 = elFactory.createMethodExpression(elContext,actionNo,null, new Class[]{});
      b2.setActionExpression(methodExpression1);
      //****************************************************************************
      
 	 
 	 OutputLabel lb_action = new OutputLabel();
 	 OutputLabel lb_x = new OutputLabel();
 	 lb_x.setValue("Axe X");
 	 lb_x.setFor("lst_X");
 	 OutputLabel lb_y = new OutputLabel();
 	 lb_y.setValue("Axe Y");
 	 lb_y.setFor("lst_Y");
 	 SelectOneMenu lst_action = new SelectOneMenu();
 	 SelectOneMenu lst_X = new SelectOneMenu();
 	SelectOneMenu lst_Y = new SelectOneMenu();
 	 OutputLabel lb_filters = new OutputLabel();
 	 lb_filters.setValue("Choisir Filters");
 	 lb_filters.setFor("mst_f");
 	
 	 
 	 lb_action.setValue("Type Action");
 	 lb_action.setFor("lst_action");
 	 UISelectItems list_items = new UISelectItems();
 	 UISelectItems list_items1 = new UISelectItems();
 	 UISelectItems list_items2 = new UISelectItems();
 	 
      ValueExpression valueExp1 = elFactory.createValueExpression(elContext, typeAction,Object.class); 
      ValueExpression valueExp2 = elFactory.createValueExpression(elContext, choixAxeX,Object.class); 
      ValueExpression valueExp3 = elFactory.createValueExpression(elContext,choixAxeY,Object.class); 
    
      lst_action.setValueExpression("value",valueExp1);
      lst_X.setValueExpression("value",valueExp2);
      lst_Y.setValueExpression("value",valueExp3);
   
     list_items.setValue(ListeActions);
     list_items1.setValue(axeX);
     list_items2.setValue(axeY);
     
     lst_action.getChildren().add(list_items); 
     lst_X.getChildren().add(list_items1);
     lst_Y.getChildren().add(list_items2);
     
     lst_action.setId("lst_action");  
     lst_X.setId("lst_X");
     lst_Y.setId("lst_Y");
   
     PanelGrid pg = new PanelGrid();
     pg.setColumns(2);
     

    
     pg.getChildren().add(lb_action);
     pg.getChildren().add(lst_action);
     pg.getChildren().add(lb_x);
     pg.getChildren().add(lst_X);
     pg.getChildren().add(lb_y);
     pg.getChildren().add(lst_Y);
    
     configuration_dialog.getChildren().add(pg);
      //****************************************************************
      configuration_dialog.getChildren().add(b1);
      configuration_dialog.getChildren().add(b2);
	       MainComponent.getChildren().add(configuration_dialog);
	}




	public List<String> getListeActions() {
		return ListeActions;
	}




	public void setListeActions(List<String> listeActions) {
		ListeActions = listeActions;
	}
	

}
