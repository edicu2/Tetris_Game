package Tetris_Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TFunction {
	boolean t=true;
	
	public void Line(JLabel[][] btn) {
		
		for(int i=0; i<20; i++) {
			for(int j=0 ;j<10 ; j++) {
				if(btn[i][j].getText().equals("0")) {
					t=false;
					break;
				}	
			}
			if(t==true) {
				for(int k=0;k<10;k++) {
					btn[i][k].setText("0");
					btn[i][k].setIcon(new ImageIcon("img//pic.jpg"));
				}		
			}
			else t=true;
		}
	}
	
}
