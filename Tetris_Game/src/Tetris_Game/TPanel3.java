package Tetris_Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TPanel3 extends JPanel {  // Last Panel 
	private JButton btn;
	private BufferedImage image;

	public TPanel3(TFrame f) {
		try {
			image = ImageIO.read(new File("Tetris_img/end.png"));
		} catch (IOException ex) {
		}
		setBackground(Color.gray);
		setSize(950, 630);
		setLayout(null);
		buttonAdd(this, "PREVIOUS", f);
		buttonAdd(this, "HOME", f);
		setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {// Setting up a background image
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 955, 635, this); 
		
	}

	public void buttonAdd(Container t, String s, TFrame f) { // Create button
		btn = new JButton(s);
		btn.setBackground(Color.white);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("PREVIOUS"))
					f.prePanel();
				else if (e.getActionCommand().equals("HOME"))
					f.nextPanel();
			}
		});
		if (btn.getText().equals("PREVIOUS"))
			btn.setBounds(12, 20, 113, 23);
		else
			btn.setBounds(130, 20, 113, 23);
		t.add(btn);
	}
}