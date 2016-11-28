package ez.mmaximus.samoliotik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import java.util.Iterator;

import ez.mmaximus.samoliotik.Sprites.*;
import ez.mmaximus.samoliotik.Sprites.Samoliot;

public class SamoliotGame implements Screen {
	final Samoliotik game;

	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private Texture samoliotSprite;
	private float samoliotSpeed;
	//private Texture background;
	private Samoliot samoliot;
	private int gameScore;
	private static final int ENEMY_COUNT = 10;
	private Array<Enemy> enemy;
	private Array<Enemy2> enemy2;
	private Array<Enemy3> enemy3;
	private Array<Bullet> bullet;
	private ez.mmaximus.samoliotik.Buttons.FireButton fireButton;
	private Sound soundHitBoss;
	private Sound soundHitEnemy;


	//private Bullet bullet;
	private long lastShotTime;

	private BitmapFont font;

	private BossHitler bossHitler;
	private BossIlluminati bossIlluminati;
	private BossBear bossBear;
	private BossCtulhu bossCtulhu;

	private BonusStar bonusStar;
	private BonusVodka bonusVodka;
	private BonusKalashnikov bonusKalashnikov;
	private Timer timer1;
	private Timer timer2,timer3;
	private Timer.Task timerTask1;
	private Timer.Task timerTask2, timerTask3;
	private Timer timerLvl;
	private Timer.Task timerTaskLvl;
	private boolean triplHren;
	private int nowLvl;
	private boolean bossHitlerLvlUp;
	private boolean bossIlluminatiLvlUp;
	private boolean bossBearLvlUp;
	private boolean bossCtulhuLvlUp;
	private ShapeRenderer shapeRenderer;
	private Color backgroundColor;
	private boolean gameOver;
	private BigEnemy bigEnemy;


	//private Enemy enemy1;
	//private Texture enemySprite;
	public static final int BulletWidth = 10;

	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;

	public static final String TITLE = "SAMOLIOTIK";

	private static final String CHAR_STRING = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	FreeTypeFontGenerator generator;
	final String FILE_PATH = "tahoma.ttf";

