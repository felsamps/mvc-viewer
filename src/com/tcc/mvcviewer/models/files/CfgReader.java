package com.tcc.mvcviewer.models.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CfgReader extends FileReader {
	private Integer numViews, gopSize;
	private List<String> traceFilePaths;
	private List<String> videoPaths;
	private Integer width, height;
	private Integer numFrames;
	private Integer searchRange;
	

	public CfgReader(String path) throws FileNotFoundException {
		super(path);
		traceFilePaths = new ArrayList<String> ();
		videoPaths = new ArrayList<String> ();		
	}

	public CfgReader(File file) throws FileNotFoundException {
		super(file);
		traceFilePaths = new ArrayList<String> ();
		videoPaths = new ArrayList<String> ();		
	}

	@Override
	public void read() {
		while( !this.finished() ) {
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
			if(command.equals("SEARCH_RANGE")) {
				this.searchRange = Integer.parseInt(line[2]);
			}
			if(command.equals("TRACE_FILES_BEGIN")) {
				for(int i=0; i<this.numViews; i++) {
					this.traceFilePaths.add(this.nextLine());
				}
			}
			if(command.equals("VIDEO_FILES_BEGIN")) {
				for(int i=0; i<this.numViews; i++) {
					this.videoPaths.add(this.nextLine());
				}
			}
		}
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
