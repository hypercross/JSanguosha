package game.api;

import game.Action;
import game.ActionSet;
import game.entity.Entity;
import gameEvent.GameEvent;

/**
 * This should only be used for declaring a playable card's prototype.
 * No further registration in the environment is required.  
 * 
 * @author hyper
 *
 */
public interface IPlayable {
	
	/**
	 * Specifies the usage of the card.
	 * Actions of types from request ActionSet and usage ActionSet are both generated.
	 * Such an action is accepted if it's accepted by the request ActionSet,
	 * in which the UsageFilter is replaced with the usage ActionSet's filter.    
	 *  
	 * 
	 * @return
	 */
	public ActionSet usage();
	
	
	/**
	 * resolve method that is called when a card is played.
	 * @param use
	 * @return
	 */
	public GameEvent onUse(Action use);
	
	/**
	 * resolve method that is called when a card is played and should resolve to a single target.
	 * @param use
	 * @param target
	 * @return
	 */
	public GameEvent onEffect(Action use, Entity target);
}
