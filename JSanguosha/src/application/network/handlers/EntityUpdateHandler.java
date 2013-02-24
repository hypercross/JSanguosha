package application.network.handlers;

import game.entity.Entity;
import hx.Log;
import application.network.EntityUpdate;
import application.network.NetworkManager.Side;
import application.view.EntityViewManager;

public class EntityUpdateHandler implements INetworkHandler{

	
	@Override
	public boolean captured(Object obj) {
		return obj instanceof EntityUpdate;
	}

	@Override
	public Object handledWithResponse(Object obj) {
		return null;
	}

	@Override
	public void handled(Object obj) {

		EntityUpdate eu = (EntityUpdate)obj;
		
		Log.fine("entity update "+eu.id+"@"+eu.getClass().getName());
		
		if(!Entity.ingame.containsKey(eu.id))Entity.ingame.put(eu.id, eu.createClientEntity());
		else eu.update(Entity.ingame.get(eu.id));
		EntityViewManager.instance.updateEntityView(Entity.ingame.get(eu.id));
	}

	@Override
	public Side side() {
		return Side.Client;
	}

}
