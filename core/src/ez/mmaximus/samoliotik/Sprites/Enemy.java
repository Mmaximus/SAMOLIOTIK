package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maximus on 05.09.2016.
 */
public class Enemy {
    public static final int HEDGEHOG_WIDTH = 60;

    private Texture enemyTexture;
    private Vector2 positionEnemy;
    private Rectangle boundsEnemy;


    public Texture getTextureEnemy() {
        return enemyTexture;
    }

    public Vector2 getPositionEnemy() {
        return positionEnemy;
    }

    public Enemy(float x, float y){
        enemyTexture = new Texture("nps.png");
        positionEnemy = new Vector2(x, y);
        boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture.getWidth(), enemyTexture.getHeight());
    }

    public void reposition(float x, float y){
        positionEnemy.set(x, y);
        boundsEnemy.setPosition(positionEnemy.x, positionEnemy.y);
    }

    public boolean collides(Rectangle boundsSamoliot){return boundsSamoliot.overlaps(boundsEnemy);
    }

    public void dispose() {
        enemyTexture.dispose();
    }
}
