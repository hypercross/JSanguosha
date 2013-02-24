package application.network;

import game.entity.CardEntity;
import game.entity.Entity;
import game.type.Type;

public class CardEntityUpdate extends EntityUpdate{
	public CardEntityUpdate(CardEntity entity) {
		super(entity);
		cardID = entity.cardID;
		number = entity.number;
		suit = entity.suit;
	}
	
	public CardEntityUpdate(){}

	public int cardID;
	public int number;
	public char suit;
	
	@Override
	public void update(Entity entity)
	{
		CardEntity ce = (CardEntity)entity;
		super.update(ce);
		ce.cardID = cardID;
		ce.number = number;
		ce.suit = suit;
	}

	@Override
	public Entity getNewEntity() {
		return new CardEntity(number, suit, Type.fromString(type));
	}
}
