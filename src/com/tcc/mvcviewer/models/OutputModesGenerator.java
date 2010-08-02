package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.InputModesFile;
import com.tcc.mvcviewer.models.files.OutputVideoFile;
import java.io.FileNotFoundException;

/**
 *
 * @author felsamps
 */
public class OutputModesGenerator {
	InputModesFile modesFile;
	OutputVideoFile outVideo;

	public OutputModesGenerator(String modesfile) {
		try {
			modesFile = new InputModesFile(modesfile);
		}
		catch( FileNotFoundException ex ) {
			ex.printStackTrace();
		}
	}

	public void parseModesFile() {
		//TODO implement it
	}

		

}
