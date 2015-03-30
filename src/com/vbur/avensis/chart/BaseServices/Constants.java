package com.vbur.avensis.chart.BaseServices;

public class Constants {
	public static final int EXIT_ERROR_NO_DATABASE = -1;
	public static final int EXIT_ERROR_INVALID_DATABASE = -2;
	public static final String[] SUPER_HOSTS = {"127.0.0.1"};
	
	/**
	 * Questions
	 */
	public static final String SELECT_READ_TYPE = "From where you want to read data: \n 1: Database \n 2: File";
	public static final String SELECT_OUTPUT_TYPE = "Pie chart type will be: \n 1: Pie \n 2: Ring";
	
	/*
	 * Pie type
	 */
	public static final int PIE_CHART = 0;
	public static final int RING_CHART = 1;
	
	
}
