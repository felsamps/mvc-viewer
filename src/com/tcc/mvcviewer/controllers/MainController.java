package com.tcc.mvcviewer.controllers;

import com.tcc.mvcviewer.models.*;
import com.tcc.mvcviewer.views.*;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author felsamps
 */
public class MainController extends ApplicationController {
	private MainView view;
	private CfgReader reader;
	private TraceFileParser parser;
	private Video video;
	
	public MainController() {
		this.view = new MainView(this);
		this.showView();
	}

	private void showView() {
		this.view.setVisible(true);
	}

	public void handleAbrirCfgButton() {
		int returnVal = this.view.showFileChooserDialog();
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this.view.getSelectedFile();
			try {
				this.reader = new CfgReader(file);
				this.reader.read();
				this.view.showOpenFile(reader);
			}
			catch(FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void handleStartParsingButton() {
		if(this.reader != null) {
			try {
				this.parser = new TraceFileParser(reader);
				this.video = this.parser.parser();
				int i=0;
			}
			catch(IOException ex) {
				JOptionPane.showMessageDialog((Component) null, ex.getLocalizedMessage(),
						"Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void handleAboutButton() {
		this.view.showAboutBox();
	}
}
