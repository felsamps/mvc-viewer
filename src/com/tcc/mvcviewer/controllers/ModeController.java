package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.views.ModeView;

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
}
