package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.views.ModeView;
import javax.swing.JFileChooser;

/**
 *
 * @author felsamps
 */
public class ModeController {
	private static ModeController instance;
	private ModeView view;

	public static ModeController getInstance() {
		if(instance == null) {
			instance = new ModeController();
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	public ModeController() {
		view = new ModeView(this);
		this.showView();
	}

	private void showView() {
		view.setVisible(true);
	}

	public void handleOpenModeFileButton() {
		if( this.view.showFileChooser() == JFileChooser.APPROVE_OPTION ) {
			String modeFile = this.view.getSelectedFile();
			this.view.showModeFileName(modeFile);
		}
	}

	public void handleOpenVideoFileButton() {
		if( this.view.showFileChooser() == JFileChooser.APPROVE_OPTION ) {
			String videoFile = this.view.getSelectedFile();
			this.view.showVideoFileName(videoFile);
		}
	}
}
