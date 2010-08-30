package com.tcc.mvcviewer.models;

import com.tcc.mvcviewer.models.files.InputModesFile;
import com.tcc.mvcviewer.models.files.InputVideoFile;
import com.tcc.mvcviewer.models.files.OutputVideoFile;
import com.tcc.mvcviewer.utils.Resolution;
import java.io.FileNotFoundException;

/**
 *
 * @author felsamps
 */
public class OutputModesGenerator {
	private static int SKIP = 0;
	private static int INTER = 1;
	private static int INTRA = 2;

	InputModesFile modesFile;
	InputVideoFile inVideo;
	OutputVideoFile outVideo;
	Resolution r;

	int[][] modes;
	boolean[][] grid;
	Byte[][] y, cb, cr;


	public OutputModesGenerator(String modesfile, String videoFile, String outFile, Resolution r) {
		try {
			this.modesFile = new InputModesFile(modesfile);
			this.outVideo = new OutputVideoFile(outFile, r.getW(), r.getH());
			this.inVideo = new InputVideoFile(videoFile, r.getW(), r.getH());	
		}
		catch( FileNotFoundException ex ) {
			ex.printStackTrace();
		}
		this.r = r;
		this.modes = new int[r.getW()/16][r.getH()/16];
		this.grid = new boolean[r.getW()][r.getH()];
	}

	public void parseModesFile(Integer targetFrame) {
		for (int i = 1; i < targetFrame; i++) {
			this.inVideo.readYFrame();
			this.inVideo.readCFrame();
			this.inVideo.readCFrame();
		}
		this.y = this.inVideo.readYFrame();
		this.cb = this.inVideo.readCFrame();
		this.cr = this.inVideo.readCFrame();

		for(int y=0; y<r.getH(); y+=16) {
			for(int x=0; x<r.getW(); x+=16) {
				int[] mode = modesFile.getNextMbPartition();
				this.printMode(mode, x, y);
			}
		}

		this.insertGridAndColors();
		this.outVideo.writeYFrame(this.y);
		this.outVideo.writeCFrame(this.cb);
		this.outVideo.writeCFrame(this.cr);

		this.outVideo.closeAndSave();
	}

	private void printMode(int[] mode, int x, int y) {
		this.modes[x/16][y/16] = (mode[0] == InputModesFile.I16MB || mode[0] == InputModesFile.I4MB) ?
			INTRA : ( mode[0] == InputModesFile.SKIP ) ?
				SKIP :
				INTER;

		//PRINT UPPER AND LEFTMOST BORDER
		for(int d=x; d<x+16; d++) {
			grid[d][y] = true;
		}
		for(int d=y; d<y+16; d++) {
			grid[x][d] = true;
		}
		//PRINT THE HOR AND VER MIDDLE BORDER FOR THE PARTITION
		printPMB(mode[0], x, y);
		if(mode[0] == InputModesFile.P8x8 || mode[0] == InputModesFile.I4MB) {
			for(int i=1; i<5; i++) {
				int pos = i-1;
				if( mode[0] == InputModesFile.I4MB ) {
					printSMB(mode[0], x+(pos%2)*8, y+(pos/2)*8);
				}
				else {
					
					printSMB(mode[i], x+(pos%2)*8, y+(pos/2)*8);
				}
			}
		}
	}

	private void printPMB(int mode, int x, int y) {
		if( mode == InputModesFile.P16x8 || mode == InputModesFile.P8x8 || mode == InputModesFile.I4MB) {
			for(int i=x; i<x+16; i++) {
				grid[i][y+8] = true;
			}
		}
		if( mode == InputModesFile.P8x16 || mode == InputModesFile.P8x8 || mode == InputModesFile.I4MB) {
			for(int i=y; i<y+16; i++) {
				grid[x+8][i] = true;
			}
		}
		
	}

	private void printSMB(int mode, int x, int y) {
		if( mode == InputModesFile.SMB8x4 || mode == InputModesFile.SMB4x4 || mode == InputModesFile.I4MB ) {
			for(int i=x; i<x+8; i++) {
				grid[i][y+4] = true;
			}
		}
		if( mode == InputModesFile.SMB4x8 || mode == InputModesFile.SMB4x4 || mode == InputModesFile.I4MB ) {
			for(int i=y; i<y+8; i++) {
				grid[x+4][i] = true;
			}
		}
	}

	private void insertGridAndColors() {
		for(int x=0; x<this.r.getW(); x++) {
			for(int y=0; y<this.r.getH(); y++) {
				int mbX = x/16;
				int mbY = y/16;
				if( grid[x][y] ) {
					this.y[y][x] = (byte) 255;
				}
				else {
					if( modes[mbX][mbY] == SKIP ) {
						//this.cb[y/2][x/2] = (byte) (this.cb[y/2][x/2].intValue() + ((255 - this.cb[y/2][x/2].intValue()) * 0.25));
						this.cb[y/2][x/2] = (byte) 120;
						this.cr[y/2][x/2] = (byte) 120;
					}
					if( modes[mbX][mbY] == INTRA ) {
						this.cb[y/2][x/2] = (byte) 150;

					}
					if( modes[mbX][mbY] == INTER ) {
						this.cr[y/2][x/2] = (byte) 150;
					}
				}
			}
		}
	}

		

}
