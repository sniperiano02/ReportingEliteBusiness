package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.CategoriesFraude;
import com.reporting.metier.entities.DecisionFraude;
import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FiltresReglesFraude;
import com.reporting.metier.entities.FluxCdr;
import com.reporting.metier.entities.ParametresCategory;
import com.reporting.metier.entities.ParametresFraude;
import com.reporting.metier.entities.ParametresReglesFraude;
import com.reporting.metier.entities.ReglesFraude;


@Remote
public interface FraudeManagerRemote {

	public ReglesFraude getRegleById(Integer id);
	public List<DecisionFraude> getDecisionFraudes(String where);
	public void updateDecision(DecisionFraude df);
	public void decision(DecisionFraude df,String msisdn);
	public List<Object[]> getCDRBActivatonyTypeRegle(String date,String where);
	public List<Object[]> getCDRRechargeByTypeRegle(String date,String where);
	public List<Object[]> getCDRTransfertByTypeRegle(String date,String where);
	public List<Object[]> getCDRByTypeRegle(String date,String where);
	public List<Object[]> getParametres(ReglesFraude prg ,String msisdn);
	public Object[] getDecisions(String msisdn);
	public List<Object[]> getAlerteFraudeByRegle(ReglesFraude rg);
	public List<Object[]> getReglesByMSISDN(String msisdn);
	public List<Object[]> getMSISDNMorethantwo();
	public List<Object[]> getAlerteHURByRegle(ReglesFraude rg,String date);

	public List<CategoriesFraude> getAllCategories();
	public List<FluxCdr> getAllFlux();
	public List<ReglesFraude> getAllFraudes(String Where);
	public List<ParametresReglesFraude> getParametresReglesFraudeByRegle(ReglesFraude rf);
	public List<FiltresReglesFraude> getFiltresReglesFraudeByRegle(ReglesFraude rf);
	public List<ParametresFraude> getParametresFraude(String Where);
	public List<FiltresFraude> getFilterFiltresFraude(String where);
	public void updateRegle(ReglesFraude rf);
	public void addParametre(ParametresReglesFraude pr);
	public void updateParametre(ParametresReglesFraude pr);
	public void deleteParametre(ParametresReglesFraude pr);
	public void addFiltre(FiltresReglesFraude fr);
	public void updateFiltre(FiltresReglesFraude fr);
	public void deleteFiltre(FiltresReglesFraude fr);
	public void addRegle(ReglesFraude rf);
	public void deleteRegle(ReglesFraude rf);
	public FiltresFraude getFilterByID(Integer id);
	public FluxCdr getFluxByID(Integer id);
	public ParametresFraude getParametreByID(Integer id);
	public CategoriesFraude getCategoryByID(Integer id);
}
