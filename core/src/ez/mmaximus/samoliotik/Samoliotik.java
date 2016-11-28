package ez.mmaximus.samoliotik;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.Random;

/**
 * Created by Maximus on 22.10.2016.
 */
public class Samoliotik extends Game {
    private Music music;
    private Music music1;
    private Music music2;
    private Music music3;
    private Music music4;
    private Music music5;
    private Random random;
    private boolean onPause;


    public void create() {
        music1 = Gdx.audio.newMusic(Gdx.files.internal("1.mp3"));
        music2 = Gdx.audio.newMusic(Gdx.files.internal("2.mp3"));
        music3 = Gdx.audio.newMusic(Gdx.files.internal("3.mp3"));
        music4 = Gdx.audio.newMusic(Gdx.files.internal("4.mp3"));
        music5 = Gdx.audio.newMusic(Gdx.files.internal("5.mp3"));
        music = music1;
        random = new Random();
        setMusic();

        onPause = true;


        this.setScreen(new MainMenuScreen(this));
    }

    public void setMusic(){

        int musicNumber = random.nextInt(5)+1;
        if(musicNumber==1) music = music1;
        if(musicNumber==2) music = music2;
        if(musicNumber==3) music = music3;
        if(musicNumber==4) music = music4;
        if(musicNumber==5) music = music5;
        music.setVolume(0.12f);

    }

    public void render() {
        super.render(); // важно!
        music.setVolume(MainMenuScreen.settings.getFloat("volumeMusic", 0.12f));
        if(!music.isPlaying() && !onPause)
        {
            setMusic();
            music.play();
        }
        if(!MainMenuScreen.settings.getBoolean("music", true)){
            music.pause();
            onPause=true;

        }
        if(!music.isPlaying() && MainMenuScreen.settings.getBoolean("music", true)){
            music.play();
            onPause=false;
        }


    }
    @Override
    public void pause() {
        music.pause();
    }
    @Override
    public void resume() {
    }
    public void dispose() {
        music.dispose();
        music1.dispose();
        music2.dispose();
        music3.dispose();
        music4.dispose();
        music5.dispose();

    }

}