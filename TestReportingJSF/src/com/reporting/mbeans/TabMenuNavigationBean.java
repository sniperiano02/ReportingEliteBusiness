package com.reporting.mbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.accordionpanel.AccordionPanel;

@ManagedBean
@SessionScoped
public class TabMenuNavigationBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long activeIndex;
	
	

	public Long getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Long activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	
	@PostConstruct
	public void init(){
		activeIndex=(long) 0;
	}
 private boolean pan_admin= false;
 private boolean pan_reporting = false;
 private boolean pan_postpaye= false;
 private boolean pan_interco=false;
private boolean pan_fraude = false;
private boolean pan_roa_in = false;
private boolean pan_intercoNat = false;
private boolean pan_roam_out =false;

public boolean isPan_roam_out() {
	return pan_roam_out;
}
public void setPan_roam_out(boolean pan_roam_out) {
	this.pan_roam_out = pan_roam_out;
}

public boolean isPan_intercoNat() {
	return pan_intercoNat;
}
public void setPan_intercoNat(boolean pan_intercoNat) {
	this.pan_intercoNat = pan_intercoNat;
}


public boolean isPan_roa_in() {
	return pan_roa_in;
}
public void setPan_roa_in(boolean pan_roa_in) {
	this.pan_roa_in = pan_roa_in;
}
 
 public boolean isPan_postpaye() {
	return pan_postpaye;
}
 public void setPan_postpaye(boolean pan_postpaye) {
	this.pan_postpaye = pan_postpaye;
}
 
 public boolean isPan_admin() {
	return pan_admin;
}
 public boolean isPan_reporting() {
	return pan_reporting;
}
 public void setPan_admin(boolean pan_admin) {
	this.pan_admin = pan_admin;
}
 public void setPan_reporting(boolean pan_reporting) {
	this.pan_reporting = pan_reporting;
}
	private boolean pan_prepayé = false;
	
	public boolean isPan_prepayé() {
		return pan_prepayé;
	}
	public void setPan_prepayé(boolean pan_prepayé) {
		this.pan_prepayé = pan_prepayé;
	}
	public boolean isPan_interco() {
		return pan_interco;
	}
	public void setPan_interco(boolean pan_interco) {
		this.pan_interco = pan_interco;
	}
	
	public void changeActiveIndex(Long x){
		activeIndex=x;
		if(x==0){
			pan_prepayé=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_admin=true;
			pan_interco=false;
			pan_fraude=false;
			pan_roa_in= false;
			pan_intercoNat= false;
			pan_roam_out = false;
			
		}
if(x==1){
	pan_prepayé=true;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_interco=false;
			pan_fraude=false;
			pan_roa_in= false;
			pan_intercoNat= false;
			pan_roam_out = false;
		}
		if(x==2){
			pan_prepayé=false;
			pan_admin=false;
			pan_postpaye=false;
			pan_reporting=true;
			pan_interco=false;
			pan_roa_in=false;
			pan_intercoNat= false;
			pan_roam_out = false;
		}
		if(x==3){
			pan_prepayé=false;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=true;
			pan_interco=false;
			pan_fraude=false;
			pan_roa_in=false;
			pan_intercoNat= false;
			pan_roam_out = false;
		}
		if(x==4){
			pan_prepayé=false;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_interco=true;
			pan_fraude=false;
			pan_roa_in=false;
			pan_intercoNat= false;
			pan_roam_out = false;
		}
		if(x==5){
			pan_prepayé=false;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_interco=false;
			pan_fraude=false;
			pan_roa_in=false;
			pan_intercoNat= true;
			pan_roam_out = false;
		}
		if(x==6){
			pan_prepayé=false;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_interco=false;
			pan_fraude=false;
			pan_roa_in=true;
			pan_intercoNat= false;
			pan_roam_out = false;
		}
		if(x==7){
			pan_prepayé=false;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_interco=false;
			pan_fraude=false;
			pan_roa_in=false;
			pan_intercoNat= false;
			pan_roam_out = true;
		}
		if(x==9){
			pan_prepayé=false;
			pan_admin=false;
			pan_reporting=false;
			pan_postpaye=false;
			pan_interco=false;
			pan_fraude=true;
			pan_roa_in=false;
			pan_intercoNat= false;
		}
		
		
		
				
			
			
		
	}
	
	
	public boolean isPan_fraude() {
		return pan_fraude;
	}
	public void setPan_fraude(boolean pan_fraude) {
		this.pan_fraude = pan_fraude;
	}
	
}
