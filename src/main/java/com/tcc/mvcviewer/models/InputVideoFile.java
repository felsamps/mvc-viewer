/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.models;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 *
 * @author felsamps
 */
public class InputVideoFile {
	private Integer width,height;
	File file;
	BufferedInputStream fileBuffer;
	
	public InputVideoFile () {

	}

	public InputVideoFile (String filePath, Integer width, Integer height) {
		try {
			file = new File(filePath);
			fileBuffer = new BufferedInputStream(new FileInputStream(file));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

//	public byte getSample() {
//		return fileScanner.nextByte();
//	}
//
//	public byte[][] getLumaFrame() {
//		byte [][] lumaFrame = new byte[this.height][this.width];
//		for (int idx = 0; idx < this.height; idx++) {
//			for (int idy = 0; idy < this.width; idy++) {
//				lumaFrame[idx][idy] = fileScanner.nextByte();
//			}
//		}
//		return lumaFrame;
//	}
//
//	public byte[][] getChromaFrame() {
//		byte [][] chromaFrame = new byte[this.height/2][this.width/2];
//		for (int idx = 0; idx < this.height; idx++) {
//			for (int idy = 0; idy < this.width; idy++) {
//				chromaFrame[idx][idy] = fileScanner.nextByte();
//			}
//		}
//		return chromaFrame;
//	}
//
	public Long getNumOfFrames() {
		int  bytesPerFrame = (int) (height * width * 1.5);
		Long totalBytes = file.length();
		return new Long(totalBytes / bytesPerFrame);
	}
	
}
