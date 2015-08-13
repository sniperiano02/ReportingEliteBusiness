package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.reporting.metier.entities.Cell;
import com.reporting.metier.interfaces.CellRemote;



@ManagedBean(name="manage_position")
public class GestionPositionsMbean {
	
	
	@EJB
	private CellRemote cell_service;
	
	private List<Cell> liste_cell = new ArrayList<>();
	private Cell cell;
	private Cell cell1;
	
	
	@PostConstruct
	public void init(){
		liste_cell= cell_service.getAllCell();
		cell= new Cell();
		cell1= new Cell();
	}
	
	
	public void ajouter(){
		cell_service.addCell(cell);
		liste_cell= cell_service.getAllCell();
	}
	public void delete(){
		cell_service.deleteCell(cell1);
		liste_cell= cell_service.getAllCell();
	}
	
	public void modifier(){
		cell_service.updateCell(cell1);
		liste_cell= cell_service.getAllCell();
	}


	public List<Cell> getListe_cell() {
		return liste_cell;
	}


	public void setListe_cell(List<Cell> liste_cell) {
		this.liste_cell = liste_cell;
	}


	public Cell getCell() {
		return cell;
	}


	public void setCell(Cell cell) {
		this.cell = cell;
	}


	public Cell getCell1() {
		return cell1;
	}


	public void setCell1(Cell cell1) {
		this.cell1 = cell1;
	}

	
	
}
