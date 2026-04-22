package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture flappy;
    private Texture pipe;
    private Texture background;
    private float flappyY = 40;
    private float velY = 0;
    private float GRAVITY = -500;
    private float FLAP_FORCE = 200;

    @Override
    public void create() {
        batch = new SpriteBatch();
        flappy = new Texture("flappy.png");
        background = new Texture("background.png");
        pipe = new Texture("pipe.png");

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        float dt = Gdx.graphics.getDeltaTime();
        velY += GRAVITY*dt;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velY = FLAP_FORCE;
        }
        else if (flappyY <= background.getHeight()) {
            velY = 0;
        }

        flappyY+= velY*dt;


        Draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        flappy.dispose();
        background.dispose();
        pipe.dispose();
    }

    private void Draw() {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(pipe, 200, 50, pipe.getWidth(), 300);
        batch.draw(flappy, 80, flappyY, 75, flappy.getHeight());
        batch.end();
    }
}
