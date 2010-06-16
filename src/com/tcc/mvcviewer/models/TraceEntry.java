package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.InputTraceFile;

/**
 *
 * @author felsamps
 */
public class TraceEntry {
	private InputTraceFile file;
	private Integer xMin, yMin;
	private Integer xMax, yMax;
	private Integer refView, refPoc;
	private Integer xMb, yMb;
	private Integer currView, currPoc;
	private Integer mbType;

	public TraceEntry(InputTraceFile file) {
		this.file = file;
		initEntry();
	}

	private void initEntry() {
		setxMin(getFile().readInt());
		setyMin(getFile().readInt());
		setxMax(getFile().readInt());
		setyMax(getFile().readInt());
		setRefPoc(getFile().readInt());
		setRefView(getFile().readInt());
		setxMb(getFile().readInt());
		setyMb(getFile().readInt());
		setCurrPoc(getFile().readInt());
		setCurrView(getFile().readInt());
		setMbType(getFile().readInt());
	}

	public AreaRef getRefRect() {
		return new AreaRef(xMin, yMin, xMax, yMax);
	}

	/**
	 * @return the file
	 */
	public InputTraceFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(InputTraceFile file) {
		this.file = file;
	}

	/**
	 * @return the xMin
	 */
	public Integer getxMin() {
		return xMin;
	}

	/**
	 * @param xMin the xMin to set
	 */
	public void setxMin(Integer xMin) {
		this.xMin = xMin;
	}

	/**
	 * @return the yMin
	 */
	public Integer getyMin() {
		return yMin;
	}

	/**
	 * @param yMin the yMin to set
	 */
	public void setyMin(Integer yMin) {
		this.yMin = yMin;
	}

	/**
	 * @return the xMax
	 */
	public Integer getxMax() {
		return xMax;
	}

	/**
	 * @param xMax the xMax to set
	 */
	public void setxMax(Integer xMax) {
		this.xMax = xMax;
	}

	/**
	 * @return the yMax
	 */
	public Integer getyMax() {
		return yMax;
	}

	/**
	 * @param yMax the yMax to set
	 */
	public void setyMax(Integer yMax) {
		this.yMax = yMax;
	}

	/**
	 * @return the refView
	 */
	public Integer getRefView() {
		return refView;
	}

	/**
	 * @param refView the refView to set
	 */
	public void setRefView(Integer refView) {
		this.refView = refView;
	}

	/**
	 * @return the refPoc
	 */
	public Integer getRefPoc() {
		return refPoc;
	}

	/**
	 * @param refPoc the refPoc to set
	 */
	public void setRefPoc(Integer refPoc) {
		this.refPoc = refPoc;
	}

	/**
	 * @return the xMb
	 */
	public Integer getxMb() {
		return xMb;
	}

	/**
	 * @param xMb the xMb to set
	 */
	public void setxMb(Integer xMb) {
		this.xMb = xMb;
	}

	/**
	 * @return the yMb
	 */
	public Integer getyMb() {
		return yMb;
	}

	/**
	 * @param yMb the yMb to set
	 */
	public void setyMb(Integer yMb) {
		this.yMb = yMb;
	}

	/**
	 * @return the currView
	 */
	public Integer getCurrView() {
		return currView;
	}

	/**
	 * @param currView the currView to set
	 */
	public void setCurrView(Integer currView) {
		this.currView = currView;
	}

	/**
	 * @return the currPoc
	 */
	public Integer getCurrPoc() {
		return currPoc;
	}

	/**
	 * @param currPoc the currPoc to set
	 */
	public void setCurrPoc(Integer currPoc) {
		this.currPoc = currPoc;
	}

	/**
	 * @return the mbType
	 */
	public Integer getMbType() {
		return mbType;
	}

	/**
	 * @param mbType the mbType to set
	 */
	public void setMbType(Integer mbType) {
		this.mbType = mbType;
	}

}
