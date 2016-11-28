package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Maximus on 24.10.2016.
 */
public class Enemy3 {
    private Texture enemyTexture;
    private Vector2 positionEnemy;
    private Rectangle boundsEnemy;
    private static int MOVEMENT;
    private Texture enemyTexture2;


    private Texture enemyTexture3;

    private Texture enemyTexture4;

    private int nowLvl;

    public Enemy3(float x, float y){
        nowLvl = 1;
        enemyTexture2 = new Texture("data/dollar3.png");
        enemyTexture3 = new Texture("data/balalaika3.png");
        enemyTexture4 = new Texture("data/fish3.png");
        MOVEMENT = 220;
        enemyTexture = new Texture("data/enemy3.png");
        positionEnemy = new Vector2(x, y);
        boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture.getWidth(), enemyTexture.getHeight());
    }
    public Texture getTextureEnemy() {
        return enemyTexture;
    }

    public Vector2 getPositionEnemy() {
        return positionEnemy;
    }

    public Rectangle getBoundsEnemy(){
        return boundsEnemy;
    }

    public void lvlUp()
    {
        nowLvl++;
        if(nowLvl == 2){
            enemyTexture = enemyTexture2;
            boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture2.getWidth(), enemyTexture2.getHeight());
        }
        if(nowLvl == 3){
            enemyTexture = enemyTexture3;
            boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture3.getWidth(), enemyTexture3.getHeight());
        }
        if(nowLvl == 4){
            enemyTexture = enemyTexture4;
            boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture4.getWidth(), enemyTexture4.getHeight());
        }
    }

    public void setEnemySpeed(int newEnemySpeed)
    {
        MOVEMENT = newEnemySpeed;
    }

    public void reposition(float x, float y){
        positionEnemy.set(x, y);
        boundsEnemy.setPosition(positionEnemy.x, positionEnemy.y);
    }

    public static int getMOVEMENT() {
        return MOVEMENT;
    }

    public void goToStart(){
        Random randomCoord = new Random();
        int newX = randomCoord.nextInt(960-enemyTexture.getWidth());
        int newY = randomCoord.nextInt(540) + 540 + 10;
        reposition(newX,newY);
    }

    public void update(float dt){
        positionEnemy.add(0, -MOVEMENT * dt);
        boundsEnemy.setPosition(positionEnemy.x, positionEnemy.y);
        if(positionEnemy.y<0-enemyTexture.getHeight() - 5) goToStart();
    }


    public boolean collides(Rectangle boundsObject){return boundsObject.overlaps(boundsEnemy);
    }

    public void dispose() {
        enemyTexture.dispose();
    }
}
