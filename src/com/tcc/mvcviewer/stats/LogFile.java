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
	private static Integer minimumRequiredSamples, memAccess, mostAccessed, lessAccessed;
	private static Double averageAccess;
	private static Integer w;
	private static Integer h;


	public static void closeLogFile() {
		try {
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void init(Integer views, Integer frames, Integer gop, Integer w, Integer h) {
		LogFile.views = views;
		LogFile.gop = gop;
		LogFile.frames = frames;
		LogFile.w = w;
		LogFile.h = h;
		LogFile.lessAccessed = Integer.MAX_VALUE;
		LogFile.mostAccessed = 0;
		LogFile.numEntries = 0;
		LogFile.memAccess = 0;
	}

	public static void log(String str) {
		try {
			writer.write(str);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void incNumEntries() {
		numEntries++;
	}

	private static void writeHeader() {
		//TODO implement it!
	}

	private static void insertAccess(int acc) {
		memAccess += acc;
		if (acc > mostAccessed) {
			mostAccessed = acc;
		}
		if (acc < lessAccessed && acc > 0) {
			lessAccessed = acc;
		}

	}

	private static void calculateStats() {
		minimumRequiredSamples = w * h;
		averageAccess = memAccess / (w * h * 1.0);

	}

	public static void report() {
		String saida = "";
		LogFile.calculateStats();
		saida += "Minimum Required Samples Access:\t" + minimumRequiredSamples.toString() + "\n";
		saida += "Accessed Samples:\t" + memAccess.toString() + "\n";
		saida += "Avarage Sample Access:\t" + averageAccess.toString() + "\n";
		saida += "Most Accessed Sample:\t" + mostAccessed.toString() + "\n";
		saida += "Less Accessed Sample:\t" + lessAccessed.toString() + "\n";
		System.out.println(saida);

	}

	public static void insertSampleAccess(int[][] frequencies) {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				LogFile.insertAccess(frequencies[i][j]);
			}
		}
	}
}
