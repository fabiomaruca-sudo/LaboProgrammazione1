package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

//Classe usata per gestire sprite, posizioni e velocità, grandezze e collisioni con un singolo oggetto
public class Entity {
    private static ArrayList<Entity> total = new ArrayList<>(); //variabile della classe, usata per aggiungere ogni Entity nuova creata
    private float x;
    private float y;
    private float velX;
    private float velY;
    private float width;
    private float height;
    private Texture corpse;
    private Rectangle hitboxes;

    //se width e height non definite, viene assegnato loro il valore in base alla grandezza della texture
    public Entity(float x, float y, float velX, float velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        width = -1; //grandezza momentanea
        height = -1; //grandezza momentanea
        total.add(this); //aggiunge questa nuova istanza all'arraylist
    }

    public Entity(float x, float y, float velX, float velY, float width, float height) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.width = width; //grandezza scelta (utile in caso di scaling diverso da 1)
        this.height = height; //grandezza scelta (utile in caso di scaling diverso da 1)
        total.add(this); //aggiunge questa nuova istanza all'arraylist
        }

    //assegna una texture visiva e se le grandezze non sono definite
    public void setCorpse(String path) {
        corpse = new Texture(path);
        if (width < 0 || height < 0) {
            width = corpse.getWidth();
            height = corpse.getHeight();
        }
    }

    //usa i valori dell'oggetto per creare un rect
    public void setHitboxes() {
        hitboxes = new Rectangle(x, y, width, height);
    }

    //versione utile in caso di scaling diverso o immagine ritagliata male
    public void setHitboxes(float x, float y, float width, float height) {
        hitboxes = new Rectangle(x, y, width, height);
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public Texture getCorpse() {
        return corpse;
    }

    public Rectangle getHitboxes() {
        return hitboxes;
    }

    public static ArrayList<Entity> getTotal() {
        return total;
    }


    public void setX(float x) {
        this.x = x;
        //se hitbox esistenti ne aggiorna la posizione
        if (hitboxes != null) {
            hitboxes.setPosition(x, y);
        }
    }

    public void setY(float y) {
        this.y = y;
        //se hitbox esistenti ne aggiorna la posizione
        if (hitboxes != null) {
            hitboxes.setPosition(x, y);
        }
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setWidth(float width) {
        this.width = width;
        //se hitbox esistenti ne aggiorna la lunghezza
        if (hitboxes != null) {
            hitboxes.setWidth(width);
        }
    }

    public void setHeight(float height) {
        this.height = height;
        //se hitbox esistenti ne aggiorna l'altezza
        if (hitboxes != null) {
            hitboxes.setHeight(height);
        }
    }


    public void Draw(SpriteBatch batch) {
        batch.draw(corpse, x, y, width, height);
    }

    //serve per mostrare delle hitbox visibili (debugging)
    public void ShowHitbox(ShapeRenderer shapeRenderer, Color color) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(hitboxes.x, hitboxes.y, hitboxes.width, hitboxes.height);
    }
}
