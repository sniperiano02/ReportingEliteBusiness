package com.reporting.mbeans.postpaye;

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
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.panelgrid.PanelGrid;

import com.reporting.metier.interfaces.ParcActiveRemote;
import com.reporting.metier.interfaces.StatParcActivePopRemote;
@ManagedBean(name="parc_active_pop")
@ViewScoped
public class ParcActivePopMbean {
	
	

	@EJB
	private StatParcActivePopRemote parcActivePrpSevice;

	private List<Object[]> listParcActive;

	private String choix_periode;


	private Date date_mois_fin;
	private Date date_mois_debut;
	public Date getDate_mois_debut() {
		return date_mois_debut;
	}
	public void setDate_mois_debut(Date date_mois_debut) {
		this.date_mois_debut = date_mois_debut;
	}
	public void setDate_mois_fin(Date date_mois_fin) {
		this.date_mois_fin = date_mois_fin;
	}
	public Date getDate_mois_fin() {
		return date_mois_fin;
	}
	public List<Object[]> getListParcActive() {
		return listParcActive;
	}
	public void setListParcActive(List<Object[]> listParcActive) {
		this.listParcActive = listParcActive;
	}
		public String getChoix_periode() {
			return choix_periode;
		}
		public void setChoix_periode(String choix_periode) {
			this.choix_periode = choix_periode;
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
		 

		
		
		 public void handlechange(AjaxBehaviorEvent event){
				
			 chartDisplayed = false;
			 	

			 		FacesContext facesCtx = FacesContext.getCurrentInstance();
			 	    ELContext elContext = facesCtx.getELContext();
			 	    Application app = facesCtx.getApplication();
			 		 ExpressionFactory elFactory = app.getExpressionFactory();
			 			PanelGrid comp = (PanelGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:PanelPeriode");
			 			System.out.println(comp.getId());
			 			System.out.println(comp.getChildCount());
			 	
			 			UIComponent compSLMois =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_mois_debut");
			 			UIComponent compSLMoisFin =FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:cld_mois_fin");
			 		
			 			
			 			if(compSLMois!=null){

			 				comp.getChildren().remove(compSLMois);
			 				comp.getChildren().remove(compSLMoisFin);
			 				
			 				
			 			}
			 		 if (this.getChoix_periode().equals("Par Mois")){
			 				Calendar cld_mois_debut = new Calendar();
			 				cld_mois_debut.setId("cld_mois_debut");
			 				cld_mois_debut.setRequired(true);
			 			
			 				
			 			ValueExpression valjour = createValueExpression("#{parc_active_pop.date_mois_debut}", Date.class);
			 			cld_mois_debut.setValueExpression("value", valjour);
			 			cld_mois_debut.setPattern("MM/yyyy");
			 			cld_mois_debut.setMask("true");
			 		
			 				Calendar cld_mois_fin = new Calendar();
			 			
			 				cld_mois_fin.setId("cld_mois_fin");
			 				cld_mois_fin.setPattern("MM/yyyy");
			 				cld_mois_fin.setMask("true");
			 			ValueExpression valjourfin = createValueExpression("#{parc_active_pop.date_mois_fin}", Date.class);
			 			cld_mois_fin.setValueExpression("value", valjourfin);
			 			
			 			
			 				
			 			
			 				
			 				comp.getChildren().add(cld_mois_debut);
			 				
			 				comp.getChildren().add(cld_mois_fin);
			 			
			 			}	
			 			
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

		
		 @PostConstruct
		 public void init(){
		 	
		 		listParcActive= new ArrayList<>();
		 
		 	    
		 	 
		 	}
		 
		 public void Valider(){
				where_liste = new ArrayList<String>();
				listParcActive= new ArrayList<>();
				if(this.getChoix_periode().equals(" ")){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur",  "Veuillez choisir une Periode " ) );
				}
				else{
					
					
				 if(this.getChoix_periode().equals("Par Mois")){
					DateFormat df = new SimpleDateFormat("MM-YYYY");
				
					String deb = df.format(this.getDate_mois_debut());
					String fin = df.format(this.getDate_mois_fin());
					
					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'MM-YYYY') And to_date("+"'"+fin+"'"+",'MM-YYYY')");
					SubTitle ="Entre le mois "+deb+" et le mois "+fin;
				}
				listParcActive= parcActivePrpSevice.getStatParcActivePop("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", where_liste);
				
					
			 
		chartDisplayed=true;
					
					 			
					
					
					}		
			}
		 
		 
		
	}
