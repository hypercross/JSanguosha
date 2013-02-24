package application.network;

import game.entity.Entity;
import game.entity.PlayerEntity;

public class PlayerEntityUpdate extends EntityUpdate {
	public PlayerEntityUpdate(){}
	
	public PlayerEntityUpdate(PlayerEntity entity) {
		super(entity);
		hp = entity.hp;
		maxHp = entity.maxHp;
		seatId = entity.seatId;
		isAlive = entity.isAlive;
		
		cseu = (CardSlotEntityUpdate) EntityUpdate.getUpdate((Entity) entity.child("hand"));
	}
	int hp,maxHp,seatId;
	boolean isAlive;
	CardSlotEntityUpdate cseu;
	
	public void update(Entity entity)
	{
		PlayerEntity pe = (PlayerEntity)entity;
		super.update(entity);
		pe.hp = hp;
		pe.maxHp = maxHp;
		pe.seatId = seatId;
		
		cseu.update((Entity) entity.child("hand"));
	}

	@Override
	public Entity getNewEntity() {
		return new PlayerEntity();
	}
}
