package application.network.handlers;

import application.network.NetworkManager.Side;

public interface INetworkHandler {

	public boolean captured(Object obj);
	
	public Object handledWithResponse(Object obj);
	
	public void handled(Object obj);
	
	public Side side();
}
