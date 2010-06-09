package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class Video {
	public CurrentFrame[][] currVistas;
	public ReferenceFrame[][] refVistas;
	public Integer width, height;

	public Video(Integer views, Integer numFrames, Integer width, Integer height) {
		initCurrentFrames(views, numFrames);
		initReferenceFrames(views, numFrames);
		this.width = width;
		this.height = height;
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
}
