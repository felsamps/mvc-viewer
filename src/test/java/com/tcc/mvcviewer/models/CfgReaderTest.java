package com.tcc.mvcviewer.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felsamps
 */
public class CfgReaderTest {
	CfgReader reader;

    public CfgReaderTest() {
    }

	@Before
	public void before() {
		reader = new CfgReader("/home/felsamps/Tcc/mvc-viewer/data/test.cfg");
		
	}

	@Test
	public void fooTest() {
		reader.read();
		assertEquals(reader.getGopSize(), new Integer(4));
		assertEquals(reader.getNumViews(), new Integer(2));
		assertEquals(reader.getWidth(), new Integer(640));
		assertEquals(reader.getHeight(), new Integer(480));
	}

}