package com.reporting.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.highfaces.component.chart.Chart;
import org.primefaces.component.outputpanel.OutputPanel;
import org.primefaces.context.RequestContext;




@ManagedBean
@ViewScoped
public class NavigationMbeanPP implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4177542362514316559L;
	
	private String pageName ;
	
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		System.out.println(pageName);
		this.pageName = pageName;
	}
	
	
	public void changePage(){
		System.out.println("page changed");
		this.pageName="/pages/mobilePP/PlanTarifiaireConf";
		
	}

	
	@PostConstruct
	public void init(){
		
		pageName="/pages/RpTraffic/rapport";
	}
	public NavigationMbeanPP() {
		// TODO Auto-generated constructor stub
	}
	
	
	}
