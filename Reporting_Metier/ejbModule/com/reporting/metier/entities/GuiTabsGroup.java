package com.reporting.metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


/**
 * The persistent class for the gui_tabs_groups database table.
 * 
 */
@Entity
@Table(name="gui_tabs_groups",schema="tableref")
@NamedQuery(name="GuiTabsGroup.findAll", query="SELECT g FROM GuiTabsGroup g")
public class GuiTabsGroup implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	
	@ManyToMany(mappedBy="tab_groups")
	private List<GuiGroup> group_tab;

	public List<GuiGroup> getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(List<GuiGroup> group_tab) {
		this.group_tab = group_tab;
	}


	@Column(name="tab_name")
	private String tabName;

	public GuiTabsGroup() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getTabName() {
		return this.tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

}