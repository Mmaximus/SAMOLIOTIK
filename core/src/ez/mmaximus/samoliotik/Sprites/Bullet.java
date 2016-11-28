package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Maximus on 10.10.2016.
 */
public class Bullet {

    private Texture bulletTexture;
    private Texture bulletTexture1;
    private Texture bulletTexture2;
    private Texture whiteBulletTexture;
    private Texture whiteBulletTexture1;
    private Texture whiteBulletTexture2;
    private static int bulletSpeed;
    private Vector2 positionBullet;
    private Rectangle boundsBullet;
    private Sprite bulletSprite;
    private int bulletSpeedX;
    private int nowLvl;




    public Bullet(float x, float y){
        nowLvl = 1;
        bulletSpeedX = 0;
        bulletSpeed = 400;
        bulletTexture = new Texture("data/miniBullet.png");
        bulletTexture1 = new Texture("data/leftBullet.png");
        bulletTexture2 = new Texture("data/rightBullet.png");
        whiteBulletTexture = new Texture("data/whiteBullet.png");
        whiteBulletTexture1 = new Texture("data/whiteLeftBullet.png");
        whiteBulletTexture2 = new Texture("data/whiteRightBullet.png");

        bulletSprite = new Sprite(bulletTexture);
        positionBullet = new Vector2(x, y);
        boundsBullet = new Rectangle(positionBullet.x, positionBullet.y, bulletSprite.getTexture().getWidth(), bulletSprite.getTexture().getHeight());
    }
    public Texture getBulletTexture() {
        return bulletSprite.getTexture();
    }

    public Vector2 getPositionBullet() {
        return positionBullet;
    }

    public Texture getBulletSprite() {
        return bulletTexture;
    }


    public Rectangle getBoundsBullet(){
        return boundsBullet;
    }


    public void reposition(float x, float y){
        positionBullet.set(x, y);
        boundsBullet.setPosition(positionBullet.x, positionBullet.y);
    }
    public void setSomething1()
    {
        bulletSpeedX = -400;
        bulletTexture = bulletTexture1;
        boundsBullet.setSize(bulletSprite.getTexture().getWidth(), bulletSprite.getTexture().getHeight());
    }
    public void setSomething2()
    {
        bulletSpeedX = 400;
        bulletTexture = bulletTexture2;
        boundsBullet.setSize(bulletSprite.getTexture().getWidth(), bulletSprite.getTexture().getHeight());
    }


    public void update(float dt){

        positionBullet.add(bulletSpeedX * dt, bulletSpeed * dt);
        boundsBullet.setPosition(positionBullet.x, positionBullet.y);

        }


    //public boolean collides(Rectangle boundsSamoliot){return boundsSamoliot.overlaps(boundsEnemy);
    //}
    public void lvlUp()
    {
            nowLvl=4;
            bulletTexture = whiteBulletTexture;
            bulletTexture1 = whiteBulletTexture1;
            bulletTexture2 = whiteBulletTexture2;

    }
    public void dispose() {
        bulletTexture.dispose();
        bulletTexture1.dispose();
        bulletTexture2.dispose();
        whiteBulletTexture.dispose();
        whiteBulletTexture1.dispose();
        whiteBulletTexture2.dispose();
    }
}