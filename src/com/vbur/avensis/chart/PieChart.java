package com.vbur.avensis.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.GridArrangement;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.vbur.avensis.chart.BaseServices.Constants;
import com.vbur.avensis.chart.DatabaseController.DataPie;
import com.vbur.avensis.chart.DatabaseController.DataRing;
import com.vbur.avensis.chart.DatabaseController.SQLPieChart;
import com.vbur.avensis.chart.DatabaseController.SQLRingChart;

public class PieChart extends JFrame {

	private static final Logger AppLog = LogManager.getLogger(PieChart.class);
	private static final long serialVersionUID = 1L;
	SQLPieChart DbPie = new SQLPieChart();
	SQLRingChart DbRing = new SQLRingChart();
	private JFreeChart chart;
	private PiePlot plot = null;
	private int width=600;
	private int height=500;

	public PieChart (String applicationTitle, String chartTitle, String fileName, int type) {
		super(applicationTitle);
		PieDataset dataset;
		if (type == Constants.PIE_CHART)
			dataset=createDataPie();
		else dataset =createDataRing();
		
		chart = createChart(dataset, chartTitle, type);
	
		Document document = new Document();
		    try {
		        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName) );
		        document.open();
		        PdfContentByte contentByte = writer.getDirectContent();
		        PdfTemplate template = contentByte.createTemplate(width, height);
		        Graphics2D graphics2d = template.createGraphics(width, height,new DefaultFontMapper());
		        Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
		        chart.draw(graphics2d, rectangle2d);
		        graphics2d.dispose();
		        contentByte.addTemplate(template, 0, 0);
		 
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    document.close();
		
		    //	ChartPanel chartPanel = new ChartPanel(chart);
		    //	chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
		    //	chartPanel.setBackground(Color.WHITE);
		    //	setContentPane(chartPanel);
	
	}

	/**
	 * Render the value from database
	 * @param type 
	 */

	private PieDataset createDataPie() {
	DefaultPieDataset result = new DefaultPieDataset();

		List<DataPie> dataPie = DbPie.dataPie();
		for (DataPie data : dataPie) {
			result.setValue(data.getName(), data.getWeight());
		}
		return result;
	}
	private PieDataset createDataRing() {
			DefaultPieDataset result = new DefaultPieDataset();
			List<DataRing> dataRing = DbRing.dataRing();
			for (DataRing data : dataRing) {
				result.setValue(data.getName(), data.getWeight());
			}
			return result;
	}

	/**
	 * Creates a chart
	 */
	
	private JFreeChart createChart(PieDataset dataset, String title, int type) {

		if (type == Constants.PIE_CHART)
			plot = new PiePlot();
		else{
			plot = new RingPlot();
			((RingPlot) plot).setOuterSeparatorExtension(0.0f);
			((RingPlot) plot).setInnerSeparatorExtension(0.0f);

		}
	    plot.setDrawingSupplier(new RandomColor());
	 	plot.setDataset(dataset);
		plot.setSimpleLabels(true);
		plot.setBackgroundPaint(null);
		plot.setShadowPaint(null);
		plot.setLabelGenerator(labelReturn);
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}{2}"));
		plot.setSimpleLabels(true);
		plot.setOutlineVisible(false);
		plot.setLabelShadowPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelFont(new Font("SansSerif", Font.BOLD, 15));

		chart = new JFreeChart(plot);
		chart.setBackgroundPaint(Color.WHITE);
		
		LegendTitle legend = chart.getLegend();
		legend.setItemFont(new Font("SansSerif", Font.BOLD, 13)); 
		legend.setBorder(0, 0, 0, 0);
		legend.setVerticalAlignment(VerticalAlignment.CENTER);
		if (type == Constants.PIE_CHART){
			legend.setPadding(50.00,220.00,0.00,220.00);
			legend.setPosition(RectangleEdge.BOTTOM);
			}
		else{
			legend.setPadding(50.00,0.00,0.00,20.00);
			legend.setPosition(RectangleEdge.RIGHT);
		}
		return chart;
	}
	
	final StandardPieSectionLabelGenerator labelReturn = new StandardPieSectionLabelGenerator("{1}") {
		@Override
		public String generateSectionLabel(PieDataset dataset, Comparable key) {
			if (dataset.getValue(key).intValue() < 6) 
				return null;
			return super.generateSectionLabel(dataset, key);
		}

	};
	}