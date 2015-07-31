package com.reporting.metier.interfaces;

import java.util.List;

import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.Result;

public interface GenericReportingRemote {
	
	
	public List<Result> BarOrLineGraphePreview(String x,List<AxeY> list_y,String table);
	
	

}
