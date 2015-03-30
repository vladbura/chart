/**
 * 
 */
package com.vbur.avensis.chart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.vbur.avensis.chart.BaseServices.Constants;
import com.vbur.avensis.chart.BaseServices.PDFServices;
import com.vbur.avensis.chart.DatabaseController.DatabaseEngine;

/**
 * @author VBUR
 * this is the main class. 
 * Running this page you will be able to
 * run the code by inputing the parameter in console line 
 *
 */
public class StartChart {
	
	private static final Logger AppLog = LogManager.getLogger(DatabaseEngine.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DOMConfigurator.configure("configs/logger.xml");
		while(true){
			int readType = UserRequest.question(Constants.SELECT_READ_TYPE);
			PieChart demo = new PieChart("Chart PDF","","chart.pdf",readType);
			System.out.println("Process finish. Restart the process \n \n");
			UserRequest.clearConsole();
		}
	}

}
