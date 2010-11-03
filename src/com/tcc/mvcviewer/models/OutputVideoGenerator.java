package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.OutputVideoFile;
import com.tcc.mvcviewer.models.files.InputVideoFile;
import com.tcc.mvcviewer.stats.LogFile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class OutputVideoGenerator {
	private static int searchRange = 96;

	private List<InputVideoFile> originalVideos;
	private List<OutputVideoFile> modifiedVideos;
	private Integer numViews, numFrames;
	private List<List<AreaRef>> listAreas;
	private Integer height, width;
	private boolean grid;
	private boolean searchRangePlot;

	public OutputVideoGenerator(List<List<AreaRef>> areas, List<String> videoPaths,
			List<String> newVideoPaths, Integer numViews, Integer numFrames,
			Integer width, Integer height, boolean grid, boolean searchRangePlot) {
		this.grid = grid;
		this.width = width;
		this.height = height;
		this.listAreas = areas;
		this.numViews = numViews;
		this.numFrames = numFrames;
		this.searchRangePlot = searchRangePlot;
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
		for (String path : videoPaths) {
			try {
				originalVideos.add(new InputVideoFile(path, this.width, this.height));
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void initModifiedVideos(List<String> paths) {
		modifiedVideos = new ArrayList<OutputVideoFile>();
		for (String path : paths) {
			modifiedVideos.add(new OutputVideoFile(path, this.width, this.height));
		}
	}

	private void adjustArea(AreaRef area) {
		if (area.getxMin() < 0) {
			area.setxMin(0);
		}
		if (area.getyMin() < 0) {
			area.setyMin(0);
		}
		if (area.getxMax() >= this.width) {
			area.setxMax(this.width - 1);
		}
		if (area.getyMax() >= this.height) {
			area.setyMax(this.height - 1);
		}
	}

	private List<List<AreaRef>> filterAreaRefList(Integer view, Integer poc) {
		List<List<AreaRef>> returnable = new ArrayList<List<AreaRef>>();
		List<AreaRef> mbAreas = null;
		for (List<AreaRef> areas : listAreas) {
			mbAreas = new ArrayList<AreaRef>();
			for (AreaRef area : areas) {
				if (area.getFrame().getView() == view && area.getFrame().getPoc() == poc) {
					mbAreas.add(area);
				}
			}
			if (!mbAreas.isEmpty()) {
				returnable.add(mbAreas);
			}
		}
		return returnable;
	}

	public void generate() {
		for (int view = 0; view < this.numViews; view++) {
			int[][] ibFreq = new int[this.height][this.width];
			int[][] irFreq = new int[this.height][this.width];
			List<MbDataAccess> listMbs = new ArrayList<MbDataAccess>();
			for (int frame = 1; frame <= this.numFrames; frame++) {
				List<List<AreaRef>> list = this.filterAreaRefList(view, frame);
				//TODO refactoring of it!
				Byte[][] yFrame = this.getOriginalLumaFrame(view);
				Byte[][] cbFrame = this.getOriginalChromaFrame(view);
				Byte[][] crFrame = this.getOriginalChromaFrame(view);
				for (List<AreaRef> areas : list) {
					listMbs.add(areas.get(0).getMbAtual());
					boolean[][] bFreq = new boolean[this.height][this.width];
					boolean[][] rFreq = new boolean[this.height][this.width];
					for (AreaRef area : areas) {
						if (area.isRefinement()) {
							this.insertArea(area, bFreq);
						} else {
							this.insertArea(area, rFreq);
						}
					}
					this.refreshIFreq(bFreq, ibFreq);
					this.refreshIFreq(rFreq, irFreq);
				}
				if (!list.isEmpty()) {
					this.modifyCFrame(cbFrame, ibFreq, 255);
					this.modifyCFrame(crFrame, irFreq, 255);
				}
				if (this.grid) {
					this.insertGrid(yFrame);
				}
				if (this.searchRangePlot) {
					for(MbDataAccess mb : listMbs) {
						this.insertSearchRange(mb, yFrame);
					}
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
		for (int frame = 1; frame <= this.numFrames; frame++) {
			Byte[][] yFrame = this.getOriginalLumaFrame(refView);
			Byte[][] cbFrame = this.getOriginalChromaFrame(refView);
			Byte[][] crFrame = this.getOriginalChromaFrame(refView);
			if (frame == refFrame) {
				for (AreaRef area : listAreas.get(0)) {
					this.insertAreaRef(area, frequencies);
				}
				this.modifyCFrame(crFrame, frequencies);
				if (this.grid) {
					this.insertGrid(yFrame);
				}
				output.writeYFrame(yFrame);
				output.writeCFrame(cbFrame);
				output.writeCFrame(crFrame);
				output.closeAndSave();
			}
		}
		LogFile.insertSampleAccess(frequencies);
	}

	public void generateCurrentMBTracing(Integer refView, Integer refFrame, Integer minX, Integer minY, Integer maxX, Integer maxY) {
		List<MbDataAccess> mbList = new ArrayList<MbDataAccess>();
		for (AreaRef area : listAreas.get(0)) {
			if (area.intersecs(minX, minY, maxX, maxY)) {
				if (!mbList.contains(area.getMbAtual())) {
					mbList.add(area.getMbAtual());
				}
			}
		}
		for (int view = 0; view < this.numViews; view++) {
			for (int frame = 1; frame <= this.numFrames; frame++) {
				Byte[][] yFrame = this.getOriginalLumaFrame(view);
				Byte[][] cbFrame = this.getOriginalChromaFrame(view);
				Byte[][] crFrame = this.getOriginalChromaFrame(view);
				List<MbDataAccess> list = this.filterMbList(mbList, view, frame);
				for (MbDataAccess mb : list) {
					this.markMbCFrame(cbFrame, mb);
				}
				if (this.grid) {
					this.insertGrid(yFrame);
				}
				this.modifiedVideos.get(view).writeYFrame(yFrame);
				this.modifiedVideos.get(view).writeCFrame(cbFrame);
				this.modifiedVideos.get(view).writeCFrame(crFrame);
			}
			this.modifiedVideos.get(view).closeAndSave();
		}
	}

	private List<MbDataAccess> filterMbList(List<MbDataAccess> mbList, int view, int frame) {
		List<MbDataAccess> returnable = new ArrayList<MbDataAccess>();
		for (MbDataAccess mb : mbList) {
			if (mb.getFrame().getView().equals(view) && mb.getFrame().getPoc().equals(frame)) {
				returnable.add(mb);
			}
		}
		return returnable;
	}

	private void markMbCFrame(Byte[][] crFrame, MbDataAccess mb) {
		int offsetX = mb.getMbX() * 4;
		int offsetY = mb.getMbY() * 4;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				crFrame[x + offsetX][y + offsetY] = new Byte((byte) 200);
			}
		}
	}

	private void refreshIFreq(boolean[][] b, int[][] i) {
		for (int x = 0; x < this.height; x++) {
			for (int y = 0; y < this.width; y++) {
				i[x][y] += (b[x][y]) ? 1 : 0;
			}
		}
	}

	private void insertAreaRef(AreaRef area, int[][] freq) {
		this.adjustArea(area);
		for (int i = area.getxMin(); i < area.getxMax(); i++) {
			for (int j = area.getyMin(); j < area.getyMax(); j++) {
				freq[j][i] += 1;
			}
		}
	}

	private void insertArea(AreaRef area, boolean[][] frequencies) {
		this.adjustArea(area);
		for (int i = area.getxMin(); i < area.getxMax(); i++) {
			for (int j = area.getyMin(); j < area.getyMax(); j++) {
				frequencies[j][i] = true;
			}
		}
	}

	private int getAverageCr(Byte[][] cFrame) {
		int acum = 0;
		for (int i = 0; i < this.height / 2; i++) {
			for (int j = 0; j < this.width / 2; j++) {
				acum += cFrame[i][j];
			}
		}
		return (acum / (height * width));
	}

	private void modifyCFrame(Byte[][] cFrame, int[][] frequencies, int max) {
		int maxFreq = this.getMaxFrequency(frequencies);
		int crMedia = 127;
		for (int i = 0; i < this.height / 2; i++) {
			for (int j = 0; j < this.width / 2; j++) {
				int media = (frequencies[i * 2][j * 2] + frequencies[i * 2 + 1][j * 2] + frequencies[i * 2][j * 2 + 1]
						+ frequencies[i * 2 + 1][j * 2 + 1]) / 4;
				if (media > 0) {
					int normalizedValue = media * ((max - crMedia) / maxFreq) + crMedia;
					cFrame[i][j] = (byte) normalizedValue;
				}
			}
		}
	}

	private void modifyCFrame(Byte[][] cFrame, int[][] frequencies) {
		int maxFreq = this.getMaxFrequency(frequencies);
		for (int i = 0; i < this.height / 2; i++) {
			for (int j = 0; j < this.width / 2; j++) {
				int media = (frequencies[i * 2][j * 2] + frequencies[i * 2 + 1][j * 2] + frequencies[i * 2][j * 2 + 1]
						+ frequencies[i * 2 + 1][j * 2 + 1]) / 4;
				if (media > 0) {
					int normalizedValue = (media * 255) / maxFreq;
					cFrame[i][j] = (byte) normalizedValue;
				}
			}
		}
	}

	private void modifyCFrame(Byte[][] cFrame, boolean[][] frequencies) {
		for (int i = 0; i < this.height / 2; i++) {
			for (int j = 0; j < this.width / 2; j++) {
				if (frequencies[i * 2][j * 2]) {
					cFrame[i][j] = (byte) 200;
				}
			}
		}
	}

	private int getMaxFrequency(int[][] frequencies) {
		int maxValue = 0;
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (maxValue < frequencies[i][j]) {
					maxValue = frequencies[i][j];
				}
			}
		}
		return maxValue;
	}

	private void insertGrid(Byte[][] yFrame) {
		//horizontal
		for (int i = 15; i < this.height; i += 16) {
			for (int j = 0; j < this.width; j++) {
				yFrame[i][j] = (byte) 255;
			}
		}
		//vertical
		for (int i = 15; i < this.width; i += 16) {
			for (int j = 0; j < this.height; j++) {
				yFrame[j][i] = (byte) 255;
			}
		}
	}

	private void insertSearchRange(MbDataAccess mb, Byte[][] yFrame) {
		int x = mb.getMbX()*16;
		int y = mb.getMbY()*16;
		int minX = (x-searchRange < 0) ? 0 : x-searchRange;
		int minY = (y-searchRange < 0) ? 0 : y-searchRange;
		int maxX = (x+searchRange+16 >= width) ? width-1 : x+searchRange+16;
		int maxY = (y+searchRange+16 >= width) ? width-1 : y+searchRange+16;

		System.out.println(x);
		System.out.println(y);
		System.out.println(minX);
		System.out.println(minY);
		System.out.println(maxX);
		System.out.println(maxY);
				
		//horizontel lines
		for(int i=minX; i<maxX; i++) {
			yFrame[minY][i] = (byte) 255;
			yFrame[maxY][i] = (byte) 255;
		}

		//vertical lines
		for(int i=minY; i<maxY; i++) {
			yFrame[i][minX] = (byte) 255;
			yFrame[i][maxX] = (byte) 255;
		}
	}
}
