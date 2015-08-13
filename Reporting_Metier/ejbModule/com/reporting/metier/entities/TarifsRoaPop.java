package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tarifs_roa_pop database table.
 * 
 */
@Entity
@Table(name="tarifs_roa_pop",schema="tableref")
@NamedQuery(name="TarifsRoaPop.findAll", query="SELECT t FROM TarifsRoaPop t")
public class TarifsRoaPop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_validite")
	private Date dateValidite;

	private BigDecimal marge;

	public TarifsRoaPop() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateValidite() {
		return this.dateValidite;
	}

	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}

	public BigDecimal getMarge() {
		return this.marge;
	}

	public void setMarge(BigDecimal marge) {
		this.marge = marge;
	}

}