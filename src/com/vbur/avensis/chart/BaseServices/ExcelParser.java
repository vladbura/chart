package com.vbur.avensis.chart.BaseServices;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.xml.DOMConfigurator;

import com.vbur.avensis.chart.DatabaseController.DataPie;
import com.vbur.avensis.chart.DatabaseController.SQLPieChart;

public class ExcelParser {

	private String inputFile;
	SQLPieChart DbPie = new SQLPieChart();
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read() throws IOException {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			for (int lines = 1; lines < sheet.getRows(); lines++) {

				Cell country = sheet.getCell(0, lines);
				Cell weight = sheet.getCell(1, lines);
				DataPie data = new DataPie();
				if (country.getContents()!=null && (!weight.getContents().isEmpty())) {
					data.setName(country.getContents());
					data.setWeight(Double.parseDouble(weight.getContents()));
					DbPie.insert(data);
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		DOMConfigurator.configure("configs/logger.xml");
		ExcelParser test = new ExcelParser();
		test.setInputFile("resources/piechart-data.xls");
		test.read();
	}
}