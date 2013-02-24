package game.entity;

import game.type.Type;

@SuppressWarnings("serial")
public class PlayerEntity extends Entity{

	public int hp, maxHp, seatId, playerID;
	public boolean isAlive;
	
	public PlayerEntity()
	{
		CardSlotEntity entity = new CardSlotEntity();
		entity.type = Type.ENTITY_SLOT;
		entity.viewable = false;
		this.setChild("hand", entity);
	}
}
