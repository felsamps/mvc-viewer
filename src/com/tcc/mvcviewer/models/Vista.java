/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class Vista {
	private ReferenceFrame[] refFrames;
	private CurrentFrame[] currFrames;
	private Integer id,gop;

	public Vista(Integer id, Integer gop) {
		this.id = id;
		this.gop = gop;
	}

	public ReferenceFrame getRefFrame(int poc) {
		return refFrames[poc];
	}

	public void setRefFrame(int poc, ReferenceFrame ref) {
		refFrames[poc] = ref;
	}

	public CurrentFrame getCurrFrame(int poc) {
		return currFrames[poc];
	}

	public void setCurrFrame(int poc, CurrentFrame ref) {
		currFrames[poc] = ref;
	}

}
