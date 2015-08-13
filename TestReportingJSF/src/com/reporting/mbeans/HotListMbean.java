package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.ListAppelant;
import com.reporting.metier.entities.ListAppele;
import com.reporting.metier.entities.ListCellid;
import com.reporting.metier.entities.ListDetailsAppelant;
import com.reporting.metier.entities.ListDetailsAppele;
import com.reporting.metier.entities.ListDetailsCellid;
import com.reporting.metier.entities.ListDetailsImei;
import com.reporting.metier.entities.ListImei;
import com.reporting.metier.interfaces.FraudeHotlistRemote;


@ManagedBean(name="manage_hotlist")
@ViewScoped
public class HotListMbean {
	
	
	@EJB
	private FraudeHotlistRemote hotlist_service;
	private ListAppele appele;
	private ListAppele appele1;
	private List<ListAppele> liste_appele ;
	
	private List<ListDetailsAppele> list_det_appele= new ArrayList<>();
	
	
	
	private ListAppelant appelant;
	private ListAppelant appelant1;
	private List<ListAppelant> liste_appelant ;
	
	private List<ListDetailsAppelant> list_det_appelant= new ArrayList<>();
	
	private ListImei imei;
	private ListImei imei1;
	private List<ListImei> liste_imei ;
	
	private List<ListDetailsImei> list_det_imei= new ArrayList<>();
	private ListCellid cellid;
	private ListCellid cellid1;
	private List<ListCellid> liste_cellid ;
	
	private List<ListDetailsCellid> list_det_cellid= new ArrayList<>();
	
	public List<ListDetailsAppele> getList_det_appele() {
		return list_det_appele;
	}
	public void setList_det_appele(List<ListDetailsAppele> list_det_appele) {
		this.list_det_appele = list_det_appele;
	}
	
	private ListDetailsAppele detailsappele;
	private ListDetailsAppele detailsappele1;
	private ListDetailsCellid detailscellid;
	private ListDetailsCellid detailscellid1;
	private ListDetailsImei detailsimei;
	private ListDetailsImei detailsimei1;
	private ListDetailsAppelant detailsappelant;
	private ListDetailsAppelant detailsappelant1;
	
	
	@PostConstruct
	public void init(){
		liste_appele=hotlist_service.getAllAppele();
		liste_appelant=hotlist_service.getAllAppelant();
		liste_cellid= hotlist_service.getAllCellId();
		liste_imei=hotlist_service.getAllImei();
		appele= new  ListAppele();
		appele1= new ListAppele();
		detailsappele= new ListDetailsAppele();
		detailsappele1= new ListDetailsAppele();
		appelant= new  ListAppelant();
		appelant1= new ListAppelant();
		detailsappelant= new ListDetailsAppelant();
		detailsappelant1= new ListDetailsAppelant();
		cellid= new ListCellid();
		cellid1= new ListCellid();
		detailscellid= new ListDetailsCellid();
		detailscellid1= new ListDetailsCellid();
		imei= new ListImei();
		imei1= new ListImei();
		detailsimei= new ListDetailsImei();
		detailsimei1= new ListDetailsImei();
		
	}

	
	public void ajoutAppele(){
		hotlist_service.addAppele(appele);
		liste_appele=hotlist_service.getAllAppele();
		
	}
	public void modifAppele(){
		hotlist_service.updateAppele(appele1);
		liste_appele=hotlist_service.getAllAppele();
		
	}
	public void deletAppele(){
		hotlist_service.deleteAppele(appele1);
		liste_appele=hotlist_service.getAllAppele();
		
	}
	
	
	public void addAppelant(){
		hotlist_service.addAppelant(appelant);
		liste_appelant=hotlist_service.getAllAppelant();

		
	}
	public void deleteAppelant(){
		hotlist_service.deleteAppelant(appelant1);
		liste_appelant=hotlist_service.getAllAppelant();

	}
	
