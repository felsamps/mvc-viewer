package com.tcc.mvcviewer.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author felsamps
 */
public class CfgReader {
	private File config;
	private Scanner configScanner;
	private Integer numViews, gopSize;
	private List<String> traceFilePaths;
	private List<String> videoPaths;
	private Integer width, height;
	

	public CfgReader(String path) {
		try {
			config = new File(path);
			configScanner = new Scanner(config);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		traceFilePaths = new ArrayList<String> ();
		
	}

	public void read() {
		this.numViews = configScanner.nextInt();
		this.gopSize = configScanner.nextInt();
		for(int i=0; i<getNumViews(); i++) {
			getTraceFilePaths().add(configScanner.nextLine());
		}
		this.width = configScanner.nextInt();
		this.height = configScanner.nextInt();
		for(int i=0; i<getNumViews(); i++) {
			getVideoPaths().add(configScanner.nextLine());
		}
	}

	/**
	 * @return the numViews
	 */
	public Integer getNumViews() {
		return numViews;
	}

	/**
	 * @return the gopSize
	 */
	public Integer getGopSize() {
		return gopSize;
	}

	/**
	 * @return the traceFilePaths
	 */
	public List<String> getTraceFilePaths() {
		return traceFilePaths;
	}

	/**
	 * @return the videoPaths
	 */
	public List<String> getVideoPaths() {
		return videoPaths;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	
}
