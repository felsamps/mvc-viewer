package com.tcc.mvcviewer.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author felsamps
 */
public class InputTraceFile {
	File file;
	Scanner fileScanner;

	public InputTraceFile(String filePath) {
		try {
			file = new File(filePath);
			fileScanner = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Integer getNextInteger() {
		try {
			return new Integer(fileScanner.nextInt());
		}
		catch (NoSuchElementException e) {
			return new Integer(Integer.MAX_VALUE);
		}
	}

	public boolean finished() {
		return ! fileScanner.hasNextInt();
	}

	void skipLine() {
		fileScanner.nextLine();
	}	
}
