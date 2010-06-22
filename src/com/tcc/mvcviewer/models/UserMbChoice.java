package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class UserMbChoice {
	private Integer mbX;
	private Integer mbY;
	private Integer view;
	private Integer poc;

	public UserMbChoice(Integer mbX, Integer mbY, Integer view, Integer poc) {
		this.mbX = mbX;
		this.mbY = mbY;
		this.view = view;
		this.poc = poc;
	}

	public Integer getMbX() {
		return mbX;
	}

	public void setMbX(Integer mbX) {
		this.mbX = mbX;
	}

	public Integer getMbY() {
		return mbY;
	}

	public void setMbY(Integer mbY) {
		this.mbY = mbY;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public Integer getPoc() {
		return poc;
	}

	public void setPoc(Integer poc) {
		this.poc = poc;
	}

	@Override
	public String toString() {
		return "MB (" + mbX.toString() + "," + mbY.toString() + ") View " + view.toString()
				+ " Frame " + poc.toString();
	}
	
}
