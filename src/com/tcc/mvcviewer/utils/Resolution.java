package com.tcc.mvcviewer.utils;

/**
 *
 * @author felsamps
 */
public class Resolution {
	private Integer w, h;
	private String comment;

	public Resolution(Integer w, Integer h) {
		this.w = w;
		this.h = h;
	}

	public Resolution(Integer w, Integer h, String comment) {
		this.w = w;
		this.h = h;
		this.comment = comment;
	}

	public String toString() {
		return getW() + "x" + getH() + " (" + comment + ")";
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	
}
