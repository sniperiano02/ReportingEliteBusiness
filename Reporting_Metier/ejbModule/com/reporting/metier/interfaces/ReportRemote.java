package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.reporting.metier.entities.Report;

@Local
public interface ReportRemote {
	
	
	public void addReport(Report report);
	public List<Report> GetAllreports();

	public Report getReportById(Integer id);
}
