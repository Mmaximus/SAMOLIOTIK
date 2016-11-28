package ez.mmaximus.samoliotik.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Maximus on 02.11.2016.
 */
public class MenuButton  extends ImageButton {
    public MenuButton(Texture button_up)
    {
        super(new SpriteDrawable(new Sprite(button_up)),
                new SpriteDrawable(new Sprite(button_up)));
    }

}