package com.reporting.mbeans;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

 
@ManagedBean
public class ThemeSwitcherView {
 
    private List<Theme> themes;
     
    @ManagedProperty("#{themeService}")
    private ThemeService service;
 
    private String theme1;
    
    
    public String getTheme1() {
		return theme1;
	}
    public void setTheme1(String theme1) {
		this.theme1 = theme1;
	}
    
    @PostConstruct
    public void init() {
        themes = service.getThemes();
      theme1="start";
    }
     
    public List<Theme> getThemes() {
        return themes;
    } 
 
    public void setService(ThemeService service) {
        this.service = service;
    }
}