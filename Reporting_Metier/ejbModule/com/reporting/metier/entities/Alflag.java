package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the alflag database table.
 * 
 */
@Entity
@Table(name="alflag",schema="tableref")
@NamedQuery(name="Alflag.findAll", query="SELECT a FROM Alflag a")
public class Alflag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idflag;

	private String lbflag;

	public Alflag() {
	}

	public long getIdflag() {
		return this.idflag;
	}

	public void setIdflag(long idflag) {
		this.idflag = idflag;
	}

	public String getLbflag() {
		return this.lbflag;
	}

	public void setLbflag(String lbflag) {
		this.lbflag = lbflag;
	}

}