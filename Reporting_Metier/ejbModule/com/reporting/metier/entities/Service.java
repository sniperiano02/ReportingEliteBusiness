package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services",schema="tableref")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="code_service")
	private String codeService;

	private String service;

	@Column(name="type_revenue")
	private String typeRevenue;

	public Service() {
	}

	public String getCodeService() {
		return this.codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTypeRevenue() {
		return this.typeRevenue;
	}

	public void setTypeRevenue(String typeRevenue) {
		this.typeRevenue = typeRevenue;
	}

}