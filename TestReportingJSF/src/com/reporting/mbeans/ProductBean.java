package com.reporting.mbeans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FiltresReglesFraude;

@ManagedBean
@ViewScoped
public class ProductBean {

  protected FiltresReglesFraude filtre_regle_fraude;
  protected List<FiltresReglesFraude>  listFiltresRegle;

  
  public FiltresReglesFraude getFiltre_regle_fraude() {
	return filtre_regle_fraude;
}
  public void setFiltre_regle_fraude(FiltresReglesFraude filtre_regle_fraude) {
	this.filtre_regle_fraude = filtre_regle_fraude;
}
  public void setListFiltresRegle(List<FiltresReglesFraude> listFiltresRegle) {
	this.listFiltresRegle = listFiltresRegle;
}
  public List<FiltresReglesFraude> getListFiltresRegle() {
	return listFiltresRegle;
}
  
  @PostConstruct
  public void init(){
	  listFiltresRegle = new ArrayList<>();
	  filtre_regle_fraude= new FiltresReglesFraude();
  }
  public void addNewPrice() {
    FiltresFraude p1 = new FiltresFraude();
    p1.setFiltre("test");
    
    filtre_regle_fraude.setFiltreFraude(p1);
    filtre_regle_fraude.setVegal("TypeDest");
    
    this.listFiltresRegle.add(filtre_regle_fraude);
    filtre_regle_fraude = new FiltresReglesFraude();
  }

}