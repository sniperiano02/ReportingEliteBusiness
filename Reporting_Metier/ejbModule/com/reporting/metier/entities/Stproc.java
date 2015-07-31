package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the stproc database table.
 * 
 */
@Entity
@Table(schema="tables")
@NamedQuery(name="Stproc.findAll", query="SELECT s FROM Stproc s")
public class Stproc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String proc;

	private BigDecimal cycle;
	
	
	
	private Integer etatlance;
	

	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name="flagpk",referencedColumnName="idflag")
	private Alflag flag;
	

	public Alflag getFlag() {
		return flag;
	}
	public void setFlag(Alflag flag) {
		this.flag = flag;
	}

	@Column(name="l_end")
	private Timestamp lEnd;

	@Column(name="l_start")
	private Timestamp lStart;

	private String lbconv;

	private String lbproc;

	private String module;

	@Column(name="nom_proc")
	private String nomProc;

	private Integer orders;

	private BigDecimal pid;

	private BigDecimal pidconv;

	private BigDecimal prmproc;

	private String procsql;

	private BigDecimal prpause;

	private BigDecimal standby;

	@Column(name="status_act")
	private BigDecimal statusAct;

	@Column(name="status_glob")
	private BigDecimal statusGlob;

	private String tablesrc;

	public Stproc() {
	}

	public String getProc() {
		return this.proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}

	public BigDecimal getCycle() {
		return this.cycle;
	}

	public void setCycle(BigDecimal cycle) {
		this.cycle = cycle;
	}

	public Integer getEtatlance() {
		return etatlance;
	}
	public void setEtatlance(Integer etatlance) {
		this.etatlance = etatlance;
	}
	
	public Timestamp getLEnd() {
		return this.lEnd;
	}

	public void setLEnd(Timestamp lEnd) {
		this.lEnd = lEnd;
	}

	public Timestamp getLStart() {
		return this.lStart;
	}

	public void setLStart(Timestamp lStart) {
		this.lStart = lStart;
	}

	public String getLbconv() {
		return this.lbconv;
	}

	public void setLbconv(String lbconv) {
		this.lbconv = lbconv;
	}

	public String getLbproc() {
		return this.lbproc;
	}

	public void setLbproc(String lbproc) {
		this.lbproc = lbproc;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getNomProc() {
		return this.nomProc;
	}

	public void setNomProc(String nomProc) {
		this.nomProc = nomProc;
	}

	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public BigDecimal getPid() {
		return this.pid;
	}

	public void setPid(BigDecimal pid) {
		this.pid = pid;
	}

	public BigDecimal getPidconv() {
		return this.pidconv;
	}

	public void setPidconv(BigDecimal pidconv) {
		this.pidconv = pidconv;
	}

	public BigDecimal getPrmproc() {
		return this.prmproc;
	}

	public void setPrmproc(BigDecimal prmproc) {
		this.prmproc = prmproc;
	}

	public String getProcsql() {
		return this.procsql;
	}

	public void setProcsql(String procsql) {
		this.procsql = procsql;
	}

	public BigDecimal getPrpause() {
		return this.prpause;
	}

	public void setPrpause(BigDecimal prpause) {
		this.prpause = prpause;
	}

	public BigDecimal getStandby() {
		return this.standby;
	}

	public void setStandby(BigDecimal standby) {
		this.standby = standby;
	}

	public BigDecimal getStatusAct() {
		return this.statusAct;
	}

	public void setStatusAct(BigDecimal statusAct) {
		this.statusAct = statusAct;
	}

	public BigDecimal getStatusGlob() {
		return this.statusGlob;
	}

	public void setStatusGlob(BigDecimal statusGlob) {
		this.statusGlob = statusGlob;
	}

	public String getTablesrc() {
		return this.tablesrc;
	}

	public void setTablesrc(String tablesrc) {
		this.tablesrc = tablesrc;
	}

}