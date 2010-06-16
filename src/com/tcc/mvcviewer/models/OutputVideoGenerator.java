package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.OutputVideoFile;
import com.tcc.mvcviewer.models.files.InputVideoFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class OutputVideoGenerator {
	private List<InputVideoFile> originalVideos;
	private List<OutputVideoFile> modifiedVideos;
	private Integer numViews, numFrames;
	private List<AreaRef> areas;
	private Integer height, width;

	public OutputVideoGenerator(List<AreaRef> areas, List<String> videoPaths,
			List<String> newVideoPaths, Integer numViews, Integer numFrames,
			Integer width, Integer height) {
		this.width = width;
		this.height = height;
		this.areas = areas;
		this.numViews = numViews;
		this.numFrames = numFrames;
		initOriginalVideos(videoPaths);
		initModifiedVideos(newVideoPaths);
	}

	private Byte[][] getOriginalLumaFrame(int view) {
		return originalVideos.get(view).readYFrame();
	}

	private Byte[][] getOriginalChromaFrame(int view) {
		return originalVideos.get(view).readCFrame();
	}

	private void initOriginalVideos(List<String> videoPaths) {
		originalVideos = new ArrayList<InputVideoFile>();
		for(String path : videoPaths) {
			try {
				originalVideos.add(new InputVideoFile(path, this.width, this.height));
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void initModifiedVideos(List<String> paths) {
		modifiedVideos = new ArrayList<OutputVideoFile>();
		for(String path : paths) {
			modifiedVideos.add(new OutputVideoFile(path, this.width, this.height));
		}
	}

	private void adjustArea(AreaRef area) {
		if(area.getxMin() < 0)
			area.setxMin(0);
		if(area.getyMin() < 0)
			area.setyMin(0);
		if(area.getxMax() >= this.width)
			area.setxMax(this.width-1);
		if(area.getyMax() >= this.height)
			area.setyMax(this.height-1);
	}

	private void modifyYArea(AreaRef area, Byte[][] yFrame) {
		byte b = 50;
		this.adjustArea(area);
		for(int i=area.getxMin(); i<area.getxMax(); i++) {
			for(int y=area.getyMin(); y<area.getyMax(); y++) {
				byte sample = (byte) (yFrame[y][i].byteValue() + b);
				yFrame[y][i] = new Byte(sample);
			}
		}
	}

	private void modifyCbArea(AreaRef area, Byte[][] cFrame) {
		byte b = 50;
		this.adjustArea(area);
		for(int i=area.getxMin()/2; i<area.getxMax()/2; i++) {
			for(int y=area.getyMin()/2; y<area.getyMax()/2; y++) {
				byte sample = (byte) (cFrame[y][i].byteValue() + b);
				cFrame[y][i] = new Byte(sample);
			}
		}
	}

	private List<AreaRef> filterAreaRefList(Integer view, Integer poc) {
		List<AreaRef> returnable = new ArrayList<AreaRef> ();
		for(AreaRef area : this.areas) {
			if(area.getFrame().getView() == view &&
					area.getFrame().getPoc() == poc) {
				returnable.add(area);
			}
		}
		return returnable;
	}

	public void generate() {
		for(int view=0; view<this.numViews; view++) {
			for(int frame=1; frame <= this.numFrames; frame++) {
				List<AreaRef> list = this.filterAreaRefList(view, frame);
				//TODO refactoring of it!
				Byte[][] yFrame = this.originalVideos.get(view).readYFrame();
				Byte[][] cbFrame = this.originalVideos.get(view).readCFrame();
				Byte[][] crFrame = this.originalVideos.get(view).readCFrame();
				for(AreaRef area : list) {
					this.modifyCbArea(area, cbFrame);
				}
				this.modifiedVideos.get(view).writeYFrame(yFrame);
				this.modifiedVideos.get(view).writeCFrame(cbFrame);
				this.modifiedVideos.get(view).writeCFrame(crFrame);
			}
			this.modifiedVideos.get(view).closeAndSave();
		}
	}

	public void generateRefFrame(Integer refView, Integer refFrame, String path) {
		OutputVideoFile output = new OutputVideoFile(path, width, height);
		int[][] frequencies = new int[this.height][this.width];
		for(int frame=1; frame<= this.numFrames; frame++) {
			Byte[][] yFrame = this.originalVideos.get(refView).readYFrame();
			Byte[][] cbFrame = this.originalVideos.get(refView).readCFrame();
			Byte[][] crFrame = this.originalVideos.get(refView).readCFrame();
			if(frame == refFrame) {
				for(AreaRef area : areas) {
					this.insertAreaRef(area, frequencies);
				}
				this.modifyCRefFrame(crFrame, frequencies, 0, 255);
				output.writeYFrame(yFrame);
				output.writeCFrame(cbFrame);
				output.writeCFrame(crFrame);
				output.closeAndSave();
			}

		}
	}

	private void insertAreaRef(AreaRef area, int[][] freq) {
		this.adjustArea(area);
		for(int i=area.getxMin(); i<area.getxMax(); i++) {
			for(int j=area.getyMin(); j<area.getyMax(); j++) {
				freq[j][i] += 1;
			}
		}
	}

	private void modifyCRefFrame(Byte[][] cFrame, int[][] frequencies) {
		for(int i=0; i<this.height/2; i++) {
			for(int j=0; j<this.width/2; j++) {
				int media = (frequencies[i*2][j*2] + frequencies[i*2+1][j*2] + frequencies[i*2][j*2+1] +
						frequencies[i*2+1][j*2+1]) / 4;
				byte b = (byte) (media / 4);
				cFrame[i][j] = new Byte(b);
			}
		}
	}

    private void modifyCRefFrame(Byte[][] cFrame, int[][] frequencies, int min, int max) {
        int maxFreq = this.getMaxFrequency(frequencies);
		for(int i=0; i<this.height/2; i++) {
			for(int j=0; j<this.width/2; j++) {
				int media = (frequencies[i*2][j*2] + frequencies[i*2+1][j*2] + frequencies[i*2][j*2+1] +
						frequencies[i*2+1][j*2+1]) / 4;
				int normalizedValue = (255*media) / maxFreq;
				cFrame[i][j] = (byte) normalizedValue;
			}
		}
    }

    private int getMaxFrequency(int[][] frequencies) {
		int maxValue = 0;
        for(int i=0; i<this.height; i++) {
			for(int j=0; j<this.width; j++) {
				if(maxValue < frequencies[i][j]) {
					maxValue = frequencies[i][j];
				}
			}
		}
		return maxValue;
    }

	
}
