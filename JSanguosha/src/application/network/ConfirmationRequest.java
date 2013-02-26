package application.network;

import game.type.Type;

public class ConfirmationRequest {
	public String actionType;
	public int id;
	
	public ConfirmationRequest(){}
	
	public ConfirmationRequest(int id, Type type)
	{
		this.id = id;
		actionType = type.fullName();
	}
	
	public ConfirmationRequest(int id, String type)
	{
		this.id = id;
		actionType = type;
	}
}
