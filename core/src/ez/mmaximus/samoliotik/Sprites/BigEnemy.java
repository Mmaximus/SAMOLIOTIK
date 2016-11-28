package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Maximus on 26.11.2016.
 */
public class BigEnemy {

    private Texture enemyTexture;

    private Texture enemyTexture2;


    private Texture enemyTexture3;

    private Texture enemyTexture4;

    private int nowLvl;
    private Vector2 positionEnemy;
    private Rectangle boundsEnemy;
    private Rectangle boundsEnemy1;
    private Rectangle boundsEnemy2;
    private Rectangle boundsEnemy3;
    private static int MOVEMENT;
    public BigEnemy(float x, float y){



        MOVEMENT = 350;
        nowLvl = 1;



        enemyTexture = new Texture("data/BigEnemy.png");
        enemyTexture2 = new Texture("data/BigEuro.png");
        enemyTexture3 = new Texture("data/BigMatr.png");
        enemyTexture4 = new Texture("data/BigOct.png");
        positionEnemy = new Vector2(x, y);
        boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture.getWidth(), enemyTexture.getHeight());
        boundsEnemy1 = new Rectangle(positionEnemy.x+53, positionEnemy.y, 26, enemyTexture.getHeight());
        boundsEnemy2 = new Rectangle(positionEnemy.x, positionEnemy.y+65, enemyTexture.getWidth(), 23);
        boundsEnemy3 = new Rectangle(positionEnemy.x+18,positionEnemy.y,enemyTexture.getWidth()-18,enemyTexture.getHeight());
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

    public void setEnemySpeed(int newEnemySpeed)
    {
        MOVEMENT = newEnemySpeed;
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


    public void reposition(float x, float y){
        positionEnemy.set(x, y);
        boundsEnemy.setPosition(positionEnemy.x, positionEnemy.y);
        boundsEnemy1.setPosition(positionEnemy.x+53, positionEnemy.y);
        boundsEnemy2.setPosition(positionEnemy.x, positionEnemy.y+65);
        boundsEnemy3.setPosition(positionEnemy.x+18,positionEnemy.y);
    }


    public void goToStart(){
        Random randomCoord = new Random();
        int newX = randomCoord.nextInt(960-enemyTexture.getWidth());
        int newY = randomCoord.nextInt(1000) + 1540 + 10;
        reposition(newX,newY);
    }

    public void update(float dt){
        positionEnemy.add(0, -MOVEMENT * dt);
        boundsEnemy.setPosition(positionEnemy.x, positionEnemy.y);
        boundsEnemy1.setPosition(positionEnemy.x + 53, positionEnemy.y);
        boundsEnemy2.setPosition(positionEnemy.x, positionEnemy.y+65);
        boundsEnemy3.setPosition(positionEnemy.x+18,positionEnemy.y);
        if(positionEnemy.y<0-enemyTexture.getHeight() - 5) goToStart();
    }


    public boolean collides(Rectangle boundsObject){
        if(nowLvl==1) return (boundsObject.overlaps(boundsEnemy1) || boundsObject.overlaps(boundsEnemy2));
        else if(nowLvl==2 && boundsObject.overlaps(boundsEnemy3)) return true;
        else return boundsObject.overlaps(boundsEnemy);
    }

    public void dispose() {
        enemyTexture.dispose();
        enemyTexture2.dispose();
        enemyTexture3.dispose();
        enemyTexture4.dispose();
    }
}
