package test;

import application.network.NetworkManager;
import game.Environment;
import game.ICard;
import game.IPlayer;
import game.entity.CardEntity;
import game.entity.CardSlotEntity;
import game.entity.Entity;
import game.entity.GameEntity;
import game.entity.PlayerEntity;
import test.card.CardDodge;
import test.card.CardSlash;
import test.event.GameEventTurnLoop;

public class TestEnvironment extends Environment{
	
	public TestEnvironment()
	{
		cards = new ICard[2];
		
		cards[0] = new CardSlash();
		cards[1] = new CardDodge();
	}
	
	public void setupGame(GameEntity gameEntity) {
		gameEntity.events.setRoot(new GameEventTurnLoop(gameEntity));
		
		for(IPlayer ip : gameEntity.players.players)
		{
			PlayerEntity pe = (PlayerEntity) gameEntity.child("player" + ip.playerID());
			
			pe.hp = 4;
			pe.isAlive = true;
			pe.maxHp = 4;
		}
	}
	
	public void setupDeck(CardSlotEntity slot)
	{
		if(slot.name().equals("drawDeck"))
		{
			for(int i =0;i<104;i++)
			{
				char suit = 'D';
				switch(i % 4)
				{
				case 0:
					suit = 'S';
					break;
				case 1:
					suit = 'C';
					break;
				case 2:
					suit = 'H';
					break;
				}
				CardEntity ce = new CardEntity(i, i % 13, suit, cards[i%2]);
				slot.add(ce);
				ce.container = slot;
			}
			slot.shuffle();
		}
	}

	private final float[] x_pos = new float[]{ 0.1f, 0.3f, 0.7f, 0.9f};
	private final float[] y_pos = new float[]{ 0.5f, 0.7f, 0.7f, 0.5f};
	
	@Override
	public void deployLayout(GameEntity game, IPlayer player) {
		
		NetworkManager.instance.sendDeployUnviewable((Entity) game.child("drawDeck"), player.playerID());
		NetworkManager.instance.sendDeployUnviewable((Entity) game.child("discardDeck"), player.playerID());
		
		
		NetworkManager.instance.sendDeployClient((Entity) game.child("player"+player.playerID()),
											player.playerID(), 0, 0);
		
		int i = 0;
		for(IPlayer ip : game.players.players)
		{
			if(ip != player)
			{
				NetworkManager.instance.sendDeploy((Entity) game.child("player"+ip.playerID()),
											player.playerID(), x_pos[i], y_pos[i]);
				i++;
			}
		}
	}
}
