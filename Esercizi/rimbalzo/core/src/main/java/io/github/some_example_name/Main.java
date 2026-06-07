package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture player;
    private Texture enemy;
    private Texture background;
    float[] velocityX;
    float[] position;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Texture("player.png");
        enemy = new Texture("enemy.png");
        background = new Texture("background2.png");
        velocityX = new float[] {200, 300};
        position = new float[] {0, Gdx.graphics.getWidth()-enemy.getWidth()};
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0, 400, 300);
        batch.draw(player, position[0], 210);
        batch.draw(enemy, position[1], 40);
        batch.end();

        if (position[0] < 0) {
            velocityX[0] = Math.abs(velocityX[0]);
        } else if ((position[0] >= Gdx.graphics.getWidth() || position[0]+player.getWidth() > position[1]) && velocityX[0] > 0) {
            velocityX[0] = velocityX[0] * -1;
        }

        if (position[1] < 0 || position[1] <= position[0]+player.getWidth()) {
            velocityX[1] = Math.abs(velocityX[1]);
        } else if ((position[1] > Gdx.graphics.getWidth()-enemy.getWidth()) && velocityX[1] > 0) {
            velocityX[1] = velocityX[1] * -1;
        }
        position[0] += velocityX[0] * Gdx.graphics.getDeltaTime();
        position[1] += velocityX[1] * Gdx.graphics.getDeltaTime();

        System.out.println(Gdx.graphics.getWidth()-enemy.getWidth());
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        enemy.dispose();
        background.dispose();
    }
}
