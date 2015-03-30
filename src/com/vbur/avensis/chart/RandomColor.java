package com.vbur.avensis.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;

import org.jfree.chart.plot.DrawingSupplier;

public class RandomColor implements DrawingSupplier {

	private static Stroke stroke = new BasicStroke();
	private static Shape shape = new Rectangle2D.Double();
	private int red = 0;
	private int green = 100;
	private int blue = 150;
	
	
	@Override
	public Paint getNextOutlinePaint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stroke getNextOutlineStroke() {
		return stroke;
	}

	@Override
	public Shape getNextShape() {
		// TODO Auto-generated method stub
		return shape;
	}

	@Override
	public Stroke getNextStroke() {
		// TODO Auto-generated method stub
		return stroke;
	}

	@Override
	public Paint getNextPaint() {
		this.setValues();
		Paint result = new Color(red, green, blue);
		return result;
	}

	@Override
	public Paint getNextFillPaint() {
		this.setValues();
		Paint result = new Color(red, green, blue);
		return result;
	}

	private void setValues() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		red = rand.nextInt(255);
		blue = rand.nextInt(255);
		green = rand.nextInt(255);
	}

}