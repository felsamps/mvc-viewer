package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class RefRect {
	private Integer xMin,yMin;
	private Integer xMax,yMax;
	private ReferenceFrame frame;

	public RefRect() {
	}

	public RefRect(Integer xMin, Integer yMin, Integer xMax, Integer yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}

	/**
	 * @return the frame
	 */
	public ReferenceFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(ReferenceFrame frame) {
		this.frame = frame;
		this.frame.insertRefRect(this);
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		RefRect ref = (RefRect) obj;
		return this.getxMin().equals(ref.getxMin()) && this.getxMax().equals(ref.getxMax())
				&& this.getyMin().equals(ref.getyMin()) && this.getyMax().equals(ref.getyMax())
				&& this.frame.getPoc().equals(ref.getFrame().getPoc())
				&& this.frame.getView().equals(ref.getFrame().getView());
	}

	@Override
	public String toString() {
		return xMin.toString() + yMin.toString() + xMax.toString() + yMax.toString();
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

}
