package com.tcc.mvcviewer.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author felsamps
 */
public abstract class InputFile {
	File file;
	FileInputStream fileStream;

	public InputFile() {
	}

	public InputFile(String filePath) throws FileNotFoundException {
		file = new File(filePath);
		fileStream = new FileInputStream(file);
	}
	public int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	private int convertToInt(byte[] b) {
		int returnable = 0x00;
		for(int i=0; i<b.length; i++) {
			returnable |= (this.unsignedByteToInt(b[i])<< (i*8));
		}
		return returnable;
	}

	public int readInt() {
		byte[] b = new byte[4];
		int returnable = 0;
		try {
			fileStream.read(b);
			returnable = this.convertToInt(b);
		} catch (IOException ex) {
			returnable = Integer.MAX_VALUE;
		}
		finally {
			return returnable;
		}
	}

	public int readByte() {
		byte[] b = new byte[1];
		int returnable = 0;
		try {
			fileStream.read(b);
			returnable = this.convertToInt(b);
		} catch (IOException ex) {
			returnable = Integer.MAX_VALUE;
		}
		finally {
			return returnable;
		}
	}

	public boolean finished() {
		boolean returnable = true;
		try {
			returnable = fileStream.available() == 0;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return returnable;
	}
	
}
