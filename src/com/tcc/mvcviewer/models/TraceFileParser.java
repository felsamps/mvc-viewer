package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.CfgReader;
import com.tcc.mvcviewer.models.files.InputTraceFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author felsamps
 */
public class TraceFileParser {
	private static Integer LIMIT=0;

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

	public Video parse(Integer refView, Integer refPoc) {
		for(InputTraceFile file : traceFiles) {
			Logger.getRootLogger().info("Parsing the trace file " + file.getFile().getName());
			resetLogCounter();
			this.parseTraceFile(file, refView, refPoc);
		}
		return video;
	}

	public Video parse() {
		for(InputTraceFile file : traceFiles) {
			Logger.getRootLogger().info("Parsing the trace file " + file.getFile().getName());
			resetLogCounter();
			this.parseTraceFile(file);
		}
		return video;
	}

	public Video parse(List<UserMbChoice> list) {
		for(InputTraceFile file : traceFiles) {
			Logger.getRootLogger().info("Parsing the trace file " + file.getFile().getName());
			resetLogCounter();
			this.parseTraceFile(file, list);
		}
		return video;
	}

	private void parseTraceFile(InputTraceFile file) {
		while(! file.finished()) {
			TraceEntry entry = new TraceEntry(file);
			this.showInfoLog(file.tell());
			CurrentFrame currFrame = video.getCurrentFrame(entry.getCurrView(), entry.getCurrPoc());
			ReferenceFrame refFrame = video.getReferenceFrame(entry.getRefView(), entry.getRefPoc());
			currFrame.insertTraceEntry(entry, refFrame);
			video.incNumEntries();
		}	
	}

	private void parseTraceFile(InputTraceFile file, Integer refView, Integer refPoc) {
		while(! file.finished()) {
			TraceEntry entry = new TraceEntry(file);
			this.showInfoLog(file.tell());
			if(entry.getRefPoc() == refPoc && entry.getRefView() == refView) {
				CurrentFrame currFrame = video.getCurrentFrame(entry.getCurrView(), entry.getCurrPoc());
				ReferenceFrame refFrame = video.getReferenceFrame(entry.getRefView(), entry.getRefPoc());
				currFrame.insertTraceEntry(entry, refFrame);
			}
		}
	}

	private void parseTraceFile(InputTraceFile file, List<UserMbChoice> list) {
		while(! file.finished()) {
			TraceEntry entry = new TraceEntry(file);
			UserMbChoice condition = new UserMbChoice(entry.getxMb(),
					entry.getyMb(), entry.getCurrView(), entry.getCurrPoc());
			this.showInfoLog(file.tell());
			if(list.contains(condition)) {
				
				CurrentFrame currFrame = video.getCurrentFrame(entry.getCurrView(), entry.getCurrPoc());
				ReferenceFrame refFrame = video.getReferenceFrame(entry.getRefView(), entry.getRefPoc());
				currFrame.insertTraceEntry(entry, refFrame);
				video.incNumEntries();
			}
		}	
	}

	private static void resetLogCounter() {
		LIMIT = 0;
	}

	private void showInfoLog(double percent) {
		if( percent > LIMIT ) {
			Logger.getRootLogger().info(LIMIT + "% lido...");
			LIMIT += 10;
		}
	}
}
