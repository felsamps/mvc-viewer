package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class Frame {
	public enum Type {
		I_FRAME, P_FRAME, B_FRAME
	}
	private Integer height;
	private Integer width;
	private Integer poc;
	private Integer view;
	private Type frameType;

	public Frame(Integer height, Integer width, Integer poc, Integer view) {
		this.height = height;
		this.width = width;
		this.poc = poc;
		this.view = view;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the poc
	 */
	public Integer getPoc() {
		return poc;
	}

	/**
	 * @param poc the poc to set
	 */
	public void setPoc(Integer poc) {
		this.poc = poc;
	}

	/**
	 * @return the view
	 */
	public Integer getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Integer view) {
		this.view = view;
	}

	/**
	 * @return the frameType
	 */
	public Type getFrameType() {
		return frameType;
	}

	/**
	 * @param frameType the frameType to set
	 */
	public void setFrameType(Type frameType) {
		this.frameType = frameType;
	}


}
