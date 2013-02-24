package game;

import game.entity.GameEntity;

public class IdlePlayer implements IPlayer{

	int playerID;
	GameEntity game;
	ActionSet as;
	boolean avail;
	
	@Override
	public int playerID() {
		return playerID;
	}

	@Override
	public void setID(int id) {
		playerID = id;
	}

	@Override
	public void setGame(GameEntity game) {
		this.game = game; 
	}

	@Override
	public GameEntity getGame() {
		return game;
	}

	@Override
	public void propose(ActionSet as) {
		this.as = as;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public Action getDecision() {
		return null;
	}

}
