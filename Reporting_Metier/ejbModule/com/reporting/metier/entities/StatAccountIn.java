package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_account_in database table.
 * 
 */
@Entity
@Table(name="stat_account_in",schema="stat")
@NamedQuery(name="StatAccountIn.findAll", query="SELECT s FROM StatAccountIn s")
public class StatAccountIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="account_in",referencedColumnName="account_id")
	private AccountIn account;
	
	
	

	@Column(name="balance_final")
	private BigDecimal balanceFinal;

	@Column(name="balance_initial")
	private BigDecimal balanceInitial;

	private BigDecimal conso;

	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal diff;

	@Column(name="valeur_alim")
	private BigDecimal valeurAlim;

	public StatAccountIn() {
	}

public AccountIn getAccount() {
	return account;
}
public void setAccount(AccountIn account) {
	this.account = account;
}

	public BigDecimal getBalanceFinal() {
		return this.balanceFinal;
	}

	public void setBalanceFinal(BigDecimal balanceFinal) {
		this.balanceFinal = balanceFinal;
	}

	public BigDecimal getBalanceInitial() {
		return this.balanceInitial;
	}

	public void setBalanceInitial(BigDecimal balanceInitial) {
		this.balanceInitial = balanceInitial;
	}

	public BigDecimal getConso() {
		return this.conso;
	}

	public void setConso(BigDecimal conso) {
		this.conso = conso;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getDiff() {
		return this.diff;
	}

	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}

	public BigDecimal getValeurAlim() {
		return this.valeurAlim;
	}

	public void setValeurAlim(BigDecimal valeurAlim) {
		this.valeurAlim = valeurAlim;
	}

}