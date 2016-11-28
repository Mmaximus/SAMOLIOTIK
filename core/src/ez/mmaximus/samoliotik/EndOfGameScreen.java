package ez.mmaximus.samoliotik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Maximus on 26.11.2016.
 */
public class EndOfGameScreen implements Screen {
    final Samoliotik game;
    private static final String CHAR_STRING = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture win, text;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    FreeTypeFontGenerator generator;
    //final String text = "Победа, Товарищ";

    final String FILE_PATH = "tahoma.ttf";

    public EndOfGameScreen(final Samoliotik gam) {
        game = gam;
        shapeRenderer= new ShapeRenderer();
        win = new Texture("data/win.png");
        text = new Texture("data/text.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 540);
        batch = new SpriteBatch();
        generator= new FreeTypeFontGenerator(Gdx.files.internal(FILE_PATH));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        parameter.characters = CHAR_STRING;
        Gdx.input.setCatchBackKey(true);
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
        batch.draw(win, (960 - win.getWidth()) / 2, 540-win.getHeight()-40);
        batch.draw(text,(960-text.getWidth())/2, 540-win.getHeight()-40-text.getHeight()-40);
        batch.end();

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