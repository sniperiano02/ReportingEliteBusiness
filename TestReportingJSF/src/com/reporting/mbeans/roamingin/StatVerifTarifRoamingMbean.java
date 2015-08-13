package com.reporting.mbeans.roamingin;


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
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.reporting.metier.interfaces.StatVerifTarifRoaRemote;


@ManagedBean(name="verif_tarif_roain")
@ViewScoped
public class StatVerifTarifRoamingMbean {

	
	
		@EJB
		StatVerifTarifRoaRemote verif_tarif_roaming_service;

		
		
		private List<Object[]> listeDetailsVerifTarif;
		public List<Object[]> getListeDetailsVerifTarif() {
			return listeDetailsVerifTarif;
		}
		public void setListeDetailsVerifTarif(
				List<Object[]> listeDetailsVerifTarif) {
			this.listeDetailsVerifTarif = listeDetailsVerifTarif;
		}
	 
	private String choix_voix;
	
	
	public String getChoix_voix() {
		return choix_voix;
	}
	public void setChoix_voix(String choix_voix) {
		this.choix_voix = choix_voix;
	}
		
		
		
		private Object[] selectedRecon;
		
		public Object[] getSelectedRecon() {
			return selectedRecon;
		}
		public void setSelectedRecon(Object[] selectedRecon) {
			this.selectedRecon = selectedRecon;
		}
		
		private List<Object[]> listeVerifTranfert;

