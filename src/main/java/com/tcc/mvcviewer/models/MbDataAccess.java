package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class MbDataAccess {

	private Integer mbX,mbY;
	private MbMode[] arrayMbModes;
	
	public MbDataAccess(Integer mbX, Integer mbY) {
		this.mbX = mbX;
		this.mbY = mbY;
		this.arrayMbModes = new MbMode[MbMode.MB_TYPE_ARRAY_SIZE];
		for (int i = 0; i < arrayMbModes.length; i++) {
			arrayMbModes[i] = new MbMode();
		}
	}

	public void addRefRect(int mbType, RefRect ref) {
		arrayMbModes[mbType].addRefRect(ref);
	}

	public MbMode getMbMode(int mbType) {
		return arrayMbModes[mbType];
	}

	public int getRefRectListSize() {
		return arrayMbModes.length;
	}

	

	/**
	 * @return the mbX
	 */
	public Integer getMbX() {
		return mbX;
	}

	/**
	 * @param mbX the mbX to set
	 */
	public void setMbX(Integer mbX) {
		this.mbX = mbX;
	}

	/**
	 * @return the mbY
	 */
	public Integer getMbY() {
		return mbY;
	}

	/**
	 * @param mbY the mbY to set
	 */
	public void setMbY(Integer mbY) {
		this.mbY = mbY;
	}
}
