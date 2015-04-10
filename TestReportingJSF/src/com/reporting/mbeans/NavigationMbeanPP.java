package com.reporting.mbeans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="nav_pp")
@ViewScoped
public class NavigationMbeanPP {
	
	
	public void MscGenerateReport(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/TestReportingJSF/PdfReportServlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
