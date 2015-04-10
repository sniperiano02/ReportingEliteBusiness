package com.reporting.mbeans;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.AbstractCategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.CategoryChartSerieBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;

import com.reporting.metier.entities.ChartDynamic;
import com.reporting.metier.entities.Report;
import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.interfaces.StatMscImplRemote;

public class GenericReport {
	
	@EJB
	private StatMscImplRemote statRemote;
	
	public GenericReport(){
		
	}
	
	public Bar3DChartBuilder createBarChart(String title,String x,List<String> y){
		Bar3DChartBuilder barchart = cht.bar3DChart();
		 TextColumnBuilder<String> XColumn = col.column(x, x, type.stringType());
		 barchart.setCategory(XColumn);
		 for(int i=0;i<y.size();i++){
			 TextColumnBuilder<BigDecimal> YColumn = col.column(y.get(i),y.get(i), type.bigDecimalType());
			 barchart.series(cht.serie(YColumn));
		 }
		
		 
		
		
		
		//barchart.setCategory(itemColumn);
		
	return barchart;
	}
	public LineChartBuilder createLineChart(LineChartBuilder ch,TextColumnBuilder<BigDecimal> colY,String y){
		
	
		 
			 ch.addSerie(cht.serie(colY));
			 
		 
		
		 
		
		
		
		//barchart.setCategory(itemColumn);
		
	return ch;
	}
	public Pie3DChartBuilder createPieChart(String title,String x,String y){
		Pie3DChartBuilder pieChart = cht.pie3DChart();
		 TextColumnBuilder<String> XColumn = col.column(x, x, type.stringType());
		 pieChart.setKey(XColumn);
		
			 TextColumnBuilder<BigDecimal> YColumn = col.column(y,y, type.bigDecimalType());
			 pieChart.series(cht.serie(YColumn));
		 
		
		 
		
		
		
		//barchart.setCategory(itemColumn);
		
	return pieChart;
	}
	public SubreportBuilder createGrid(JasperReportBuilder subrep){
		
		
			SubreportBuilder grid = cmp.subreport(subrep);
		
		 
		
		
		
		//barchart.setCategory(itemColumn);
		
	return grid;
	}	
	
	
	
}
