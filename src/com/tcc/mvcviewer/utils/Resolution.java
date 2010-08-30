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
		return w + "x" + h + " (" + comment + ")";
	}
}
