package com.reporting.mbeans;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

 



















































import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.reporting.metier.entities.FichierLcr;
import com.reporting.metier.entities.FichierLcr2;
import com.reporting.metier.entities.FichierLcrProcess;
import com.reporting.metier.entities.TarifsInterTmp;
import com.reporting.metier.entities.Template;
import com.reporting.metier.interfaces.LcrRemoteManager;
import com.reporting.metier.interfaces.TarifinterLcrRemote;
import com.reporting.metier.interfaces.TemplateRemote;
 
@ManagedBean
@ViewScoped
public class FileUploadView implements Serializable {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -458644391303601821L;

	@EJB
	private TemplateRemote template_service;
	
	@EJB
	private LcrRemoteManager lcr_service;
	
	@EJB
	private TarifinterLcrRemote tarifsTmp_Service;
	
	
	private boolean ValideDisplayed = false;
	
	
	public boolean isValideDisplayed() {
		return ValideDisplayed;
	}
	public void setValideDisplayed(boolean valideDisplayed) {
		ValideDisplayed = valideDisplayed;
	}
	private Integer progress;
	
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	private String nomfich;
	
	public String getNomfich() {
		return nomfich;
	}
	public void setNomfich(String nomfich) {
		this.nomfich = nomfich;
	}
	
	
	private List<TarifsInterTmp> listTarif;
	
	
	private boolean dateDisplayed = false;
	
	public boolean isDateDisplayed() {
		return dateDisplayed;
	}
	public void setDateDisplayed(boolean dateDisplayed) {
		this.dateDisplayed = dateDisplayed;
	}
	private List<Template> list_template;
	
	private FichierLcrProcess fichierlcrprocess;
	
	public FichierLcrProcess getFichierlcrprocess() {
		return fichierlcrprocess;
	}
	public void setFichierlcrprocess(FichierLcrProcess fichierlcrprocess) {
		this.fichierlcrprocess = fichierlcrprocess;
	}
	
	private boolean dataTableDisplayed=false;
	
	public boolean isDataTableDisplayed() {
		return dataTableDisplayed;
	}
	public void setDataTableDisplayed(boolean dataTableDisplayed) {
		this.dataTableDisplayed = dataTableDisplayed;
	}
	private Integer idTemplate;
	private Template selected_template;
	public Template getSelected_template() {
		return selected_template;
	}
	public void setSelected_template(Template selected_template) {
		this.selected_template = selected_template;
	}
	
	public Integer getIdTemplate() {
		return idTemplate;
	}
	public void setIdTemplate(Integer idTemplate) {
		this.idTemplate = idTemplate;
	}
	public List<Template> getList_template() {
		return list_template;
	}
	public void setList_template(List<Template> list_template) {
		this.list_template = list_template;
	}
	
	public List<TarifsInterTmp> getListTarif() {
		return listTarif;
	}
	public void setListTarif(List<TarifsInterTmp> listTarif) {
		this.listTarif = listTarif;
	}
	
	
	private boolean fichiertraite=false;
	
	public boolean isFichiertraite() {
		return fichiertraite;
	}
	public void setFichiertraite(boolean fichiertraite) {
		this.fichiertraite = fichiertraite;
	}
	
	private Template template;
	private boolean show_dataTable =false;
	
		public boolean isShow_dataTable() {
		return show_dataTable;
	}
	
	public void setShow_dataTable(boolean show_dataTable) {
		this.show_dataTable = show_dataTable;
	}
	
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	
	
