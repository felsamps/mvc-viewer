package com.tcc.mvcviewer.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felsamps
 */
public class ReferenceFrame extends Frame {
	private List<AreaRef> entries;
	private List<AreaRef> entriesBiPred;
	private List<CurrentFrame> dependencies;

	public ReferenceFrame(Integer width, Integer height, Integer poc, Integer view) {
		super(width, height, poc, view);
		this.entries = new ArrayList<AreaRef>();
		this.entriesBiPred = new ArrayList<AreaRef>();
		this.dependencies = new ArrayList<CurrentFrame>();
	}

	void insertRefRect(AreaRef aThis) {
		CurrentFrame curr = aThis.getMbAtual().getFrame();
		if( !dependencies.contains(curr) ) {
			getDependencies().add(curr);
		}
		this.getEntries().add(aThis);
		
	}

	void insertRefRectBiPred(AreaRef aThis) {
		this.getEntriesBiPred().add(aThis);
	}

	public List<AreaRef> getEntries() {
		return entries;
	}

	public void setEntries(List<AreaRef> entries) {
		this.entries = entries;
	}

	public List<AreaRef> getEntriesBiPred() {
		return entriesBiPred;
	}

	public void setEntriesBiPred(List<AreaRef> entriesBiPred) {
		this.entriesBiPred = entriesBiPred;
	}

	/**
	 * @return the dependencies
	 */
	public List<CurrentFrame> getDependencies() {
		return dependencies;
	}

	/**
	 * @param dependencies the dependencies to set
	 */
	public void setDependencies(List<CurrentFrame> dependencies) {
		this.dependencies = dependencies;
	}
}
