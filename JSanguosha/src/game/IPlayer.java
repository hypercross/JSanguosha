package game;

import game.entity.GameEntity;

public interface IPlayer {

	public int playerID();
	
	public void setID(int id);
	
	public void setGame(GameEntity game);
	
	public GameEntity getGame();
	
	public void propose(ActionSet as);
	
	public boolean isReady();
	
	public Action getDecision();
}
