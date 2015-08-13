package com.reporting.metier.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(schema="tableref")
public class Location {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="id_fraudeur",referencedColumnName="id")
	private ParcGptoAct fraudeur;
	
	@ManyToOne
	@JoinColumn(name="id_cell",referencedColumnName="idcell")
	private Cell fraudeur_location;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ParcGptoAct getFraudeur() {
		
		return fraudeur;
	}
	public void setFraudeur(ParcGptoAct fraudeur) {
		this.fraudeur = fraudeur;
	}
	public void setFraudeur_location(Cell fraudeur_location) {
		this.fraudeur_location = fraudeur_location;
	}
	public Cell getFraudeur_location() {
		return fraudeur_location;
	}
	
	
	

}
