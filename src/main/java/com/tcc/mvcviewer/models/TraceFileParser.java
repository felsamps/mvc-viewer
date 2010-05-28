package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class TraceFileParser {
	private List<InputTraceFile> traceFiles;
	private List<String> traceFilePath;
	private Integer numViews;
	private Integer gopSize;
	private Video video;
	private Integer height, width;

	public TraceFileParser(Integer height, Integer width, Integer numViews, Integer gopSize, List<String> traceFilePath) {
		this.height = height;
		this.width = width;
		this.numViews = numViews;
		this.gopSize = gopSize;
		this.traceFilePath = traceFilePath;
		this.initTraceFiles();
		this.initVideo();
	}

	private void initTraceFiles() {
		traceFiles = new ArrayList<InputTraceFile> ();
		for(String path : traceFilePath) {
			traceFiles.add(new InputTraceFile(path));
		}
	}

	private void initVideo() {
		video = new Video(numViews, gopSize, width, height);
	}


	public Video parser() {
		for(InputTraceFile file : traceFiles) {
			this.parserTraceFile(file);
		}
		return video;
	}

	private void parserTraceFile(InputTraceFile file) {
		while(! file.finished()) {
			TraceEntry entry = new TraceEntry(file);
			CurrentFrame currFrame = video.getCurrentFrame(entry.getCurrView(), entry.getCurrPoc());
			ReferenceFrame refFrame = video.getReferenceFrame(entry.getCurrView(), entry.getCurrPoc());
			currFrame.insertTraceEntry(entry);
			refFrame.insertRefRect(entry);
		}
		
	}
}
