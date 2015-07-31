package com.reporting.metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(schema="stat")
public class AxeY implements Serializable {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id_axe;
	
	
	private String axey;
	
	
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable( name="op_axes",schema="stat", joinColumns=@JoinColumn(name="operations_y_ID",referencedColumnName="id_axe")
	)
	private List<String> operations;
	
	
	
	
	public Integer getId_axe() {
		return id_axe;
	}
	public void setId_axe(Integer id_axe) {
		this.id_axe = id_axe;
	}
	public String getAxey() {
		return axey;
	}
	public void setAxey(String axey) {
		this.axey = axey;
	}
	public List<String> getOperations() {
		return operations;
	}
	public void setOperations(List<String> operations) {
		this.operations = operations;
	}

	@ManyToOne
	@JoinColumn(name ="chartOP_id",nullable=true)
	private ChartDynamic chartD;
	
	@ManyToOne
	@JoinColumn(name ="gridOP_id")
	private Datagrid GridD;
	
	
public Datagrid getGridD() {
	return GridD;
}
public void setGridD(Datagrid gridD) {
	GridD = gridD;
}
	
	public ChartDynamic getChartD() {
		return chartD;
	}
	public void setChartD(ChartDynamic chartD) {
		this.chartD = chartD;
	}
}
