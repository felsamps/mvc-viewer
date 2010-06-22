package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

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

	public void addRefRect(int mbType, AreaRef ref) {
		arrayMbModes[mbType].addRefRect(ref);
	}

	public MbMode getMbMode(int mbType) {
		return arrayMbModes[mbType];
	}

	public List<AreaRef> getAreaRectList(int mbMode) {
		return arrayMbModes[mbMode].getList();
	}

	public List<AreaRef> getAreaRectList() {
		List<AreaRef> returnable = new ArrayList<AreaRef> ();
		for(MbMode mode : arrayMbModes) {
			returnable.addAll(mode.getList());
		}
		return returnable;
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
}
