package com.reporting.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.ItalicAction;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;

import com.reporting.metier.entities.ChartDynamic;
import com.reporting.metier.entities.Datagrid;
import com.reporting.metier.entities.Report;
import com.reporting.metier.interfaces.ReportRemote;
import com.reporting.metier.interfaces.StatMscImplRemote;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.chart.AbstractCategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.GenericElementBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.view.JasperViewer;

@ManagedBean(name ="PdfReport")

public class PdfReportServlet extends HttpServlet {

	
	@EJB
	StatMscImplRemote statRemote;
	@EJB
	ReportRemote reportremote;
	
private String st;
public void setSt(String st) {
	this.st = st;
}
	private List<String> where_liste = new ArrayList<String>();
	
	
	private String choix_filter_date;
	
	
	public void setChoix_filter_date(String choix_filter_date) {
		this.choix_filter_date = choix_filter_date;
	}
	public void setRapport(Report rapport) {
		this.rapport = rapport;
	}
	public void setWhere_liste(List<String> where_liste) {
		this.where_liste = where_liste;
	}
	public String getChoix_filter_date() {
		return choix_filter_date;
	}
	
	public List<String> getWhere_liste() {
		return where_liste;
	}
	

	private Report rapport;
	
	public Report getRapport() {
		return rapport;
	}
	
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
  {
	  where_liste = (List<String>) req.getSession().getAttribute("where");
	  rapport = (Report) req.getSession().getAttribute("rapport");
	  choix_filter_date = (String) req.getSession().getAttribute("choix_date");
	  String table = rapport.getTable_name();
	 System.out.println(where_liste.size()); 
	 

    resp.setContentType("application/pdf");
    OutputStream out = resp.getOutputStream();
    GenericReport generic_report = new GenericReport();
    FontBuilder boldFont = stl.fontArialBold().setFontSize(12);

   StyleBuilder boldStyle         = stl.style().bold();
   StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
   StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
                                       .setBorder(stl.pen1Point())
                                       .setBackgroundColor(Color.LIGHT_GRAY);
    
   
    try {
    	
    	 Class c = Class.forName("com.reporting.metier.entities."+table);
    	 JasperReportBuilder rp1 = report();
      rp1
              .setColumnTitleStyle(columnTitleStyle)
           .highlightDetailEvenRows()
         
           .title(cmp.text(rapport.getLib_rapport()).setStyle(boldCenteredStyle))//shows report title
           
           

           .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle));//shows number of page at page footer
          
