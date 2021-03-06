package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.models.files.CfgReader;
import com.tcc.mvcviewer.models.*;
import com.tcc.mvcviewer.stats.LogFile;
import com.tcc.mvcviewer.views.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author felsamps
 */
public class MainController extends ApplicationController {
	private MainView view;
	private CfgReader reader;
	private TraceFileParser parser;
	private Video video;

	ModeController modeController;
	
	public MainController() {
		this.view = new MainView(this);
		this.showView();
	}

	private void showView() {
		this.view.setVisible(true);
	}

	private void initFields() {
		view.initFrameList(this.reader.getNumFrames());
		view.initViewList(this.reader.getNumViews());
		view.initMbXList(this.reader.getWidth());
		view.initMbYList(this.reader.getHeight());
		view.initFrameRefList(this.reader.getNumFrames());
		view.initViewRefList(this.reader.getNumViews());
		view.initCacheFields(this.reader.getNumViews(), this.reader.getNumFrames());
	}

	private List<String> getNewVideoPaths() {
		List<String> returnable = new ArrayList<String> ();
		for(Integer i=0; i<reader.getNumViews(); i++) {
			returnable.add(reader.getOutputPath() + "output_" + i.toString() + ".yuv");
		}
		return returnable;
	}

	public void handleAbrirCfgButton() {
		int returnVal = this.view.showFileChooserDialog();
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this.view.getSelectedFile();
			try {
				this.reader = new CfgReader(file);
				this.reader.read();
				this.parser = new TraceFileParser(reader);
				this.view.showOpenFile(reader);
				this.initFields();
				LogFile.init(reader.getNumViews(), reader.getNumFrames(), reader.getGopSize(), reader.getWidth(), reader.getHeight());
			}
			catch(FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void handleAboutButton() {
		this.view.showAboutBox();
	}

	public void handleGenerateCurrentButton() {
		List<UserMbChoice> list = view.getMbChoices();
		List<List<AreaRef>> areas = new ArrayList<List<AreaRef>>();
		video = parser.parse(list);
		for(UserMbChoice choice : list) {
			areas.add(video.getAreaRefs(choice));
		}		
		view.fillAreaList(areas);		
		OutputVideoGenerator generator = new OutputVideoGenerator(areas, reader.getVideoPaths(),
				this.getNewVideoPaths(), reader.getNumViews(), reader.getNumFrames(),
				reader.getWidth(), reader.getHeight(), view.isGridSelected(), false);
		generator.generate();
	}


	public void handleGenerateReferenceButton() {
		Integer refFrame = view.getReferenceFrame();
		Integer refView = view.getReferenceView();
		String output = view.getOutputFileName();
		video = parser.parse(refView, refFrame);
		List<List<AreaRef>> areas = new ArrayList<List<AreaRef>>();
		areas.add(video.getAreaRefs(refView, refFrame));
		view.fillAreaList(areas);
		OutputVideoGenerator generator = new OutputVideoGenerator(areas, reader.getVideoPaths(),
				this.getNewVideoPaths(), reader.getNumViews(), reader.getNumFrames(),
				reader.getWidth(), reader.getHeight(), view.isGridSelected(), false);
		generator.generateRefFrame(refView, refFrame, output);
		if( view.isCurrentMbTracingSelected() ) {
			generator.generateCurrentMBTracing(refView, refFrame, view.getMinX(), view.getMinY(), view.getMaxX(), view.getMaxY());
		}
		LogFile.report();
	}

	public void handleAddButton() {
		UserMbChoice choice = new UserMbChoice(view.getMbX(),
				view.getMbY(), view.getView(), view.getCurrentFrame());
		view.addMbChoice(choice);
	}

	public void handleModeViewBotton() {
		modeController = ModeController.getInstance();
	}

	public void handleGenerateCacheButton() {
		Integer refFrame = view.getRefFrameCache();
		Integer refView = view.getRefViewCache();
		Integer currFrame = view.getCurrFrameCache();
		Integer currView = view.getCurrViewCache();
		String fileName = "/home/felsamps/Tcc/cache-mvc/mb_access.txt";
		video = parser.parse(refView, refFrame, currView, currFrame);
		OutputCacheGenerator generator = new OutputCacheGenerator(fileName, video, refView, refFrame, currView, currFrame);
		generator.generate();
	}
}
