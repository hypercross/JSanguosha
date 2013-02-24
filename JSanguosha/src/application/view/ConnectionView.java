package application.view;

import game.entity.Entity;

import java.io.IOException;

import application.network.NetworkManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ConnectionView extends Table{
	public ConnectionView()
	{
		final Button btn0 = new Button(new Label("run host",DefaultSkin.instance), DefaultSkin.instance);
		final Button btn1 = new Button(new Label("run server",DefaultSkin.instance), DefaultSkin.instance);
		final Button btn2 = new Button(new Label("run client",DefaultSkin.instance), DefaultSkin.instance);
		
		this.add(btn0);
		btn0.addListener(new ClickListener()
		{
			public void clicked (InputEvent event, float x, float y) {
				try {
					NetworkManager.instance.startHost();
					btn0.remove();
					btn1.remove();
					btn2.remove();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		this.add(btn1);
		btn1.addListener(new ClickListener()
		{
			public void clicked (InputEvent event, float x, float y) {
				try {
					NetworkManager.instance.startServer();
					btn0.remove();
					btn1.remove();
					btn2.remove();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		this.add(btn2);
		btn2.addListener(new ClickListener()
		{
			public void clicked (InputEvent event, float x, float y) {
				try {
					NetworkManager.instance.startClient("localhost");
					Entity.ingame.clear();
					btn0.remove();
					btn1.remove();
					btn2.remove();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
