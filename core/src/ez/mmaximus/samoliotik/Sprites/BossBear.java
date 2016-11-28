package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maximus on 31.10.2016.
 */
public class BossBear {
    private Texture bossTexture;
    private static int bossSpeed;
    private Vector2 positionBoss;
    private Rectangle boundsBoss;
    private int bossHitPoint;


    public BossBear(float x, float y){
        bossSpeed = 0;
        bossTexture = new Texture("data/bossBear.png");
        positionBoss = new Vector2(x, y);
        boundsBoss = new Rectangle(positionBoss.x, positionBoss.y, bossTexture.getWidth(), bossTexture.getHeight());
        bossHitPoint = 1;
    }
    public Texture getBossTexture() {
        return bossTexture;
    }

    public Vector2 getPositionBoss() {
        return positionBoss;
    }

    public Rectangle getBoundsBoss(){
        return boundsBoss;
    }

    public static int getBossSpeed() {
        return bossSpeed;
    }

    public void reposition(float x, float y){
        positionBoss.set(x, y);
        boundsBoss.setPosition(positionBoss.x, positionBoss.y);
    }

    public void setBossSpeed(int newBossSpeed)
    {
        bossSpeed = newBossSpeed;
    }
    public void bossDamage()
    {
        bossHitPoint = bossHitPoint - 1;
        //if(bossHitPoint <= 0) dispose();
    }

    public int getBossHitPoint() {
        return bossHitPoint;
    }

    public void update(float dt){
        positionBoss.add(0, bossSpeed * dt);
        boundsBoss.setPosition(positionBoss.x, positionBoss.y);
    }


    public boolean collides(Rectangle boundsBullet){return boundsBoss.overlaps(boundsBullet);
    }

    public void dispose() {
        bossTexture.dispose();
    }
}
