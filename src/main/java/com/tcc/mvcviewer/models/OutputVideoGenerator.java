/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class OutputVideoGenerator {
	private String videoSeq;
	private InputVideoFile[] originalVideos;

	public OutputVideoGenerator(String videoSeq) {
		this.videoSeq = videoSeq;
		initOriginalVideos();
	}

//	public byte[][] getOriginalLumaFrame(int view) {
//		return originalVideos[view].getLumaFrame();
//	}

//	public byte[][] getOriginalChromaFrame(int view) {
//		return originalVideos[view].getChromaFrame();
//	}

	private void initOriginalVideos() {
		//TODO implement
	}

}
