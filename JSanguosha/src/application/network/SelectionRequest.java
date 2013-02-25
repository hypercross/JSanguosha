package application.network;

import game.entity.Entity;

public class SelectionRequest {
	public int id;
	public int player;
	
	public SelectionRequest(){}
	
	public SelectionRequest(Entity entity, int playerID)
	{
		id = entity.id();
		player = playerID;
	}
}
