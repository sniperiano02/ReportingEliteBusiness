package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.interfaces.VerifBilingRemote;;





@Stateless
public class VerifBilingService implements VerifBilingRemote{

	
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getStatVerifBiling(String x, String list_y,
			String groupby, List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
			Query q=  em.createQuery("Select "+x+","+list_y+" From StatReconInter  Where  "+where+" Group By "+x+" Order By "+x );
			
			return q.getResultList();

	}
	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select  distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatReconInter  order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
		}
	@Override
	public List<Object[]> getDetailsStatVerifBiling(String Where) {
		// TODO Auto-generated method stub
	Query q=  em.createQuery("Select codeOperateur,SUM(nbAppelCti),SUM(dureeCti),SUM(montantCti),SUM(nbAppelIntec),SUM(dureeIntec),SUM(montantIntec) From StatReconInter Where  "+Where+" Group By codeOperateur"  );
	
		return q.getResultList();
	}
}
