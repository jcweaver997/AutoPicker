package com.jcweaver.autopicker;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.io.File;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class Display {
	private JFrame frame;
	private static ButtonGroup bg;
	private static JLabel status;
	
	public enum CurrentState{
		SendingFile, Ready, Failed, Restart
	}
	private static CurrentState currentState;
	
	public static void SetState(CurrentState cs){
		currentState = cs;
		switch(currentState){
		case SendingFile:

			
			while(!status.getText().equals("Sending File...")){
				status.setText("Sending File...");
			}
			System.out.println("set text to "+status.getText());
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
			System.out.println("set text to "+status.getText());
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
	public static void SetStatus(String status){
		Display.status.setText(status);
	}
	
	public static CurrentState GetState(){
		return currentState;
	}
	
	public Display(){
		frame = new JFrame("FRC 1296 Auto Chooser");
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); 
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
		SetState(CurrentState.Ready);
		if(bg.getButtonCount() == 0){
			SetStatus("No Auto Code Found");
		}
	}
	
	public void show(){
		frame.setVisible(true);
		
	}
	
	public void createGroups(){
		File f = new File("autoFiles");
		f.mkdir();
		System.out.println(f.getAbsolutePath());
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
