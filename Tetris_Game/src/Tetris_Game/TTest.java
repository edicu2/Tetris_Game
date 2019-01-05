package Tetris_Game;

public class TTest {
	public static void main(String[] args) {
		TFrame t = new TFrame(); 
		Runnable TMusic = new TMusic();
		Thread m = new Thread(TMusic);
		m.start();
	}
}
