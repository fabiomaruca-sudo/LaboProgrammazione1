package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.*;
import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Sprite sprite;
    private float rotationSpeed = 90f; // degrees per second

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Load texture and create sprite
        Texture texture = new Texture(Gdx.files.internal("flappy.png"));
        sprite = new Sprite(texture);

        // Position sprite in the center of the screen
        sprite.setPosition(
                (Gdx.graphics.getWidth() - sprite.getWidth()) / 2,
                (Gdx.graphics.getHeight() - sprite.getHeight()) / 2
        );

        // Set origin to the center for proper rotation
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update rotation
        sprite.rotate(rotationSpeed * Gdx.graphics.getDeltaTime());

        // Draw sprite
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        sprite.getTexture().dispose();
    }
}

