package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the stat_lcr database table.
 * 
 */
@Entity
@Table(name="stat_lcr",schema="stat")
@NamedQuery(name="StatLcr.findAll", query="SELECT s FROM StatLcr s")
public class StatLcr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="code_dest1")
	private String codeDest1;

	@Column(name="code_dest2")
	private String codeDest2;

	@Column(name="code_dest3")
	private String codeDest3;

	@Temporal(TemporalType.DATE)
	@Column(name="date_validite")
	private Date dateValidite;

	private String destination;

	@Column(name="max_tarifs_1")
	private Double maxTarifs1;

	@Column(name="max_tarifs_2")
	private Double maxTarifs2;

	@Column(name="max_tarifs_3")
	private Double maxTarifs3;

	@Column(name="min_tarifs_1")
	private Double minTarifs1;

	@Column(name="min_tarifs_2")
	private Double minTarifs2;

	@Column(name="min_tarifs_3")
	private Double minTarifs3;

	
	
	@ManyToOne
	@JoinColumn(name="operateur_1",referencedColumnName="id")
	private OperateurInterco operateur1;
	
	@ManyToOne
	@JoinColumn(name="operateur_2",referencedColumnName="id")
	private OperateurInterco operateur2;
	

	@ManyToOne
	@JoinColumn(name="operateur_3",referencedColumnName="id")
	private OperateurInterco operateur3;
	
	@ManyToOne
	@JoinColumn(name="pays",referencedColumnName="id")
	private Pay pays;

	@Column(name="qualite_1")
	private String qualite1;

	@Column(name="qualite_2")
	private String qualite2;

	@Column(name="qualite_3")
	private String qualite3;

	public StatLcr() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeDest1() {
		return this.codeDest1;
	}

	public void setCodeDest1(String codeDest1) {
		this.codeDest1 = codeDest1;
	}

	public String getCodeDest2() {
		return this.codeDest2;
	}

	public void setCodeDest2(String codeDest2) {
		this.codeDest2 = codeDest2;
	}

	public String getCodeDest3() {
		return this.codeDest3;
	}

	public void setCodeDest3(String codeDest3) {
		this.codeDest3 = codeDest3;
	}

	public Date getDateValidite() {
		return this.dateValidite;
	}

	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getMaxTarifs1() {
		return this.maxTarifs1;
	}

	public void setMaxTarifs1(Double maxTarifs1) {
		this.maxTarifs1 = maxTarifs1;
	}

	public Double getMaxTarifs2() {
		return this.maxTarifs2;
	}

	public void setMaxTarifs2(Double maxTarifs2) {
		this.maxTarifs2 = maxTarifs2;
	}

	public Double getMaxTarifs3() {
		return this.maxTarifs3;
	}

	public void setMaxTarifs3(Double maxTarifs3) {
		this.maxTarifs3 = maxTarifs3;
	}

	public Double getMinTarifs1() {
		return this.minTarifs1;
	}

	public void setMinTarifs1(Double minTarifs1) {
		this.minTarifs1 = minTarifs1;
	}

	public Double getMinTarifs2() {
		return this.minTarifs2;
	}

	public void setMinTarifs2(Double minTarifs2) {
		this.minTarifs2 = minTarifs2;
	}

	public Double getMinTarifs3() {
		return this.minTarifs3;
	}

	public void setMinTarifs3(Double minTarifs3) {
		this.minTarifs3 = minTarifs3;
	}



	public OperateurInterco getOperateur1() {
		return operateur1;
	}

	public void setOperateur1(OperateurInterco operateur1) {
		this.operateur1 = operateur1;
	}

	public OperateurInterco getOperateur2() {
		return operateur2;
	}

	public void setOperateur2(OperateurInterco operateur2) {
		this.operateur2 = operateur2;
	}

	public OperateurInterco getOperateur3() {
		return operateur3;
	}

	public void setOperateur3(OperateurInterco operateur3) {
		this.operateur3 = operateur3;
	}

	public Pay getPays() {
		return pays;
	}

	public void setPays(Pay pays) {
		this.pays = pays;
	}

	public String getQualite1() {
		return this.qualite1;
	}

	public void setQualite1(String qualite1) {
		this.qualite1 = qualite1;
	}

	public String getQualite2() {
		return this.qualite2;
	}

	public void setQualite2(String qualite2) {
		this.qualite2 = qualite2;
	}

	public String getQualite3() {
		return this.qualite3;
	}

	public void setQualite3(String qualite3) {
		this.qualite3 = qualite3;
	}

}