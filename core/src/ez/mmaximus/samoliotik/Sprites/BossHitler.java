package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maximus on 22.10.2016.
 */
public class BossHitler {
    private Texture bossTexture;
    private static int bossSpeed;
    private Vector2 positionBoss;
    private Rectangle boundsBoss;
    private int bossHitPoint;
    private Rectangle rect1, rect2, rect3, rect4;


    public BossHitler(float x, float y){
        bossSpeed = 0;
        bossTexture = new Texture("data/bossHitler.png");
        positionBoss = new Vector2(x, y);
        boundsBoss = new Rectangle(positionBoss.x, positionBoss.y, bossTexture.getWidth(), bossTexture.getHeight());
        bossHitPoint = 1;
        rect1 = new Rectangle(positionBoss.x+8, positionBoss.y+100, 217, 73);
        rect2 = new Rectangle(positionBoss.x+70, positionBoss.y+10, 93, 290);
        rect3 = new Rectangle(positionBoss.x+33, positionBoss.y+45, 166, 55);
        rect4 = new Rectangle(positionBoss.x+20, positionBoss.y+175, 180, 106);


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
        rect1.setPosition(positionBoss.x + 8, positionBoss.y + 100);
        rect2.setPosition(positionBoss.x+70, positionBoss.y+10);
        rect3.setPosition(positionBoss.x+33, positionBoss.y+45);
        rect4.setPosition(positionBoss.x+20, positionBoss.y+175);
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
        rect1.setPosition(positionBoss.x + 8, positionBoss.y + 100);
        rect2.setPosition(positionBoss.x+70, positionBoss.y+10);
        rect3.setPosition(positionBoss.x + 33, positionBoss.y + 45);
        rect4.setPosition(positionBoss.x+20, positionBoss.y+175);
    }


    public boolean collides(Rectangle boundsBullet){
        return boundsBullet.overlaps(rect1) || boundsBullet.overlaps(rect2) || boundsBullet.overlaps(rect3) || boundsBullet.overlaps(rect4);
    }

    public void dispose() {
        bossTexture.dispose();
    }
}
