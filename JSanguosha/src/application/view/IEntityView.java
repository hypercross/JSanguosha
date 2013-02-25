package application.view;

import game.entity.Entity;

public interface IEntityView {

	public void updateProperty();
	
	public Entity entity();
	
	public Selectable selectable();
	
}
