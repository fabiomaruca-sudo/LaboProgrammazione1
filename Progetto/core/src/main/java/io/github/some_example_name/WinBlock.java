package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;


//Classe non implementata, sarebbe un blocco che, al tocco con character, fa terminare il livello
public class WinBlock extends Block{
    public WinBlock(float x, float y, float width, float height, String path) {
        super(x, y, width, height, path);
    }

    public WinBlock(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    @Override
    public void onTouch(Character character, Map map) {

    }
}