	public SamoliotGame(final Samoliotik gam) {

		this.game = gam;


		gameOver = false;
		soundHitBoss = Gdx.audio.newSound(Gdx.files.internal("hitBoss.wav"));
		soundHitEnemy = Gdx.audio.newSound(Gdx.files.internal("hitEnemy.wav"));

		timerLvl = new Timer();
		backgroundColor = new Color(165 / 255f, 38 / 255f, 10 / 255f, 1);
		triplHren = false;
		bossHitlerLvlUp = true;
		bossIlluminatiLvlUp = true;
		bossBearLvlUp = true;
		bossCtulhuLvlUp = true;
		timer1 = new Timer();
		nowLvl = 1;
		timerTaskLvl = new Timer.Task() {
			@Override
			public void run() {
				nowLvl++;
				for (int i = 0; i < 3; i++){
					enemy.add((new Enemy(-300,-300)));;

				}
				for(Enemy enemyGO: enemy)
				{
					if(enemyGO.getNowLvl()==(nowLvl-1))enemyGO.lvlUp();
					else enemyGO.changeLvl(nowLvl);
				}
				bigEnemy.lvlUp();
				for(Enemy2 enemyGO: enemy2)
				{
					enemyGO.lvlUp();
				}
				for(Enemy3 enemyGO: enemy3)
				{
					enemyGO.lvlUp();
				}
				samoliot.lvlUp();


				if(nowLvl==2) backgroundColor = new Color(74 / 255f, 127 / 255f, 52 / 255f, 1);
				if(nowLvl==3) backgroundColor = new Color(201 / 255f, 77 / 255f, 8 / 255f, 1);
				if(nowLvl==4)
				{
					backgroundColor = new Color(20 / 255f, 7 / 255f, 131 / 255f, 1);
					bonusKalashnikov.lvlUp();
					bonusStar.lvlUp();
					bonusVodka.lvlUp();
					Iterator<Bullet> iter = bullet.iterator();
					while (iter.hasNext()) {
						iter.next().lvlUp();
					}
				}
			}
		};
		timerTask1 = new Timer.Task() {
			@Override
			public void run() {
				for (Enemy enemyGO : enemy) {
					enemyGO.setEnemySpeed(200);
				}
				bigEnemy.setEnemySpeed(350);
			}
		};
		timer3 = new Timer();
		timerTask3 = new Timer.Task() {
			@Override
			public void run() {
				game.setScreen(new EndOfGameScreen(game));
				dispose();
			}
		};
		timer2 = new Timer();

		timerTask2 = new Timer.Task() {
			@Override
			public void run() {
				triplHren = false;
			}
		};
		shapeRenderer = new ShapeRenderer();
		generator= new FreeTypeFontGenerator(Gdx.files.internal(FILE_PATH));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 23;
		parameter.borderWidth = 1;
		parameter.characters = CHAR_STRING;

		font = generator.generateFont(parameter);
		generator.dispose();

		batch = new SpriteBatch();
		//Create camera
		gameScore = 0;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
//сделать сразу посантре
		samoliot = new Samoliot(300, 300);
		bossHitler = new BossHitler(1000, 1000);
		bossHitler.reposition(WIDTH/2-bossHitler.getBossTexture().getWidth()/2, 600);

		bossIlluminati = new BossIlluminati(-1000, -1000);
		bossIlluminati.reposition(WIDTH/2-bossIlluminati.getBossTexture().getWidth()/2, 600);

		bossBear = new BossBear(1000, 1000);
		bossBear.reposition(WIDTH/2-bossBear.getBossTexture().getWidth()/2, 600);

		bossCtulhu = new BossCtulhu(1000, 1000);
		bossCtulhu.reposition(WIDTH/2-bossCtulhu.getBossTexture().getWidth()/2, 600);


		bonusStar = new BonusStar(-1000, -1000);
		bonusStar.goToStart();
		bonusVodka = new BonusVodka(-1000, -1000);
		bonusVodka.goToStart();
		bonusKalashnikov = new BonusKalashnikov(-1000, -1000);
		bonusKalashnikov.goToStart();

		bullet = new Array<Bullet>();
		lastShotTime = TimeUtils.nanoTime();
		enemy = new Array<Enemy>();
		enemy2 = new Array<Enemy2>();
		enemy3 = new Array<Enemy3>();
		bigEnemy = new BigEnemy(-300,-300);
//сделать сразу рандомно
		for (int i = 0; i < ENEMY_COUNT; i++){
			enemy.add(new Enemy(-300,-300));
		}

		for (int i = 0; i < ENEMY_COUNT; i++){
			enemy2.add(new Enemy2(-300,-300));
		}
		for (int i = 0; i < ENEMY_COUNT; i++){
			enemy3.add(new Enemy3(-300,-300));
		}
		//





		//background = new Texture(Gdx.files.internal("back.png"));

		//Create a touchpad skin
		touchpadSkin = new Skin();
		//Set background image
		touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));
		//Set knob image
		touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
		//Create TouchPad Style
		touchpadStyle = new TouchpadStyle();
		//Create Drawable's from TouchPad skin
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		//Apply the Drawables to the TouchPad Style
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		//Create new TouchPad with the created style
		touchpad = new Touchpad(10, touchpadStyle);
		//setBounds(x,y,width,height)
		touchpad.setBounds(15, 15, 200, 200);

		//-----------
		Texture textureUp = new Texture(Gdx.files.internal("data/touchBackground.png"));
		Texture textureDown = new Texture(Gdx.files.internal("data/fireButton.png"));
		Texture background = new Texture(Gdx.files.internal("data/touchBackground.png"));
		fireButton = new ez.mmaximus.samoliotik.Buttons.FireButton(textureUp, textureDown, background);
		fireButton.setPosition(WIDTH - 215, 15);
		//-----------
		//Create a Stage and add TouchPad


		camera.update();
		stage = new Stage(new ExtendViewport(WIDTH,HEIGHT));

		stage.addActor(touchpad);
		stage.addActor(fireButton);
		Gdx.input.setInputProcessor(stage);

		samoliotSprite = samoliot.getSamoliotTexture();
		samoliot.reposition(WIDTH / 2 - samoliotSprite.getWidth() / 2, HEIGHT / 2 - samoliotSprite.getHeight() / 2);
		samoliotSpeed = 5;


		//enemySprite = enemy1.getTextureEnemy();
	}


	public int getNowLvl() {
		return nowLvl;
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//------????????
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



		for (Enemy enemyGO : enemy) {
			enemyGO.update(Gdx.graphics.getDeltaTime());
		}
		for (Enemy2 enemyGO : enemy2) {
			enemyGO.update(Gdx.graphics.getDeltaTime());
		}
		for (Enemy3 enemyGO : enemy3) {
			enemyGO.update(Gdx.graphics.getDeltaTime());
		}
		//-------
		Iterator<Bullet> iter = bullet.iterator();
		while (iter.hasNext()) {
			iter.next().update(Gdx.graphics.getDeltaTime());
		}

		bossHitler.update(Gdx.graphics.getDeltaTime());
		bossIlluminati.update(Gdx.graphics.getDeltaTime());
		bossBear.update(Gdx.graphics.getDeltaTime());
		bossCtulhu.update(Gdx.graphics.getDeltaTime());
		bonusStar.update(Gdx.graphics.getDeltaTime());
		bonusVodka.update(Gdx.graphics.getDeltaTime());
		bonusKalashnikov.update(Gdx.graphics.getDeltaTime());
		bigEnemy.update(Gdx.graphics.getDeltaTime());

		float newX = (samoliot.getPosition().x + touchpad.getKnobPercentX()*samoliotSpeed);
		float newY = (samoliot.getPosition().y + touchpad.getKnobPercentY()*samoliotSpeed);

		if (newX + samoliot.getSamoliotTexture().getWidth() > WIDTH) newX = WIDTH - samoliot.getSamoliotTexture().getWidth();
		else if(newX < 0) newX = 0;

		if (newY + samoliot.getSamoliotTexture().getHeight() > HEIGHT) newY = HEIGHT - samoliot.getSamoliotTexture().getHeight();
		else if(newY < 0) newY = 0;

		samoliot.reposition(newX, newY);
		if(gameScore>=5 && bossHitler.getPositionBoss().y>500)
		{
			bossHitler.setBossSpeed(-100);
		}
		if(bossHitler.getPositionBoss().y<HEIGHT-bossHitler.getBossTexture().getHeight() && bossHitler.getBossHitPoint()>0)
		{
			bossHitler.reposition(WIDTH / 2 -  bossHitler.getBossTexture().getWidth() / 2, HEIGHT - bossHitler.getBossTexture().getHeight());
			bossHitler.setBossSpeed(0);
		}
		if(bossHitler.getBossHitPoint()<=0 && bossHitler.getPositionBoss().y<HEIGHT)
			{
				bossHitler.setBossSpeed(-100);
				if(timerLvl.isEmpty() && bossHitlerLvlUp)
				{
					timerLvl.scheduleTask(timerTaskLvl, 7);
					bossHitlerLvlUp = false;
				}
			}

		if(gameScore>=10 && bossIlluminati.getPositionBoss().y>500 && bossHitler.getBossHitPoint()<=0)
		if(gameScore>=10 && bossIlluminati.getPositionBoss().y>500 && bossHitler.getBossHitPoint()<=0)
		{
			bossIlluminati.setBossSpeed(-100);

		}
		if(bossIlluminati.getPositionBoss().y<HEIGHT-bossIlluminati.getBossTexture().getHeight() && bossIlluminati.getBossHitPoint()>0)
		{
			bossIlluminati.reposition(WIDTH / 2 -  bossIlluminati.getBossTexture().getWidth() / 2, HEIGHT - bossIlluminati.getBossTexture().getHeight());
			bossIlluminati.setBossSpeed(0);
		}
		if(bossIlluminati.getBossHitPoint()<=0)
		{
			bossIlluminati.setBossSpeed(-100);
			if(timerLvl.isEmpty() && bossIlluminatiLvlUp)
			{
				timerLvl.scheduleTask(timerTaskLvl, 7);
				bossIlluminatiLvlUp = false;
			}
		}

		if(gameScore>=15 && bossBear.getPositionBoss().y>500 && bossIlluminati.getBossHitPoint()<=0)
		{
			bossBear.setBossSpeed(-100);

		}
		if(bossBear.getPositionBoss().y<HEIGHT-bossBear.getBossTexture().getHeight() && bossBear.getBossHitPoint()>0)
		{
			bossBear.reposition(WIDTH / 2 - bossBear.getBossTexture().getWidth() / 2, HEIGHT - bossBear.getBossTexture().getHeight());
			bossBear.setBossSpeed(0);
		}
		if(bossBear.getBossHitPoint()<=0)
		{
			bossBear.setBossSpeed(-100);
			if(timerLvl.isEmpty() && bossBearLvlUp)
			{
				timerLvl.scheduleTask(timerTaskLvl, 7);
				bossBearLvlUp = false;
			}
		}

		if(gameScore>=20 && bossCtulhu.getPositionBoss().y>500 && bossBear.getBossHitPoint()<=0)
		{
			bossCtulhu.setBossSpeed(-100);

		}
		if(bossCtulhu.getPositionBoss().y<HEIGHT-bossCtulhu.getBossTexture().getHeight() && bossCtulhu.getBossHitPoint()>0)
		{
			bossCtulhu.reposition(WIDTH / 2 -  bossCtulhu.getBossTexture().getWidth() / 2, HEIGHT - bossCtulhu.getBossTexture().getHeight());
			bossCtulhu.setBossSpeed(0);
		}
		if(bossCtulhu.getBossHitPoint()<=0)
		{
			bossCtulhu.setBossSpeed(-100);
			if(timer3.isEmpty() && bossCtulhuLvlUp)
			{
				timer3.scheduleTask(timerTask3, 4);
				bossCtulhuLvlUp = false;
			}
		}



		//Draw
		//---------------
		if(fireButton.isPressed())
		{
			if (TimeUtils.nanoTime() - lastShotTime > 500000000)
			{
				Bullet bullet1 = new Bullet(samoliot.getPosition().x +samoliot.getSamoliotTexture().getWidth()/2-BulletWidth/2
						, samoliot.getPosition().y+samoliot.getSamoliotTexture().getHeight());
				if(nowLvl==4) bullet1.lvlUp();
				bullet.add(bullet1);
				if(triplHren == true)
				{
					Bullet bullet2 = new Bullet(samoliot.getPosition().x +samoliot.getSamoliotTexture().getWidth()/2-BulletWidth/2-25
							, samoliot.getPosition().y+samoliot.getSamoliotTexture().getHeight());
					if(nowLvl==4)bullet2.lvlUp();
					bullet.add(bullet2);
					bullet2.setSomething1();

					Bullet bullet3 = new Bullet(samoliot.getPosition().x +samoliot.getSamoliotTexture().getWidth()/2-BulletWidth/2+25
							, samoliot.getPosition().y+samoliot.getSamoliotTexture().getHeight());
					if(nowLvl==4) bullet3.lvlUp();
					bullet.add(bullet3);
					bullet3.setSomething2();
				}
				lastShotTime = TimeUtils.nanoTime();
			}
		}
		//---------------


		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		//batch.draw(background, 0, 0);
		for (Enemy3 enemyGO : enemy3) {
			batch.draw(enemyGO.getTextureEnemy(), enemyGO.getPositionEnemy().x, enemyGO.getPositionEnemy().y);
		}
		for (Enemy2 enemyGO : enemy2) {
			batch.draw(enemyGO.getTextureEnemy(), enemyGO.getPositionEnemy().x, enemyGO.getPositionEnemy().y);
		}
		font.setColor(Color.BLACK);
		font.draw(batch, "SCORE:" + gameScore, 20, HEIGHT - 20);

		batch.draw(samoliot.getSamoliotTexture(), samoliot.getPosition().x, samoliot.getPosition().y);

		//batch.draw(enemy1.getTextureEnemy(), enemy1.getPositionEnemy().x, enemy1.getPositionEnemy().y);
		for (Bullet bulletGO : bullet) {
			batch.draw(bulletGO.getBulletSprite(),bulletGO.getPositionBullet().x,bulletGO.getPositionBullet().y);
		}


		for (Enemy enemyGO : enemy) {
			batch.draw(enemyGO.getTextureEnemy(), enemyGO.getPositionEnemy().x, enemyGO.getPositionEnemy().y);
		}
		batch.draw(bigEnemy.getTextureEnemy(),bigEnemy.getPositionEnemy().x,bigEnemy.getPositionEnemy().y);



		batch.draw(bonusStar.getTextureBonus(), bonusStar.getPositionBonus().x, bonusStar.getPositionBonus().y);
		batch.draw(bonusVodka.getTextureBonus(),bonusVodka.getPositionBonus().x, bonusVodka.getPositionBonus().y);
		batch.draw(bonusKalashnikov.getTextureBonus(), bonusKalashnikov.getPositionBonus().x, bonusKalashnikov.getPositionBonus().y);
		batch.draw(bossHitler.getBossTexture(), bossHitler.getPositionBoss().x, bossHitler.getPositionBoss().y);
		batch.draw(bossIlluminati.getBossTexture(), bossIlluminati.getPositionBoss().x, bossIlluminati.getPositionBoss().y);
		batch.draw(bossBear.getBossTexture(), bossBear.getPositionBoss().x, bossBear.getPositionBoss().y);
		batch.draw(bossCtulhu.getBossTexture(), bossCtulhu.getPositionBoss().x, bossCtulhu.getPositionBoss().y);
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();


		Iterator<Bullet> iteratorBullet = bullet.iterator();
		while (iteratorBullet.hasNext()) {
			if(iteratorBullet.next().getPositionBullet().y > HEIGHT) iteratorBullet.remove();
		}

		if(bigEnemy.collides(samoliot.getBounds1())|| bigEnemy.collides(samoliot.getBounds2())) gameOver=true;

		for (Enemy enemyGO : enemy) {
			if(enemyGO.collides(samoliot.getBounds1()) || enemyGO.collides(samoliot.getBounds2())){
				gameOver = true;

			}
		}

		if(gameOver == true)
		{
			timer3.clear();
			game.setScreen(new GameOverScreen(game));

			dispose();

		}

		for (Enemy enemyGO : enemy) {
			Iterator<Bullet> iteratorCollides = bullet.iterator();
			while (iteratorCollides.hasNext()) {
				if (enemyGO.collides(iteratorCollides.next().getBoundsBullet())) {

					enemyGO.goToStart();
					iteratorCollides.remove();
					gameScore++;
					if(MainMenuScreen.settings.getBoolean("sound", true))soundHitEnemy.play();
				}
			}
		}
		Iterator<Bullet> iteratorCollidesBig = bullet.iterator();
		while (iteratorCollidesBig.hasNext()) {
			if (bigEnemy.collides(iteratorCollidesBig.next().getBoundsBullet()))
			{
				bigEnemy.goToStart();
				gameScore++;
				if(MainMenuScreen.settings.getBoolean("sound", true))soundHitEnemy.play();
				iteratorCollidesBig.remove();
			}
		}
		//устранение коллизий самолетов
		/*
		for (int i = 0; i < ENEMY_COUNT + (nowLvl-1)*4; i++) {
			for (int j = 0; j < ENEMY_COUNT + (nowLvl-1)*4; j++) {

				if (i!=j && enemy.items[i].collides(enemy.items[j].getBoundsEnemy()))
				{
					if(enemy.items[i].getPositionEnemy().y>enemy.items[j].getPositionEnemy().y)
					{
						enemy.items[i].reposition(enemy.items[i].getPositionEnemy().x, enemy.items[i].getPositionEnemy().y - 100);
					}
				}
			}
		}*/

		Iterator<Bullet> iteratorCollidesBoss = bullet.iterator();
		while (iteratorCollidesBoss.hasNext()) {
			if (bossHitler.collides(iteratorCollidesBoss.next().getBoundsBullet())) {
				bossHitler.bossDamage();
				iteratorCollidesBoss.remove();
				if(MainMenuScreen.settings.getBoolean("sound", true)) soundHitBoss.play(0.7f);
			}
		}
		Iterator<Bullet> iteratorCollidesBoss2 = bullet.iterator();
		while (iteratorCollidesBoss2.hasNext()) {
			if (bossIlluminati.collides(iteratorCollidesBoss2.next().getBoundsBullet())) {
				bossIlluminati.bossDamage();
				iteratorCollidesBoss2.remove();
				if(MainMenuScreen.settings.getBoolean("sound", true)) soundHitBoss.play();
			}
		}
		Iterator<Bullet> iteratorCollidesBoss3 = bullet.iterator();
		while (iteratorCollidesBoss3.hasNext()) {

			if (bossBear.collides(iteratorCollidesBoss3.next().getBoundsBullet())) {
				bossBear.bossDamage();
				iteratorCollidesBoss3.remove();
				if(MainMenuScreen.settings.getBoolean("sound", true)) soundHitBoss.play();
			}
		}
		Iterator<Bullet> iteratorCollidesBoss4 = bullet.iterator();
		while (iteratorCollidesBoss4.hasNext()) {

			if (bossCtulhu.collides(iteratorCollidesBoss4.next().getBoundsBullet())) {
				bossCtulhu.bossDamage();
				iteratorCollidesBoss4.remove();
				if(MainMenuScreen.settings.getBoolean("sound", true)) soundHitBoss.play();
			}
		}

		if(bonusStar.collides(samoliot.getBounds1()) ||  bonusStar.collides(samoliot.getBounds2())){
			bonusStar.goToStart();
			for(int i = 0; i< 10; i++)
			{
				Bullet bulletSupp1 = new Bullet((i+1)*90,10);
				Bullet bulletSupp2 = new Bullet((i+1)*90,-500);
				if(nowLvl==4){
					bulletSupp1.lvlUp();
					bulletSupp2.lvlUp();
				}
				bullet.add(bulletSupp1);
				bullet.add(bulletSupp2);
			}

		}
		if(bonusStar.getPositionBonus().y<-bonusStar.getTextureBonus().getHeight()-20) bonusStar.goToStart();

		if(bonusVodka.collides(samoliot.getBounds1()) || bonusVodka.collides(samoliot.getBounds2())){
			bonusVodka.goToStart();

			for (Enemy enemyGO : enemy) {
				enemyGO.setEnemySpeed(50);
			}
			bigEnemy.setEnemySpeed(100);

			if(timer1.isEmpty())
			timer1.scheduleTask(timerTask1, 4);



		}
		if(bonusVodka.getPositionBonus().y<-bonusVodka.getTextureBonus().getHeight()-20) bonusVodka.goToStart();

		if(bonusKalashnikov.collides(samoliot.getBounds1()) || bonusKalashnikov.collides(samoliot.getBounds2())){
			bonusKalashnikov.goToStart();
			triplHren = true;
			if(timer2.isEmpty())
				timer2.scheduleTask(timerTask2, 8);

		}
		if(bonusKalashnikov.getPositionBonus().y<-bonusKalashnikov.getTextureBonus().getHeight()-20) bonusKalashnikov.goToStart();

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}
	@Override
	public void dispose() {
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
		samoliot.dispose();
		bossHitler.dispose();
		bossIlluminati.dispose();
		bossBear.dispose();
		bossCtulhu.dispose();
		bigEnemy.dispose();

		Iterator<Bullet> iteratorDispose = bullet.iterator();
		while (iteratorDispose.hasNext()) {
			iteratorDispose.next().dispose();
		}

		for (Enemy enemyGO : enemy) {

			enemyGO.dispose();
		}

		for (Enemy2 enemyGO : enemy2) {
			enemyGO.dispose();
		}
		for (Enemy3 enemyGO : enemy3) {
			enemyGO.dispose();
		}



		stage.dispose();
	}


	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
}