package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.Report;
import com.reporting.metier.interfaces.ReportRemote;

/**
 * Session Bean implementation class ReportImpl
 */
@Stateless
public class ReportImpl implements ReportRemote {
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public ReportImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addReport(Report report) {
		em.persist(report);
		
		
		
	}

	@Override
	public List<Report> GetAllreports() {
		// TODO Auto-generated method stub
		return em.createQuery("Select r from Report r").getResultList();
	}

	@Override
	public Report getReportById(Integer id) {
		return em.find(Report.class, id);
	}

}
