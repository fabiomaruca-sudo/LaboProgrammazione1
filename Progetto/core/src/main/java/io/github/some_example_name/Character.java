package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

/**
 * Rappresenta il personaggio giocabile.
 * Gestisce movimento, salto, fisica e collisioni con i blocchi della mappa.
 * Estende {@link Entity} ereditando posizione, dimensioni, texture e hitbox.
 */
public class Character extends Entity {

    /** Velocità di movimento orizzontale costante. */
    private float movementForce;

    /** Forza applicata verticalmente al momento del salto. */
    private float jumpForce;

    /** Velocità orizzontale corrente (influenzata dall'accelerazione). */
    private float velX;

    /** Velocità verticale corrente (influenzata dalla gravità). */
    private float velY;

    /** Numero di salti effettuati dall'ultimo atterraggio. */
    private int jumpAmount;

    /** Numero massimo di salti consecutivi consentiti. */
    private int MAXJUMPS = 2;

    /**
     * Restituisce la velocità orizzontale corrente.
     *
     * @return velocità orizzontale
     */
    public float getVelX() {
        return velX;
    }

    /**
     * Restituisce la velocità verticale corrente.
     *
     * @return velocità verticale
     */
    public float getVelY() {
        return velY;
    }

    /**
     * Imposta la velocità orizzontale.
     *
     * @param velX nuova velocità orizzontale
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }

    /**
     * Imposta la velocità verticale.
     *
     * @param velY nuova velocità verticale
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }

    /**
     * Restituisce la forza di movimento orizzontale base.
     *
     * @return forza di movimento
     */
    public float getMovementForce() {
        return movementForce;
    }

    /**
     * Imposta la forza di movimento orizzontale base.
     *
     * @param movementForce nuova forza di movimento
     */
    public void setMovementForce(float movementForce) {
        this.movementForce = movementForce;
    }

    /**
     * Restituisce la forza di salto.
     *
     * @return forza di salto
     */
    public float getJumpForce() {
        return jumpForce;
    }

    /**
     * Imposta la forza di salto.
     *
     * @param jumpForce nuova forza di salto
     */
    public void setJumpForce(float jumpForce) {
        this.jumpForce = jumpForce;
    }

    /**
     * Restituisce il numero di salti effettuati dall'ultimo atterraggio.
     *
     * @return numero di salti correnti
     */
    public int getJumpAmount() {
        return jumpAmount;
    }

    /**
     * Imposta il contatore dei salti effettuati.
     *
     * @param jumpAmount nuovo valore del contatore salti
     */
    public void setJumpAmount(int jumpAmount) {
        this.jumpAmount = jumpAmount;
    }

    /**
     * Restituisce il numero massimo di salti consecutivi consentiti.
     *
     * @return numero massimo di salti
     */
    public int getMAXJUMPS() {
        return MAXJUMPS;
    }

    /**
     * Crea un nuovo personaggio con le proprietà specificate.
     * Carica la texture, inizializza le hitbox e imposta le velocità iniziali.
     *
     * @param x             posizione orizzontale iniziale
     * @param y             posizione verticale iniziale
     * @param width         larghezza in pixel
     * @param height        altezza in pixel
     * @param movementForce forza di movimento orizzontale
     * @param jumpForce     forza di salto verticale
     * @param path          percorso della texture del personaggio
     */
    public Character(float x, float y, float width, float height, float movementForce, float jumpForce, String path) {
        super(x, y, width, height);
        this.movementForce = movementForce;
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();;
        this.jumpForce = jumpForce;
        this.velX = movementForce;
        this.velY = 0;
        jumpAmount = 0;
        setCorpse(path);
        setHitboxes();
    }

    /**
     * Sposta il personaggio orizzontalmente della quantità specificata.
     *
     * @param vel spostamento orizzontale (positivo = destra, negativo = sinistra)
     */
    public void Move(float vel) {
        setX(getX()+vel);
    }

    /**
     * Esegue un salto se il numero di salti effettuati non ha raggiunto il massimo.
     * Massimo 2 salti: controlla quanti ne sono stati effettuati prima di toccare terra.
     */
    public void jump() {
        if (jumpAmount < MAXJUMPS) {
            jumpAmount += 1;
            setY(getY()+1);
            velY = jumpForce;
        }
    }

    /**
     * Gestisce le collisioni tra il personaggio e tutti i blocchi della mappa.
     * Per ogni sovrapposizione, calcola il lato di collisione tramite penetration depth (quanto viene compenetrato per ogni lato)
     * e corregge la posizione di conseguenza. Gestisce inoltre l'interazione
     * con i blocchi di tipo {@link Rope} tramite il tasto E.
     *
     * @param map la mappa corrente contenente la lista dei blocchi
     */
    public void handleHitboxes(Map map) {

        // Itera su ogni blocco presente nella mappa
        for (Block block : map.getBlocks()) {

            Rectangle player = getHitboxes();
            Rectangle blockRect = block.getHitboxes();

            // Controlla se i due rettangoli si sovrappongono
            if (player.overlaps(blockRect)) {

                // Calcola la profondità di sovrapposizione su ciascun lato.
                // Es: overlapLeft = quanto il lato destro del player è entrato
                //     nel lato sinistro del blocco
                float overlapLeft   = player.x + player.width  - blockRect.x;
                float overlapRight  = blockRect.x + blockRect.width - player.x;
                float overlapBottom = player.y + player.height - blockRect.y;
                float overlapTop    = blockRect.y + blockRect.height - player.y;

                // Il lato con sovrapposizione minima è quello da cui
                // il personaggio è entrato nel blocco (penetration depth)
                float minOverlap = Math.min(
                    Math.min(overlapLeft, overlapRight),
                    Math.min(overlapBottom, overlapTop)
                );

                if (minOverlap == overlapTop) {
                    // Il player è atterrato sopra il blocco:
                    // lo riallinea al bordo superiore e azzera la caduta
                    setY(blockRect.y + blockRect.height - 1);
                    setVelY(0);
                    setJumpAmount(0); // permette di saltare di nuovo
                }
                else if (minOverlap == overlapBottom) {
                    // Il player ha colpito il soffitto:
                    // lo spinge sotto il blocco e interrompe la salita
                    setY(blockRect.y - player.height);
                    setVelY(0);
                }
                else if (minOverlap == overlapLeft) {
                    // Il player ha colpito il lato sinistro del blocco:
                    // lo sposta fuori e applica una velY negativa per simulare
                    // lo scivolamento a parete (wall slide)
                    setX(blockRect.x - player.width - 2);
                    setVelY(-80);
                }
                else if (minOverlap == overlapRight) {
                    // Speculare al caso sinistro
                    setX(blockRect.x + blockRect.width + 2);
                    setVelY(-80);
                }

                // Notifica il blocco del contatto (es. KillBlock rispawna il player)
                block.onTouch(this, map);
            }

            // Gestione separata per le corde: non serve la collisione,
            // basta che il player prema E mentre è vicino
            if (block instanceof Rope) {
                Rope rope = (Rope) block;
                if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                    rope.interaction(this);
                }
            }
        }
    }
}
