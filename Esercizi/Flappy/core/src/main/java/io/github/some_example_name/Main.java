package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.*;
import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Entity flappy;
    private Entity pipeDown;
    private Entity pipeTop;
    private Entity background;
    private BitmapFont font;
    private int score = 0;
    private int GRAVITY = -500;
    private int FLAP_FORCE = 200;
    private boolean isGameOver = false;
    private int lifes = 3;
    private ShapeRenderer shapeRenderer;
    private boolean puntoFatto = false;
    private Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer(); //per mostrare visivamente le hitbox
        music = Gdx.audio.newMusic(Gdx.files.internal("mp3/music.mp3"));
        music.setVolume(0.2f);   // 0.0 → 1.0
        music.setLooping(true);  // true = riparte automaticamente

        flappy = new Entity(80, 200, 0, 0);
        flappy.setCorpse("flappy.png");
        flappy.setHitboxes();

        pipeDown = new Entity(300, 0, -100, 0);
        pipeDown.setCorpse("pipeDown.png");
        pipeDown.setHitboxes();

        pipeTop = new Entity(300, 120, -100, 0);
        pipeTop.setCorpse("pipeTop.png");
        pipeTop.setHitboxes();

        background = new Entity(0, 0, 0, 0);
        background.setCorpse("background.png");
        background.setHitboxes(0, 0, background.getWidth(), 35);

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(3.0f);

        SpawnBlock();
        music.play();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float dt = Gdx.graphics.getDeltaTime();
        if (!isGameOver) {
            flappy.setVelY(flappy.getVelY()+GRAVITY * dt);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()) {
                flappy.setVelY(FLAP_FORCE);
                //Se la X della pipe è fuori dallo schermo (sinistra)
                if (pipeDown.getX() < -pipeDown.getWidth()) {
                    SpawnBlock();
                }
            }

            flappy.setY(flappy.getY()+flappy.getVelY()*dt);
            flappy.getHitboxes().setPosition(flappy.getX(), flappy.getY());

            pipeDown.setX(pipeDown.getX()+pipeDown.getVelX()*dt);
            pipeDown.getHitboxes().x = pipeDown.getX();
            pipeDown.getHitboxes().y = pipeDown.getY();

            pipeTop.setX(pipeDown.getX());
            pipeTop.getHitboxes().x = pipeTop.getX();
            pipeTop.getHitboxes().y = pipeTop.getY();

            //se flappy tocca le pipe o il pavimento
            if (flappy.getHitboxes().overlaps(background.getHitboxes()) || flappy.getHitboxes().overlaps(pipeDown.getHitboxes()) || flappy.getHitboxes().overlaps(pipeTop.getHitboxes())) {
                lifes -= 1;
                flappy.setY(background.getHeight()/2- flappy.getHeight()/2);
                flappy.getHitboxes().y = flappy.getY();
                flappy.setVelY(0);
                SpawnBlock();
                if (lifes <= 0) {
                    isGameOver = true;
                }
            }
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()) {
                RestartGame();
            }
        }

        if (flappy.getX() > pipeDown.getX()+pipeDown.getWidth() && !puntoFatto) {
            puntoFatto = true;
            score += 1;
        }

        Draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        flappy.getCorpse().dispose();
        background.getCorpse().dispose();
        pipeDown.getCorpse().dispose();
        pipeTop.getCorpse().dispose();
    }

    private void Draw() {
        batch.begin();
        background.Draw(batch);
        pipeDown.Draw(batch);
        pipeTop.Draw(batch);
        flappy.Draw(batch);

        if (isGameOver) {
            font.draw(batch, "GameOver", 30, Gdx.graphics.getHeight()/2);
            font.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
        } else {
            font.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
            font.draw(batch, "Lifes: " + lifes, 20, Gdx.graphics.getHeight() - 70);
        }
        batch.end();

        //Per vedere le hitbox
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        flappy.ShowHitbox(shapeRenderer, Color.GREEN);
//        pipeDown.ShowHitbox(shapeRenderer, Color.RED);
//        pipeTop.ShowHitbox(shapeRenderer, Color.RED);
//        shapeRenderer.end();
    }

    private void RestartGame() {
        flappy.setY(200);
        isGameOver = false;
        score = 0;
        flappy.setVelY(0);
        lifes = 3;
        SpawnBlock();
    }

    private void SpawnBlock() {
        pipeDown.setX(280);
        pipeDown.setY(RandomDistance(-200, 25));
        pipeTop.setX(pipeDown.getX());
        pipeTop.setY(pipeDown.getY()+pipeDown.getHeight()+150);
        puntoFatto = false;
    }

    private float RandomDistance(float min, float max) {
        double heightRandom = Math.random();
        return (float) (heightRandom * (max - min) + min);
    }
}
