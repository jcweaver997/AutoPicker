package com.jcweaver.autopicker;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowL implements WindowListener {

	@Override
	public void windowActivated(WindowEvent arg0) {
		if(Display.GetState()==Display.CurrentState.Restart){
			Display.SetState(Display.CurrentState.Ready);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
