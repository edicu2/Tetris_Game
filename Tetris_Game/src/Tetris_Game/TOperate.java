package Tetris_Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TOperate {
	private int dBlock = 10; 
	private int maxX = 21;
	private int x = 2;
	private int y = 5;
	private int upKey = 0;
	private boolean Lcheck = true;   // LeftMove Check 
	private boolean Rcheck = true;	 // RightMove Check
	private boolean delete = true;	 // one block stop -> block delete 
	private boolean roopExit = true; // block stop check   
	private boolean gameOver = false;// game over check
	private TBlock tBlock;			 // block information
	private TPanel2 tP2;			 // game panel 

	public TOperate(TBlock tBlock, TPanel2 tP2) { 
		this.tBlock = tBlock;
		this.tP2 = tP2;
	}

	public void setUpKey(int k) {   //shape of block change
		if (k == 1)
			upKey += k;
		else
			upKey = 0;
	}

	

	public void StartX() {  //startSetting
		x = 1;
	}

	public void StartY() {  //startSetting
 		y = 5;
	}

	public void setDBlock(int z) { //getter and setter    
		dBlock += z;
	}

	public int getDBlock() {
		return dBlock;
	}
	
	public int getX() {
		return y;
	}

	public synchronized void setX(int x) {   // synchronized
		this.x += x;
	}

	public int getY() {
		return y;
	}

	public synchronized void setY(int y) {   // synchronized
		this.y += y;
	}

	public boolean isRoopExit() {
		return roopExit;
	}

	public synchronized void setRoopExit(boolean roopExit) {   // synchronized
		this.roopExit = roopExit;
	}

	public boolean isLCheck() {
		return Lcheck;
	}

	public boolean isRCheck() {
		return Rcheck;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void Erase(int num) {     // Erase a block

		for (int i = 0; i < 4; i++) {
			for (int w = 0; w < 2; w++) {
				for (int h = 0; h < 12; h++) {
					tP2.btn[w][h].setIcon(new ImageIcon("Tetris_img//white.jpg"));
				}
			}
			int px = tBlock.getArray(num, upKey, i, 0);
			int py = tBlock.getArray(num, upKey, i, 1);
			JLabel array = tP2.btn[px + x][py + y];
			array.setIcon(new ImageIcon("Tetris_img//pic.jpg"));
			array.setText("0");
		}
	}

	public void Position(int num) {   // Drawing blocks

		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num, upKey, i, 0);
			int py = tBlock.getArray(num, upKey, i, 1);
			tP2.btn[px + x][py + y].setText(Integer.toString(dBlock));
			tP2.btn[px + x][py + y].setIcon(new ImageIcon("Tetris_img//block0" + (dBlock % 7) + ".png"));
		}
		GoDownCheck(num);
	}

	public void StorBlock(int num) {  // Save a stopped block
		if (maxX > x)
			maxX = x;
		for (int i = maxX; i < 22; i++) {
			for (int y = 0; y < 12; y++) {
				Position(num);
				setDBlock(1);
				GameOver(num);
			}
		}
	}

	public void del() {   // A full row delete 
		for (int i = maxX; i < 21; i++) {
			for (int j = 1; j < 11; j++) {
				if (tP2.btn[i][j].getText().equals("0")) {
					delete = false;
				}
			}
			if (delete == true) {
				for (int x = i; x >= maxX; x--) {
					for (int y = 1; y < 11; y++) {
						tP2.btn[x][y].setText(tP2.btn[x - 1][y].getText());
						tP2.btn[x - 1][y].setIcon(new ImageIcon("Tetris_img//pic.jpg"));
						tP2.btn[x - 1][y].setText("0");
						if (!(tP2.btn[x][y].getText().equals("0")))
							tP2.btn[x][y].setIcon(new ImageIcon(
									"Tetris_img//block0" + Integer.parseInt(tP2.btn[x][y].getText()) % 7 + ".png"));
						else {
							tP2.btn[x][y].setIcon(new ImageIcon("Tetris_img//pic.jpg"));
						}
					}
				}
			}
			delete = true;
		}

	}

	public void GoBottom(int num) {  // The descent of a block
		while (true) {
			x++;
			Position(num);
			GoDownCheck(num);
			Erase(num);
			if (roopExit == false)
				break;
		}
	}

	public void GoDownCheck(int num) {   // Check for lowering
		int fx = 21;
		String dblock = Integer.toString(dBlock);
		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num, upKey, i, 0);
			int py = tBlock.getArray(num, upKey, i, 1);

			if (tP2.btn[px + x][py + y].getText().equals(dblock)) {
				if (tP2.btn[px + x + 1][py + y].getText().equals("9")
						|| !(tP2.btn[px + x + 1][py + y].getText().equals(dblock))
								&& !(tP2.btn[px + x + 1][py + y].getText().equals("0"))) {
					roopExit = false;
				}
			}

		}
	}

	public void GoLCheck(int num) { // Left Movement Check
		Lcheck = true;
		String dblock = Integer.toString(dBlock);
		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num, upKey, i, 0);
			int py = tBlock.getArray(num, upKey, i, 1);
			if (py + y == 1) {
				Lcheck = false;
				break;
			} else if (!(tP2.btn[px + x][py + y - 1].getText().equals(dblock))
					&& !(tP2.btn[px + x][py + y - 1].getText().equals("0"))) {
				Lcheck = false;
				break;
			}
		}
	}

	public void GoRCheck(int num) { // Right Movement Check
		Rcheck = true;
		String dblock = Integer.toString(dBlock);
		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num, upKey, i, 0);
			int py = tBlock.getArray(num, upKey, i, 1);
			if (py + y == 10) {
				Rcheck = false;
				break;
			} else if (!(tP2.btn[px + x][py + y + 1].getText().equals(dblock))
					&& !(tP2.btn[px + x][py + y + 1].getText().equals("0"))) {
				Rcheck = false;
				break;
			}
		}
	}

	public void turnCheck(int num) { // Rotating the block
		upKey += 1;
		upKey = upKey % 4;
		for (int i = 0; i < 4; i++) {
			int py = tBlock.getArray(num, upKey, i, 1);
			if (!(py + y > 0 && py + y < 11)) {
				upKey -= 1;
				break;
			}
		}
	}

	public void GameOver(int num) {   // GameOver Check
		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num, upKey, i, 0);
			if (px + x <= 2) {
				gameOver = true;
				break;
			}
		}
	}
}
