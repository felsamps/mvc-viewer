package com.tcc.mvcviewer.models.files;

import com.tcc.mvcviewer.models.UserMbChoice;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class DoFileReader extends FileReader {
	private String cfgName;
	private int mode;
	private Integer targetView;
	private Integer targetFrame;
	private int numOfMbs;
	private List<UserMbChoice> listMb;
	private String outputVideo;
	private String outputLogFile;

	public DoFileReader(String fileName) throws FileNotFoundException {
		super(fileName);
		listMb = new ArrayList<UserMbChoice>();
	}

	@Override
	public void read() {
		while( !this.finished() ) {
			String[] line = this.parseLine();
			String command = line[0];
			if(command.equals("CFG_NAME")) {
				setCfgName(line[2]);
			}
			if(command.equals("MODE")) {
				setMode(Integer.parseInt(line[2]));
			}
			if(command.equals("TARGET_VIEW")) {
				setTargetView((Integer) Integer.parseInt(line[2]));
			}
			if(command.equals("TARGET_FRAME")) {
				setTargetFrame((Integer) Integer.parseInt(line[2]));
			}
			if(command.equals("NUM_OF_MB")) {
				setNumOfMbs(Integer.parseInt(line[2]));
			}
			if(command.equals("INIT_MB")) {
				while(numOfMbs-- == 0) {
					String[] mb = this.parseLine();
					getListMb().add(new UserMbChoice(mb));
				}
			}
			if(command.equals("OUTPUT_VIDEO")) {
				setOutputVideo(line[2]);
			}
			if(command.equals("OUTPUT_LOGFILE")) {
				setOutputLogFile(line[2]);
			}
		}
	}
	
	public String getCfgName() {
		return cfgName;
	}

	public void setCfgName(String cfgName) {
		this.cfgName = cfgName;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Integer getTargetView() {
		return targetView;
	}

	public void setTargetView(Integer targetView) {
		this.targetView = targetView;
	}

	public Integer getTargetFrame() {
		return targetFrame;
	}

	public void setTargetFrame(Integer targetFrame) {
		this.targetFrame = targetFrame;
	}

	public int getNumOfMbs() {
		return numOfMbs;
	}

	public void setNumOfMbs(int numOfMbs) {
		this.numOfMbs = numOfMbs;
	}

	public List<UserMbChoice> getListMb() {
		return listMb;
	}

	public void setListMb(List<UserMbChoice> listMb) {
		this.listMb = listMb;
	}

	public String getOutputVideo() {
		return outputVideo;
	}

	public void setOutputVideo(String outputVideo) {
		this.outputVideo = outputVideo;
	}

	public String getOutputLogFile() {
		return outputLogFile;
	}

	public void setOutputLogFile(String outputLogFile) {
		this.outputLogFile = outputLogFile;
	}
	
}
