package Tetris_Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TArray {
	TBlock blocks;
	TGoDown tGoDown;
	JFrame frame;
	JPanel panel;
	JLabel btn[][]= new JLabel[22][12];
	
	
	public TArray(TBlock blocks) {		
		this.blocks = blocks;
		this.tGoDown = new TGoDown(blocks,this);
		frame = new JFrame();
		frame.setSize(380,630);
		frame.setLayout(null);
		panel = new JPanel();
		panel.setBounds(0,0,375,610);
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(22,12,2,2));		
		addArray();
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){}
			public void keyReleased(KeyEvent e) {
				int blockNum= blocks.getBlockNum();
				if(e.getKeyCode()==KeyEvent.VK_UP) {	
					tGoDown.Erase(blockNum);
					tGoDown.turnJudge(blockNum);
					tGoDown.Position(blockNum);
					tGoDown.Judge(blockNum);
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {			
					tGoDown.setRCheck(true);
					if(tGoDown.isLCheck()==true) {
						System.out.println("left");		
						tGoDown.Erase(blockNum);
						tGoDown.setY(-1);
						tGoDown.Position(blockNum);
						tGoDown.Judge(blockNum);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					tGoDown.setLCheck(true);
					if(tGoDown.isRCheck()==true) {
						System.out.println("right");
						tGoDown.Erase(blockNum);
						tGoDown.setY(1);
						tGoDown.Position(blockNum);
						tGoDown.Judge(blockNum);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					System.out.println("space");
					tGoDown.Erase(blockNum);
					tGoDown.GoBottom(blockNum);
					tGoDown.Position(blockNum);
					tGoDown.Judge(blockNum);
				}
				
			}
			public void keyTyped(KeyEvent e) {}
		});	
		
	}
	
	
	public void addArray() {
		for(int i=0 ; i<22 ;i++) {
			for(int j=0 ; j<12;j++ ) {
				if( (j>=1 && j<=10) && (i>=2 && i<=20) ) {
					btn[i][j] = new JLabel("0");
					btn[i][j].setIcon(new ImageIcon("img//pic.jpg"));			
				}
				else{		
					if(i>=2) {
						btn[i][j] = new JLabel("9");
						btn[i][j].setIcon(new ImageIcon("img//block1.png"));
					}
					else {
						btn[i][j] = new JLabel();
					}
				}
				panel.add(btn[i][j]);
			}
		}
	}
}
