package application.network.handlers;

import game.IPlayer;
import hx.Log;
import hx.TempLog;
import application.network.ConfirmationRequest;
import application.network.NetworkManager;
import application.network.NetworkPlayer;
import application.network.SelectionUpdate;
import application.network.NetworkManager.Side;

@TempLog
public class ConfirmationRequestHandler implements INetworkHandler{
	
	@Override
	public boolean captured(Object obj) {
		Log.t("handler checked");
		return obj instanceof ConfirmationRequest;
	}

	@Override
	public Object handledWithResponse(Object obj) {
		
		Log.t("received confirmation: " + obj);
		
		ConfirmationRequest cr = (ConfirmationRequest)obj;
		boolean valid = false;
		
		IPlayer ip = NetworkManager.instance.playerManager().find(cr.id);
		if(ip instanceof NetworkPlayer)
		{
			NetworkPlayer np = (NetworkPlayer) ip;
			valid = np.acceptAction(cr.actionType);
		}
		if(valid)
			return SelectionUpdate.getIdle();
		return null;
	}

	@Override
	public void handled(Object obj) {
	}

	@Override
	public Side side() {
		return Side.Server;
	}

}
