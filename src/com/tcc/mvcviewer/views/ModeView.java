package com.tcc.mvcviewer.views;

import com.tcc.mvcviewer.controllers.ModeController;
import com.tcc.mvcviewer.utils.Resolution;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;

/**
 *
 * @author felsamps
 */
public class ModeView extends javax.swing.JFrame {
	ModeController controller;
	JFileChooser fileChooser;

    /** Creates new form ModeView */
    public ModeView(ModeController controller) {
		this.controller = controller;
		this.fileChooser = new JFileChooser();
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        resolutionComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        modeFileTextField = new javax.swing.JTextField();
        videoFileTextField = new javax.swing.JTextField();
        openModeFileButton = new javax.swing.JButton();
        openVideoFileButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        outputFileTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numberOfFramesSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        closeButton.setText("Close");
        closeButton.setName("closeButton"); // NOI18N

        resolutionComboBox.setModel(new DefaultComboBoxModel());
        resolutionComboBox.setName("resolutionComboBox"); // NOI18N

        jLabel1.setText("Resolution");
        jLabel1.setName("jLabel1"); // NOI18N

        modeFileTextField.setText("Modes File...");
        modeFileTextField.setName("modeFileTextField"); // NOI18N

        videoFileTextField.setText("Video File...");
        videoFileTextField.setName("videoFileTextField"); // NOI18N

        openModeFileButton.setText("Open");
        openModeFileButton.setName("openModeFileButton"); // NOI18N
        openModeFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openModeFileButtonActionPerformed(evt);
            }
        });

        openVideoFileButton.setText("Open");
        openVideoFileButton.setName("openVideoFileButton"); // NOI18N
        openVideoFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openVideoFileButtonActionPerformed(evt);
            }
        });

        generateButton.setText("Generate");
        generateButton.setName("generateButton"); // NOI18N
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        outputFileTextField.setText("/home/felsamps/Tcc/mvc-viewer/data/output-files/outModes.yuv");
        outputFileTextField.setName("outputFileTextField"); // NOI18N

        jLabel2.setText("Output File");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Number of Frames");
        jLabel3.setName("jLabel3"); // NOI18N

        numberOfFramesSpinner.setName("numberOfFramesSpinner"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(outputFileTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(resolutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numberOfFramesSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                            .addComponent(videoFileTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(modeFileTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(openVideoFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(openModeFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modeFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openModeFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(videoFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openVideoFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resolutionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfFramesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void openModeFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openModeFileButtonActionPerformed
		this.controller.handleOpenModeFileButton();
	}//GEN-LAST:event_openModeFileButtonActionPerformed

	private void openVideoFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openVideoFileButtonActionPerformed
		this.controller.handleOpenVideoFileButton();
	}//GEN-LAST:event_openVideoFileButtonActionPerformed

	private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
		this.controller.handleGenerateButton();
	}//GEN-LAST:event_generateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField modeFileTextField;
    private javax.swing.JSpinner numberOfFramesSpinner;
    private javax.swing.JButton openModeFileButton;
    private javax.swing.JButton openVideoFileButton;
    private javax.swing.JTextField outputFileTextField;
    private javax.swing.JComboBox resolutionComboBox;
    private javax.swing.JTextField videoFileTextField;
    // End of variables declaration//GEN-END:variables

	public int showFileChooser() {
		return this.fileChooser.showOpenDialog(ModeView.this);
	}

	public String getSelectedFile() {
		return this.fileChooser.getSelectedFile().getAbsolutePath();
	}

	public void showModeFileName(String name) {
		this.modeFileTextField.setText(name);
	}

	public void showVideoFileName(String name) {
		this.videoFileTextField.setText(name);
	}

	public void fillResolutionList(List<Resolution> supportedResolutions) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) this.resolutionComboBox.getModel();
		for( Resolution r : supportedResolutions) {
			model.addElement(r);
		}
	}

	public String getModesFilePath() {
		return modeFileTextField.getText();
	}

	public String getVideoFilePath() {
		return videoFileTextField.getText();
	}

	public String getOutFilePath() {
		return outputFileTextField.getText();
	}

	public Resolution getSelectedResolution() {
		return (Resolution) this.resolutionComboBox.getSelectedItem();
	}

}
