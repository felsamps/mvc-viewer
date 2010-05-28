package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class Video {
	public CurrentFrame[][] currVistas;
	public ReferenceFrame[][] refVistas;
	public Integer width, height;

	public Video(Integer views, Integer gop, Integer width, Integer height) {
		initCurrentFrames(views, gop);
		initReferenceFrames(views, gop);
		this.width = width;
		this.height = height;
	}

	private void initCurrentFrames(Integer views, Integer gop) {
		currVistas = new CurrentFrame[views][gop + 1];
	}

	private void initReferenceFrames(Integer views, Integer gop) {
		refVistas = new ReferenceFrame[views][gop + 1];
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
