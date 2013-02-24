package application.network;

import game.entity.CardSlotEntity;
import game.entity.Entity;

public class CardSlotEntityUpdate extends EntityUpdate {
	public CardSlotEntityUpdate(CardSlotEntity entity) {
		super(entity);
		sizeCap = entity.sizeCap;
		viewable = entity.viewable;
	}
	
	public CardSlotEntityUpdate(){}

	public int sizeCap;
	public boolean viewable;
	
	public void update(Entity entity)
	{
		CardSlotEntity cse = (CardSlotEntity)entity;
		super.update(cse);
		cse.sizeCap = sizeCap;
		cse.viewable = viewable;
	}

	@Override
	public Entity getNewEntity() {
		return new CardSlotEntity();
	}
}
