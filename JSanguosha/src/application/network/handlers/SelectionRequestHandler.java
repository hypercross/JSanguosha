package application.network.handlers;

import game.IPlayer;
import application.network.NetworkManager.Side;
import application.network.NetworkManager;
import application.network.NetworkPlayer;
import application.network.SelectionRequest;

public class SelectionRequestHandler implements INetworkHandler {

	@Override
	public boolean captured(Object obj) {
		return obj instanceof SelectionRequest;
	}

	@Override
	public Object handledWithResponse(Object obj) {
		return null;
	}

	@Override
	public void handled(Object obj) {
		SelectionRequest sr = (SelectionRequest)obj;
		
		IPlayer ip = NetworkManager.instance.playerManager().find(sr.player);
		if(ip instanceof NetworkPlayer)
		{
			NetworkPlayer np = (NetworkPlayer) ip;
			np.acceptToggleSelection(sr.id);
		}
	}

	@Override
	public Side side() {
		return Side.Server;
	}

}
