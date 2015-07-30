package com.reporting.metier.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(schema="stat")
public class ChartDynamic implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Integer id_chart;
	
	private String nom_chart;
	private String type_chart;
	
	private String operation;
	private String axe_x;
	private String axe_y;
	@OneToMany(mappedBy="chartD",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AxeY> list_axe_y;
	
	
	public List<AxeY> getList_axe_y() {
		return list_axe_y;
	}
	public void setList_axe_y(List<AxeY> list_axe_y) {
		this.list_axe_y = list_axe_y;
	}
	public String getNom_chart() {
		return nom_chart;
	}
	public void setNom_chart(String nom_chart) {
		this.nom_chart = nom_chart;
	}
	@ManyToOne
	@JoinColumn(name ="report_chart_id")
	private Report report_chart;
	
	
	public String getType_chart() {
		return type_chart;
	}
	public void setType_chart(String type_chart) {
		this.type_chart = type_chart;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getAxe_x() {
		return axe_x;
	}
	public void setAxe_x(String axe_x) {
		this.axe_x = axe_x;
	}
	public String getAxe_y() {
		return axe_y;
	}
	public void setAxe_y(String axe_y) {
		this.axe_y = axe_y;
	}

	public Integer getId_chart() {
		return id_chart;
	}
	public void setId_chart(Integer id_chart) {
		this.id_chart = id_chart;
	}


	public Report getReport_chart() {
		return report_chart;
	}
	public void setReport_chart(Report report_chart) {
		this.report_chart = report_chart;
	}
	
	

}
