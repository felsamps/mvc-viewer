package com.tcc.mvcviewer.models;

import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felsamps
 */
public class VideoFileTest {
	private InputVideoFile video;
    public VideoFileTest() {
    }

	@Before
	public void before() {
		try {
			video = new InputVideoFile("/home/felsamps/Tcc/jmvc-tcc/bin/ballroom_0.yuv", 640, 480);
		} catch (FileNotFoundException ex) {
			//DO NOTHING
		}
	}

	@Test
	public void readShortTest() {
		Byte[][] yFrame, cbFrame, crFrame;
		assertEquals(new Long(250),video.getNumOfFrames());
		yFrame = video.readYFrame();
		cbFrame = video.readCFrame();
		crFrame = video.readCFrame();
		assertEquals(480, yFrame.length);
		assertEquals(cbFrame.length, crFrame.length);
		assertEquals(240, cbFrame.length);
	}

}