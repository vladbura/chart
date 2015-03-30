package com.vbur.avensis.chart.DatabaseController;

public class DataPie {
	private String name;
	private double weight;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public DataPie(String name, double weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	public DataPie(){
	}
	@Override
	public String toString() {
		return "DataPie [name=" + name + ", weight=" + weight + "]";
	}
	
}