	private Date dateeffective;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDateeffective() {
		return dateeffective;
	}
	public void setDateeffective(Date dateeffective) {
		this.dateeffective = dateeffective;
	}
	
	
	 public void onComplete() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fichier LCR Validé"));
	    }
	@PostConstruct
	public void init(){
	
		selected_template=new Template();
		listTarif= new ArrayList<>();
		list_template= template_service.getAllTemplates();
		
	}
	
	public void change(){
		if(template.getDateEffective().equals("Non")){
		if(template.getDateIndique().equals("Non")){
			dateDisplayed=true;
		}else{
			dateDisplayed=false;
		}}
		
		
	}
	
	private String filename;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println(event.getFile().getFileName());
        filename=event.getFile().getFileName();

		 if (event.getFile().equals(null)) {
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File is null", null));
		 }
		 InputStream file;
		 org.apache.poi.ss.usermodel.Workbook workbook = null;
		 System.out.println(this.getIdTemplate());
	
		System.out.println(template.getNomTemplate());
		
		 try {
			file = event.getFile().getInputstream();
			  workbook = WorkbookFactory.create(file);

		Sheet sheet = workbook.getSheetAt(0); 

		FichierLcrProcess fichierlcrprocess = new FichierLcrProcess();
		System.out.println(event.getFile().getFileName());
		nomfich=event.getFile().getFileName();
fichierlcrprocess = lcr_service.findFichierByName(event.getFile().getFileName());
		if(fichierlcrprocess==null){
			
		
	
		 int nb= template.getLsttemplateFeuille().get(0).getNbLigne();
			boolean check = false;
			Iterator rows = sheet.rowIterator();
		  System.out.println(sheet.getPhysicalNumberOfRows());
			 
		  while(nb<sheet.getLastRowNum() && check==false && rows.hasNext()){

			  FichierLcr fichierLcr = new FichierLcr();
	                Row row = sheet.getRow(nb);
	                
	               
	                Iterator<Cell> cellIterator = row.cellIterator();
	                if(!cellIterator.hasNext()){
	            		check=true;
	            		
	            	}else{
	            		fichierLcr.setNomFichier(event.getFile().getFileName());
		                fichierLcr.setNomTemplate(template.getNomTemplate());
		               fichierLcr.setOperateur(template.getOperateur().getId()+"");
	            	}
	                
	                //For each row, iterate through all the columns
	                  
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    
    	 
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne1(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne1(date);
    		 }
		
    	 }else 
    		 {cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne1(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne1(cell.getStringCellValue() + "");
         break;
         
       
	
 }
	       }
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne2(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne2(date);
    		 }
		
    	 }else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne2(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne2(cell.getStringCellValue() + "");
         break;
         
       
	
 }
	 }
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne3(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne3(date);
    		 }
		
    	 } else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne3(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne3(cell.getStringCellValue() + "");
         break;
         
       
	
 }
}
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne4(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne4(date);
    		 }
		
    	 }else{ 
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne4(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne4(cell.getStringCellValue() + "");
         break;
         
       
	
 }
}
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne5(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne5(date);
    		 }
		
    	 }else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne5(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne5(cell.getStringCellValue() + "");
         break;
         
       
	
 }	 }
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne6(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne6(date);
    		 }
		
    	 }else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne6(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne6(cell.getStringCellValue() + "");
         break;
         
       
	
 }
}
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne7(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne7(date);
    		 }
		
    	 } else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne7(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne7(cell.getStringCellValue() + "");
         break;
         
       
	
 }
}

if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne8(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne8(date);
    		 }
		
    	 }else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne8(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne8(cell.getStringCellValue() + "");
         break;
         
       
	
 }
	 }
