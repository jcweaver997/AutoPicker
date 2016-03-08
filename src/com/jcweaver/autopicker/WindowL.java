package com.jcweaver.autopicker;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowL implements WindowListener {

	public void windowActivated(WindowEvent arg0) {
		if(Display.getState()==Display.CurrentState.Restart){
			Display.setState(Display.CurrentState.Ready);
		}

	}

	public void windowClosed(WindowEvent arg0) {}

	public void windowClosing(WindowEvent arg0) {}

	public void windowDeactivated(WindowEvent arg0) {}

	public void windowDeiconified(WindowEvent arg0) {}

	public void windowIconified(WindowEvent arg0) {}

	public void windowOpened(WindowEvent arg0) {}

}
