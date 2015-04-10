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

import net.sf.dynamicreports.report.builder.chart.AbstractCategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
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
    	

    	 JasperReportBuilder rp1 = report();
      rp1
              .setColumnTitleStyle(columnTitleStyle)
           .highlightDetailEvenRows()
         
           .title(cmp.text("Getting started").setStyle(boldCenteredStyle))//shows report title
           
           

           .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle));//shows number of page at page footer
          
         if(this.getRapport().getLst_datagrid().size()>0){
        	 for(int i = 0;i<this.getRapport().getLst_datagrid().size();i++){
           	  Datagrid chd= this.getRapport().getLst_datagrid().get(i);
           	  JasperReportBuilder gridReport = null;
           	String[] columns3 = new String[2];
           	
           	  TextColumnBuilder<String> XColumn4 = col.column(chd.getDaxe_x(), chd.getDaxe_x(), type.stringType());
           	  TextColumnBuilder<BigDecimal> YColumn4 = col.column(chd.getDaxe_y(), chd.getDaxe_y(), type.bigDecimalType());
           	  List<String> axe_y_datagrid = new ArrayList<String>();
           	  axe_y_datagrid.add(chd.getDaxe_y());
       			 List<Object[]> lst4=statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", axe_y_datagrid, chd.getOperationD(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
       			 //List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(), chd.getOperation(), "Group By trancheHoraire ", where_liste);
       			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getDaxe_x().equals("dateAppel")){
       		 			lst4 =statRemote.getAllStatMsc("trancheHoraire", axe_y_datagrid, chd.getOperationD(), "Group By trancheHoraire ", where_liste);
       		    		
       		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getDaxe_x().equals("dateAppel")){
       		 			lst4 =statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", axe_y_datagrid, chd.getOperationD(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
       			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getDaxe_x().equals("dateAppel")){
       			 			lst4 =statRemote.getAllStatMsc("Extract (month from to_date(dateAppel,'YYMMDD'))", axe_y_datagrid, chd.getOperationD(), "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
       				 		 }
       		 		 else if(chd.getDaxe_x().equals("typeDest")){
       		 			lst4 =statRemote.getAllStatMsc("s.destination.typeDest", axe_y_datagrid, chd.getOperationD(), "Group By s.destination.typeDest ", where_liste);
       				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getDaxe_x().equals("dateAppel")){
       				 			lst4 =statRemote.getAllStatMsc("Extract (day from to_date(dateAppel,'YYMMDD'))", axe_y_datagrid, chd.getOperationD(), "Group By Extract (day from to_date(dateAppel,'YYMMDD')) ORDER BY Extract (day from to_date(dateAppel,'YYMMDD')) DESC ", where_liste);
       					 		 }else{
       					 			lst4=statRemote.getAllStatMsc(chd.getDaxe_x(), axe_y_datagrid, chd.getOperationD(), "Group By "+chd.getDaxe_x(), where_liste);
       					 		 }
       			columns3[0]=chd.getDaxe_x();
       			columns3[1]=chd.getDaxe_y();
       			// TextColumnBuilder<BigDecimal> YColumn2= col.column(chd.getDaxe_y(), chd.getDaxe_y(), type.bigDecimalType());
       			 gridReport = report();
gridReport.columns(XColumn4,YColumn4);
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
			 String[] columns = new String[chd.getList_axe_y().size()+1];
			
			 LineChartBuilder linechart = cht.lineChart();
			 columns[0]=chd.getAxe_x();
			 TextColumnBuilder<String> XColumn = col.column(chd.getAxe_x(), chd.getAxe_x(), type.stringType());
			 linechart.setCategory(XColumn);
			 
			 List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(), chd.getOperation(), "Group By trancheHoraire ", where_liste);
			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			lst =statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(), chd.getOperation(), "Group By trancheHoraire ", where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			lst =statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", chd.getList_axe_y(), chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			lst =statRemote.getAllStatMsc("Extract (month from to_date(dateAppel,'YYMMDD'))", chd.getList_axe_y(), chd.getOperation(), "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
		 			lst =statRemote.getAllStatMsc("s.destination.typeDest", chd.getList_axe_y(), chd.getOperation(), "Group By s.destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			lst =statRemote.getAllStatMsc("to_date(dateAppel,'YYMMDD')", chd.getList_axe_y(), chd.getOperation(), "Group By to_date(dateAppel,'YYMMDD') ", where_liste);
					 		 }else{
					 			 lst=statRemote.getAllStatMsc(chd.getAxe_x(), chd.getList_axe_y(), chd.getOperation(), "Group By "+chd.getAxe_x(), where_liste);
					 		 }
		 		 
			 for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
				int j=nb_y+1;
				 linechart.addSerie(cht.serie(col.column(chd.getList_axe_y().get(nb_y), chd.getList_axe_y().get(nb_y), type.bigDecimalType())));
				 			//data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", this.getWhere_liste());
				 		
				 			columns[j]=chd.getList_axe_y().get(nb_y);
				 			
				 			
				 		
				
				
				
			 }

				
				linechart.setDataSource(createDataSource(columns,lst)).customizers(new ChartCustomizer());
				
				 lineReport = report();
				 
				 
			 
			 
			
			 lineReport.summary(linechart);
			 SubreportBuilder sp = generic_report.createGrid(lineReport);
			 rp1.summary(sp);
			 
		 	
		 	break;
		 case "bar":
			
			 String[] columns1 = new String[chd.getList_axe_y().size()+1];
				
			 Bar3DChartBuilder barchart = cht.bar3DChart();
			 columns1[0]=chd.getAxe_x();
			 TextColumnBuilder<String> XColumn1 = col.column(chd.getAxe_x(), chd.getAxe_x(), type.stringType());
			 barchart.setCategory(XColumn1);
			 List<Object[]> lst1=statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", chd.getList_axe_y(), chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 //List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(), chd.getOperation(), "Group By trancheHoraire ", where_liste);
			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			lst1 =statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(), chd.getOperation(), "Group By trancheHoraire ", where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			lst1 =statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", chd.getList_axe_y(), chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			lst1 =statRemote.getAllStatMsc("Extract (month from to_date(dateAppel,'YYMMDD'))", chd.getList_axe_y(), chd.getOperation(), "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
		 			lst1 =statRemote.getAllStatMsc("s.destination.typeDest", chd.getList_axe_y(), chd.getOperation(), "Group By s.destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			lst1 =statRemote.getAllStatMsc("Extract (day from to_date(dateAppel,'YYMMDD'))", chd.getList_axe_y(), chd.getOperation(), "Group By Extract (day from to_date(dateAppel,'YYMMDD')) ORDER BY Extract (day from to_date(dateAppel,'YYMMDD')) DESC ", where_liste);
					 		 }else{
					 			 lst1=statRemote.getAllStatMsc(chd.getAxe_x(), chd.getList_axe_y(), chd.getOperation(), "Group By "+chd.getAxe_x(), where_liste);
					 		 }
			 for(int nb_y=0;nb_y<chd.getList_axe_y().size();nb_y++){
				int j=nb_y+1;
				barchart.addSerie(cht.serie(col.column(chd.getList_axe_y().get(nb_y), chd.getList_axe_y().get(nb_y), type.bigDecimalType())));
				 			//data =statRemote.getMscByFilters("Extract (year from to_date(dateAppel,'YYMMDD'))",chd.getList_axe_y().get(nb_y)+")", chd.getOperation()+"(","Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", this.getWhere_liste());
				 		
				 			columns1[j]=chd.getList_axe_y().get(nb_y);
				 			
				 			
				 		
				
				
				
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
			 List<Object[]> lst2=statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", axe_y_pie, chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 //List<Object[]> lst=statRemote.getAllStatMsc("trancheHoraire", chd.getList_axe_y(), chd.getOperation(), "Group By trancheHoraire ", where_liste);
			 if(this.getChoix_filter_date().equals("Par heure")&&chd.getAxe_x().equals("dateAppel")){
		 			lst2 =statRemote.getAllStatMsc("trancheHoraire", axe_y_pie, chd.getOperation(), "Group By trancheHoraire ", where_liste);
		    		
		 		 }else if(this.getChoix_filter_date().equals("Par An")&&chd.getAxe_x().equals("dateAppel")){
		 			lst2 =statRemote.getAllStatMsc("Extract (year from to_date(dateAppel,'YYMMDD'))", axe_y_pie, chd.getOperation(), "Group By Extract (year from to_date(dateAppel,'YYMMDD')) ", where_liste);
			 		 }else if(this.getChoix_filter_date().equals("Par mois")&&chd.getAxe_x().equals("dateAppel")){
			 			lst2 =statRemote.getAllStatMsc("Extract (month from to_date(dateAppel,'YYMMDD'))", axe_y_pie, chd.getOperation(), "Group By Extract (month from to_date(dateAppel,'YYMMDD')) ", where_liste);
				 		 }
		 		 else if(chd.getAxe_x().equals("typeDest")){
		 			lst2 =statRemote.getAllStatMsc("s.destination.typeDest", axe_y_pie, chd.getOperation(), "Group By s.destination.typeDest ", where_liste);
				 		 }else if(this.getChoix_filter_date().equals("Par Jour")&&chd.getAxe_x().equals("dateAppel")){
				 			lst2 =statRemote.getAllStatMsc("Extract (day from to_date(dateAppel,'YYMMDD'))", axe_y_pie, chd.getOperation(), "Group By Extract (day from to_date(dateAppel,'YYMMDD')) ORDER BY Extract (day from to_date(dateAppel,'YYMMDD')) DESC ", where_liste);
					 		 }else{
					 			lst2=statRemote.getAllStatMsc(chd.getAxe_x(), axe_y_pie, chd.getOperation(), "Group By "+chd.getAxe_x(), where_liste);
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
    }
    out.close();
        
  }
  
  private JRDataSource createDataSource(String[] columns,List<Object[]> lst) {
      DRDataSource dataSource = new DRDataSource( columns);
         for (Object[] result :lst){
      
    if(result.length==3){
				dataSource.add(result[0].toString(),  (BigDecimal)result[1],(BigDecimal)result[2] );
			}else if (result.length==4){
				dataSource.add(result[0].toString(),  (BigDecimal)result[1],(BigDecimal)result[2],(BigDecimal)result[3]);
			}else if (result.length==5){
				dataSource.add(result[0].toString(),  (BigDecimal)result[1],(BigDecimal)result[2],(BigDecimal)result[3],(BigDecimal)result[4]);
			}else if(result.length==2){
				dataSource.add(result[0].toString(),  (BigDecimal)result[1]);
			}
			
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
      
     
   
   private class ChartCustomizer implements DRIChartCustomizer, Serializable {
	   
	         private static final long serialVersionUID = 1L;
	  
	         @Override
	         public void customize(JFreeChart chart, ReportParameters reportParameters) {
	           // LineRenderer3D renderer = (LineRenderer3D) chart.getCategoryPlot().getRenderer();
	            
	   
	            CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
	   
	            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
	   
	         }
	   
	      }

}

