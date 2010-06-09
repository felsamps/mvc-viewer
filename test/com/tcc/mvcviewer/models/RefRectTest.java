package com.tcc.mvcviewer.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felsamps
 */
public class RefRectTest {
	private RefRect rect1, rect2;
	private ReferenceFrame ref;

    public RefRectTest() {
    }

	@Before
	public void before() {
		ref = new ReferenceFrame(640, 480, 1, 0);
		rect1 = new RefRect(20, 20, 20, 20);
		rect1.setFrame(ref);
		rect2 = new RefRect(20, 20, 20, 20);
		rect2.setFrame(ref);
	}

	@Test
	public void equalsTest() {
		assertEquals(rect1, rect2);
	}

}