package ez.mmaximus.samoliotik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Maximus on 02.11.2016.
 */
public class AboutScreen implements Screen {
    private static final String CHAR_STRING = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    final Samoliotik game;
    private ShapeRenderer shapeRenderer;
    OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    FreeTypeFontGenerator generator;
    final String FILE_PATH = "tahoma.ttf";


    public AboutScreen(final Samoliotik gam) {
        game = gam;
        Gdx.input.setCatchBackKey(true);
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 540);
        batch = new SpriteBatch();
        //font = new BitmapFont();
        generator= new FreeTypeFontGenerator(Gdx.files.internal(FILE_PATH));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.characters = CHAR_STRING;

        font = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(165 / 255f, 38 / 255f, 10 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 30 / 255f);
        Matrix4 mat = camera.combined.cpy();
        mat.setToOrtho2D(0, 0, 960, 540);
        shapeRenderer.setProjectionMatrix(mat);
        for(int i = 0; i <= 54; i++) {
            if(i%2==0) shapeRenderer.rect(0, 10*i, 960, 10);
        }
        shapeRenderer.end();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.setColor(Color.LIME);
        font.draw(batch, "Разработка - Maximus.ez\n" +
                "\n" +
                "Оформление - John Preston\n\n" +
                "Музыка - HateBit\n\n" +
                "Отдельное спасибо ребятам с сайта Noun Project:\n" +
                "Yuri Mazursky, Austin Andrews, Gregor Črešnar, Oleg Frolov, José Manuel\n" +
                "de Laá, enrico chialastri, Sergio Morozov, karol, John T. Garcia,\n" +
                "Sofya Ovchinnikova, Devin Goodall, iconic, ✦ Shmidt Sergey ✦,\n" +
                "Celina Chang,Dmitry Mirolyubov, Alexey Bondarenko, Studio Fibonacci,\n" +
                "Creativ Stail, corpus delicti, Artem Kovyazin,.\n", 10 , 515);
        batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }


    @Override
    public void resize(int width, int height) {
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
        font.dispose();
        batch.dispose();
    }
}