/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcc.mvcviewer.views;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author felsamps
 */
public class FileTableModel extends AbstractTableModel {
	String[] columns = {"view", "file name"};
	List<String> rows;

	public FileTableModel() {
		this.rows = new ArrayList<String> ();
	}

	public int getRowCount() {
		return rows.size();
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return "View " + new Integer(rowIndex).toString();
		}
		else {  //columnIndex == 1
			return rows.get(rowIndex);
		}
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	void addRow(String path) {
		rows.add(path);
	}
	

}
