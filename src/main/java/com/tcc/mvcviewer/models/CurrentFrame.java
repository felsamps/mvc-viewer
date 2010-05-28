package com.tcc.mvcviewer.models;

/**
 *
 * @author felsamps
 */
public class CurrentFrame extends Frame {
	private MbDataAccess[][] mbMatrix;
	private int mbHeight,mbWidth;

	public CurrentFrame(Integer width, Integer height, Integer poc, Integer view) {
		super(width, height, poc, view);
		mbHeight = height/16;
		mbWidth = width/16;
		mbMatrix = new MbDataAccess[mbHeight][mbWidth];
		initMbMatix();
	}

	private void initMbMatix() {
		for (int i = 0; i < mbHeight; i++) {
			for (int j = 0; j < mbWidth; j++) {
				mbMatrix[i][j] = new MbDataAccess(i,j);
			}
		}
	}

	public Integer getMbNumber() {
		return new Integer(getHeight()/16 * getWidth()/16);
	}

	public MbDataAccess getMb(int x,int y) {
		MbDataAccess returnable = null;
		try {
			returnable = mbMatrix[x][y];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			returnable = null;
		}
		finally {
			return returnable;
		}
	}

	void insertTraceEntry(TraceEntry entry) {
		MbDataAccess mb = getMb(entry.getxMb(), entry.getyMb());
		RefRect refRect = entry.getRefRect();
		mb.addRefRect(entry.getMbType(), refRect);
	}
	
}
