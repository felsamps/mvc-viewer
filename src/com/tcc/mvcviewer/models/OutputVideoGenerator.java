package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.OutputVideoFile;
import com.tcc.mvcviewer.models.files.InputVideoFile;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class OutputVideoGenerator {
	private List<InputVideoFile> originalVideos;
	private List<OutputVideoFile> modifiedVideos;
	private Integer numViews, numFrames;
	private List<RefRect> areas;

	public OutputVideoGenerator(List<RefRect> areas, List<String> videoPaths, Integer numViews, Integer numFrames) {
		this.areas = areas;
		this.numViews = numViews;
		this.numFrames = numFrames;
		initOriginalVideos(videoPaths);
		initModifiedVideos();
	}

	private Byte[][] getOriginalLumaFrame(int view) {
		return originalVideos.get(view).readYFrame();
	}

	private Byte[][] getOriginalChromaFrame(int view) {
		return originalVideos.get(view).readCFrame();
	}

	private void initOriginalVideos(List<String> videoPaths) {
		for(String path : videoPaths) {
			try {
				originalVideos.add(new InputVideoFile(path, this.numFrames, this.numViews));
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void initModifiedVideos() {
		//TODO implement this setUp method
	}

	private void modifyArea(RefRect area) {
		//TODO think more about it
	}

	public void generate() {
		for(RefRect area : this.areas) {
			this.modifyArea(area);
		}
	}
}
