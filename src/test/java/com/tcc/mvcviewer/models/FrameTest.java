/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.models;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author felsamps
 */
public class FrameTest {
	CurrentFrame curr;
	ReferenceFrame ref;
	
    public FrameTest() {
    }

	@Before
	public void before() {
		curr = new CurrentFrame(640, 480, 1, 1);
		ref = new ReferenceFrame(640, 480, 1, 1);
	}

	@Test
	public void numOfMbTest() {
		assertEquals(new Integer(30*40), curr.getMbNumber());
	}

	@Test
	public void getMbDataOutOfBounds() {
		assertNotNull(curr.getMb(29, 29));
		assertNull(curr.getMb(30, 40));
	}

}