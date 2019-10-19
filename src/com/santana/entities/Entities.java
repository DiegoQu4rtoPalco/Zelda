package com.santana.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entities {
	
	protected double x;
	protected double y;
	protected int whidth;
	protected int height;
	
	private BufferedImage sprite;
	
	public Entities(int x, int y, int whidth, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.whidth = whidth;
		this.height = height;
		
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}
	
	public void ticks() {
		
	}
	
	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public int getWhidth() {
		return whidth;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
