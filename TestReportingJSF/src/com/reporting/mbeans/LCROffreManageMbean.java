package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.reporting.metier.entities.FichierLcrProcess;
import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.OperateurInterco;
import com.reporting.metier.entities.TarifsInter;
import com.reporting.metier.interfaces.LcrRemoteManager;
import com.reporting.metier.interfaces.OperateurIntercoRemote;
import com.reporting.metier.interfaces.TarifinterLcrRemote;


@ManagedBean(name="lcr_offre_manage")
@ViewScoped
public class LCROffreManageMbean {
	
	
	private List<SelectItem> LcrOffreFilterOptions;
	
	
	
	@EJB
	private TarifinterLcrRemote tarifs_service;
	private List<OperateurInterco> operateurs = new ArrayList<>();
	
	@EJB
	private OperateurIntercoRemote operateur_service;
	
	public List<SelectItem> getLcrOffreFilterOptions() {
		return LcrOffreFilterOptions;
	}
	public void setLcrOffreFilterOptions(List<SelectItem> lcrOffreFilterOptions) {
		LcrOffreFilterOptions = lcrOffreFilterOptions;
	}
	
	private List<TarifsInter> listeTarifs = new ArrayList<>();
	private List<FichierLcrProcess> liste_fichierLcrProcess;
	

	
	public List<FichierLcrProcess> getListe_fichierLcrProcess() {
		return liste_fichierLcrProcess;
	}
	public void setListe_fichierLcrProcess(
			List<FichierLcrProcess> liste_fichierLcrProcess) {
		this.liste_fichierLcrProcess = liste_fichierLcrProcess;
	}
	
	private FichierLcrProcess fichierlcrprocess;
	public FichierLcrProcess getFichierlcrprocess() {
		return fichierlcrprocess;
	}
	public void setFichierlcrprocess(FichierLcrProcess fichierlcrprocess) {
		this.fichierlcrprocess = fichierlcrprocess;
	}
	@EJB
	private LcrRemoteManager lcr_service;
	
	
	
	@PostConstruct
	public void init(){
		liste_fichierLcrProcess= new ArrayList<>();
		liste_fichierLcrProcess = lcr_service.getAllLcrProcess();
		operateurs=operateur_service.getAllOperateurs("I");
		LcrOffreFilterOptions= new ArrayList<SelectItem>(
				operateurs.size() + 1);
		LcrOffreFilterOptions.add(new SelectItem("", "Choisir.."));
		for (OperateurInterco op : operateurs) {
			LcrOffreFilterOptions.add(new SelectItem(op.getOperateur(),
					op.getOperateur()));
		}
	fichierlcrprocess= new FichierLcrProcess();
	}
	
	
	public List<TarifsInter> getListeTarifs() {
		return listeTarifs;
	}
	public void setListeTarifs(List<TarifsInter> listeTarifs) {
		this.listeTarifs = listeTarifs;
	}
	public void change(){
		listeTarifs= tarifs_service.getTarifsInterByFichier(fichierlcrprocess.getNomFichier());
		
		
	}
	
	public void deleteOffreLcr(){
		lcr_service.deleteOffre(fichierlcrprocess);
		liste_fichierLcrProcess = lcr_service.getAllLcrProcess();
		
	}

}

