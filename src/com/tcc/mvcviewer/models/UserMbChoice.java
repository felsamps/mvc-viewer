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

	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public boolean equals(Object obj) {
		UserMbChoice u = (UserMbChoice) obj;
		return this.mbX.equals(u.mbX) && this.mbY.equals(u.mbY) &&
				this.poc.equals(u.poc) && this.view.equals(u.view);
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 67 * hash + (this.mbX != null ? this.mbX.hashCode() : 0);
		hash = 67 * hash + (this.mbY != null ? this.mbY.hashCode() : 0);
		hash = 67 * hash + (this.view != null ? this.view.hashCode() : 0);
		hash = 67 * hash + (this.poc != null ? this.poc.hashCode() : 0);
		return hash;
	}
	
}
