/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.views.MainView;

/**
 *
 * @author felsamps
 */
public class MainController extends ApplicationController {
	private MainView view;
	
	public MainController() {
		view = new MainView(this);
		this.showView();
	}

	private void showView() {
		view.setVisible(true);
	}
	
}
