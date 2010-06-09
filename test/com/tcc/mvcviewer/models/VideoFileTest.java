/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.models;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

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
			video = new InputVideoFile("/home/felsamps/Tcc/mvc-viewer/data/bin_gen/binary.dat", 640, 480);
		} catch (FileNotFoundException ex) {
			//DO NOTHING
		}
	}

	@Test
	public void readShortTest() {
//		System.out.println(new Integer(video.readInt()));
//		System.out.println(new Integer(video.readByte()));
//		System.out.println(new Integer(video.readByte()));
	}

}