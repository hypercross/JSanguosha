import application.JSanguosha;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class JSanguoshaDesktop {
        public static void main (String[] args) {
        	
        	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
            cfg.title = "JSanguosha";
            cfg.width = 1280;
            cfg.height = 800;
            cfg.useGL20 = true;
        	
            new LwjglApplication(new JSanguosha(), cfg);
        }
}