package com.tcc.mvcviewer.stats;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author felsamps
 */
public class LogFile {
	private static FileWriter logFile;
	private static BufferedWriter writer;
	private static Integer views, frames, gop;
	private static Integer numEntries;
	
	static {
		//variable initialization
		numEntries = 0;
		try {
			logFile = new FileWriter("/home/Tcc/mvc-viewer/data/output-files/logFile.log");
			writer = new BufferedWriter(logFile);
			writeHeader();
		}
		catch( IOException ex ) {
			ex.printStackTrace();
		}
	}

	public static void closeLogFile() {
		try {
			writer.close();
		}
		catch( IOException ex ) {
			ex.printStackTrace();
		}
	}

	public static void init(Integer views, Integer frames, Integer gop) {
		LogFile.views = views;
		LogFile.gop = gop;
		LogFile.frames = frames;
	}

	public static void log(String str) {
		try {
			writer.write(str);
		}
		catch( IOException ex ) {
			ex.printStackTrace();
		}
	}

	public static void incNumEntries() {
		numEntries ++;
	}

	private static void writeHeader() {
		//TODO implement it!
	}
}
