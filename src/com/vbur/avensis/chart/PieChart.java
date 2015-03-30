package com.vbur.avensis.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.text.AttributedString;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.vbur.avensis.chart.BaseServices.Constants;
import com.vbur.avensis.chart.DatabaseController.DataPie;
import com.vbur.avensis.chart.DatabaseController.SQLPieChart;

public class PieChart extends JFrame {

	private static final Logger AppLog = LogManager.getLogger(PieChart.class);
	private static final long serialVersionUID = 1L;
	SQLPieChart DbPie = new SQLPieChart();
	private JFreeChart chart;
	private PiePlot plot = null;

	public PieChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		PieDataset dataset = createDataset();
		chart = createChart(dataset, chartTitle, Constants.RING_CHART);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(537, 750));
		chartPanel.setBackground(Color.WHITE);
		setContentPane(chartPanel);

	}

	/**
	 * Creates a sample dataset
	 */

	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		List<DataPie> dataPie = DbPie.dataPie();
		for (DataPie data : dataPie) {
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
		else
			plot = new RingPlot();

	    plot.setDrawingSupplier(new RandomColor());
	 	plot.setDataset(dataset);
		plot.setSimpleLabels(true);
		
		plot.setBackgroundPaint(null);
		this.plot.setShadowPaint(null);
		this.plot.setLabelGenerator(labelReturn);
		this.plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}"));
		this.plot.setSimpleLabels(true);
		this.plot.setOutlineVisible(false);
		this.plot.setLabelShadowPaint(null);
		this.plot.setLabelOutlinePaint(null);
		this.plot.setLabelFont(new Font("SansSerif", Font.BOLD, 15));
		this.plot.setLabelBackgroundPaint(Color.WHITE);LegendItemCollection chartLegend = new LegendItemCollection();
		Shape shape = new Rectangle(10, 10);
		chartLegend.add(new LegendItem("label1", null, null, null, shape, Color.red));
		chartLegend.add(new LegendItem("label2", null, null, null, shape, Color.blue));
		plot.getLegendItems().addAll(chartLegend);
	
		chart = new JFreeChart(plot);
		chart.setBackgroundPaint(Color.WHITE);
		chart.getLegend();
		//chart.removeLegend();
		return chart;
	}
	
	
	
	final StandardPieSectionLabelGenerator labelReturn = new StandardPieSectionLabelGenerator("{1}") {
		@Override
		public String generateSectionLabel(PieDataset dataset, Comparable key) {
			if (dataset.getValue(key).intValue() < 5) 
				return null;
			return super.generateSectionLabel(dataset, key);
		}

	};
	}