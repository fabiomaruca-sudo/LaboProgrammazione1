package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Rappresenta un blocco statico della mappa.
 * È la classe base per tutti i tipi di blocco: può essere estesa per definire
 * comportamenti specifici al contatto o all'avvicinamento del personaggio.
 * Estende {@link Entity} ereditando posizione, dimensioni, texture e hitbox.
 */
public class Block extends Entity {

    /**
     * Crea un blocco caricando la texture dal percorso specificato.
     *
     * @param x      posizione orizzontale
     * @param y      posizione verticale
     * @param width  larghezza in pixel
     * @param height altezza in pixel
     * @param path   percorso del file texture
     */
    public Block(float x, float y, float width, float height, String path) {
        super(x, y, width, height);
        setHitboxes();
    }

    /**
     * Crea un blocco usando una texture già caricata.
     * Preferibile quando più blocchi condividono la stessa texture.
     *
     * @param x       posizione orizzontale
     * @param y       posizione verticale
     * @param width   larghezza in pixel
     * @param height  altezza in pixel
     * @param texture texture già caricata da assegnare al blocco
     */
    public Block(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height);
        setCorpse(texture);
        setHitboxes();
    }

    /**
     * Controlla se il personaggio si trova nel raggio di interazione del blocco.
     * Implementazione base: restituisce sempre {@code false}.
     * Può essere sovrascritta nelle sottoclassi (es. {@link Rope}).
     *
     * @param character     il personaggio da controllare
     * @param blockDistance distanza massima considerata "in range"
     * @return {@code true} se il personaggio è abbastanza vicino, altrimenti {@code false}
     */
    public boolean inRange(Character character, int blockDistance) {
        return false;
    }

    /**
     * Azione eseguita quando il personaggio tocca il blocco.
     * Implementazione base vuota: può essere sovrascritta nelle sottoclassi
     * per definire comportamenti specifici (es. {@link KillBlock}).
     *
     * @param character il personaggio che ha toccato il blocco
     * @param map       la mappa corrente
     */
    public void onTouch(Character character, Map map) {
    }

    /**
     * Disegna il blocco usando la propria texture.
     *
     * @param batch SpriteBatch usato per il rendering
     */
    public void drawTexture(SpriteBatch batch) {
        draw(batch);
    }

    /**
     * Disegna eventuali elementi UI associati al blocco.
     * Implementazione base vuota: può essere sovrascritta nelle sottoclassi.
     *
     * @param batch SpriteBatch usato per il rendering
     */
    public void drawUI(SpriteBatch batch) {
        return;
    }
}
