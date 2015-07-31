package com.reporting.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.reporting.mbeans.HelperBean;
import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.PlanTarifaire;


@FacesConverter("grp_converter")
public class GroupConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		GuiGroup g = null;
		if(!arg2.trim().equals("")){
			HelperBean helper = arg0.getApplication().evaluateExpressionGet(arg0,"#{helperBean}", HelperBean.class);
			g = helper.findGroupByName(arg2);
		}
		return g;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		String equivalentString = null;
		if(value == null || value.equals("")){
			equivalentString = "";
		}else{
			equivalentString = ((GuiGroup) value).getGName();
		}
		return equivalentString;
	}

	

}
