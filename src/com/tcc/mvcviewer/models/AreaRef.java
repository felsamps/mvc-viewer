package com.tcc.mvcviewer.models;

import java.awt.Rectangle;

/**
 *
 * @author felsamps
 */
public class AreaRef {
	private Integer xMin,yMin;
	private Integer xMax,yMax;
	private ReferenceFrame frame;
	private MbDataAccess mbAtual;
	private boolean refinement;

	public AreaRef() {
	}

	public AreaRef(Integer xMin, Integer yMin, Integer xMax, Integer yMax, MbDataAccess mbAtual, boolean refinement) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
		this.mbAtual = mbAtual;
		this.refinement = refinement;
	}

	public ReferenceFrame getFrame() {
		return frame;
	}

	public void setFrame(ReferenceFrame frame) {
		this.frame = frame;
		this.frame.insertRefRect(this);
	}

	public void setFrameBiPred(ReferenceFrame frame) {
		this.frame = frame;
		this.frame.insertRefRectBiPred(this);
	}

	@Override
	public boolean equals(Object obj) {
		AreaRef ref = (AreaRef) obj;
		return this.getxMin().equals(ref.getxMin()) && this.getxMax().equals(ref.getxMax())
				&& this.getyMin().equals(ref.getyMin()) && this.getyMax().equals(ref.getyMax())
				&& this.frame.getPoc().equals(ref.getFrame().getPoc())
				&& this.frame.getView().equals(ref.getFrame().getView());
	}

	@Override
	public String toString() {
		return "("+frame.getView().toString()+","+frame.getPoc().toString()+") " +
				xMin.toString() + " " + yMin.toString() + " " + xMax.toString() + " " + yMax.toString();
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

	public MbDataAccess getMbAtual() {
		return mbAtual;
	}

	public void setMbAtual(MbDataAccess mbAtual) {
		this.mbAtual = mbAtual;
	}

	public boolean intersecs(Integer minX, Integer minY, Integer maxX, Integer maxY) {
		Rectangle right = new Rectangle(this.xMin, this.yMin, this.xMax-this.xMin, this.yMax-this.yMin);
		Rectangle left = new Rectangle(minX, minY, maxX-minX, maxY-minY);
		return left.intersects(right);
	}
	
	public boolean isRefinement() {
		return refinement;
	}


}
