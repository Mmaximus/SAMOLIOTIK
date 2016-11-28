package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Maximus on 25.10.2016.
 */
public class BonusVodka {
    private Texture bonusTexture;
    private Vector2 positionBonus;
    private Rectangle boundsBonus;
    private static int MOVEMENT = 40;
    private Texture whiteBonus;

    public BonusVodka(float x, float y){
        whiteBonus = new Texture("data/whiteVodka.png");
        bonusTexture = new Texture("data/vodka.png");
        positionBonus = new Vector2(x, y);
        boundsBonus = new Rectangle(positionBonus.x, positionBonus.y, bonusTexture.getWidth(), bonusTexture.getHeight());
    }
    public Texture getTextureBonus() {
        return bonusTexture;
    }

    public Vector2 getPositionBonus() {
        return positionBonus;
    }

    public Rectangle getBoundsBonus(){
        return boundsBonus;
    }

    public void reposition(float x, float y){
        positionBonus.set(x, y);
        boundsBonus.setPosition(positionBonus.x, positionBonus.y);
    }

    public static int getMOVEMENT() {
        return MOVEMENT;
    }

    public void goToStart(){
        Random randomCoord = new Random();
        int newX = randomCoord.nextInt(960-bonusTexture.getWidth());
        int newY = randomCoord.nextInt(100)+540;
        reposition(newX,newY);
    }
    public void lvlUp()
    {
        bonusTexture = whiteBonus;
    }

    public void update(float dt){
        positionBonus.add(0, -MOVEMENT * dt);
        boundsBonus.setPosition(positionBonus.x, positionBonus.y);
        if(positionBonus.y<0-bonusTexture.getHeight() - 5) goToStart();
    }


    public boolean collides(Rectangle boundsObject){return boundsObject.overlaps(boundsBonus);
    }

    public void dispose() {
        bonusTexture.dispose();
    }
}