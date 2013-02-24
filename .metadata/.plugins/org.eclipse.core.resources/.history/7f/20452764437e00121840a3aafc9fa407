package application.network.handlers;

import game.entity.Entity;
import application.network.EntityRemoveUpdate;
import application.network.NetworkManager.Side;
import application.view.EntityViewManager;

public class EntityRemoveHandler implements INetworkHandler{

	@Override
	public boolean captured(Object obj) {
		return obj instanceof EntityRemoveUpdate;
	}

	@Override
	public Object handledWithResponse(Object obj) {
		return null;
	}

	@Override
	public void handled(Object obj) {
		EntityRemoveUpdate eru = (EntityRemoveUpdate)obj;
		
		if(eru.id() < 0)
		{
			EntityViewManager.instance.removeAll();
		}
		
		EntityViewManager.instance.removeRelated(Entity.ingame.get(eru.id()));
		Entity.ingame.put(eru.id(),null);
	}

	@Override
	public Side side() {
		return Side.Client;
	}

}
