package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


/**
 *
 * @author felsamps
 */
public class InputFileTest {
	private InputTraceFile traceFile;
	private InputVideoFile videoFile;

    public InputFileTest() {
    }

	@Before
	public void before() {
		traceFile = new InputTraceFile("/home/felsamps/Tcc/mvcviewer-tcc/test_data.dat");
		//this sequence has 250 frames
		videoFile = new InputVideoFile("/home/felsamps/Tcc/mvcviewer-tcc/ballroom_0.yuv", 640, 480);
	}

	@Test
	public void refRectTest() {
		TraceEntry entry = new TraceEntry(traceFile);
		ReferenceFrame ref = new ReferenceFrame(640, 480, 1, 0);
		RefRect rect = entry.getRefRect();
		rect.setFrame(ref);
		RefRect rectTest = new RefRect(-24, -24, 96, 96);
		rectTest.setFrame(ref);
		assertEquals(rectTest, rect);
	}

	@Test
	public void twoRefRectReadingTest() {
		TraceEntry entry = new TraceEntry(traceFile);
		ReferenceFrame ref = new ReferenceFrame(640, 480, 1, 0);
		RefRect rect = entry.getRefRect();
		rect.setFrame(ref);
		TraceEntry entry2 = new TraceEntry(traceFile);
		RefRect rect2 = entry2.getRefRect();
		rect2.setFrame(ref);
		assertEquals(rect2, rect);
	}

	@Test
	public void getNumOfFramesTest() {
		assertEquals(new Long(250), videoFile.getNumOfFrames());
	}

	@Test
	public void stopTest() {
		before();
		List<TraceEntry> entries = new ArrayList<TraceEntry> ();
		while(! traceFile.finished()) {
			entries.add(new TraceEntry(traceFile));
		}
		assertEquals(3, entries.size());
	}
}