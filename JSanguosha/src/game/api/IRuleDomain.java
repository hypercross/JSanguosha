package game.api;

import game.entity.CardEntity;

/**
 * Specifies whether a rule is in effect given the condition of a CardEntity.
 * Normally a rule is in effect when the card is in a living player's area.
 * @author hyper
 *
 */
public interface IRuleDomain 
{
	public boolean isValid(CardEntity ce);
	
}
