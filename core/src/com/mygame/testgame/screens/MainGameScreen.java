package com.mygame.testgame.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygame.testgame.Main;

public class MainGameScreen implements Screen{
	Main main;
	Texture img1, food, backgroundImg;
	
	Sound score_effect = Gdx.audio.newSound(Gdx.files.internal("point_effect.mp3"));
	Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.wav"));
	
	Random r = new Random();
	
	float x, y;
	public static float SPEED = 240;
	float foodPosX = r.nextInt(800);
	float foodPosY = r.nextInt(600);
	int score = 0;
	
	BitmapFont font;
	Preferences prefs = Gdx.app.getPreferences("high-score.prefs");
	
	public MainGameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		img1 = new Texture("spider.png");
		food = new Texture("food.png");
		backgroundImg = new Texture("background.jpg");
		
		font = new BitmapFont();
		
		backgroundMusic.play();
		backgroundMusic.setPan(0, 0.6f);
		backgroundMusic.setLooping(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(Math.abs(x-foodPosX) <= 30F && Math.abs(y-foodPosY) <= 30F) {
			foodPosX = r.nextInt(650)+50;
			foodPosY = r.nextInt(450)+50;
			SPEED += 10;
			score++;
			score_effect.play(0.4f);
		}
	
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y = y + SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x = x + SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y = y - SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x = x - SPEED*Gdx.graphics.getDeltaTime();
		}
		
		main.batch.begin();
		main.batch.draw(backgroundImg, 0, 0);
		main.batch.draw(img1, x, y);
		main.batch.draw(food, foodPosX, foodPosY, 40, 60);
		font.draw(main.batch, "SCORE: " + score, 400, 600);
		font.draw(main.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 600);
		main.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		prefs.putInteger("high_score", score);
		prefs.flush();
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		img1.dispose();
		food.dispose();
		score_effect.dispose();
		backgroundMusic.dispose();
		main.batch.dispose();
	}

}
