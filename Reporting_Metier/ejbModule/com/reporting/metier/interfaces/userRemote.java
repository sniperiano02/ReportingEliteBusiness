package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiUser;

@Remote
public interface userRemote {
	
	public List<GuiUser>  getAllUsers();
	
	public void addUser(GuiUser u);
	
	public void deleteUser(GuiUser u);
	
	public void updateUser(GuiUser u);
	public GuiGroup getGroupByName(String name);
	
	public List<GuiGroup> getAllGroup();
	
	public GuiUser authenticate(String login,String password);

}
