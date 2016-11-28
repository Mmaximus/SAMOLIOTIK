package ez.mmaximus.samoliotik.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Maximus on 05.09.2016.
 */
public class Samoliot {
    private Vector2 position;
    private Rectangle boundsSamoliot;
    private Rectangle boundsSamoliot1;
    private Rectangle boundsSamoliot2;
    private Texture samoliot;
    private Texture whiteSamoliot;
    private int nowLvl;

    public Samoliot(int x, int y){
        position = new Vector2(x, y);
        samoliot = new Texture("data/samoliotik.png");
        whiteSamoliot = new Texture("data/whiteSamoliotik.png");
        boundsSamoliot = new Rectangle(x, y, samoliot.getWidth(), samoliot.getHeight());
        boundsSamoliot1 = new Rectangle(x,y+40,samoliot.getWidth(),13);
        boundsSamoliot2 = new Rectangle(x+24,y,21,samoliot.getHeight());
        nowLvl = 1;
    }

    public Texture getSamoliotTexture() {
        return samoliot;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds(){
        return boundsSamoliot;
    }
    public Rectangle getBounds1(){
        return boundsSamoliot1;
    }
    public Rectangle getBounds2(){
        return boundsSamoliot2;
    }
    public void lvlUp()
    {
        nowLvl++;

        if(nowLvl == 4){
            samoliot = whiteSamoliot;
        }
    }




    public void reposition(float x, float y){
        position.set(x, y);
        boundsSamoliot.setPosition(position.x, position.y);
        boundsSamoliot1.setPosition(position.x, position.y + 40);
        boundsSamoliot2.setPosition(position.x+24,position.y);
    }

    public void update(float dt){
    }

    public void dispose() {
        samoliot.dispose();
    }
}