		public List<Object[]> getListeVerifTranfert() {
			return listeVerifTranfert;
		}
		public void setListeVerifTranfert(List<Object[]> listeVerifTranfert) {
			this.listeVerifTranfert = listeVerifTranfert;
		}
		private String choix_periode;
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
		public void setListe_desAns(List<Integer> liste_desAns) {
			this.liste_desAns = liste_desAns;
		}
		public List<Integer> getListeMois() {
			return listeMois;
		}
		public void setListeMois(List<Integer> listeMois) {
			this.listeMois = listeMois;
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
		 
		 private boolean detail_displayed=false;
		 
		 public boolean isDetail_displayed() {
			return detail_displayed;
		}
		 public void setDetail_displayed(boolean detail_displayed) {
			this.detail_displayed = detail_displayed;
		}
		
			
		
		public String getChoix_typeCall() {
			return choix_typeCall;
		}
		public void setChoix_typeCall(String choix_typeCall) {
			this.choix_typeCall = choix_typeCall;
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
		public Date getDate_mois_fin() {
			return date_mois_fin;
		}
		public void setDate_mois_fin(Date date_mois_fin) {
			this.date_mois_fin = date_mois_fin;
		}
		public Date getDate_mois_debut() {
			return date_mois_debut;
		}
		public void setDate_mois_debut(Date date_mois_debut) {
			this.date_mois_debut = date_mois_debut;
		}
		public Integer getDate_year() {
			return date_year;
		}
		public void setDate_year(Integer date_year) {
			this.date_year = date_year;
		}
			private String choix_typeCall;
			 private Date date_Parheure;
			 private Date date_ParJourDeb;
			 private Date date_ParJourFin;
			 private Integer date_year_deb;
			 private Integer date_year_fin;
			 private Date date_mois_fin;
			 private Date date_mois_debut;
			 private Integer date_year;  
			 
			 @PostConstruct
			 public void init(){
				 liste_desAns = verif_tarif_roaming_service.getAllYears();
				 listeVerifTranfert = new ArrayList<>();
				 
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
				 				
				 					
				 				ValueExpression valjour = createValueExpression("#{verif_tarif_roain.date_ParJourDeb}", Date.class);
				 				cld_jour_debut.setValueExpression("value", valjour);
				 					
				 					Calendar cld_jour_fin = new Calendar();
				 				
				 					cld_jour_fin.setId("cld_jour_fin");
				 					
				 				ValueExpression valjourfin = createValueExpression("#{verif_tarif_roain.date_ParJourFin}", Date.class);
				 				cld_jour_fin.setValueExpression("value", valjourfin);
				 				
				 				
				 					
				 				
				 					
				 					comp.getChildren().add(cld_jour_debut);
				 					
				 					comp.getChildren().add(cld_jour_fin);
				 				
				 				
				 				
				 					
				 			
				 			}else if (this.getChoix_periode().equals("Par An")){
				 				SelectOneMenu lstDateYears = new SelectOneMenu();
				 				lstDateYears.setId("lstDateYears");
				 				 ValueExpression valueExp = createValueExpression("#{verif_tarif_roain.date_year_deb}", Integer.class);
				 		         UISelectItems list_items0 = new UISelectItems();
				 				 lstDateYears.setValueExpression("value", valueExp);
				 		         list_items0.setValue(liste_desAns);
				 		         lstDateYears.getChildren().add(list_items0);
				 				
				 				SelectOneMenu lstDateYearsFin = new SelectOneMenu();
				 				lstDateYearsFin.setId("lstDateYearsFin");
				 				 ValueExpression valueExp1 = createValueExpression("#{verif_tarif_roain.date_year_fin}", Integer.class);
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
				 			
				 				
				 			ValueExpression valjour = createValueExpression("#{verif_tarif_roain.date_mois_debut}", Date.class);
				 			cld_mois_debut.setValueExpression("value", valjour);
				 			cld_mois_debut.setPattern("MM/yyyy");
				 			cld_mois_debut.setMask("true");
				 		
				 				Calendar cld_mois_fin = new Calendar();
				 			
				 				cld_mois_fin.setId("cld_mois_fin");
				 				cld_mois_fin.setPattern("MM/yyyy");
				 				cld_mois_fin.setMask("true");
				 			ValueExpression valjourfin = createValueExpression("#{verif_tarif_roain.date_mois_fin}", Date.class);
				 			cld_mois_fin.setValueExpression("value", valjourfin);
				 			
				 			
				 				
				 			
				 				
				 				comp.getChildren().add(cld_mois_debut);
				 				
				 				comp.getChildren().add(cld_mois_fin);
				 			
				 			
				 			
				 			}
				 			
				 				
				 				
				 			
				 			
				 			
				 			
				 	}
			
				 	public void Valider(){
				 		
				 		where_liste = new ArrayList<String>();
				 		listeVerifTranfert = new ArrayList<>();
				 		List<Object[]> generateList= new ArrayList<>();
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
						            Object[] ob = new Object[3];
						            ob[0]=s.substring(0,10);
						            ob[1]=0.0;
						            ob[2]=0.0;
						       
						            
						            generateList.add(ob);
						        }
				 				
				 					DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
				 					String deb = df.format(this.getDate_ParJourDeb());
				 					String fin = df.format(this.getDate_ParJourFin());
				 					where_liste.add(" to_date(dateAppel,'YYMMDD') Between to_date("+"'"+deb+"'"+",'yyyy-MM-dd') And to_date("+"'"+fin+"'"+",'yyyy-MM-dd')");
				 				SubTitle ="Periode entre "+deb+" et "+fin;
				 				
				 				
				 			}else  if(this.getChoix_periode().equals("Par An")){
				 			
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
									 Object[] ob = new Object[3];
							            ob[0]=date1.toString("MM/yyyy");
							            ob[1]=0.0;
							            ob[2]=0.0;
							            
							            
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
				 			
				 		
				 			
				 			
				 			if(!this.getChoix_voix().equals("")){
				 				where_liste.add(" typeCall ='"+this.getChoix_voix()+"'");
				 				SubTitle =SubTitle + "avec type call comme "+this.getChoix_voix();
				 			}
				 			
				 		

				 	
				 				
				 	 if(this.getChoix_periode().equals("Par An")){
				 	 			 listeVerifTranfert = verif_tarif_roaming_service.getStatVerifTarifRoa("Extract (year from to_date(dateAppel,'YYMMDD'))", "SUM(impact),SUM(nbAppel)",  "", where_liste);
				 	 			
				 	 			
				 		 		 }else if(this.getChoix_periode().equals("Par Mois")){
				 		 			 listeVerifTranfert = removeDuplicate(generateList,verif_tarif_roaming_service.getStatVerifTarifRoa("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", "SUM(impact),SUM(nbAppel)",  "", where_liste));

				 		 		 }else if (this.getChoix_periode().equals("Par Jour")){
				 		 			 listeVerifTranfert = removeDuplicate(generateList,verif_tarif_roaming_service.getStatVerifTarifRoa("to_char(to_date(dateAppel,'YYMMDD'),'YYYY-MM-DD')", "SUM(impact),SUM(nbAppel)", "", where_liste));

				 		 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 
				 		 		 }
				 		
				 		
				 			 chartDisplayed=true;
				 			     
				 			 	



				 			 			//data =statRemote.getMscByFilters(" to_date(dateAppel,'YYMMDD')",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By  to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC", where_liste); 

				 			
				 			
				 			}		
				 	}
				 	
				
				 	public void getDetail(){
				 		detail_displayed=true;
				 		//listeDetailsVerifTarifInte= new ArrayList<>();
				 		String where="";
				 		if(this.getChoix_periode().equals("Par Jour")){
							
							
							
				 			
							where=" to_date(dateAppel,'YYMMDD') = to_date("+"'"+selectedRecon[0].toString()+"'"+",'DD/MM/YYYY') ";
						
							
						
						}else if(this.getChoix_periode().equals("Par An")){
					
						
						
						
						 where ="  Extract(year from to_date(dateAppel,'YYMMDD')) = "+Integer.valueOf(selectedRecon[0].toString());
					
						
					}else if(this.getChoix_periode().equals("Par Mois")){
						
						
						
						String mois = selectedRecon[0].toString();
						where=" to_date(to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY'),'MM/YYYY') =to_date("+"'"+mois+"'"+",'MM/YYYY')";
				
				}
				 		if(!this.getChoix_impact().equals("")){
			 				where=where+" AND "+this.getChoix_impact();
			 			}
				 		where= where+" AND typeCall = '"+this.getChoix_typeCall()+"'";
					listeDetailsVerifTarif=verif_tarif_roaming_service.getDetailsStatVerifTarifRoa(where);
						//System.out.println(lineChartDetails.getSeries().get(0).getName()+":"+lineChartDetails.getSeries().get(0).getMap().entrySet().size());
						
						System.out.println(detail_displayed);
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

			private String choix_impact;
			
			public String getChoix_impact() {
				return choix_impact;
			}
			public void setChoix_impact(String choix_impact) {
				this.choix_impact = choix_impact;
			}
			
		
		
	}


