package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the type_palier database table.
 * 
 */
@Entity
@Table(name="type_palier",schema="tableref")
@NamedQuery(name="TypePalier.findAll", query="SELECT t FROM TypePalier t")
public class TypePalier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="type_palier")
	private Integer typePalier;

	private String description;

	public TypePalier() {
	}

	public Integer getTypePalier() {
		return this.typePalier;
	}

	public void setTypePalier(Integer typePalier) {
		this.typePalier = typePalier;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}