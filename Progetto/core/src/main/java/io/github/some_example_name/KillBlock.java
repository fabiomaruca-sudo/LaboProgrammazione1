package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Blocco letale che respawna il personaggio alla posizione iniziale al contatto.
 * Sovrascrive {@link Block#onTouch(Character, Map)} per definire
 * il comportamento specifico di questo tipo di blocco.
 * Estende {@link Block}.
 */
public class KillBlock extends Block {

    /**
     * Crea un KillBlock caricando la texture dal percorso specificato.
     *
     * @param x      posizione orizzontale
     * @param y      posizione verticale
     * @param width  larghezza in pixel
     * @param height altezza in pixel
     * @param path   percorso del file texture
     */
    public KillBlock(float x, float y, float width, float height, String path) {
        super(x, y, width, height, path);
    }

    /**
     * Crea un KillBlock usando una texture già caricata.
     *
     * @param x       posizione orizzontale
     * @param y       posizione verticale
     * @param width   larghezza in pixel
     * @param height  altezza in pixel
     * @param texture texture già caricata da assegnare al blocco
     */
    public KillBlock(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    /**
     * Respawna il personaggio alle coordinate di spawn della mappa.
     * Viene chiamato automaticamente quando il personaggio tocca questo blocco.
     *
     * @param character il personaggio che ha toccato il blocco
     * @param map       la mappa corrente, usata per ottenere le coordinate di spawn
     */
    @Override
    public void onTouch(Character character, Map map) {
        character.setX(map.getSpawnX());
        character.setY(map.getSpawnY());
    }
}
