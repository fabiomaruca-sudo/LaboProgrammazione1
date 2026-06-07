package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Classe base astratta per tutti gli oggetti di gioco.
 * Gestisce posizione, dimensioni, texture e hitbox di ogni entità.
 * Ogni istanza creata viene automaticamente aggiunta alla lista statica {@code total}.
 */
public abstract class Entity {

    /** Lista statica contenente tutte le istanze di Entity create. */
    private static ArrayList<Entity> total = new ArrayList<>();

    /** Posizione orizzontale dell'entità. */
    private float x;

    /** Posizione verticale dell'entità. */
    private float y;

    /** Larghezza dell'entità in pixel. */
    private float width;

    /** Altezza dell'entità in pixel. */
    private float height;

    /** Texture visiva dell'entità. */
    private Texture corpse;

    /** Rettangolo usato per il rilevamento delle collisioni. */
    private Rectangle hitboxes;

    /**
     * Crea un'entità con solo posizione definita.
     * Width e height vengono impostati a -1 e aggiornati automaticamente
     * al caricamento della texture tramite {@link #setCorpse(String)}.
     *
     * @param x posizione orizzontale iniziale
     * @param y posizione verticale iniziale
     */
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        width = -1; //grandezza momentanea
        height = -1; //grandezza momentanea
        total.add(this); //aggiunge questa nuova istanza all'arraylist
    }

    /**
     * Crea un'entità con posizione e dimensioni definite.
     * Utile in caso di scaling diverso da 1.
     *
     * @param x      posizione orizzontale iniziale
     * @param y      posizione verticale iniziale
     * @param width  larghezza in pixel
     * @param height altezza in pixel
     */
    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width; //grandezza scelta (utile in caso di scaling diverso da 1)
        this.height = height; //grandezza scelta (utile in caso di scaling diverso da 1)
        total.add(this); //aggiunge questa nuova istanza all'arraylist
    }

    /**
     * Carica la texture dall'path specificato.
     * Se width e height non sono ancora definite (valore -1), le imposta
     * automaticamente in base alle dimensioni della texture.
     *
     * @param path percorso del file immagine
     */
    public void setCorpse(String path) {
        corpse = new Texture(path);
        if (width < 0 || height < 0) {
            width = corpse.getWidth();
            height = corpse.getHeight();
        }
    }

    /**
     * Assegna una texture già caricata all'entità.
     * Utile per condividere una stessa texture tra più entità,
     * caricando l'immagine una sola volta.
     * Se width e height non sono ancora definite (valore -1), le imposta
     * automaticamente in base alle dimensioni della texture.
     *
     * @param texture texture già caricata
     */
    public void setCorpse(Texture texture) {
        corpse = texture;
        if (width < 0 || height < 0) {
            width = corpse.getWidth();
            height = corpse.getHeight();
        }
    }

    /**
     * Crea la hitbox usando i valori correnti di posizione e dimensione dell'entità.
     */
    public void setHitboxes() {
        hitboxes = new Rectangle(x, y, width, height);
    }

    /**
     * Crea la hitbox con valori personalizzati.
     * Versione utile in caso di scaling diverso o immagine ritagliata male.
     *
     * @param x      posizione orizzontale della hitbox
     * @param y      posizione verticale della hitbox
     * @param width  larghezza della hitbox
     * @param height altezza della hitbox
     */
    public void setHitboxes(float x, float y, float width, float height) {
        hitboxes = new Rectangle(x, y, width, height);
    }

    /**
     * Restituisce la larghezza dell'entità.
     *
     * @return larghezza in pixel
     */
    public float getWidth() {
        return width;
    }

    /**
     * Restituisce l'altezza dell'entità.
     *
     * @return altezza in pixel
     */
    public float getHeight() {
        return height;
    }

    /**
     * Restituisce la posizione orizzontale dell'entità.
     *
     * @return coordinata X
     */
    public float getX() {
        return x;
    }

    /**
     * Restituisce la posizione verticale dell'entità.
     *
     * @return coordinata Y
     */
    public float getY() {
        return y;
    }

    /**
     * Restituisce la texture visiva dell'entità.
     *
     * @return texture corrente
     */
    public Texture getCorpse() {
        return corpse;
    }

    /**
     * Restituisce il rettangolo di collisione dell'entità.
     *
     * @return hitbox come {@link Rectangle}
     */
    public Rectangle getHitboxes() {
        return hitboxes;
    }

    /**
     * Restituisce la lista di tutte le entità create.
     *
     * @return lista statica di tutte le istanze di Entity
     */
    public static ArrayList<Entity> getTotal() {
        return total;
    }

    /**
     * Imposta la posizione orizzontale e aggiorna la hitbox di conseguenza.
     *
     * @param x nuova coordinata X
     */
    public void setX(float x) {
        this.x = x;
        //se hitbox esistenti ne aggiorna la posizione
        if (hitboxes != null) {
            hitboxes.setPosition(x, y);
        }
    }

    /**
     * Imposta la posizione verticale e aggiorna la hitbox di conseguenza.
     *
     * @param y nuova coordinata Y
     */
    public void setY(float y) {
        this.y = y;
        //se hitbox esistenti ne aggiorna la posizione
        if (hitboxes != null) {
            hitboxes.setPosition(x, y);
        }
    }

    /**
     * Imposta la larghezza e aggiorna la hitbox di conseguenza.
     *
     * @param width nuova larghezza in pixel
     */
    public void setWidth(float width) {
        this.width = width;
        //se hitbox esistenti ne aggiorna la lunghezza
        if (hitboxes != null) {
            hitboxes.setWidth(width);
        }
    }

    /**
     * Imposta l'altezza e aggiorna la hitbox di conseguenza.
     *
     * @param height nuova altezza in pixel
     */
    public void setHeight(float height) {
        this.height = height;
        //se hitbox esistenti ne aggiorna l'altezza
        if (hitboxes != null) {
            hitboxes.setHeight(height);
        }
    }

    /**
     * Disegna l'entità usando la propria texture.
     *
     * @param batch SpriteBatch usato per il rendering
     */
    public void draw(SpriteBatch batch) {
        batch.draw(corpse, x, y, width, height);
    }

    /**
     * Disegna l'entità usando una texture esterna condivisa.
     * Utile per condividere una skin tra più entità: si carica
     * una sola immagine e la si riutilizza.
     *
     * @param batch SpriteBatch usato per il rendering
     * @param skin  texture condivisa da usare al posto di quella interna
     */
    public void draw(SpriteBatch batch, Texture skin) {
        batch.draw(skin, x, y, width, height);
    }

    /**
     * Libera la memoria occupata dalla texture dell'entità.
     * Da chiamare quando l'entità non è più necessaria.
     */
    public void dispose() {
        corpse.dispose();
    }

    /**
     * Mostra visivamente la hitbox dell'entità (utile per il debugging).
     *
     * @param shapeRenderer renderer usato per disegnare la forma
     * @param color         colore con cui disegnare il rettangolo
     */
    public void showHitbox(ShapeRenderer shapeRenderer, Color color) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(hitboxes.x, hitboxes.y, hitboxes.width, hitboxes.height);
    }
}
