package com.vbur.avensis.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;

import javax.swing.JLabel;

public class Legend extends JLabel{
	private int size = 15;
	private int shapeAdjust = 3;
	private int textXAdjust = 5;
	private int textYAdjust = 1;
	private Paint rectangleColor;
	private boolean rectangleVisible = true;

	/*
	 * Public properties
	 */
	
	public Paint getRectangleColor() {
		return rectangleColor;
	}

	public void setRectangleColor(Paint paint) {
		this.rectangleColor = paint;
	}

	public void setRectangleVisible(boolean visible) {
		this.rectangleVisible = visible;
	}

	public boolean getRectangleVisible() {
		return this.rectangleVisible;
	}

	/*
	 * Custom draw the label if rectangle is needed
	 */
	@Override
	public void paintComponent(Graphics g) {
		// if the rectangle should be visible, then custom draw the label
		// otherwise use default rendering
		if (this.rectangleVisible) {
			g.setColor(Color.BLACK);
			g.drawRect(0, shapeAdjust, size, size);
			g.setColor((Color) rectangleColor);
			g.fillRect(0, shapeAdjust, size, size);
			g.setColor(super.getForeground());
			g.drawString(this.getText(), size + textXAdjust, size + textYAdjust);
		}
		else{
			super.paintComponent(g);
		}
	}

}