	public void modifAppelant(){
		hotlist_service.updateAppelant(appelant1);
		liste_appelant=hotlist_service.getAllAppelant();

	}
	
	public void addImei(){
		hotlist_service.addImei(imei);
		liste_imei=hotlist_service.getAllImei();

	}
	public void deleteImei(){
		hotlist_service.deleteImei(imei1);
		liste_imei=hotlist_service.getAllImei();

	}
	public void modifImei(){
		hotlist_service.updateImei(imei1);
		liste_imei=hotlist_service.getAllImei();


	}
	public void addCellid(){
		hotlist_service.addCellId(cellid);
		liste_cellid= hotlist_service.getAllCellId();

		
	}
	public void deleteCellId(){
		hotlist_service.deleteCellId(cellid1);
		liste_cellid= hotlist_service.getAllCellId();

	}
	public void modifCellId(){
		hotlist_service.updateCellId(cellid1);
		liste_cellid= hotlist_service.getAllCellId();

	}
	public void loadDetailsAppelant(){
		list_det_appelant = hotlist_service.getListDetailsAppelant(appelant1);
		
		
	}
	public void loadDetailsCell(){
		list_det_cellid = hotlist_service.getListDetailsCellId(cellid1);
		
		
	}
	public void loadDetailsImei(){
		list_det_imei = hotlist_service.getListDetailsImei(imei1);
		
		
	}
	public void loadDetails(){
		list_det_appele = hotlist_service.getListDetailsAppeles(appele1);
		
		
	}
	public void addDetailAppelant(){
		
		detailsappelant.setAppelant(appelant1);
		hotlist_service.addDetailAppelant(detailsappelant);
		loadDetailsAppelant();
		
	}
	public void modifDetailAppelant(){
		hotlist_service.updateDetailAppelant(detailsappelant1);
		loadDetailsAppelant();
		
	}
	public void deleteDetailAppelant(){
		hotlist_service.deleteDetailAppelant(detailsappelant1);
		loadDetailsAppelant();
	}
	public void addDetailCellId(){
		detailscellid.setCellId(cellid1);
		hotlist_service.addDetailCellId(detailscellid);
		loadDetailsCell();
	}
	
	public void modifDetailCellId(){
		hotlist_service.updateDetailCellId(detailscellid1);
		loadDetailsCell();
	}
	public void deleteDetailCellId(){
		hotlist_service.deleteDetailCellId(detailscellid1);
	}
	public void addDetailImei(){
		detailsimei.setImei(imei1);
		hotlist_service.addDetailImei(detailsimei);
		loadDetailsImei();
	}
	public void deleteDetailImei(){
		hotlist_service.deleteDetailImei(detailsimei1);
		loadDetailsImei();
	}
	public void modifDetailImei(){
		hotlist_service.updateDetailImei(detailsimei1);
		loadDetailsImei();
	}
	public void addDetailsAppele(){
		
		
		detailsappele.setAppele(appele1);
		hotlist_service.addDetailAppele(detailsappele);
		list_det_appele = hotlist_service.getListDetailsAppeles(appele1);
		
	}
public void modifierDetailsAppele(){
		
		
		
		hotlist_service.updateDetailAppele(detailsappele1);
		list_det_appele = hotlist_service.getListDetailsAppeles(appele1);
		
	}
public void suppDetailsAppele(){
	
	
	
	hotlist_service.deleteDetailAppele(detailsappele1);
	list_det_appele = hotlist_service.getListDetailsAppeles(appele1);
	
}

















	public ListAppele getAppele() {
		return appele;
	}


	public void setAppele(ListAppele appele) {
		this.appele = appele;
	}


	public ListAppele getAppele1() {
		return appele1;
	}


	public void setAppele1(ListAppele appele1) {
		this.appele1 = appele1;
	}


	public List<ListAppele> getListe_appele() {
		return liste_appele;
	}


	public void setListe_appele(List<ListAppele> liste_appele) {
		this.liste_appele = liste_appele;
	}
	

