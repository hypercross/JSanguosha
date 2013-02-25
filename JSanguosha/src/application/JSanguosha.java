package application;

import java.util.ArrayList;

import application.network.NetworkManager;
import application.view.BackgroundView;
import application.view.ConnectionView;
import application.view.EntityInfoLabel;
import game.entity.GameEntity;
import game.type.MetaLoader;
import test.TestEnvironment;
import test.TestPlayerManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class JSanguosha implements ApplicationListener {
    public Stage stage;
    GameEntity ge;
    BackgroundView bv;
    
    ArrayList<EntityInfoLabel> labels = new ArrayList<EntityInfoLabel>(); 

	public void create () {
		MetaLoader.loadAll();
		
    	 stage = new Stage();
    	 stage.setViewport(1280, 800, false);
         Gdx.input.setInputProcessor(stage);
         bv = new BackgroundView();
         stage.addActor(bv);
         bv.layout();
         
         Table table = new Table();
         table.setFillParent(true);
         stage.addActor(table);
         
         //--------------------------------
         ge = new GameEntity();
         ge.init();
         ge.environment = new TestEnvironment();
         ge.players = new TestPlayerManager();
         ge.players.game = ge;
         ge.setupCommon();
         //--------------------------------
         
         EntityInfoLabel game = 
         new EntityInfoLabel(ge);
         
         labels.add(game);
         table.add(game);
         table.row();
         
         game.addListener(new ClickListener()
         {
        	 public void clicked (InputEvent event, float x, float y) {
        		 ge.step();
        		 for(EntityInfoLabel label : labels)
        			 label.update();
        	}
         });
         
//         for(IPlayer ip : ge.players.players)
//         {
//        	 PlayerEntity player = (PlayerEntity) ge.child("player" + ip.playerID());
//        	 
//        	 EntityInfoLabel label = new EntityInfoLabel(player);
//        	 labels.add(label);
//        	 table.add(label);
//         }
//         
         table.row();

         NetworkManager.initialize(stage);
         NetworkManager.instance.setPlayerManager(ge.players);
         table.add(new ConnectionView());
    }

	public void resize (int width, int height) {
        stage.setViewport(width, height, false);
}

public void render () {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
}

public void dispose() {
        stage.dispose();
}

@Override
public void pause() {
	
}

@Override
public void resume() {
	
}

public static String version()
{
	return "Indev Prime 20130224";
}
}