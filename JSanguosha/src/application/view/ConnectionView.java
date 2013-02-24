package application.view;

import game.entity.Entity;

import java.io.IOException;

import application.network.NetworkManager;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ConnectionView extends Table{
	public ConnectionView()
	{
		Button btn = new Button(new Label("run host",DefaultSkin.instance), DefaultSkin.instance);
		this.add(btn);
		btn.addListener(new ClickListener()
		{
			public void clicked (InputEvent event, float x, float y) {
				try {
					NetworkManager.instance.startHost();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn = new Button(new Label("run server",DefaultSkin.instance), DefaultSkin.instance);
		this.add(btn);
		btn.addListener(new ClickListener()
		{
			public void clicked (InputEvent event, float x, float y) {
				try {
					NetworkManager.instance.startServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btn = new Button(new Label("run client",DefaultSkin.instance), DefaultSkin.instance);
		this.add(btn);
		btn.addListener(new ClickListener()
		{
			public void clicked (InputEvent event, float x, float y) {
				try {
					NetworkManager.instance.startClient("localhost");
					Entity.ingame.clear();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
