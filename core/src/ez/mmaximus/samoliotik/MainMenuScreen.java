package ez.mmaximus.samoliotik;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import ez.mmaximus.samoliotik.Buttons.FireButton;
import ez.mmaximus.samoliotik.Buttons.MenuButton;
import ez.mmaximus.samoliotik.Buttons.PlayButton;

import java.util.Random;

import ez.mmaximus.samoliotik.Sprites.Enemy2;
import ez.mmaximus.samoliotik.Sprites.Enemy3;


/**
 * Created by Maximus on 22.10.2016.
 */
public class MainMenuScreen implements Screen {
    final Samoliotik game;
    OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Texture logo;
    private boolean aboutPressed;
    private Texture playButton1;
    private Texture soundOn, soundOff;
    private Texture musicOn, musicOff;
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;
    public static final int ENEMY_COUNT = 12;
    private PlayButton nonSound;
    private PlayButton sound;
    private PlayButton play;
    private PlayButton nonMusic;
    private PlayButton music;
    private MenuButton about;
    private Texture aboutTexture;
    public static Preferences settings;

    private Array<Enemy2> enemy2;
    private Array<Enemy3> enemy3;
    private Stage stage;
    private Sound soundPlay;

    public MainMenuScreen(final Samoliotik gam) {
        game = gam;
        aboutPressed=false;
        settings = Gdx.app.getPreferences("settings");
        soundPlay = Gdx.audio.newSound(Gdx.files.internal("play.mp3"));
        stage = new Stage(new ExtendViewport(WIDTH,HEIGHT));
        playButton1 = new Texture("data/playButton.png");
        soundOff = new Texture("data/nonSound.png");
        soundOn = new Texture("data/sound.png");
        aboutTexture = new Texture("data/about.png");

        musicOff = new Texture("data/nonMusic.png");
        musicOn = new Texture("data/music.png");
        logo = new Texture("data/Logo.png");
        about = new MenuButton(aboutTexture);
        play = new PlayButton(playButton1);
        sound = new PlayButton(soundOn);
        nonSound = new PlayButton(soundOff);
        music = new PlayButton(musicOn);
        nonMusic = new PlayButton(musicOff);
        about.setPosition(10,10);
        play.setPosition((WIDTH - play.getWidth()) / 2, HEIGHT / 4);
        nonSound.setPosition((WIDTH - logo.getWidth()) / 2 + 80, HEIGHT / 4 + 60);
        sound.setPosition((WIDTH - logo.getWidth()) / 2 + 80, HEIGHT / 4 + 60);
        nonMusic.setPosition((WIDTH + play.getWidth()) / 2 + 60, HEIGHT / 4 + 60);
        music.setPosition((WIDTH + play.getWidth()) / 2 + 60, HEIGHT / 4 + 60);
        sound.setVisible(settings.getBoolean("sound", true));
        music.setVisible(settings.getBoolean("music", true));


        nonSound.setVisible(!settings.getBoolean("sound", true));
        nonMusic.setVisible(!settings.getBoolean("music", true));
        stage.addActor(about);
        stage.addActor(play);
        stage.addActor(sound);
        stage.addActor(nonSound);
        stage.addActor(music);
        stage.addActor(nonMusic);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        enemy2 = new Array<Enemy2>();
        enemy3 = new Array<Enemy3>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 540);
        batch = new SpriteBatch();
        font = new BitmapFont();


        shapeRenderer = new ShapeRenderer();
        Random randomCoord = new Random();
        for (int i = 0; i < ENEMY_COUNT; i++){
            enemy2.add(new Enemy2(randomCoord.nextInt(960-65),randomCoord.nextInt(540-69)));
        }
        for (int i = 0; i < ENEMY_COUNT; i++){
            enemy3.add(new Enemy3(randomCoord.nextInt(960-65),randomCoord.nextInt(540-69)));
        }
        //play.setVisible(false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(165 / 255f, 38 / 255f, 10 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Enemy2 enemyGO : enemy2) {
            enemyGO.update(Gdx.graphics.getDeltaTime());
        }
        for (Enemy3 enemyGO : enemy3) {
            enemyGO.update(Gdx.graphics.getDeltaTime());
        }
        camera.update();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 30 / 255f);
        Matrix4 mat = camera.combined.cpy();
        mat.setToOrtho2D(0, 0, WIDTH, HEIGHT);
        shapeRenderer.setProjectionMatrix(mat);
        for(int i = 0; i <= 54; i++) {
            if(i%2==0) shapeRenderer.rect(0, 10*i, WIDTH, 10);
        }
        shapeRenderer.end();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (Enemy3 enemyGO : enemy3) {
            batch.draw(enemyGO.getTextureEnemy(), enemyGO.getPositionEnemy().x, enemyGO.getPositionEnemy().y);
        }
        for (Enemy2 enemyGO : enemy2) {
            batch.draw(enemyGO.getTextureEnemy(), enemyGO.getPositionEnemy().x, enemyGO.getPositionEnemy().y);
        }
        batch.draw(logo, (WIDTH - logo.getWidth()) / 2, HEIGHT * 3 / 4 - 40);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if(about.isPressed())
        {
            aboutPressed = true;
        }
        if(!about.isPressed() && aboutPressed){
            aboutPressed = false;
            if(settings.getBoolean("sound", true)) soundPlay.play();
            game.setScreen(new AboutScreen(game));
            dispose();
        }
        if(play.isPressed())
        {
            if(settings.getBoolean("sound", true)) soundPlay.play();
            game.setScreen(new SamoliotGame(game));
            dispose();
        }
        if(sound.isPressed())
        {
            sound.setVisible(false);
            nonSound.setVisible(true);
            settings.putBoolean("sound", false);

            settings.flush();
        }

        if(nonSound.isPressed())
        {
            nonSound.setVisible(false);
            sound.setVisible(true);
            settings.putBoolean("sound", true);
            settings.flush();
        }
        if (music.isPressed())
        {
            music.setVisible(false);
            nonMusic.setVisible(true);
            settings.putBoolean("music", false);
            settings.putFloat("volumeMusic", 0);
            settings.flush();
        }

        if(nonMusic.isPressed())
        {
            nonMusic.setVisible(false);
            music.setVisible(true);
            settings.putBoolean("music", true);
            settings.putFloat("volumeMusic", 0.12f);
            settings.flush();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

        for (Enemy2 enemyGO : enemy2) {
            enemyGO.dispose();
        }
        for (Enemy3 enemyGO : enemy3) {
            enemyGO.dispose();
        }
        font.dispose();
        batch.dispose();
        soundPlay.dispose();
    }
}