package Tetris_Game;

public class Tbuffer {

	private int data;
	private boolean empty = true;

	public synchronized int get() {
		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		empty = true;
		notifyAll();
		return data;
	}

	public synchronized void put(int data) {
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		empty = false;
		this.data = data;
		notifyAll();
	}

}
