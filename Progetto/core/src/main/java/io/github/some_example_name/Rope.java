package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;

/**
 * Blocco di tipo corda con cui il personaggio può interagire.
 * Quando il personaggio è nel raggio d'azione e preme il tasto E,
 * viene agganciato alla sommità della corda.
 * Estende {@link Block}.
 */
public class Rope extends Block {

    /**
     * Crea una corda caricando la texture dal percorso specificato.
     *
     * @param x      posizione orizzontale
     * @param y      posizione verticale
     * @param width  larghezza in pixel
     * @param height altezza in pixel
     * @param path   percorso del file texture
     */
    public Rope(float x, float y, float width, float height, String path) {
        super(x, y, width, height, path);
    }

    /**
     * Crea una corda usando una texture già caricata.
     *
     * @param x       posizione orizzontale
     * @param y       posizione verticale
     * @param width   larghezza in pixel
     * @param height  altezza in pixel
     * @param texture texture già caricata da assegnare alla corda
     */
    public Rope(float x, float y, float width, float height, Texture texture) {
        super(x, y, width, height, texture);
    }

    /**
     * Aggancia il personaggio alla sommità della corda se si trova nel raggio d'azione.
     * Centra il personaggio orizzontalmente rispetto alla corda
     * e lo posiziona sopra di essa.
     *
     * @param c il personaggio da agganciare
     */
    public void interaction(Character c) {
        if (inRange(c, 2)) {
            c.setX(getX() + getWidth()/2 - c.getWidth()/2);
            c.setY(getY() + getHeight());
        }
    }

    /**
     * Controlla se il personaggio si trova nel raggio d'interazione della corda.
     * La distanza massima è calcolata come il doppio della larghezza del blocco,
     * confrontando i centri dei due oggetti su entrambi gli assi.
     *
     * @param character il personaggio da controllare
     * @param range     moltiplicatore per calcolare la distanza massima
     * @return {@code true} se il personaggio è abbastanza vicino, altrimenti {@code false}
     */
    @Override
    public boolean inRange(Character character, int range) {

        float charX = character.getX() + character.getWidth()/2;
        float charY = character.getY() + character.getHeight()/2;

        float blockX = getX() + getWidth()/2;
        float blockY = getY() + getHeight()/2;

        float maxDistance = getWidth()*2;

        return Math.abs(charX - blockX) <= maxDistance
            && Math.abs(charY - blockY) <= maxDistance;
    }
}
