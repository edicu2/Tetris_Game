package Tetris_Game;

public class TRun implements Runnable {
	private TBlock tblock;
	private TOperate tOperate;
	private TFrame frame;

	public TRun(TOperate tOperate, TBlock tblock, TFrame frame) {
		this.tOperate = tOperate;
		this.tblock = tblock;
		this.frame = frame;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);  //Waiting time before game start
			while (true) {
				tblock.randomBlock();  // setting random block
				tOperate.StartX(); // setting start x, y 
				tOperate.StartY();     
				while (true) {
					Thread.sleep(200);  //Tetris moves once every 0.2 seconds
					if (tOperate.isRoopExit() == true) {
						tOperate.Erase(tblock.getBlockNum());
						tOperate.setX(1); // x += 1;
						tOperate.Position(tblock.getBlockNum());
					} else {  // roopExit ==false 
						tOperate.StorBlock(tblock.getBlockNum());
						tOperate.del();
						tOperate.setUpKey(0);
						break;
					}
				}
				tOperate.setRoopExit(true);
				if (tOperate.isGameOver() == true) {
					Thread.sleep(1000); 
					frame.nextPanel();  // GameOver panel 
					break;
				}
			}
		} catch (InterruptedException g) {
		}

	}

}
