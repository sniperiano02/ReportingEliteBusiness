package com.reporting.metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(schema="stat")
public class Datagrid implements Serializable{
	
	
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private static final long serialVersionUID = 1L;
	private String operationD;
	private String Daxe_x;
	
	@OneToMany(mappedBy="GridD",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AxeY> list_axe_y;
	
	@ManyToOne
	@JoinColumn(name ="report_grid_id",nullable=false)
	private Report report_grid;
	
	public Report getReport_grid() {
		return report_grid;
	}
	public void setReport_grid(Report report_grid) {
		this.report_grid = report_grid;
	}
	
	public String getOperationD() {
		return operationD;
	}
	public void setOperationD(String operationD) {
		this.operationD = operationD;
	}
	public String getDaxe_x() {
		return Daxe_x;
	}
	public void setDaxe_x(String daxe_x) {
		Daxe_x = daxe_x;
	}
	public List<AxeY> getList_axe_y() {
		return list_axe_y;
	}
	public void setList_axe_y(List<AxeY> list_axe_y) {
		this.list_axe_y = list_axe_y;
	}
	
	
	

}
