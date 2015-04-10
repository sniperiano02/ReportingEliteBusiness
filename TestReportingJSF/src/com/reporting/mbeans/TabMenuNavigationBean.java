package com.reporting.mbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.accordionpanel.AccordionPanel;

@ManagedBean
@SessionScoped
public class TabMenuNavigationBean implements Serializable {

	
	
	private Long activeIndex;

	public Long getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Long activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	public void changeActiveIndex(Long x){
		try {
		if(x==1){
			if(this.activeIndex!=x){
				this.activeIndex=x;
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("/TestReportingJSF/pages/RpTraffic/rapport.jsf?faces-redirect=true");
			
			}
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
