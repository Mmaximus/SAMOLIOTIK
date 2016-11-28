package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by Maximus on 05.09.2016.
 */
public class Enemy {

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
    public Enemy(float x, float y){



        MOVEMENT = 200;
        nowLvl = 1;



        enemyTexture = new Texture("data/enemy.png");
        enemyTexture2 = new Texture("data/dollar.png");
        enemyTexture3 = new Texture("data/balalaika.png");
        enemyTexture4 = new Texture("data/fish.png");
        positionEnemy = new Vector2(x, y);
        boundsEnemy = new Rectangle(positionEnemy.x, positionEnemy.y, enemyTexture.getWidth(), enemyTexture.getHeight());
        boundsEnemy1 = new Rectangle(positionEnemy.x+26, positionEnemy.y, 12, enemyTexture.getHeight());
        boundsEnemy2 = new Rectangle(positionEnemy.x, positionEnemy.y+31, enemyTexture.getWidth(), 15);
        boundsEnemy3 = new Rectangle(positionEnemy.x,positionEnemy.y,8,enemyTexture.getHeight());
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

    public int getNowLvl() {
        return nowLvl;
    }

    public void changeLvl(int lvl)
    {
        nowLvl = lvl;
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
        boundsEnemy1.setPosition(positionEnemy.x+26, positionEnemy.y);
        boundsEnemy2.setPosition(positionEnemy.x, positionEnemy.y+31);
        boundsEnemy3.setPosition(positionEnemy.x+21,positionEnemy.y);
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
        boundsEnemy1.setPosition(positionEnemy.x + 26, positionEnemy.y);
        boundsEnemy2.setPosition(positionEnemy.x, positionEnemy.y+31);
        boundsEnemy3.setPosition(positionEnemy.x+21,positionEnemy.y);
        if(positionEnemy.y<0-enemyTexture.getHeight() - 5) goToStart();
    }


    public boolean collides(Rectangle boundsObject){
        if(nowLvl==1) return (boundsObject.overlaps(boundsEnemy1) || boundsObject.overlaps(boundsEnemy2));
        else if(nowLvl==3){
            if (boundsObject.overlaps(boundsEnemy3)) return true;

            else if(onTriangle(boundsObject.x + boundsObject.getWidth(), boundsObject.y+boundsObject.getHeight(),
                    positionEnemy.x+25,positionEnemy.y+47,
                    positionEnemy.x,positionEnemy.y,
                    positionEnemy.x+enemyTexture3.getWidth(),positionEnemy.y)) return true;
            else if(onTriangle(boundsObject.x, boundsObject.y + boundsObject.getHeight(),
                    positionEnemy.x + 25, positionEnemy.y + 47,
                    positionEnemy.x, positionEnemy.y,
                    positionEnemy.x + enemyTexture3.getWidth(), positionEnemy.y )) return true;
            else if (onTriangle(boundsObject.x, boundsObject.y,
                    positionEnemy.x + 25, positionEnemy.y + 47,
                    positionEnemy.x, positionEnemy.y,
                    positionEnemy.x + enemyTexture3.getWidth(), positionEnemy.y )) return true;
            else if (onTriangle(boundsObject.x+ boundsObject.getWidth(), boundsObject.y,
                    positionEnemy.x + 25, positionEnemy.y + 47,
                    positionEnemy.x, positionEnemy.y,
                    positionEnemy.x + enemyTexture3.getWidth(), positionEnemy.y )) return true;
            else return false;
        }
        else return boundsObject.overlaps(boundsEnemy);

    }

    public void dispose() {
        enemyTexture.dispose();
        enemyTexture2.dispose();
        enemyTexture3.dispose();
        enemyTexture4.dispose();
    }
}