         if(this.getRapport().getLst_datagrid().size()>0){
        	 for(int i = 0;i<this.getRapport().getLst_datagrid().size();i++){
           	  Datagrid chd= this.getRapport().getLst_datagrid().get(i);
           	 int nb=0;
			 for(int s=0;s<chd.getList_axe_y().size();s++){
				 nb = nb +chd.getList_axe_y().get(s).getOperations().size()+1;
				
			 }
			 
			 System.out.println(nb);
           	  JasperReportBuilder gridReport = null;
           	String[] columns3 = new String[nb];
           
           	  TextColumnBuilder<String> XColumn4 = col.column(chd.getDaxe_x(), chd.getDaxe_x(), type.stringType());
           	 /// TextColumnBuilder<BigDecimal> YColumn4 = col.column(chd.getDaxe_y(), chd.getDaxe_y(), type.bigDecimalType());
           	  List<String> axe_y_datagrid = new ArrayList<String>();
           	  columns3[0]=chd.getDaxe_x();
       			 List<Object[]> lst4= new ArrayList<>();
       			 //List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(),table, chd.getOperation(), "Group By trancheHoraire ", where_liste);
       			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getDaxe_x().equals("dateAppel")){
 		 			lst4 =statRemote.getAllStatMsc1("trancheHoraire", chd.getList_axe_y(),table, chd.getOperationD(), "Group By trancheHoraire ", where_liste);
 		    		
 		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getDaxe_x().equals("dateAppel")){
 		 			lst4 =statRemote.getAllStatMsc1("to_char(Extract (year from to_date(dateAppel,'YYMMDD')),'9999')", chd.getList_axe_y(),table, chd.getOperationD(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
 			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getDaxe_x().equals("dateAppel")){
 			 			lst4 =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", chd.getList_axe_y(),table, chd.getOperationD(), "Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
 				 		 }
 		 		 else if(chd.getDaxe_x().equals("typeDest")){
 		 			lst4 =statRemote.getAllStatMsc1("destination.typeDest", chd.getList_axe_y(),table,"" , "Group By destination.typeDest ", where_liste);
 				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getDaxe_x().equals("dateAppel")){
 				 			lst4 =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'DD/MM')", chd.getList_axe_y(),table, chd.getOperationD(), "Group By to_date(dateAppel,'YYMMDD') ", where_liste);
 					 		 }else{
 					 			 lst4=statRemote.getAllStatMsc1(chd.getDaxe_x(), chd.getList_axe_y(),table, chd.getOperationD(), "Group By "+chd.getDaxe_x(), where_liste);
 					 		 }
       			 gridReport = report();
 		 		 gridReport.addColumn(XColumn4);
       			 for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
    				 System.out.println(nb);
    				
    				 
    				for(int k=0;k<chd.getList_axe_y().get(nb_y).getOperations().size();k++){
    					
    				Class type =	c.getDeclaredField(chd.getList_axe_y().get(nb_y).getAxey()).getType();
    				if (chd.getList_axe_y().get(nb_y).getOperations().get(k).equals("AVG")){
    					type = Double.class;
    				}
    					 gridReport.addColumn(col.column(chd.getList_axe_y().get(nb_y).getOperations().get(k)+chd.getList_axe_y().get(nb_y).getAxey(), chd.getList_axe_y().get(nb_y).getOperations().get(k)+chd.getList_axe_y().get(nb_y).getAxey(), type));
    			 			//data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", this.getWhere_liste());
    					 int j=k+1;
    			 			columns3[j]=chd.getList_axe_y().get(nb_y).getOperations().get(k)+chd.getList_axe_y().get(nb_y).getAxey();
    				}
       			 }
       			// TextColumnBuilder<BigDecimal> YColumn2= col.column(chd.getDaxe_y(), chd.getDaxe_y(), type.bigDecimalType());
       			

gridReport.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();

       			 gridReport.setDataSource(createDataSource(columns3, lst4));
       			SubreportBuilder sp = generic_report.createGrid(gridReport);
       			rp1.summary(sp);
       			 
             }
             
         }
      
      List<TextColumnBuilder<BigDecimal>> list = new ArrayList<TextColumnBuilder<BigDecimal>>();
      for(int i = 0;i<this.getRapport().getLst_chart().size();i++){
    	  JasperReportBuilder lineReport = null;
  		 ChartDynamic chd= this.getRapport().getLst_chart().get(i);
  		switch (chd.getType_chart()) {
		 case "line":
			 
			 System.out.println(chd.getList_axe_y().size());
			 int nb=0;
			 for(int s=0;s<chd.getList_axe_y().size();s++){
				 nb = nb +chd.getList_axe_y().get(s).getOperations().size()+1;
			 }
		

			 String[] columns = new String[nb];
			
			 LineChartBuilder linechart = cht.lineChart();
			 
			 linechart.setSubtitle(chd.getNom_chart());
			 columns[0]=chd.getAxe_x();
			 TextColumnBuilder<String> XColumn = col.column(chd.getAxe_x(), chd.getAxe_x(), type.stringType());
			 linechart.setCategory(XColumn);
			 
			 List<Object[]> lst=new ArrayList<>();
			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			lst =statRemote.getAllStatMsc1("trancheHoraire", chd.getList_axe_y(),table, chd.getOperation(), "Group By trancheHoraire ", where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			lst =statRemote.getAllStatMsc1("to_char(Extract (year from to_date(dateAppel,'YYMMDD')),'9999')", chd.getList_axe_y(),table, chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			lst =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", chd.getList_axe_y(),table, chd.getOperation(), "Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
		 			lst =statRemote.getAllStatMsc1("destination.typeDest", chd.getList_axe_y(),table, chd.getOperation(), "Group By destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			lst =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'DD/MM')", chd.getList_axe_y(),table, chd.getOperation(), "Group By to_date(dateAppel,'YYMMDD') ", where_liste);
					 		 }else{
					 			 lst=statRemote.getAllStatMsc1(chd.getAxe_x(), chd.getList_axe_y(),table, chd.getOperation(), "Group By "+chd.getAxe_x(), where_liste);
					 		 }
		 		 
			 for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
				 System.out.println(nb);
				
				for(int k=0;k<chd.getList_axe_y().get(nb_y).getOperations().size();k++){
					
					 linechart.addSerie(cht.serie(col.column(chd.getList_axe_y().get(nb_y).getOperations().get(k)+chd.getList_axe_y().get(nb_y).getAxey(), chd.getList_axe_y().get(nb_y).getOperations().get(k)+chd.getList_axe_y().get(nb_y).getAxey(), type.bigDecimalType())));
			 			//data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", this.getWhere_liste());
					 int j=k+1;
			 			columns[j]=chd.getList_axe_y().get(nb_y).getOperations().get(k)+chd.getList_axe_y().get(nb_y).getAxey();
				}
				
				 			
				 			
				 		
				
				
				
			 }

				
				linechart.setDataSource(createDataSource(columns,lst));
				
				 lineReport = report();
				 
				 
			 
			 
			
			 lineReport.summary(linechart);
			 SubreportBuilder sp = generic_report.createGrid(lineReport);
			 rp1.summary(sp);
			 
		 	
		 	break;
		 case "bar":
			
			 String[] columns1 = new String[chd.getList_axe_y().size()+2];
				
			 Bar3DChartBuilder barchart = cht.bar3DChart();
			 columns1[0]=chd.getAxe_x();
			 TextColumnBuilder<String> XColumn1 = col.column(chd.getAxe_x(), chd.getAxe_x(), type.stringType());
			 barchart.setCategory(XColumn1);
			 List<Object[]> lst1= new ArrayList<>();
			 //List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(),table, chd.getOperation(), "Group By trancheHoraire ", where_liste);
			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			lst1 =statRemote.getAllStatMsc1("trancheHoraire", chd.getList_axe_y(),table, chd.getOperation(), "Group By trancheHoraire ", where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			lst1 =statRemote.getAllStatMsc1("to_char(Extract (year from to_date(dateAppel,'YYMMDD')),'9999')", chd.getList_axe_y(),table, chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			lst1 =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", chd.getList_axe_y(),table, chd.getOperation(), "Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
		 			lst1 =statRemote.getAllStatMsc1("s.destination.typeDest", chd.getList_axe_y(),table, chd.getOperation(), "Group By s.destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			lst1 =statRemote.getAllStatMsc1("to_char(to_date(dateAppel,'YYMMDD'),'DD/MM')", chd.getList_axe_y(),table, chd.getOperation(), "Group By to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC ", where_liste);
					 		 }else{
					 			 lst1=statRemote.getAllStatMsc1(chd.getAxe_x(), chd.getList_axe_y(),table, chd.getOperation(), "Group By "+chd.getAxe_x(), where_liste);
					 		 }
			 for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
				int j=nb_y+1;
				
				barchart.addSerie(cht.serie(col.column(chd.getList_axe_y().get(nb_y).getAxey(), chd.getList_axe_y().get(nb_y).getAxey(), type.bigDecimalType())));
				 			//data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", this.getWhere_liste());
				 		
				 			columns1[j]=chd.getList_axe_y().get(nb_y).getAxey();
				 			
				 			
				 		
				
				
				
			 }

				
			 barchart.setDataSource(createDataSource(columns1,lst1));
				
				 lineReport = report();
				 
				 
			 
			 
			
			 lineReport.summary(barchart);
			 SubreportBuilder sp1 = generic_report.createGrid(lineReport);
			 rp1.summary(sp1);
			 
		 	
		 	break;
		 case "pie":
			 String[] columns2 = new String[2];
				
			Pie3DChartBuilder pichart = cht.pie3DChart();
			 columns2[0]=chd.getAxe_x();
			 TextColumnBuilder<String> XColumn2= col.column(chd.getAxe_x(), chd.getAxe_x(), type.stringType());
			 pichart.setKey(XColumn2);
			 List<String> axe_y_pie = new ArrayList<String>();
			 axe_y_pie.add(chd.getAxe_y());
			 List<Object[]> lst2=new ArrayList<>();
			 //List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(),table, chd.getOperation(), "Group By trancheHoraire ", where_liste);
			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			lst2 =statRemote.getAllStatMsc("trancheHoraire", axe_y_pie,table, chd.getOperation(), "Group By trancheHoraire ", where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			lst2 =statRemote.getAllStatMsc("to_char(Extract (year from to_date(dateAppel,'YYMMDD')),'9999')", axe_y_pie,table, chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			lst2 =statRemote.getAllStatMsc("to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY')", axe_y_pie,table, chd.getOperation(), "Group By to_char(to_date(dateAppel,'YYMMDD'),'MM/YYYY') ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
		 			lst2 =statRemote.getAllStatMsc("destination.typeDest", axe_y_pie,table, chd.getOperation(), "Group By destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			lst2 =statRemote.getAllStatMsc("to_char(to_date(dateAppel,'YYMMDD'),'DD/MM')", axe_y_pie,table, chd.getOperation(), "Group By to_date(dateAppel,'YYMMDD') ORDER BY to_date(dateAppel,'YYMMDD') DESC ", where_liste);
					 		 }else{
					 			lst2=statRemote.getAllStatMsc(chd.getAxe_x(), axe_y_pie,table, chd.getOperation(), "Group By "+chd.getAxe_x(), where_liste);
					 		 }
			 columns2[1]=chd.getAxe_y();
			 TextColumnBuilder<BigDecimal> YColumn2= col.column(chd.getAxe_y(), chd.getAxe_y(), type.bigDecimalType());

				pichart.series(cht.serie(YColumn2));
			 pichart.setDataSource(createDataSource(columns2,lst2));
				
				 lineReport = report();
				 
				 
			 
			 
			
			 lineReport.summary(pichart);
			 SubreportBuilder sp2= generic_report.createGrid(lineReport);
			 rp1.summary(sp2);
			 
			
		       
		 	break;
		 	
		 default:
		 	break;
		 }
		
      }
     rp1.toPdf(out);

    } catch (DRException e) {
      throw new ServletException(e);
    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    out.close();
        
  }
  
  private JRDataSource createDataSource(String[] columns,List<Object[]> lst) {
	  System.out.println(columns.length);
	  
      DRDataSource dataSource = new DRDataSource( columns);
         for (Object[] result :lst){
     System.out.println(result.length);
   
				
				dataSource.add(result);
				
		    	
			}
			
			
	
	return dataSource; 

  }
      private JRDataSource createDataSource1(String x ,String y) {
	      DRDataSource dataSource = new DRDataSource(x, y);
	    
	      
	      
	      for (Object[] result :statRemote.getTest()){
			  
				
				dataSource.add(result[0].toString(),  (BigDecimal)result[1] );
			
		}
		return dataSource; 

      }
      
     
      private class CategoryExpression extends AbstractSimpleExpression<String> {
    	 
    	        private static final long serialVersionUID = 1L;
    	  
    	   
    	 
    	        @Override
    	
    	        public String evaluate(ReportParameters reportParameters) {
    	  
    	           return type.integerType().valueToString("date", reportParameters);
    	  
    	        }
    	 
    	     }

  
}

