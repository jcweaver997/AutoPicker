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

	
	public void actionPerformed(ActionEvent arg0) {
		if(Display.getState()!=Display.CurrentState.SendingFile){
			
			Display.setState(Display.CurrentState.SendingFile);
			
			new Thread(new Thread(){
				
				public void run(){
					
					Display.setStatus("Sending "+name);
					
					Scp scp = new Scp();
					scp.setPort(1180);
					scp.setLocalFile("autoFiles\\"+name);
					scp.setTodir( "lvuser:@10.12.96.2:RhsScript.txt");
					scp.setProject( new Project() );
					scp.setTrust( true );
					
					try{
						
					scp.execute();
					Display.setStatus("Sent "+name+"!");
					//Thread.sleep(2000);
					Display.setState(Display.CurrentState.Restart);
					
					}catch(Exception e){
						
						Display.setStatus("Couldn't Send");
						
						try{
							
							//Thread.sleep(2000);
							
						}catch(Exception ef){}
						
						Display.setState(Display.CurrentState.Failed);
						
					}finally{
						
						this.interrupt();
						
					}

				}
			}).start();

		}
	}
	
	
}
