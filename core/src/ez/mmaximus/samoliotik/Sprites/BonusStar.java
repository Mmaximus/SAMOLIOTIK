package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Maximus on 25.10.2016.
 */
public class BonusStar {
private Texture bonusTexture;
private Vector2 positionBonus;
private Rectangle boundsBonus;
    private Texture whiteBonus;

private static int MOVEMENT = 40;

        public BonusStar(float x, float y){
            bonusTexture = new Texture("data/star.png");
            whiteBonus = new Texture("data/whiteStar.png");
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
            int newY = randomCoord.nextInt(600) + 540*2;
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
