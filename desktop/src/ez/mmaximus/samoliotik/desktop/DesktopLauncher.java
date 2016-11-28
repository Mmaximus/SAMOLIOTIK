package ez.mmaximus.samoliotik.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ez.mmaximus.samoliotik.SamoliotGame;
import ez.mmaximus.samoliotik.Samoliotik;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Samoliotik(), config);
	}
}
