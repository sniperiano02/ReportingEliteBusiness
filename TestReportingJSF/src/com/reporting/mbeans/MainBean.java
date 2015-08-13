package com.reporting.mbeans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class MainBean implements Serializable{
 
    private String currentNav;
 
    public MainBean() {
        currentNav = "/pages/administration/cdr_erreurs/Utarif.xhtml"; //default page to load
    }
 
    public void updateNav() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        currentNav = (String) map.get("currentNav");
    }
 
    public String getCurrentNav() {
        return currentNav;
    }
 
    public void setCurrentNav(String currentNav) {
        this.currentNav = currentNav;
    }
 
}