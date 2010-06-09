package com.tcc.mvcviewer.models;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felsamps
 */
public class VideoFileTest {
	private InputVideoFile video;
	private OutputVideoFile videoOut;
	Byte[][] yFrame, cbFrame, crFrame;
			
    public VideoFileTest() {
    }

	@Before
	public void before() {
		try {
			video = new InputVideoFile("/home/felsamps/Tcc/jmvc-tcc/bin/ballroom_0.yuv", 640, 480);
			videoOut = new OutputVideoFile("/home/felsamps/Tcc/mvc-viewer/data/ballroom_0_frame0.yuv", 640, 480);
		} catch (FileNotFoundException ex) {
			//DO NOTHING
		}
	}

//	@Test
	public void readWriteFrameTest() {
		assertEquals(new Long(250),video.getNumOfFrames());
		yFrame = video.readYFrame();
		cbFrame = video.readCFrame();
		crFrame = video.readCFrame();
		assertEquals(480, yFrame.length);
		assertEquals(cbFrame.length, crFrame.length);
		assertEquals(240, cbFrame.length);

		videoOut.writeYFrame(yFrame);
		videoOut.writeCFrame(cbFrame);
		videoOut.writeCFrame(crFrame);
	}

	@Test
	public void readWriteChangesTest() {
		yFrame = video.readYFrame();
		cbFrame = video.readCFrame();
		crFrame = video.readCFrame();
		this.changeFrameValue(yFrame);
		videoOut.writeYFrame(yFrame);
		videoOut.writeCFrame(cbFrame);
		videoOut.writeCFrame(crFrame);
	}

	@After
	public void after() {
		videoOut.closeAndSave();
	}

	private void changeFrameValue(Byte[][] crFrame) {
		Byte b = new Byte((byte) 100);
		for(int i=100; i<200; i++) {
			for(int j=100; j<200; j++) {
				crFrame[i][j] = new Byte((byte)(crFrame[i][j] + b));
			}
		}
	}

}