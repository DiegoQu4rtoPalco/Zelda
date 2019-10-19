package com.santana.entities;

import java.awt.image.BufferedImage;

public class Player extends Entities{
	
	public boolean right;
	public boolean left;
	public boolean up;
	public boolean down;
	public int speed = 4;

	public Player(int x, int y, int whidth, int height, BufferedImage sprite) {
		super(x, y, whidth, height, sprite);
		
	}
	
	public void ticks() {
		if(right) {
			x+=speed;
		}
		else if(left) {
			x-= speed;
		}
		
		if(up) {
			y-=speed;
		}
		else if(down) {
			y+=speed;
		}
	}

}
