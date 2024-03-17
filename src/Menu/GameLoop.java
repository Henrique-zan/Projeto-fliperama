package Menu;

public class GameLoop extends Thread {
	private boolean running = true;

	@Override
	public void run() {
		while (running) {
			try {
				if (Game.gamestate == GameState.ingame) {
					if (!Collision.collideWithWall(Game.currentBlock, 0) && !Collision.collideWithBlock(Game.currentBlock, 0)) {
						Game.currentBlock.setY(Game.currentBlock.getY() +1);
						Collision.collideWithWall(Game.currentBlock, 0);
					}
					
					if (Game.spawnNewBlock) {
						Collision.checkFullRow(1);
						Game.blocks.add(Game.nextBlock);
						Game.currentBlock = Game.nextBlock;
						Game.nextBlock = new Block();
						Game.spawnNewBlock = false;
					}
				}

				if (!Game.speedup) {
					sleep(1000);
				} else {
					sleep(200);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
