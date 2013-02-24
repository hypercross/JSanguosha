package application.network;

import game.type.Type;

public class ConfirmationRequest {
	public String actionType;
	public ConfirmationRequest(Type type)
	{
		actionType = type.fullName();
	}
}
