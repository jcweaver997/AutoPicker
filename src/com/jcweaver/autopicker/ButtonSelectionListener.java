package com.jcweaver.autopicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.Scp;

public class ButtonSelectionListener implements ActionListener {

	private String name;
	public ButtonSelectionListener(String name){
		this.name = name;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(name);
		if(Display.GetState()!=Display.CurrentState.SendingFile){
			System.out.println("sendingfile");
			Display.SetState(Display.CurrentState.SendingFile);
			new Thread(new Thread(){
				public void run(){
					System.out.println("started scp thread");
					Display.SetStatus("Sending "+name);
					Scp scp = new Scp();
					scp.setPort(22);
					scp.setLocalFile("autoFiles\\"+name);
					scp.setTodir( "lvuser:@roboRIO-1296-FRC.local:RhsScript.txt");
					scp.setProject( new Project() );
					scp.setTrust( true );
					try{
					scp.execute();
					Display.SetStatus("Sent "+name+"!");
					Thread.sleep(2000);
					
					Display.SetState(Display.CurrentState.Restart);
					
					}catch(Exception e){
						Display.SetStatus("Couldn't Send");
						try{
							Thread.sleep(2000);
						}catch(Exception ef){}
						Display.SetState(Display.CurrentState.Failed);
					}finally{
						this.interrupt();
					}
					

				}
			}).start();

		}
	}
	
	
}
