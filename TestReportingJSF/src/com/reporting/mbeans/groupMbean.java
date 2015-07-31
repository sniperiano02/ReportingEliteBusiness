package com.reporting.mbeans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiTabsGroup;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.interfaces.GroupRemote;
import com.reporting.metier.interfaces.userRemote;


@ManagedBean(name="group_bean")
@ViewScoped
public class groupMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1767834881454937310L;
	
	@EJB
	private GroupRemote service_group;
	


	

	
	private GuiGroup group;
	private GuiGroup group1;
	
	
	public GuiGroup getGroup() {
		return group;
	}
	public GuiGroup getGroup1() {
		return group1;
	}
	public void setGroup(GuiGroup group) {
		this.group = group;
	}
	public void setGroup1(GuiGroup group1) {
		this.group1 = group1;
	}
	
	private List<GuiTabsGroup> tabsGroup= new ArrayList<>();
	
	
	  private List<GuiTabsGroup> tabsSource = new ArrayList<GuiTabsGroup>();
     private  List<GuiTabsGroup> tabsTarget = new ArrayList<GuiTabsGroup>();
	
      private DualListModel<GuiTabsGroup> allTabs ;
	
public List<GuiTabsGroup> getTabsGroup() {
	return tabsGroup;
}
public void setTabsGroup(List<GuiTabsGroup> tabsGroup) {
	this.tabsGroup = tabsGroup;
}
	

	private List<GuiGroup> list_groups= new ArrayList<GuiGroup>();

	@PostConstruct
	public void init(){
		tabsGroup =service_group.getAllTab();
		
		for(int i=0;i<tabsGroup.size()-1;i++){
		
			if(!tabsGroup.get(i).getTabName().equals(tabsGroup.get(i+1).getTabName())){
			
				
				tabsSource.add(tabsGroup.get(i));
			}
			
			
		}
		
		 allTabs = new DualListModel<GuiTabsGroup>(tabsSource, tabsTarget);
		list_groups = service_group.getAllGroup();
		group1 = new GuiGroup();
		group= new GuiGroup();
		
		
		
	}
	
	public DualListModel<GuiTabsGroup> getAllTabs() {
		return allTabs;
	}
	public void setAllTabs(DualListModel<GuiTabsGroup> allTabs) {
		this.allTabs = allTabs;
	}
	public List<GuiTabsGroup> getTabsSource() {
		return tabsSource;
	}
	public void setTabsSource(List<GuiTabsGroup> tabsSource) {
		this.tabsSource = tabsSource;
	}
	public List<GuiTabsGroup> getTabsTarget() {
		return tabsTarget;
	}
	public void setTabsTarget(List<GuiTabsGroup> tabsTarget) {
		this.tabsTarget = tabsTarget;
	}
	
	
	public void createGroup(){
		System.out.println(tabsTarget.isEmpty());
		group1.setTab_groups(allTabs.getTarget());
		group1.setDateCreation(new Timestamp(new Date().getTime()));
  service_group.addGroup(group1);
  list_groups = service_group.getAllGroup();
  tabsTarget = new ArrayList<GuiTabsGroup>();
  allTabs = new DualListModel<GuiTabsGroup>(tabsSource, tabsTarget);
	}
	public void modifGroup(){
		
		System.out.println(allTabs.getTarget().get(0).getTabName());
		group.setTab_groups(allTabs.getTarget());
		group.setDateModif(new Timestamp(new Date().getTime()));
  service_group.updateGroup(group);
list_groups = service_group.getAllGroup();
tabsTarget = new ArrayList<GuiTabsGroup>();
allTabs = new DualListModel<GuiTabsGroup>(tabsSource, tabsTarget);

	
		
	}
	public void deleteGroup(){
		
	
  service_group.deleteGroup(group);
list_groups = service_group.getAllGroup();

	
		
	}
	
	

	public List<GuiGroup> getList_groups() {
		return list_groups;
	}
	public void setList_groups(List<GuiGroup> list_groups) {
		this.list_groups = list_groups;
	}



}
