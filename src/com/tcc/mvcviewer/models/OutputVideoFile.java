package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class OutputVideoFile extends OutputFile {
	private Integer width;
	private Integer height;


	public OutputVideoFile(String filePath, Integer width, Integer height) {
		super(filePath);
		this.width = width;
		this.height = height;
	}

	public void writeYFrame(Byte[][] yFrame) {
		for(int idy=0; idy<this.height; idy++) {
			for(int idx=0; idx<this.width; idx++) {
				this.writeByte(yFrame[idy][idx]);
			}
		}
	}

	public void writeCFrame(Byte[][] cFrame) {
		for(int idy=0; idy<this.height/2; idy++) {
			for(int idx=0; idx<this.width/2; idx++) {
				this.writeByte(cFrame[idy][idx]);
			}
		}
	}

}
