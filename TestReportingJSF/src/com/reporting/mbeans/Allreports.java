package com.reporting.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.reporting.metier.entities.Report;
import com.reporting.metier.interfaces.ReportRemote;

@ManagedBean(name="reports_view")
@ViewScoped
public class Allreports  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ReportRemote reportmanager;
	private List<Report> allreports;
	private String id ;
	
	
	private Report selected_report;
	
	@PostConstruct
	public void init(){
		allreports = reportmanager.GetAllreports();
		
	}

	public List<Report> getAllreports() {
		return allreports;
	}

	public void setAllreports(List<Report> allreports) {
		this.allreports = allreports;
	}

	public Report getSelected_report() {
		return selected_report;
	}

	public void setSelected_report(Report selected_report) {
		this.selected_report = selected_report;
	}
	

	public String ViewRapport(Report p ){
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
        .put("param", p.getId_rapport()+"");
		this.setId(p.getId_rapport()+"");
		 return "/pages/RpTraffic/allreports.jsf?faces-redirect=true&rapport_id=" + p.getId_rapport()+"";
	
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
