package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fichier_lcr database table.
 * 
 */
@Entity
@Table(name="fichier_lcr",schema="tableref")
@NamedQuery(name="FichierLcr.findAll", query="SELECT f FROM FichierLcr f")
public class FichierLcr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	private String colonne1;

	private String colonne10;

	private String colonne2;

	private String colonne3;

	private String colonne4;

	private String colonne5;

	private String colonne6;

	private String colonne7;

	private String colonne8;

	private String colonne9;

	@Column(name="nom_fichier")
	private String nomFichier;

	@Column(name="nom_template")
	private String nomTemplate;

	private String operateur;

	public FichierLcr() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColonne1() {
		return this.colonne1;
	}

	public void setColonne1(String colonne1) {
		this.colonne1 = colonne1;
	}

	public String getColonne10() {
		return this.colonne10;
	}

	public void setColonne10(String colonne10) {
		this.colonne10 = colonne10;
	}

	public String getColonne2() {
		return this.colonne2;
	}

	public void setColonne2(String colonne2) {
		this.colonne2 = colonne2;
	}

	public String getColonne3() {
		return this.colonne3;
	}

	public void setColonne3(String colonne3) {
		this.colonne3 = colonne3;
	}

	public String getColonne4() {
		return this.colonne4;
	}

	public void setColonne4(String colonne4) {
		this.colonne4 = colonne4;
	}

	public String getColonne5() {
		return this.colonne5;
	}

	public void setColonne5(String colonne5) {
		this.colonne5 = colonne5;
	}

	public String getColonne6() {
		return this.colonne6;
	}

	public void setColonne6(String colonne6) {
		this.colonne6 = colonne6;
	}

	public String getColonne7() {
		return this.colonne7;
	}

	public void setColonne7(String colonne7) {
		this.colonne7 = colonne7;
	}

	public String getColonne8() {
		return this.colonne8;
	}

	public void setColonne8(String colonne8) {
		this.colonne8 = colonne8;
	}

	public String getColonne9() {
		return this.colonne9;
	}

	public void setColonne9(String colonne9) {
		this.colonne9 = colonne9;
	}

	public String getNomFichier() {
		return this.nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getNomTemplate() {
		return this.nomTemplate;
	}

	public void setNomTemplate(String nomTemplate) {
		this.nomTemplate = nomTemplate;
	}

	public String getOperateur() {
		return this.operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

}