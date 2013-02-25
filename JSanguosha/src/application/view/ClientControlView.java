package application.view;

import java.util.ArrayList;

import game.ActionSet;
import game.entity.CardEntity;
import game.entity.Entity;
import game.entity.PlayerEntity;
import game.type.Type;
import hx.Log;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ClientControlView extends Group implements IEntityView{
	
	public static ClientControlView instance;

	PlayerEntityView pev;
	ArrayList<CardEntityView> hand = new ArrayList<CardEntityView>();
	Table controls;

	ActionSet request;

	public ClientControlView(Stage stage, PlayerEntity pe)
	{		
		stage.addActor(this);
		EntityViewManager.instance.addActor(this, pev = new PlayerEntityView(pe), pe);

		this.addActor(controls = new Table());

		updateProperty();
		
		instance = this;
	}

	public void layout()
	{
		controls.setPosition(100,controls.getCells().size() * 20f);
		pev.setPosition(getStage().getWidth() - pev.getWidth(), 0);

		float start = 300;
		float end   = this.getStage().getWidth() - pev.getWidth() - 100;
		float interval = (end - start)/ hand.size();

		if(interval > 90)
		{
			start += (interval - 90) * hand.size() / 2;
			interval = 90;
		}

		for(int i =0;i<hand.size();i++)
			animateTo(hand.get(i), 
					start + interval * i ,
					0);
	}

	public void addCards(CardEntityView... cards)
	{
		for(CardEntityView card : cards)
		{
			EntityViewManager.instance.addActor(this, card, card.card);
			hand.add(card);
		}

	}

	public void deployButtons(Type... types)
	{
		controls.clear();
		for(Type atype : types)
		{
			controls.add(new Button(new Label(atype.toString(),DefaultSkin.instance), DefaultSkin.instance));
			controls.row();
		}
	}

	public void deployButtons(String... types)
	{
		controls.clear();
		for(String atype : types)
		{
			controls.add(new Button(new Label(atype,DefaultSkin.instance), DefaultSkin.instance));
			controls.row();
		}
	}

	private void animateTo(Actor actor, float x, float y)
	{
		MoveToAction mta = new MoveToAction();
		mta.setDuration(0.3f);
		mta.setPosition(x, y);

		actor.addAction(mta);
	}

	@Override
	public void updateProperty() {
		
		Log.fine("new: " + pev.player.child("hand").toString());
		String blah = "[";
		for(CardEntityView cev : hand)blah += cev.card.toString()+ ", ";
		Log.fine("old: " + blah + "]");

		for(Entity ce : pev.player.child("hand"))
		{
			boolean has = false;
			for(CardEntityView cev : hand)
				if(cev.card.id() == ce.id())
				{
					has = true;
					break;
				}

			if(!has)addCards(new CardEntityView((CardEntity) ce));
		}

		ArrayList<CardEntityView> toRemove = new ArrayList<CardEntityView>();  
		for(CardEntityView cev : hand)
		{
			boolean has = false;
			for(Entity ce : pev.player.child("hand"))
				if(cev.card.id() == ce.id())
				{
					has = true;
					break;
				}

			if(!has)
			{
				EntityViewManager.instance.removeActor(cev);
				toRemove.add(cev);
			}
		}
		
		for(CardEntityView cev : toRemove)
			hand.remove(cev);

		deployButtons("Confirm", "Cancel");
		layout();

	}

	@Override
	public Entity entity() {
		return pev.player;
	}

	@Override
	public Selectable selectable() {
		return null;
	}
}
