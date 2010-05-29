/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.models.CfgReader;
import com.tcc.mvcviewer.models.TraceFileParser;
import com.tcc.mvcviewer.views.MainView;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author felsamps
 */
public class MainController extends ApplicationController {
	private MainView view;
	private CfgReader reader;
	private TraceFileParser parser;
	
	public MainController() {
		view = new MainView(this);
		this.showView();
	}

	private void showView() {
		view.setVisible(true);
	}

	public void handleAbrirCfgButton() {
		int returnVal = view.showFileChooserDialog();
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = view.getSelectedFile();
			reader = new CfgReader(file);
			reader.read();
		}
	}

	public void handleStartParsingButton() {
		if(reader != null) {
			parser = new TraceFileParser(reader);
			parser.parser();
		}
	}
	
}
