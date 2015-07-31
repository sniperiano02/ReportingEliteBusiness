package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiTabsGroup;

@Remote
public interface GroupRemote {

	public GuiTabsGroup getTabByName(String name);
	public List<GuiGroup> getAllGroup();
	
	public List<GuiTabsGroup> getAllTab();
	public void addGroup(GuiGroup g);
	public void deleteGroup(GuiGroup g);
	public void updateGroup(GuiGroup g);
}
