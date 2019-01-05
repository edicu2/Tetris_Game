package Tetris_Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TGoDown {
	private boolean delete = true;
	private int dBlock = 10;
	private int maxX = 21;
	private int x = 1;
	private int y = (int) (Math.random() * 10 + 1);
	private int upKey =0;
	private boolean turnJudge = true;
	private boolean Lcheck = true;
	private boolean Rcheck = true;
	private boolean roopExit = true;
	private boolean gameOver = false;
	TBlock tBlock;
	TArray tArray;

	public TGoDown(TBlock tBlock, TArray tArray) {
		this.tBlock = tBlock;
		this.tArray = tArray;
	}

	public void setUpKey(int k) {
		if(k==1)
			upKey+=k;
		else 
			upKey=0;
	}
	
	public void setDBlock(int z) {

		dBlock += z;
	}
	public int getDBlock() {
		return dBlock;
	}

	public void StartX() {
		x = 1;
	}

	public void StartY() {
		y = 5;
	}

	public int getX() {
		return x;
	}

	public synchronized void setX(int x) {
		this.x += x;
	}

	public int getY() {
		return y;
	}

	public synchronized void setY(int y) {
		this.y += y;
	}

	public boolean isRoopExit() {
		return roopExit;
	}

	public synchronized void setRoopExit(boolean roopExit) {
		this.roopExit = roopExit;
	}

	public boolean isLCheck() {
		return Lcheck;
	}

	public void setLCheck(boolean x) {
		Lcheck = true;
	}

	public boolean isRCheck() {
		return Rcheck;
	}

	public void setRCheck(boolean x) {
		Rcheck = true;
	}

	public boolean isGameOver() {
		return gameOver;
	}
	public void Erase(int num) {
		
		for (int i = 0; i < 4; i++) {
			for(int w = 0 ; w < 2 ; w++) {
				for(int h=0 ; h < 12; h++) {
					tArray.btn[w][h].setIcon(new ImageIcon("img//white.jpg"));
				}
			}
			int px = tBlock.getArray(num,upKey,i, 0);
			int py = tBlock.getArray(num,upKey,i, 1);
			JLabel array = tArray.btn[px + x][py + y];
			array.setIcon(new ImageIcon("img//pic.jpg"));
			array.setText("0");
		}
	}
	
	
	public void Position(int num) {
		
		
		if(x >= 1) {
			System.out.println(x + " " + y);
		}
		for (int i = 0; i < 4; i++) {
		
			int px = tBlock.getArray(num,upKey,i, 0);
			int py = tBlock.getArray(num,upKey,i, 1);
		
			
			tArray.btn[px + x][py + y].setText(Integer.toString(dBlock));
			tArray.btn[px + x][py + y].setIcon(new ImageIcon("img//block" + (dBlock % 7) + ".png"));
			
		}
		Judge(num);
	}

	
	public void Judge(int num) {
		int fx = 21;
		String dblock = Integer.toString(dBlock);
		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num,upKey,i, 0);
			int py = tBlock.getArray(num,upKey,i, 1);
			if (tArray.btn[px + x][py + y].getText().equals(dblock)) {
				if (tArray.btn[px + x + 1][py + y].getText().equals("9")
						|| !(tArray.btn[px + x + 1][py + y].getText().equals(dblock))
								&& !(tArray.btn[px + x + 1][py + y].getText().equals("0"))) {
					roopExit = false;
				}
			}
			if (tArray.btn[px + x][py + y + 1].getText().equals("9")
					|| !(tArray.btn[px + x][py + y + 1].getText().equals(dblock))
							&& !(tArray.btn[px + x][py + y + 1].getText().equals("0"))) {
				Rcheck = false;
			}
			if (tArray.btn[px + x][py + y - 1].getText().equals("9")
					|| !(tArray.btn[px + x][py + y - 1].getText().equals(dblock))
							&& !(tArray.btn[px + x][py + y - 1].getText().equals("0"))) {
				Lcheck = false;
			}
		}
	}

	public void GameOver(int num) {
		for (int i = 0; i < 4; i++) {
			int px = tBlock.getArray(num,upKey,i,0);
			if(px+x<=2) {
				gameOver =true;
				break;
			}
		}
	}
	
	public void StorBlock(int num) {
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

	
	public void del() {
		for (int i = maxX; i < 21; i++) {
			for (int j = 1; j < 11; j++) {
				if (tArray.btn[i][j].getText().equals("0")) {
					delete = false;
				}
			}
			
			if (delete == true) {
				for (int x = i; x >= maxX; x--) {
					for (int y = 1; y < 11; y++) {
						tArray.btn[x][y].setText(tArray.btn[x - 1][y].getText());
						tArray.btn[x - 1][y].setIcon(new ImageIcon("img//pic.jpg"));
						tArray.btn[x - 1][y].setText("0");

						if (!(tArray.btn[x][y].getText().equals("0")))
							tArray.btn[x][y].setIcon(new ImageIcon(
									"img//block" + Integer.parseInt(tArray.btn[x][y].getText()) % 7 + ".png"));
						else {
							tArray.btn[x][y].setIcon(new ImageIcon("img//pic.jpg"));
						}

					}
				}
			}
			delete = true;
		}

	}

	
	public void turnJudge(int num) {
		upKey+=1;
		upKey= upKey%4;
		for (int i = 0; i < 4; i++) {
			int py = tBlock.getArray(num,upKey,i,1);
			if(!(py+y >0 && py+y<11)) {
				upKey-=0;
			}
		}
	}
	
	
	public void GoBottom(int num) {
		while (true) {
			x++;
			Position(num);
			Judge(num);
			Erase(num);
			if (roopExit == false)
				break;
		}
	}
}
