package com.tcc.mvcviewer.models;

import java.io.FileNotFoundException;

/**
 *
 * @author felsamps
 */
public class InputVideoFile extends InputFile {
	private Integer width,height;
	
	public InputVideoFile () {

	}

	public InputVideoFile (String filePath, Integer width, Integer height) throws FileNotFoundException {
		super(filePath);
		this.setWidth(width);
		this.setHeight(height);
	}

	public Long getNumOfFrames() {
		int  bytesPerFrame = (int) (getHeight() * getWidth() * 1.5);
		Long totalBytes = file.length();
		return new Long(totalBytes / bytesPerFrame);
	}

	public Byte[][] readYFrame() {
		Byte[][] yFrame = new Byte[this.height][this.width];
		for(int idy=0; idy < this.height; idy++) {
			for(int idx=0; idx < this.width; idx++) {
				yFrame[idy][idx] = this.readByte();
			}
		}
		return yFrame;
	}

	public Byte[][] readCFrame() {
		Byte[][] cFrame = new Byte[this.height/2][this.width/2];
		for(int idy=0; idy < this.height/2; idy++) {
			for(int idx=0; idx < this.width/2; idx++) {
				cFrame[idy][idx] = this.readByte();
			}
		}
		return cFrame;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	
}