if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne9(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne9(date);
    		 }
		
    	 }else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne9(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne9(cell.getStringCellValue() + "");
         break;
         
       
	
 }
}if(cellIterator.hasNext()){
	 Cell cell = cellIterator.next();
	 System.out.println(cell.getCellType());
	 switch(cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
    	 if( DateUtil.isCellDateFormatted(cell)){
    		 if(template.getFormatDate().equals("jj/mm/aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 
    			 fichierLcr.setColonne10(date);
    		 }
    		 if(template.getFormatDate().equals("jj-mm-aaaa")){
    			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    			 Date datecell =cell.getDateCellValue();
    			 String date =dateFormat.format(datecell);
    			 fichierLcr.setColonne10(date);
    		 }
		
    	 }else {
    		 cell.setCellType(Cell.CELL_TYPE_STRING);
    		 fichierLcr.setColonne10(cell.toString());}
         break;
     case Cell.CELL_TYPE_STRING:
    	 fichierLcr.setColonne10(cell.getStringCellValue() + "");
         break;
         
       
	
 }
}
if(fichierLcr.getColonne1()==null && fichierLcr.getColonne2()==null && fichierLcr.getColonne3()==null
&& fichierLcr.getColonne4()==null
&& fichierLcr.getColonne5()==null
&& fichierLcr.getColonne6()==null
&& fichierLcr.getColonne7()==null
&& fichierLcr.getColonne8()==null
&& fichierLcr.getColonne9()==null
&& fichierLcr.getColonne10()==null){
	check=true;
}else{
	lcr_service.addFichierLcr(fichierLcr);
	rows.next();
	nb++;
}


	                
	            }
			 
			 
	             if(template.getNbFeuille()==2){
	            		 sheet = workbook.getSheetAt(1); 

	            	
	            	 int nb1= template.getLsttemplateFeuille().get(1).getNbLigne();
	     			
	    			 
	       		  
	            	  while(nb1<sheet.getLastRowNum() && check==false ){
	    				 FichierLcr2 fich= new FichierLcr2();
	    	                Row row = sheet.getRow(nb1);
	    	                Iterator<Cell> cellIterator = row.cellIterator();
	    	                if(!cellIterator.hasNext()){
	    	            		check=true;
	    	            		
	    	            	}else{
	    	                fich.setNomFichier(event.getFile().getFileName());
	    	                fich.setNomTemplate(template.getNomTemplate());
	    	                fich.setOperateur(template.getOperateur().getId()+"");
	    	                
	    	            	}
	    	                //For each row, iterate through all the columns
	    	              
	    	                if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   
	    	                   	 
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne1(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne1(date);
	    	                   		 }
	    	               		
	    	                   	 }else 
	    	                   		 {cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne1(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne1(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               	       }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne2(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne2(date);
	    	                   		 }
	    	               		
	    	                   	 }else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne2(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne2(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               	 }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne3(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne3(date);
	    	                   		 }
	    	               		
	    	                   	 } else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne3(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne3(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne4(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne4(date);
	    	                   		 }
	    	               		
	    	                   	 }else{ 
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne4(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne4(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne5(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne5(date);
	    	                   		 }
	    	               		
	    	                   	 }else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne5(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne5(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }	 }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne6(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne6(date);
	    	                   		 }
	    	               		
	    	                   	 }else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne6(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne6(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne7(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne7(date);
	    	                   		 }
	    	               		
	    	                   	 } else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne7(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne7(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               }

	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne8(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne8(date);
	    	                   		 }
	    	               		
	    	                   	 }else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne8(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne8(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               	 }
	    	               if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne9(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne9(date);
	    	                   		 }
	    	               		
	    	                   	 }else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne9(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne9(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               }if(cellIterator.hasNext()){
	    	               	 Cell cell = cellIterator.next();
	    	               	 System.out.println(cell.getCellType());
	    	               	 switch(cell.getCellType()) {
	    	                    case Cell.CELL_TYPE_NUMERIC:
	    	                   	 if( DateUtil.isCellDateFormatted(cell)){
	    	                   		 if(template.getFormatDate().equals("jj/mm/aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 
	    	                   			 fich.setColonne10(date);
	    	                   		 }
	    	                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
	    	                   			 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    	                   			 Date datecell =cell.getDateCellValue();
	    	                   			 String date =dateFormat.format(datecell);
	    	                   			 fich.setColonne10(date);
	    	                   		 }
	    	               		
	    	                   	 }else {
	    	                   		 cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                   		 fich.setColonne10(cell.toString());}
	    	                        break;
	    	                    case Cell.CELL_TYPE_STRING:
	    	                   	 fich.setColonne10(cell.getStringCellValue() + "");
	    	                        break;
	    	                        
	    	                      
	    	               	
	    	                }
	    	               }
	    	               if(fich.getColonne1()==null && fich.getColonne2()==null && fich.getColonne3()==null
	    	    	               && fich.getColonne4()==null
	    	    	               && fich.getColonne5()==null
	    	    	               && fich.getColonne6()==null
	    	    	               && fich.getColonne7()==null
	    	    	               && fich.getColonne8()==null
	    	    	               && fich.getColonne9()==null
	    	    	               && fich.getColonne10()==null){
	    	    	               	check=true;
	    	    	               }else{
	    	    	            		lcr_service.addFichierLcr2(fich);
	    	    	            		
	    	    	            		nb1++;
	    	    	            	}
	    	            }
	    	               
	            	
	            	
	}
	             fichierlcrprocess = new FichierLcrProcess();
	             fichierlcrprocess.setType(this.getType());
	             if(template.getDateEffective().equals("Non")){
	            	 if(template.getDateIndique().equals("Oui")){
	            		 
	            		 Cell cell = sheet.getRow(Integer.valueOf(template.getTempDateIndique().getNumLigne()+"")).getCell(Integer.valueOf(template.getTempDateIndique().getNumColonne()-1+""));
	            		 System.out.println(Integer.valueOf(template.getTempDateIndique().getNumLigne()+1+"")+":"+Integer.valueOf(template.getTempDateIndique().getNumColonne()+""));
System.out.println(cell.getDateCellValue());

	            	System.out.println(cell.getCellType());	 
if(template.getFormatDate().equals("jj/mm/aaaa")){
                   			 DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
                   			 Date datecell =cell.getDateCellValue();
                   			 String date =dateFormat.format(datecell);
                   			 
                   			fichierlcrprocess.setDateEffective(datecell);
                   		 }
                   		 if(template.getFormatDate().equals("jj-mm-aaaa")){
                   			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                   			 SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MMM-dd");
                   			 SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MMM/yyyy");
                   			 
                   			Date datecell =cell.getDateCellValue();
                  			 String date =dateFormat.format(datecell);
                   			
                   			
                   			
                   			
                   			 
                   			fichierlcrprocess.setDateEffective(datecell);
                   		 }
               		
	            		
	            	 }
	             }
	             
	 			fichierlcrprocess.setTemplate(template.getNomTemplate());
	 			fichierlcrprocess.setOperateur(template.getOperateur());
	 			fichierlcrprocess.setNomFichier(event.getFile().getFileName());
	 			fichierlcrprocess.setType(this.getType());
	 		
	 			fichierlcrprocess.setEtat("0");
	 			lcr_service.addFichierLcrProcess(fichierlcrprocess);
	 			System.out.println("begin");
	 			lcr_service.LaunchProc(event.getFile().getFileName());
	 			System.out.println("end");
	 			
			listTarif=tarifsTmp_Service.getTarifTempByFichier(event.getFile().getFileName());
			dataTableDisplayed=true;
			ValideDisplayed=true;
		}else if(fichierlcrprocess.getEtat().equals("5")){
			FacesContext.getCurrentInstance().addMessage(
					"@form",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Fichier déja Traité!!"," aux List des offres"));
			dataTableDisplayed=true;
		}else if(fichierlcrprocess.getEtat().equals("2")|| fichierlcrprocess.getEtat().equals("3")){
			listTarif=tarifsTmp_Service.getTarifTempByFichier(event.getFile().getFileName());
			System.out.println(listTarif.size());
			dataTableDisplayed=true;
			 
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Fichier en cours de traitement!!","Veuillez Valider"));
	ValideDisplayed=true;
			
		}
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	 
		 
	}
	
	public void launch(){
		fichierlcrprocess = lcr_service.findFichierByName(this.getNomfich());
		fichierlcrprocess.setEtat("3");
		lcr_service.updateFichierLcrProcess(fichierlcrprocess);
		progress=5;
		progress=20;
		progress=40;
		lcr_service.truncateTmp();
		progress=80;
		progress=100;
		dataTableDisplayed=false;
		ValideDisplayed=false;
		
	}
	
	
	
	
}
	