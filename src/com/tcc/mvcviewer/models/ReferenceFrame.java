package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class ReferenceFrame extends Frame {
	//FIXME improve Reference Frame members
	private List<AreaRef> entries;

	public ReferenceFrame(Integer width, Integer height, Integer poc, Integer view) {
		super(width, height, poc, view);
		this.entries = new ArrayList<AreaRef>();
	}

	void insertRefRect(AreaRef aThis) {
		this.getEntries().add(aThis);
	}

	public List<AreaRef> getEntries() {
		return entries;
	}

	public void setEntries(List<AreaRef> entries) {
		this.entries = entries;
	}


}
