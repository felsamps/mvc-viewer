package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.models.AreaRef;
import com.tcc.mvcviewer.models.CurrentFrame;
import com.tcc.mvcviewer.models.MbDataAccess;
import com.tcc.mvcviewer.models.Video;
import com.tcc.mvcviewer.models.files.OutputFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felsamps
 */
class OutputCacheGenerator {
	private String fileName;
	private Video video;
	private Integer refView;
	private Integer refFrame;
	private Integer currView;
	private Integer currFrame;
	private BufferedWriter output;

	public OutputCacheGenerator(String fileName, Video video, Integer refView, Integer refFrame, Integer currView, Integer currFrame) {
		this.fileName = fileName;
		this.video = video;
		this.refView = refView;
		this.refFrame = refFrame;
		this.currView = currView;
		this.currFrame = currFrame;
		try {
			output = new BufferedWriter(new FileWriter(new File(fileName)));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	void generate() {
		CurrentFrame curr = video.getCurrentFrame(currView, currFrame);
		for(int i=0; i<curr.getHeight()/16; i++) {
			for (int j = 0; j < curr.getWidth()/16; j++) {
				MbDataAccess mb = curr.getMb(i, j);
				this.write(mb);
			}
		}
		try {
			output.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void write(MbDataAccess mb) {
		try {
			output.write(mb.getMbX().toString().toCharArray());
			output.newLine();
			output.write(mb.getMbY().toString().toCharArray());
			output.newLine();
			output.write(new Integer(mb.getAreaRectList().size()).toString().toCharArray());
			output.newLine();
			for(AreaRef area : mb.getAreaRectList()) {
				Integer h = area.getyMax()-area.getyMin();
				Integer w = area.getxMax()-area.getxMin();
				output.write(area.getxMin().toString().toCharArray());
				output.newLine();
				output.write(area.getyMin().toString().toCharArray());
				output.newLine();
				output.write(w.toString().toCharArray());
				output.newLine();
				output.write(h.toString().toCharArray());
				output.newLine();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
