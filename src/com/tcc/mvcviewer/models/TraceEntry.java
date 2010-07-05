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
	private boolean biPrediction;

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
		setBiPrediction(getFile().readByte());
	}

	public AreaRef getRefRect(MbDataAccess mb) {
		return new AreaRef(xMin, yMin, xMax, yMax, mb);
	}

	public InputTraceFile getFile() {
		return file;
	}

	public void setFile(InputTraceFile file) {
		this.file = file;
	}

	public Integer getxMin() {
		return xMin;
	}

	public void setxMin(Integer xMin) {
		this.xMin = xMin;
	}

	public Integer getyMin() {
		return yMin;
	}

	public void setyMin(Integer yMin) {
		this.yMin = yMin;
	}

	public Integer getxMax() {
		return xMax;
	}

	public void setxMax(Integer xMax) {
		this.xMax = xMax;
	}

	public Integer getyMax() {
		return yMax;
	}

	public void setyMax(Integer yMax) {
		this.yMax = yMax;
	}

	public Integer getRefView() {
		return refView;
	}

	public void setRefView(Integer refView) {
		this.refView = refView;
	}

	public Integer getRefPoc() {
		return refPoc;
	}

	public void setRefPoc(Integer refPoc) {
		this.refPoc = refPoc;
	}

	public Integer getxMb() {
		return xMb;
	}

	public void setxMb(Integer xMb) {
		this.xMb = xMb;
	}

	public Integer getyMb() {
		return yMb;
	}

	public void setyMb(Integer yMb) {
		this.yMb = yMb;
	}

	public Integer getCurrView() {
		return currView;
	}

	public void setCurrView(Integer currView) {
		this.currView = currView;
	}

	public Integer getCurrPoc() {
		return currPoc;
	}

	public void setCurrPoc(Integer currPoc) {
		this.currPoc = currPoc;
	}

	public Integer getMbType() {
		return mbType;
	}

	public void setMbType(Integer mbType) {
		this.mbType = mbType;
	}

	private void setBiPrediction(Byte readByte) {
		setBiPrediction(((char) readByte.byteValue()) == 'B');
	}

	public boolean isBiPrediction() {
		return biPrediction;
	}

	public void setBiPrediction(boolean biPrediction) {
		this.biPrediction = biPrediction;
	}

}
