package com.tcc.mvcviewer.models.files;

import java.io.FileNotFoundException;

/**
 *
 * @author felsamps
 */
public class InputModesFile extends InputFile {
	public static int SKIP		= 0;
	public static int P16x16	= 1;
	public static int P16x8		= 2;
	public static int P8x16		= 3;
	public static int SMB8x8	= 4;
	public static int SMB8x4	= 5;
	public static int SMB4x8	= 6;
	public static int SMB4x4	= 7;
	public static int P8x8		= 8;
	public static int I4MB		= 9;
	public static int I16MB		= 10;


	public InputModesFile(String filePath) throws FileNotFoundException {
		super(filePath);
	}

	public int[] getNextMbPartition() {
		int partition = this.readInt();
		if( partition == InputModesFile.P8x8 ) {
			int[] returnable = new int[5];
			returnable[0] = partition;
			for (int i = 1; i < returnable.length; i++) {
				returnable[i] = this.readInt();
			}
			return returnable;
		}
		else {
			int[] returnable = new int[1];
			returnable[0] = partition;
			return returnable;
		}
	}


}

