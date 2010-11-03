package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.CfgReader;
import com.tcc.mvcviewer.models.files.DoFileReader;
import com.tcc.mvcviewer.stats.LogFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class PromptHandler {

	private static String OUTPUT_PATH = "/home/felsamps/Tcc/mvc-viewer/data/output-files/";
	DoFileReader doReader;
	CfgReader cfgReader;
	Video video;
	TraceFileParser parser;

	public PromptHandler(String doFileName) {
		try {
			doReader = new DoFileReader(doFileName);
			doReader.read();
			cfgReader = new CfgReader(doReader.getCfgName());
			cfgReader.read();
			parser = new TraceFileParser(cfgReader);
			LogFile.init(cfgReader.getNumViews(), cfgReader.getNumFrames(), cfgReader.getGopSize(),
					cfgReader.getWidth(), cfgReader.getHeight());
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public void run() {

		int mode = doReader.getMode();
		if (mode == 0) {
			this.runCurrentMBMode();
		} else {
			this.runReferenceFrameMode();
		}
	}

	private void runCurrentMBMode() {
		video = parser.parse(doReader.getListMb());
		List<List<AreaRef>> areas = new ArrayList<List<AreaRef>>();
		List<UserMbChoice> list = this.doReader.getListMb();
		for (UserMbChoice choice : list) {
			areas.add(video.getAreaRefs(choice));
		}
		OutputVideoGenerator generator = new OutputVideoGenerator(areas, cfgReader.getVideoPaths(),
				getNewVideoPaths(), cfgReader.getNumViews(), cfgReader.getNumFrames(), cfgReader.getWidth(),
				cfgReader.getHeight(), false, true);
		generator.generate();
		//LogFile.reportPredictionStructure(video);
	}

	private List<String> getNewVideoPaths() {
		List<String> returnable = new ArrayList<String>();
		for (Integer i = 0; i < cfgReader.getNumViews(); i++) {
			returnable.add(OUTPUT_PATH + "output_" + i.toString() + ".yuv");
		}
		return returnable;
	}

	private void runReferenceFrameMode() {
		video = parser.parse(doReader.getTargetView(), doReader.getTargetFrame());
		List<List<AreaRef>> areas = new ArrayList<List<AreaRef>>();
		areas.add(video.getAreaRefs(doReader.getTargetView(), doReader.getTargetFrame()));
		OutputVideoGenerator generator = new OutputVideoGenerator(areas, cfgReader.getVideoPaths(),
				getNewVideoPaths(), cfgReader.getNumViews(), cfgReader.getNumFrames(), cfgReader.getWidth(),
				cfgReader.getHeight(), true, true);
		generator.generateRefFrame(doReader.getTargetView(), doReader.getTargetFrame(), doReader.getOutputVideo());
		LogFile.report();
		LogFile.reportPredictionStructure(video);
	}
}