	public ListDetailsAppele getDetailsappele() {
		return detailsappele;
	}
	public void setDetailsappele(ListDetailsAppele detailsappele) {
		this.detailsappele = detailsappele;
	}
	public ListDetailsAppele getDetailsappele1() {
		return detailsappele1;
	}
	public void setDetailsappele1(ListDetailsAppele detailsappele1) {
		this.detailsappele1 = detailsappele1;
	}
	public ListAppelant getAppelant() {
		return appelant;
	}
	public void setAppelant(ListAppelant appelant) {
		this.appelant = appelant;
	}
	public ListAppelant getAppelant1() {
		return appelant1;
	}
	public void setAppelant1(ListAppelant appelant1) {
		this.appelant1 = appelant1;
	}
	public List<ListAppelant> getListe_appelant() {
		return liste_appelant;
	}
	public void setListe_appelant(List<ListAppelant> liste_appelant) {
		this.liste_appelant = liste_appelant;
	}
	public List<ListDetailsAppelant> getList_det_appelant() {
		return list_det_appelant;
	}
	public void setList_det_appelant(List<ListDetailsAppelant> list_det_appelant) {
		this.list_det_appelant = list_det_appelant;
	}
	public ListImei getImei() {
		return imei;
	}
	public void setImei(ListImei imei) {
		this.imei = imei;
	}
	public ListImei getImei1() {
		return imei1;
	}
	public void setImei1(ListImei imei1) {
		this.imei1 = imei1;
	}
	public List<ListImei> getListe_imei() {
		return liste_imei;
	}
	public void setListe_imei(List<ListImei> liste_imei) {
		this.liste_imei = liste_imei;
	}
	public List<ListDetailsImei> getList_det_imei() {
		return list_det_imei;
	}
	public void setList_det_imei(List<ListDetailsImei> list_det_imei) {
		this.list_det_imei = list_det_imei;
	}
	public ListCellid getCellid() {
		return cellid;
	}
	public void setCellid(ListCellid cellid) {
		this.cellid = cellid;
	}
	public ListCellid getCellid1() {
		return cellid1;
	}
	public void setCellid1(ListCellid cellid1) {
		this.cellid1 = cellid1;
	}
	public List<ListCellid> getListe_cellid() {
		return liste_cellid;
	}
	public void setListe_cellid(List<ListCellid> liste_cellid) {
		this.liste_cellid = liste_cellid;
	}
	public List<ListDetailsCellid> getList_det_cellid() {
		return list_det_cellid;
	}
	public void setList_det_cellid(List<ListDetailsCellid> list_det_cellid) {
		this.list_det_cellid = list_det_cellid;
	}
	public ListDetailsCellid getDetailscellid() {
		return detailscellid;
	}
	public void setDetailscellid(ListDetailsCellid detailscellid) {
		this.detailscellid = detailscellid;
	}
	public ListDetailsCellid getDetailscellid1() {
		return detailscellid1;
	}
	public void setDetailscellid1(ListDetailsCellid detailscellid1) {
		this.detailscellid1 = detailscellid1;
	}
	public ListDetailsImei getDetailsimei() {
		return detailsimei;
	}
	public void setDetailsimei(ListDetailsImei detailsimei) {
		this.detailsimei = detailsimei;
	}
	public ListDetailsImei getDetailsimei1() {
		return detailsimei1;
	}
	public void setDetailsimei1(ListDetailsImei detailsimei1) {
		this.detailsimei1 = detailsimei1;
	}
	public ListDetailsAppelant getDetailsappelant() {
		return detailsappelant;
	}
	public void setDetailsappelant(ListDetailsAppelant detailsappelant) {
		this.detailsappelant = detailsappelant;
	}
	public ListDetailsAppelant getDetailsappelant1() {
		return detailsappelant1;
	}
	public void setDetailsappelant1(ListDetailsAppelant detailsappelant1) {
		this.detailsappelant1 = detailsappelant1;
	}
	
	
	
}
