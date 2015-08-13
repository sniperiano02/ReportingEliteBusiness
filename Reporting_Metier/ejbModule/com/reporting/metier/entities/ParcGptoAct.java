package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parc_gpto_act database table.
 * 
 */
@Entity
@Table(name="parc_gpto_act",schema="tableref")
@NamedQuery(name="ParcGptoAct.findAll", query="SELECT p FROM ParcGptoAct p")
public class ParcGptoAct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="activation_date")
	private String activationDate;

	@Column(name="billing_group")
	private String billingGroup;

	@Column(name="billing_type")
	private String billingType;

	@Column(name="class")
	private String class_;

	@Column(name="deactivation_date")
	private String deactivationDate;

	private String fleet;

	
	@Id
	private String id;

	private String imsi;

	@Column(name="max_amount")
	private String maxAmount;

	@Column(name="mobile_contact")
	private String mobileContact;

	private String name;

	private String product;

	private String sequence;

	private String status;

	@Column(name="subscription_kind")
	private String subscriptionKind;

	private String town;

	@Column(name="type_subscriber")
	private String typeSubscriber;

	public ParcGptoAct() {
	}

	public String getActivationDate() {
		return this.activationDate;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	public String getBillingGroup() {
		return this.billingGroup;
	}

	public void setBillingGroup(String billingGroup) {
		this.billingGroup = billingGroup;
	}

	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getDeactivationDate() {
		return this.deactivationDate;
	}

	public void setDeactivationDate(String deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	public String getFleet() {
		return this.fleet;
	}

	public void setFleet(String fleet) {
		this.fleet = fleet;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getMobileContact() {
		return this.mobileContact;
	}

	public void setMobileContact(String mobileContact) {
		this.mobileContact = mobileContact;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubscriptionKind() {
		return this.subscriptionKind;
	}

	public void setSubscriptionKind(String subscriptionKind) {
		this.subscriptionKind = subscriptionKind;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getTypeSubscriber() {
		return this.typeSubscriber;
	}

	public void setTypeSubscriber(String typeSubscriber) {
		this.typeSubscriber = typeSubscriber;
	}

}