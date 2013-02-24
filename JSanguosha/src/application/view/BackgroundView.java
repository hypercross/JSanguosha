package application.view;

import application.JSanguosha;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackgroundView extends Actor{

	public void draw (SpriteBatch batch, float parentAlpha) {
		batch.setColor(1f,1f,1f,1f);
		batch.draw(DefaultSkin.instance.getRegion("banner"),
				this.getX(),
				this.getY(),
				this.getWidth(),
				this.getHeight());
		batch.setColor(1f,1f,0f,1f);
		DefaultSkin.instance.getFont("default").draw(batch, JSanguosha.version(), 0, 15);
	}
	
	public void layout()
	{
		this.setPosition(0,0);
		this.setSize(this.getStage().getWidth(), this.getStage().getHeight());
	}
}
