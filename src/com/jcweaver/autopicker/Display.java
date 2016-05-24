package com.jcweaver.autopicker;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class Display {
	private JFrame frame;
	private static ButtonGroup bg;
	private static JLabel status;
	private static CurrentState currentState;
	
	public enum CurrentState{
		SendingFile, Ready, Failed, Restart
	}

	public Display(){
		
		frame = new JFrame("FRC 1296 Auto Chooser");
		frame.setLayout(new GridLayout(15,3)); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.addWindowListener(new WindowL());
		
		bg = new ButtonGroup();
		createGroups();
		
		status = new JLabel();
		status.setFont(new Font("Serif", Font.BOLD, 26));
		status.setText("RESTART ROBOT CODE");
		frame.add(status);
		
		frame.pack();
		frame.setSize(frame.getWidth()+25, frame.getHeight());
		
		setState(CurrentState.Ready);
		
		if(bg.getButtonCount() == 0){
			setStatus("No Auto Code Found");
		}
	}
	
	public static void setState(CurrentState cs){
		
		currentState = cs;
		
		switch(currentState){
		
		case SendingFile:
			
			while(!status.getText().equals("Sending File...")){
				status.setText("Sending File...");
			}
			
			for(Enumeration<AbstractButton> ab = bg.getElements(); ab.hasMoreElements();){
				ab.nextElement().setEnabled(false);
			}
			
			break;
			
		case Ready:
			
			status.setText("Ready");
			
			for(Enumeration<AbstractButton> ab = bg.getElements(); ab.hasMoreElements();){
				ab.nextElement().setEnabled(true);
			}
			
			break;
			
		case Failed:
			
			status.setText("Failed to Send");
			
			for(Enumeration<AbstractButton> ab = bg.getElements(); ab.hasMoreElements();){
				ab.nextElement().setEnabled(true);
			}
			
			break;
			
		case Restart:
			
			status.setText("RESTART ROBOT CODE");
			
			for(Enumeration<AbstractButton> ab = bg.getElements(); ab.hasMoreElements();){
				ab.nextElement().setEnabled(true);
			}
			
			break;
		}
	}
	
	public static CurrentState getState(){
		return currentState;
	}
	
	public static void setStatus(String status){
		Display.status.setText(status);
	}
	
	public void show(){
		frame.setVisible(true);	
	}
	
	public void createGroups(){
		
		File f = new File("autoFiles");
		f.mkdir();
		
		String[] s = f.list();
		if(s==null){
			return;
		}
		
		JRadioButton temp = null;
		
		for(int i =0; i < s.length; i++){
			
			temp = new JRadioButton(s[i]);
			temp.setFocusable(false);
			temp.addActionListener(new ButtonSelectionListener(s[i]));
			bg.add(temp);
			frame.add(temp);
			
		}


	}
	

}
