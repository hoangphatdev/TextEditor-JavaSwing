package com.hplat.controller;
import com.hplat.view.Dashboard;

import java.awt.Font;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChangeFontSizeListener implements ChangeListener{
	Dashboard dashboard;
	public ChangeFontSizeListener(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		dashboard.textArea.setFont(new Font(dashboard.textArea.getFont().getFamily(), Font.PLAIN, (int) dashboard.spinner.getValue()));
		
		
	}

}
