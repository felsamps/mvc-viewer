package com.tcc.mvcviewer.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author felsamps
 */
public class OutputFile {
	protected File file;
	protected FileOutputStream fileStream;

	public OutputFile(String filePath) {
		try {
			file = new File(filePath);
			fileStream = new FileOutputStream(file);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void writeByte(Byte b) {
		try {
			byte[] byteArray = new byte[1];
			byteArray[0] = b.byteValue();
			fileStream.write(byteArray);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void closeAndSave() {
		try {
			fileStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
