package com.hplat.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;

import com.hplat.view.Dashboard;

public class ChangeTextColorListener implements MouseListener{
	Dashboard dashboard;
	public ChangeTextColorListener(Dashboard db) {
		this.dashboard = db;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		JColorChooser colorChooser = new JColorChooser();
		Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
		dashboard.textArea.setForeground(color);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
