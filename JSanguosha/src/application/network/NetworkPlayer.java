package application.network;

import java.util.ArrayList;

import game.Action;
import game.ActionSet;
import game.IPlayer;
import game.entity.Entity;
import game.entity.GameEntity;
import game.entity.PlayerEntity;
import game.type.LinkedType;
import game.type.Type;

public class NetworkPlayer implements IPlayer{

	int playerID;
	ActionSet as;
	boolean available;
	ArrayList<Integer> selection;
	String actionType;
	GameEntity game;
	
	@Override
	public int playerID() {
		return playerID;
	}

	@Override
	public void setID(int id) {
		playerID = id;
	}

	@Override
	public void propose(ActionSet as) {
		this.as = as;
		available = false;
		NetworkManager.instance.sendDecisionRequest(playerID,getSelectionUpdate());
	}

	@Override
	public boolean isReady() {
		return available;
	}

	@Override
	public Action getDecision()
	{
		return getDecision(game,new LinkedType(actionType,Type.BASE_TYPE));
	}
	
	public Action getDecision(GameEntity game, Type actionType) {
		
		Entity[] entities = new Entity[selection.size()];
		for(int i =0;i<selection.size();i++)entities[i] = Entity.ingame.get(selection.get(i));
		
		return new Action(actionType, 
				(PlayerEntity)game.child("player" + playerID), 
				entities);
	}
	
	public NetworkPlayer()
	{
		available = false;
		selection = new ArrayList<Integer>();
	}
	
	public SelectionUpdate getSelectionUpdate()
	{
		SelectionUpdate su = new SelectionUpdate();
		
		int[] selectID = new int[selection.size()];		
		int i = 0;
		for(Integer id : selection)selectID[i++] = id;
		
		ArrayList<Type> replies = new ArrayList<Type>();		
		for(Type atype : as.types)
		{
			if(as.contains(getDecision(game,atype)))replies.add(atype);
		}
		
		su.select(selectID);
		su.repliable((Type[]) replies.toArray());
		
		return su;
	}

	@Override
	public void setGame(GameEntity game) {
		this.game = game;
	}

	@Override
	public GameEntity getGame() {
		return game;
	}
}
