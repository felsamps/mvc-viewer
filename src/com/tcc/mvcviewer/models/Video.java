package com.tcc.mvcviewer.models;

import java.util.List;

/**
 *
 * @author felsamps
 */
public class Video {
	public CurrentFrame[][] currVistas;
	public ReferenceFrame[][] refVistas;
	public Integer width, height;
	public Integer numEntries;

	public Video(Integer views, Integer numFrames, Integer width, Integer height) {
		initCurrentFrames(views, numFrames);
		initReferenceFrames(views, numFrames);
		this.width = width;
		this.height = height;
		this.numEntries = 0;
	}

	private void initCurrentFrames(Integer views, Integer numFrames) {
		currVistas = new CurrentFrame[views][numFrames + 1];
	}

	private void initReferenceFrames(Integer views, Integer numFrames) {
		refVistas = new ReferenceFrame[views][numFrames + 1];
	}

	public CurrentFrame getCurrentFrame(Integer viewId, Integer poc) {
		if(currVistas[viewId][poc] == null) {
			currVistas[viewId][poc] = new CurrentFrame(width, height, poc, viewId);
		}
		return currVistas[viewId][poc];
	}

	public ReferenceFrame getReferenceFrame(Integer viewId, Integer poc) {
		if(refVistas[viewId][poc] == null) {
			refVistas[viewId][poc] = new ReferenceFrame(width, height, poc, viewId);
		}
		return refVistas[viewId][poc];
	}

	public void incNumEntries() {
		this.numEntries ++;
	}

	public List<AreaRef> getAreaRefs(UserMbChoice choice) {
		CurrentFrame frame = currVistas[choice.getView()][choice.getPoc()];
		return frame.getMb(choice.getMbX(), choice.getMbY()).getAreaRectList();
	}

	//TODO handle the choice for different block sizes search
	public List<AreaRef> getAreaRefs(Integer targetView, Integer targetFrame, Integer targetMbX, Integer targetMbY) {
		CurrentFrame frame = currVistas[targetView][targetFrame];
		return frame.getMb(targetMbX, targetMbY).getAreaRectList();
	}

	public List<AreaRef> getAreaRefs(Integer refView, Integer refFrame) {
		ReferenceFrame frame = refVistas[refView][refFrame];
		return frame.getEntries();
	}
}
