package game;

import java.util.HashMap;

import game.entity.CardSlotEntity;
import game.entity.GameEntity;

public abstract class Environment {
	public HashMap<String, IPackage> packages = new HashMap<String, IPackage>();
	
	public ICard[] cards;
	
	public void setupDeck(CardSlotEntity container)
	{}

	public void setupGame(GameEntity gameEntity) {
		
	}
	
	public abstract void deployLayout(GameEntity game, IPlayer player);
}
