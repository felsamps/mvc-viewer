package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class ReferenceFrame extends Frame {
	//FIXME improve Reference Frame members
	List<RefRect> entries;

	public ReferenceFrame(Integer width, Integer height, Integer poc, Integer view) {
		super(width, height, poc, view);
		this.entries = new ArrayList<RefRect>();
	}

	void insertRefRect(RefRect aThis) {
		this.entries.add(aThis);
	}
}
