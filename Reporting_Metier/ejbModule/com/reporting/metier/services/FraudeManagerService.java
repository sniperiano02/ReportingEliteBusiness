package com.reporting.metier.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.annotations.Filters;

import com.reporting.metier.entities.CategoriesFraude;
import com.reporting.metier.entities.DecisionFraude;
import com.reporting.metier.entities.FiltresFlux;
import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FiltresReglesFraude;
import com.reporting.metier.entities.FluxCdr;
import com.reporting.metier.entities.ParametresCategory;
import com.reporting.metier.entities.ParametresFraude;
import com.reporting.metier.entities.ParametresReglesFraude;
import com.reporting.metier.entities.ReglesFraude;
import com.reporting.metier.interfaces.FraudeManagerRemote;


@Stateless
public class FraudeManagerService implements FraudeManagerRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<CategoriesFraude> getAllCategories() {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select c From CategoriesFraude c").getResultList();
	}

	@Override
	public List<FluxCdr> getAllFlux() {
		// TODO Auto-generated method stub
		return em.createQuery("Select f From FluxCdr f").getResultList();
	}

	@Override
	public List<ParametresFraude> getParametresFraude(String Where) {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select Distinct p.parametre From ParametresCategory p where "+Where).getResultList();
	}

	@Override
	public List<FiltresFraude> getFilterFiltresFraude(String where) {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select Distinct f.filtre From FiltresFlux f where "+where).getResultList();
	}

	@Override
	public void updateRegle(ReglesFraude rf) {
		// TODO Auto-generated method stub
		em.merge(rf);
	}

	@Override
	public void addRegle(ReglesFraude rf) {
		// TODO Auto-generated method stub
		em.persist(rf);
		
	}

	@Override
	public void deleteRegle(ReglesFraude rf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FiltresFraude getFilterByID(Integer id) {
		// TODO Auto-generated method stub
		return em.find(FiltresFraude.class, id);
	}

	@Override
	public FluxCdr getFluxByID(Integer id) {
		// TODO Auto-generated method stub
		return em.find(FluxCdr.class, id);
	}

	@Override
	public ParametresFraude getParametreByID(Integer id) {
		// TODO Auto-generated method stub
		return em.find(ParametresFraude.class, id);
	}

	@Override
	public CategoriesFraude getCategoryByID(Integer id) {
		// TODO Auto-generated method stub
		return em.find(CategoriesFraude.class, id);
	}

	@Override
	public List<ReglesFraude> getAllFraudes(String Where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select r From ReglesFraude r "+Where).getResultList();
	}

	@Override
	public void addParametre(ParametresReglesFraude pr) {
		// TODO Auto-generated method stub
		em.persist(pr);
		
	}

	@Override
	public void updateParametre(ParametresReglesFraude pr) {
		// TODO Auto-generated method stub
		em.merge(pr);
		
	}

	@Override
	public void deleteParametre(ParametresReglesFraude pr) {
		// TODO Auto-generated method stub
		em.createQuery("DELETE   FROM ParametresReglesFraude f WHERE f.id="+pr.getId()).executeUpdate();
	}

	@Override
	public void addFiltre(FiltresReglesFraude fr) {
		// TODO Auto-generated method stub
		em.persist(fr);
	}

	@Override
	public void updateFiltre(FiltresReglesFraude fr) {
		// TODO Auto-generated method stub
		em.merge(fr);
	}

	@Override
	public void deleteFiltre(FiltresReglesFraude fr) {
		// TODO Auto-generated method stub
		
		em.createQuery("DELETE   FROM FiltresReglesFraude f WHERE f.id="+fr.getId()).executeUpdate();
	}

	@Override
	public List<ParametresReglesFraude> getParametresReglesFraudeByRegle(
			ReglesFraude rf) {
		// TODO Auto-generated method stub
		return em.createQuery("Select f From ParametresReglesFraude f where f.regle.id = "+rf.getId() ).getResultList();
	}

	@Override
	public List<FiltresReglesFraude> getFiltresReglesFraudeByRegle(
			ReglesFraude rf) {
		// TODO Auto-generated method stub
		return em.createQuery("Select f From FiltresReglesFraude f where f.regle.id = "+rf.getId() ).getResultList();
	}

	@Override
	public List<Object[]> getAlerteFraudeByRegle(ReglesFraude rg) {
		// TODO Auto-generated method stub
		return em.createNativeQuery(" Select id_regle,msisdn,to_timestamp(min(date_debut), 'YYMMDDHH24MISS') as date_debut,to_timestamp(max(date_fin), 'YYMMDDHH24MISS')as date_fin,max(date_detection) as date_detection,count(*)as nb_occurance ,min(date_debut) from stat.alerte_fraude_seq where id_regle ="+rg.getId()+"  group by id_regle,msisdn").getResultList();
	}
	@Override
	public List<Object[]> getAlerteHURByRegle(ReglesFraude rg,String date) {
		// TODO Auto-generated method stub
		return em.createNativeQuery(" Select id_regle, msisdn,to_date(date_appel,'YYMMDD'), nb_occurance,date_appel  from stat.alerte_fraude where id_regle ="+rg.getId()+" and to_date(date_appel,'YYMMDD')=to_date('"+date+"','DD/MM/YYYY')  group by id_regle,msisdn,date_appel,nb_occurance").getResultList();
	}
	

	@Override
	public List<Object[]> getParametres(ReglesFraude prg,String msisdn) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT tableref.parametres_fraudes.nom_parametre, tbl.valeur ,to_timestamp(tbl.date_debut, 'YYMMDDHH24MISS') as date_debut, to_timestamp(tbl.date_fin, 'YYMMDDHH24MISS') as date_fin   FROM (select * from stat.regle_parametres_valeur_seq where id_regle = "+prg.getId()+" and stat.regle_parametres_valeur_seq.msisdn = '"+msisdn+"'  )  as tbl,tableref.parametres_fraudes  where tbl.id_parametre=tableref.parametres_fraudes.id ").getResultList();
				
	}

	@Override
	public Object[] getDecisions(String msisdn) {
		// TODO Auto-generated method stub
		
		
		return (Object[]) em.createNativeQuery("Select * from  tables.parc_gpto_act where id='"+msisdn.substring(3,msisdn.length())+"' LIMIT 1").getSingleResult();
		}

	@Override
	public List<Object[]> getCDRByTypeRegle(String date,String where) {
		// TODO Auto-generated method stub
		try{
			return em.createNativeQuery("Select * From cdrs_archives.cdrs_"+date+" "+where).getResultList();
		}catch(PersistenceException exp){
			return null;
		}
		
		
	}

	@Override
	public List<Object[]> getCDRBActivatonyTypeRegle(String date,String where) {
		// TODO Auto-generated method stub
		try{
			return em.createNativeQuery("Select * From cdrs_archives.cdrs_in_act_"+date+" "+where).getResultList();
		}catch(PersistenceException exp){
			return null;
		}
		}

	@Override
	public List<Object[]> getCDRRechargeByTypeRegle(String date,String where) {
		
		try{
			return em.createNativeQuery("Select * From cdrs_archives.cdrs_in_recharge"+date+" "+where).getResultList();
		}catch(PersistenceException exp){
			return null;
		}
	}

	@Override
	public List<Object[]> getCDRTransfertByTypeRegle(String date,String where) {
		// TODO Auto-generated method stub
		try{
			return em.createNativeQuery("Select * From cdrs_archives.cdrs_in_mgr_"+date+" "+where).getResultList();
		}catch(PersistenceException exp){
			return null;
		}
	}

	@Override
	public void decision(DecisionFraude df,String msisdn) {
		// TODO Auto-generated method stub
		em.persist(df);
		em.createNativeQuery("Delete FROM stat.alerte_fraude_seq  where msisdn = '"+msisdn+"'").executeUpdate();

	}

	@Override
	public void updateDecision(DecisionFraude df) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DecisionFraude> getDecisionFraudes(String where) {
		// TODO Auto-generated method stub
	
		return em.createQuery("Select d From DecisionFraude d  "+where).getResultList();
	}

	@Override
	public List<Object[]> getMSISDNMorethantwo() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("select msisdn,count(*) from (select msisdn,id_regle,count(*)as nombre from stat.alerte_fraude_seq group by msisdn,id_regle) a group by msisdn having count(*)>1").getResultList();
	}

	
	@Override
	public ReglesFraude getRegleById(Integer id){
		return em.find(ReglesFraude.class, id);
	}

	@Override
	public List<Object[]> getReglesByMSISDN(String msisdn){
		return em.createNativeQuery("select a.nom,a.id,b.date_debut,b.date_fin,b.date_detection,b.nb_occurance,b.datee from( select id_regle,msisdn,to_timestamp(min(date_debut), 'YYMMDDHH24MISS') as date_debut,to_timestamp(max(date_fin), 'YYMMDDHH24MISS')as date_fin,max(date_detection) as date_detection,count(*)as nb_occurance ,min(date_debut) as datee from stat.alerte_fraude_seq  where msisdn='"+msisdn+"' group by id_regle,msisdn ) as b,tableref.regles_fraudes as a where a.id=b.id_regle").getResultList();

	}
	



}
