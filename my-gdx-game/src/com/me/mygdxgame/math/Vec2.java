package com.me.mygdxgame.math;

import java.io.Serializable;

public class Vec2 implements Serializable {

	private static final long serialVersionUID = 1L;
	public int x, y;
	
	public Vec2(int xa, int ya) {
		this.x = xa;
		this.y = ya;
	}
	public Vec2() {
		this(0, 0);
	}
	public Vec2(Vec2 vec) {
		this(vec.x, vec.y);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void set(int num) {
		setX(num);
		setY(num);
	}
	public void set(int xa, int ya) {
		setX(xa);
		setY(ya);
	}
	public void setX(int xa) {
		x = xa;
	}
	public void setY(int ya) {
		y = ya;
	}
	
	public void add(int num) {
		addX(num);
		addY(num);
	}
	public void sub(int num) {
		subX(num);
		subY(num);
	}
	public void add(int xa, int ya) {
		addX(xa);
		addY(ya);
	}
	public void sub(int xa, int ya) {
		subX(xa);
		subY(ya);
	}
	public void addX(int num) {
		x+=num;
	}
	public void subX(int num) {
		x-=num;
	}
	public void addY(int num) {
		y+=num;
	}
	public void subY(int num) {
		y-=num;
	}
	
	public int getBoth() {
		return x+y;
	}
	public int getMultiplied() {
		return x*y;
	}
}
