package Tetris_Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TPanel1 extends JPanel {  // Start Panel 

	private JLabel label;
	private BufferedImage image;

	public TPanel1(TFrame f) {
		label = new JLabel("Loading...");
		try {
			image = ImageIO.read(new File("Tetris_img/tetrisBack.jpg"));
		} catch (IOException ex) {
		}
		setSize(950, 630);
		setLayout(null);
		labelAdd(this);
		setVisible(true);
		f.setVisible(true);

		Thread t = new Thread(new loadingM(f));
		t.start();
	}

	public void labelAdd(Container t) {  // labelAdd method
		label.setOpaque(false);
		label.setFont(new Font("Dialog.plain", 3, 40));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(10, 10, 40));
		label.setBounds(350, 300, 250, 60);
		t.add(label);
	}

	public class loadingM implements Runnable {  // loading print Thread 
		TFrame frame;

		public loadingM(TFrame frame) {
			this.frame = frame;
		}

		public void run() {  // loading letter 
			try {

				for (int i = 0; i < 3; i++) {
					label.setVisible(false);
					Thread.sleep(500);
					label.setVisible(true);
					Thread.sleep(500);
				}
				label.setText("Game Start!!");
				Thread.sleep(1300);
			} catch (InterruptedException g) {
			}
			frame.nextPanel();
		}
	}

	@Override
	public void paintComponent(Graphics g) {  // setting backgroundImage
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 950, 630, this);
	}

}
