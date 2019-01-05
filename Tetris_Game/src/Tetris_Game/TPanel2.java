package Tetris_Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TPanel2 extends JPanel implements KeyListener { // Game Panel 
	private BufferedImage image;
	private TFrame frame;
	private JPanel panelCenter = new JPanel();
	private TBlock tblocks = new TBlock();
	private TOperate tOperate = new TOperate(tblocks, this);
	protected JLabel btn[][] = new JLabel[22][12];

	public TPanel2(TFrame frame) {
		try {
			image = ImageIO.read(new File("Tetris_img/tetrisBack.jpg"));
		} catch (IOException ex) {};
		this.frame = frame;
		this.setLayout(null);
		this.setBackground(Color.white);

		panelCenter.setBackground(Color.white);
		panelCenter.setBounds(250, -60, 430, 690);
		panelCenter.setLayout(new GridLayout(22, 12, 2, 2));
		addArray();

		this.add(panelCenter);
		frame.add(this);
		
		//(Thread) Deploy games within the array 
		Runnable TRun = new TRun(tOperate, tblocks, frame); 
		Thread t = new Thread(TRun);
		t.start();

		frame.setFocusable(true);
		frame.addKeyListener(this);

	}

	public void addArray() {  // Create TetrisArray 
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 12; j++) {
				if ((j >= 1 && j <= 10) && (i >= 2 && i <= 20)) {
					btn[i][j] = new JLabel("0");
					btn[i][j].setIcon(new ImageIcon("Tetris_img//pic.jpg"));
				} else {
					if (i >= 2) {
						btn[i][j] = new JLabel("9");
						btn[i][j].setIcon(new ImageIcon("Tetris_img//border.png"));
					} else {
						btn[i][j] = new JLabel();
					}
				}
				panelCenter.add(btn[i][j]);
			}
		}
	}
	
	

	@Override  // KeyListener
	public void keyReleased(KeyEvent e) {
		int blockNum = tblocks.getBlockNum();
		tOperate.Erase(blockNum);
		if (e.getKeyCode() == KeyEvent.VK_UP) {// Rotating the block
			tOperate.turnCheck(blockNum);      
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {  //Move to the left.
			tOperate.GoLCheck(blockNum);
			if (tOperate.isLCheck() == true) {
				tOperate.setY(-1);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //Move to the right.
			tOperate.GoRCheck(blockNum);
			if (tOperate.isRCheck() == true) {
				tOperate.setY(1);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {// Lowering a block to the floor
			tOperate.GoBottom(blockNum);
		}
		tOperate.Position(blockNum);
		tOperate.GoDownCheck(blockNum);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}
	
	
	
	public void paintComponent(Graphics g) {  // setting backgroundImage
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 950, 630, this);
	}
}
