package com.tcc.mvcviewer.models.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author felsamps
 */
public abstract class FileReader {
	File file;
	Scanner scanner;

	public FileReader(String fileName) throws FileNotFoundException {
		file = new File(fileName);
		scanner = new Scanner(file);
	}

	public FileReader(File file) throws FileNotFoundException {
		this.file = file;
		scanner = new Scanner(file);
	}

	public String[] parseLine() {
		return scanner.nextLine().split(" ");
	}

	public boolean finished() {
		return !scanner.hasNext();
	}

	public String nextLine() {
		return scanner.nextLine();
	}

	public abstract void read();
}
