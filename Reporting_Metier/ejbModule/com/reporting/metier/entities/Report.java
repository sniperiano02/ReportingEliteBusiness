package com.reporting.metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(schema="stat")
public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
private Integer id_rapport;

private String lib_rapport;
private String table_name;



@OneToMany(mappedBy="report_chart")
@LazyCollection(LazyCollectionOption.FALSE)
private List<ChartDynamic> lst_chart; 
@ElementCollection(targetClass=Datagrid.class)
@LazyCollection(LazyCollectionOption.FALSE)
@CollectionTable(
      name="datagrids_report",
    		  schema="stat",
      joinColumns=@JoinColumn(name="rep_grid_ID")
)
private List<Datagrid> lst_datagrid;


@ElementCollection
@LazyCollection(LazyCollectionOption.FALSE)
@CollectionTable( name="report_filters",schema="stat", joinColumns=@JoinColumn(name="report_ID",referencedColumnName="id_rapport")
)
private List<String> filters_report;

public Integer getId_rapport() {
	return id_rapport;
}
public void setId_rapport(Integer id_rapport) {
	this.id_rapport = id_rapport;
}
public String getLib_rapport() {
	return lib_rapport;
}
public void setLib_rapport(String lib_rapport) {
	this.lib_rapport = lib_rapport;
}
public String getTable_name() {
	return table_name;
}
public void setTable_name(String table_name) {
	this.table_name = table_name;
}

public List<ChartDynamic> getLst_chart() {
	return lst_chart;
}
public void setLst_chart(List<ChartDynamic> lst_chart) {
	this.lst_chart = lst_chart;
}

public List<Datagrid> getLst_datagrid() {
	return lst_datagrid;
}
public void setLst_datagrid(List<Datagrid> lst_datagrid) {
	this.lst_datagrid = lst_datagrid;
}
public List<String> getFilters_report() {
	return filters_report;
}
public void setFilters_report(List<String> filters_report) {
	this.filters_report = filters_report;
}




}
