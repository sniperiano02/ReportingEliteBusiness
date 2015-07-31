package com.reporting.mbeans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
import com.reporting.metier.interfaces.userRemote;

@ManagedBean(name="user_gestion")
@ViewScoped
public class userMbean implements Serializable {
private static final long serialVersionUID = -3488417247629137575L;
	
	
	@EJB
	private userRemote service_user;
	
	private GuiUser user ;
	private List<SelectItem> GroupFilterOptions;
	
	private GuiUser user1;
	
	private GuiGroup user_grp;
	
	
	public GuiGroup getUser_grp() {
		return user_grp;
	}
	public void setUser_grp(GuiGroup user_grp) {
		this.user_grp = user_grp;
	}
	
	
	public GuiUser getUser1() {
		return user1;
	}
	public void setUser1(GuiUser user1) {
		this.user1 = user1;
	}
	

	

	
	private List<GuiUser> list_users = new ArrayList<GuiUser>();
	private List<GuiGroup> list_groups= new ArrayList<GuiGroup>();
	
	public List<SelectItem> getGroupFilterOptions() {
		return GroupFilterOptions;
	}
	public void setGroupFilterOptions(List<SelectItem> groupFilterOptions) {
		GroupFilterOptions = groupFilterOptions;
	}
	
	@PostConstruct
	public void init(){
		user1 = new GuiUser();
		list_users= service_user.getAllUsers();
		list_groups = service_user.getAllGroup();
		GroupFilterOptions = new ArrayList<SelectItem>(
				list_groups.size() + 1);
		GroupFilterOptions.add(new SelectItem("", "Choisir.."));
		for (GuiGroup group : list_groups) {
			GroupFilterOptions.add(new SelectItem(group.getGName(),
					group.getGName()));
		}
	}
	
	public String getEncodedPassword(String key) {
		  byte[] uniqueKey = key.getBytes();
		  byte[] hash = null;
		  try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		  } catch (NoSuchAlgorithmException e) {
			throw new Error("no MD5 support in this VM");
		  }
		  StringBuffer hashString = new StringBuffer();
		  for ( int i = 0; i < hash.length; ++i ) {
			String hex = Integer.toHexString(hash[i]);
			if ( hex.length() == 1 ) {
			  hashString.append('0');
			  hashString.append(hex.charAt(hex.length()-1));
			} else {
			  hashString.append(hex.substring(hex.length()-2));
			}
		  }
		  return hashString.toString();
		}
	public void createUser(){
user1.setUPwd(getEncodedPassword(user1.getUPwd()));

		service_user.addUser(user1);
		list_users= service_user.getAllUsers();
		user1 = new GuiUser();
	}
	public void modifUser(){
		service_user.updateUser(user);
		list_users= service_user.getAllUsers();
		
	}
	public void DeleteUser(){


		service_user.deleteUser(user);
		list_users= service_user.getAllUsers();
		
	}
	
	

	public List<GuiGroup> getList_groups() {
		return list_groups;
	}
	public void setList_groups(List<GuiGroup> list_groups) {
		this.list_groups = list_groups;
	}

public List<GuiUser> getList_users() {
	return list_users;
}
public void setList_users(List<GuiUser> list_users) {
	this.list_users = list_users;
}
public GuiUser getUser() {
	return user;
}
public void setUser(GuiUser user) {
	this.user = user;
}

}
