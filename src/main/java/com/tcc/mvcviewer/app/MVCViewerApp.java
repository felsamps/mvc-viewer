/*
 * MVCViewerApp.java
 */

package com.tcc.mvcviewer.app;

import com.tcc.mvcviewer.controllers.MainController;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class MVCViewerApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
	protected void startup() {
		new MainController();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
	 * @param root
	 */
    @Override
	protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MVCViewerApp
     */
    public static MVCViewerApp getApplication() {
        return (MVCViewerApp) Application.getInstance(MVCViewerApp.class);
    }

    /**
     * Main method launching the application.
	 * @param args
	 */
    public static void main(String[] args) {
        launch(MVCViewerApp.class, args);
    }
}
