package Tetris_Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TMusic implements Runnable {
	
	@Override
	public void run() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Tetris_img/TetrisBGM.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}	
	}
}
