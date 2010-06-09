package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class MbMode {
	//static definitions
	public static int MB_TYPE_ARRAY_SIZE	= 12;
	public static int MB16x16				= 1;
	public static int MB16x8				= 2;
	public static int MB8x16				= 3;
	public static int MB8x8					= 4;
	public static int MB8x8_ref0			= 5;
	public static int B8x8					= 8;
	public static int B8x4					= 9;
	public static int B4x8					= 10;
	public static int B4x4					= 11;

	private List<RefRect> list;

	public MbMode() {
		list = new ArrayList<RefRect>();
	}

	public void addRefRect(RefRect ref) {
		list.add(ref);
	}

	//TODO implement the getters, the setters and, the RefRect list analysis

}
