package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the filtres_flux database table.
 * 
 */
@Entity
@Table(name="filtres_flux",schema="tableref")
@NamedQuery(name="FiltresFlux.findAll", query="SELECT f FROM FiltresFlux f")
public class FiltresFlux implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_filtre",referencedColumnName="id")
	private FiltresFraude filtre;
	
	
	public FiltresFraude getFiltre() {
		return filtre;
	}
	public void setFiltre(FiltresFraude filtre) {
		this.filtre = filtre;
	}
	
	@ManyToOne
	@JoinColumn(name="id_flux",referencedColumnName="id")
	private FluxCdr flux;
	
	
	public FluxCdr getFlux() {
		return flux;
	}
	public void setFlux(FluxCdr flux) {
		this.flux = flux;
	}
	
	
	public FiltresFlux() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

}