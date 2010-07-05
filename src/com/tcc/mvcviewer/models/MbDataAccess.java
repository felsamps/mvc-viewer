package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class MbDataAccess {

	private Integer mbX,mbY;
	private CurrentFrame frame;
	private MbMode[] arrayMbModes;
	private MbMode[] arrayMbModesBiPred;
	
	public MbDataAccess(Integer mbX, Integer mbY, CurrentFrame frame) {
		this.frame = frame;
		this.mbX = mbX;
		this.mbY = mbY;
		this.arrayMbModes = new MbMode[MbMode.MB_TYPE_ARRAY_SIZE];
		this.arrayMbModesBiPred = new MbMode[MbMode.MB_TYPE_ARRAY_SIZE];
		for (int i = 0; i < arrayMbModes.length; i++) {
			arrayMbModes[i] = new MbMode();
			arrayMbModesBiPred[i] = new MbMode();
		}
	}

	public void addRefRect(int mbType, AreaRef ref) {
		arrayMbModes[mbType].addRefRect(ref);
	}

	public void addRefRectBiPred(int mbType, AreaRef ref) {
		getArrayMbModesBiPred()[mbType].addRefRect(ref);
	}

	public MbMode getMbMode(int mbType) {
		return arrayMbModes[mbType];
	}

	public List<AreaRef> getAreaRectList(int mbMode) {
		return arrayMbModes[mbMode].getList();
	}

	public List<AreaRef> getAreaRectListBiPred(int mbMode) {
		return arrayMbModesBiPred[mbMode].getList();
	}

	public List<AreaRef> getAreaRectListBiPred() {
		List<AreaRef> returnable = new ArrayList<AreaRef> ();
		for(MbMode mode : arrayMbModesBiPred) {
			returnable.addAll(mode.getList());
		}
		return returnable;
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

	public MbMode[] getArrayMbModesBiPred() {
		return arrayMbModesBiPred;
	}

	public void setArrayMbModesBiPred(MbMode[] arrayMbModesBiPred) {
		this.arrayMbModesBiPred = arrayMbModesBiPred;
	}

	/**
	 * @return the frame
	 */
	public CurrentFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(CurrentFrame frame) {
		this.frame = frame;
	}
}
