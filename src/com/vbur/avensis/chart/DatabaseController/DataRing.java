package com.vbur.avensis.chart.DatabaseController;

public class DataRing extends DataPie {

	private String date;
	public DataRing(String name, double weight ,String date) {
		super(name, weight);
		this.date=date;
	}
	public DataRing() {
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
