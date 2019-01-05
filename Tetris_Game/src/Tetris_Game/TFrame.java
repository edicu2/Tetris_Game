package Tetris_Game;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TFrame extends JFrame {  
	private CardLayout cards = new CardLayout();

	public TFrame() {

		setSize(950, 660);
		getContentPane().setLayout(cards);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add("one", new TPanel1(this)); // cardLayout
		getContentPane().add("two", new TPanel2(this));
		getContentPane().add("three", new TPanel3(this));
		setVisible(true);

	}

	public void prePanel() {    // To move to the pre panel 
		cards.previous(this.getContentPane());
	}

	public void nextPanel() {   // To move to the next panel 
		cards.next(this.getContentPane());
	}
}
