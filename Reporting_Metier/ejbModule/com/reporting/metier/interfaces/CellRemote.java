package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Cell;


@Remote
public interface CellRemote {

	
	public List<Cell> getAllCell();
	
	public void addCell(Cell cell);
	public void updateCell(Cell cell);
	public void deleteCell(Cell cell);
	
	
}
