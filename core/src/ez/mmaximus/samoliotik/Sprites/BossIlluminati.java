package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maximus on 26.10.2016.
 */
public class BossIlluminati {
    private Texture bossTexture;
    private static int bossSpeed;
    private Vector2 positionBoss;
    private Rectangle boundsBoss;
    private Boolean bossAlive = true;
    private int bossHitPoint;


    public BossIlluminati(float x, float y){
        bossSpeed = 0;
        bossHitPoint = 1;
        bossTexture = new Texture("data/bossIlluminati.png");
        positionBoss = new Vector2(x, y);
        boundsBoss = new Rectangle(positionBoss.x, positionBoss.y, bossTexture.getWidth(), bossTexture.getHeight());
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

    public boolean onTriangle(float x,float y, float x1, float y1, float x2, float y2, float x3, float y3)
    {

        float a = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
        float b = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y);
        float c = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y);

        if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0))
        {
            return true;
        }
        else
        {
            return false;
        }


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


    public boolean collides(Rectangle boundsObject){
        if(onTriangle(boundsObject.x + boundsObject.getWidth(), boundsObject.y+boundsObject.getHeight(),
                positionBoss.x + bossTexture.getWidth()/2, positionBoss.y + bossTexture.getHeight(),
                positionBoss.x, positionBoss.y,
                positionBoss.x + bossTexture.getWidth(), positionBoss.y)) return true;
        else if(onTriangle(boundsObject.x, boundsObject.y + boundsObject.getHeight(),
                positionBoss.x + bossTexture.getWidth()/2, positionBoss.y + bossTexture.getHeight(),
                positionBoss.x, positionBoss.y,
                positionBoss.x + bossTexture.getWidth(), positionBoss.y)) return true;
        else if (onTriangle(boundsObject.x, boundsObject.y,
                positionBoss.x + bossTexture.getWidth()/2, positionBoss.y + bossTexture.getHeight(),
                positionBoss.x, positionBoss.y,
                positionBoss.x + bossTexture.getWidth(), positionBoss.y)) return true;
        else if (onTriangle(boundsObject.x + boundsObject.getWidth(), boundsObject.y,
                positionBoss.x + bossTexture.getWidth()/2, positionBoss.y + bossTexture.getHeight(),
                positionBoss.x, positionBoss.y,
                positionBoss.x + bossTexture.getWidth(), positionBoss.y)) return true;
        else return false;

    }

    public void dispose() {
        bossTexture.dispose();
    }
}