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

	
}
