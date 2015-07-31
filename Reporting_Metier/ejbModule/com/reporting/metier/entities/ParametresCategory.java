package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametres_categories database table.
 * 
 */
@Entity
@Table(name="parametres_categories",schema="tableref")
@NamedQuery(name="ParametresCategory.findAll", query="SELECT p FROM ParametresCategory p")
public class ParametresCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	
	@ManyToOne
	@JoinColumn(name="id_flux",referencedColumnName="id")
	private FluxCdr flux;
	
	@ManyToOne
	@JoinColumn(name="id_parametre",referencedColumnName="id")
	private ParametresFraude parametre;
	
	
public FluxCdr getFlux() {
	return flux;
}
public ParametresFraude getParametre() {
	return parametre;
}
public void setFlux(FluxCdr flux) {
	this.flux = flux;
}
public void setParametre(ParametresFraude parametre) {
	this.parametre = parametre;
}
	public ParametresCategory() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}