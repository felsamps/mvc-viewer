package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.CfgReader;
import com.tcc.mvcviewer.models.files.InputTraceFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class TraceFileParser {
	private static boolean COND_MASTER = false;

	private List<InputTraceFile> traceFiles;
	private List<String> traceFilePath;
	private Integer numViews;
	private Integer gopSize;
	private Integer numFrames;
	private Video video;
	private Integer height, width;

	public TraceFileParser(Integer height, Integer width, Integer numViews, Integer gopSize, Integer numFrames, List<String> traceFilePath) throws FileNotFoundException {
		this.height = height;
		this.width = width;
		this.numViews = numViews;
		this.numFrames = numFrames;
		this.gopSize = gopSize;
		this.traceFilePath = traceFilePath;
		this.initTraceFiles();
		this.initVideo();
	}

	public TraceFileParser(CfgReader reader) throws FileNotFoundException {
		this.height = reader.getHeight();
		this.width = reader.getWidth();
		this.numViews = reader.getNumViews();
		this.numFrames = reader.getNumFrames();
		this.gopSize = reader.getGopSize();
		this.traceFilePath = reader.getTraceFilePaths();
		this.initTraceFiles();
		this.initVideo();
	}

	private void initTraceFiles() throws FileNotFoundException {
		traceFiles = new ArrayList<InputTraceFile> ();
		for(String path : traceFilePath) {
			traceFiles.add(new InputTraceFile(path));
		}
	}

	private void initVideo() {
		video = new Video(numViews, numFrames, width, height);
	}


	public Video parse() {
		for(InputTraceFile file : traceFiles) {
			this.parseTraceFile(file);
		}
		return video;
	}

	private void parseTraceFile(InputTraceFile file) {
		while(! file.finished()) {
			TraceEntry entry = new TraceEntry(file);
			CurrentFrame currFrame = video.getCurrentFrame(entry.getCurrView(), entry.getCurrPoc());
			ReferenceFrame refFrame = video.getReferenceFrame(entry.getRefView(), entry.getRefPoc());
			currFrame.insertTraceEntry(entry, refFrame);
			video.incNumEntries();
		}	
	}

	private void parseTraceFile(InputTraceFile file, Integer targetFrame, Integer targetMbX, Integer targetMbY) {
		while(! file.finished()) {
			TraceEntry entry = new TraceEntry(file);
			System.err.println(entry.getxMb().toString() + entry.getyMb().toString());
			boolean temp = COND_MASTER;
			if(entry.getCurrPoc() == targetFrame && entry.getxMb() == targetMbX && entry.getyMb() == targetMbY) {
				CurrentFrame currFrame = video.getCurrentFrame(entry.getCurrView(), entry.getCurrPoc());
				ReferenceFrame refFrame = video.getReferenceFrame(entry.getRefView(), entry.getRefPoc());
				currFrame.insertTraceEntry(entry, refFrame);
				COND_MASTER = true;
			}
			else {
				COND_MASTER = false;
			}
			if(temp == true && COND_MASTER == false) {
				break;
			}
		}
	}

	public Video parse(Integer targetView, Integer targetFrame, Integer targetMbX, Integer targetMbY) {
		parseTraceFile(traceFiles.get(targetView), targetFrame, targetMbX, targetMbY);
		return video;
	}

}