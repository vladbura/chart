package com.vbur.avensis.chart.BaseServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.xml.DOMConfigurator;

import com.vbur.avensis.chart.DatabaseController.DataRing;
import com.vbur.avensis.chart.DatabaseController.SQLRingChart;

public class CSVParser {

	private String inputFile;
	SQLRingChart DbRing = new SQLRingChart();
	String line = "";
	String cvsSplitBy = ",";
	boolean firstLine = true;

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read() throws IOException {
		BufferedReader inputCSV = new BufferedReader(new FileReader(inputFile));
		try {
			while ((line = inputCSV.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				String[] pieData = line.split(cvsSplitBy);
				System.out.println("Country [date= " + pieData[0]+ " , security =" + pieData[1] + " value=" + pieData[2]+ "]");
				DataRing data = new DataRing();
				data.setDate(pieData[0]);
				data.setName(pieData[1]);
				data.setWeight(Double.parseDouble(pieData[2]));
			    DbRing.insert(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		DOMConfigurator.configure("configs/logger.xml");
		CSVParser test = new CSVParser();
		test.setInputFile("resources/Ring Chart Data.csv");
		test.read();
	}
}