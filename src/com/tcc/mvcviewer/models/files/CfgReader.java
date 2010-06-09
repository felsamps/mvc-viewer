package com.tcc.mvcviewer.models.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CfgReader {
	private File config;
	private Scanner configScanner;
	private Integer numViews, gopSize;
	private List<String> traceFilePaths;
	private List<String> videoPaths;
	private Integer width, height;
	private Integer numFrames;
	

	public CfgReader(String path) throws FileNotFoundException {
		config = new File(path);
		configScanner = new Scanner(config);
		traceFilePaths = new ArrayList<String> ();
		videoPaths = new ArrayList<String> ();		
	}

	public CfgReader(File file) throws FileNotFoundException {
		config = file;
		configScanner = new Scanner(config);
		traceFilePaths = new ArrayList<String> ();
		videoPaths = new ArrayList<String> ();		
	}

	public void read() {
		while( configScanner.hasNext() ) {
			String[] line = this.parseLine();
			String command = line[0];
			if(command.equals("GOP_SIZE")) {
				this.gopSize = Integer.parseInt(line[2]);
			}
			if(command.equals("VIEWS")) {
				this.numViews = Integer.parseInt(line[2]);
			}
			if(command.equals("NUM_FRAMES")) {
				this.numFrames = Integer.parseInt(line[2]);
			}
			if(command.equals("WIDTH")) {
				this.width = Integer.parseInt(line[2]);
			}
			if(command.equals("HEIGHT")) {
				this.height = Integer.parseInt(line[2]);
			}
			if(command.equals("TRACE_FILES_BEGIN")) {
				for(int i=0; i<this.numViews; i++) {
					this.traceFilePaths.add(this.configScanner.nextLine());
				}
			}
			if(command.equals("VIDEO_FILES_BEGIN")) {
				for(int i=0; i<this.numViews; i++) {
					this.videoPaths.add(this.configScanner.nextLine());
				}
			}
		}
	}
	private String[] parseLine() {
		return configScanner.nextLine().split(" ");
	}

	public Integer getNumViews() {
		return numViews;
	}

	public Integer getGopSize() {
		return gopSize;
	}

	public List<String> getTraceFilePaths() {
		return traceFilePaths;
	}

	public List<String> getVideoPaths() {
		return videoPaths;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getNumFrames() {
		return numFrames;
	}


	
}
