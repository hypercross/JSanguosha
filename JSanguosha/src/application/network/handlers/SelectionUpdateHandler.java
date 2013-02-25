package application.network.handlers;

import application.network.NetworkManager.Side;
import application.network.SelectionUpdate;
import application.view.ClientControlView;
import application.view.EntityViewManager;

public class SelectionUpdateHandler implements INetworkHandler{
	
	@Override
	public boolean captured(Object obj) {
		return obj instanceof SelectionUpdate;
	}

	@Override
	public Object handledWithResponse(Object obj) {
		return null;
	}

	@Override
	public void handled(Object obj) {
		SelectionUpdate su = (SelectionUpdate)obj;
		EntityViewManager.instance.toggleSelected(su.toToggle());
		ClientControlView.instance.deployButtons(su.types());
	}

	@Override
	public Side side() {
		return Side.Client;
	}

}
