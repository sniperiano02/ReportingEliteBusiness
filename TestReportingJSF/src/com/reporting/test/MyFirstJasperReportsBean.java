package com.reporting.test;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
@RequestScoped
public class MyFirstJasperReportsBean extends AbstractBaseReportBean {
  
  private final String COMPILE_FILE_NAME = "my_first_report";
  
  @Override
  protected String getCompileFileName() {
    return COMPILE_FILE_NAME;
  }
  
  @Override
  protected Map<String, Object> getReportParameters() {
    Map<String, Object> reportParameters = new HashMap<String, Object>();
    
    reportParameters.put("ReportTitle", "Hello JasperReports");
    
    return reportParameters;
  }

  @Override
  protected JRDataSource getJRDataSource() {
    // return your custom datasource implementation
    MyFirstJasperReportsDataSource dataSource = new MyFirstJasperReportsDataSource();
    
    return  dataSource;
  }
  
  public String execute() {
    try {
      super.prepareReport();
    } catch (Exception e) {
      // make your own exception handling
      e.printStackTrace();
    }
    
    return null;
  }
}