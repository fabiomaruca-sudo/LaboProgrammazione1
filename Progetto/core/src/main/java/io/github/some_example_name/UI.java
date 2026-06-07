package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Componente UI interattivo con testo e sfondo colorato.
 * Gestisce il rendering di rettangoli cliccabili con testo centrato,
 * usati per pulsanti e selettori nel menu di gioco.
 * Estende {@link Entity} ereditando posizione e dimensioni.
 */
public class UI extends Entity {

    /** Testo visualizzato al centro del componente. */
    private String text;

    /** Colore di sfondo del rettangolo. */
    private Color color;

    /** Renderer usato per disegnare il rettangolo di sfondo. */
    private ShapeRenderer shape;

    /**
     * Restituisce il testo del componente.
     *
     * @return testo corrente
     */
    public String getText() {
        return text;
    }

    /**
     * Imposta il testo del componente.
     *
     * @param text nuovo testo da visualizzare
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Restituisce il colore di sfondo del componente.
     *
     * @return colore corrente
     */
    public Color getColor() {
        return color;
    }

    /**
     * Imposta il colore di sfondo del componente.
     *
     * @param color nuovo colore di sfondo
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Restituisce il {@link ShapeRenderer} usato per il rettangolo.
     *
     * @return shape renderer corrente
     */
    public ShapeRenderer getShape() {
        return shape;
    }

    /**
     * Imposta il {@link ShapeRenderer} usato per il rettangolo.
     *
     * @param shape nuovo shape renderer
     */
    public void setShape(ShapeRenderer shape) {
        this.shape = shape;
    }

    /**
     * Crea un componente UI con solo posizione e testo.
     * Dimensioni determinate dalla texture (se presente).
     *
     * @param x    posizione orizzontale
     * @param y    posizione verticale
     * @param text testo da visualizzare
     */
    public UI(float x, float y, String text) {
        super(x, y);
        this.text = text;
        color = new Color(Color.BLACK);
        shape = new ShapeRenderer();
    }

    /**
     * Crea un componente UI con posizione, dimensioni e testo.
     *
     * @param x      posizione orizzontale
     * @param y      posizione verticale
     * @param width  larghezza in pixel
     * @param height altezza in pixel
     * @param text   testo da visualizzare
     */
    public UI(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        this.text = text;
        color = new Color(Color.BLACK);
        shape = new ShapeRenderer();
    }

    /**
     * Disegna il testo centrato all'interno del componente.
     * Usa {@link GlyphLayout} per calcolare le dimensioni in pixel del testo
     * e posizionarlo correttamente al centro del rettangolo.
     *
     * @param batch    SpriteBatch usato per il rendering
     * @param font     font da usare per il testo
     * @param fontSize scala del font
     */
    public void centerText(SpriteBatch batch, BitmapFont font, float fontSize) {
        //Aiutato da Claude AI
        font.getData().setScale(fontSize);
        GlyphLayout layout = new GlyphLayout(font, text); //classe usata per calcolare le dimensioni (px) di una stringa
        float textX = getX() + (getWidth() - layout.width) / 2f;
        float textY = getY() + (getHeight() + layout.height) / 2f; // height/2 + layout.height/2 perché Y è baseline
        font.draw(batch, layout, textX, textY);
    }

    /**
     * Disegna il testo in una posizione specifica.
     *
     * @param batch    SpriteBatch usato per il rendering
     * @param font     font da usare per il testo
     * @param fontSize scala del font
     * @param textX    coordinata X del testo
     * @param textY    coordinata Y del testo
     */
    public void placeText(SpriteBatch batch, BitmapFont font, float fontSize, float textX, float textY) {
        font.getData().setScale(fontSize);
        font.draw(batch, text, textX, textY);
    }

    /**
     * Disegna il rettangolo di sfondo con il colore corrente.
     *
     * @param batch    SpriteBatch (non usato direttamente, richiesto per uniformità)
     * @param font     font (non usato direttamente, richiesto per uniformità)
     * @param fontSize scala del font (non usata direttamente)
     */
    public void drawRect(SpriteBatch batch, BitmapFont font, float fontSize) {
        if (shape != null) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(color);
            shape.rect(getX(), getY(), getWidth(), getHeight());
            shape.end();
        }
    }

    /**
     * Libera la memoria occupata dallo {@link ShapeRenderer}.
     * Da chiamare quando il componente non è più necessario.
     */
    public void dispose() {
        shape.dispose();
    }

    /**
     * Disegna il testo centrato nel componente, solo se il testo è presente.
     *
     * @param batch    SpriteBatch usato per il rendering
     * @param font     font da usare per il testo
     * @param fontSize scala del font
     */
    public void drawText(SpriteBatch batch, BitmapFont font, float fontSize) {
        if (text != null && !text.isEmpty()) {
            centerText(batch, font, fontSize);
        }
    }

    /**
     * Controlla se il componente è stato cliccato nel frame corrente.
     * Converte le coordinate del mouse (origine in alto a sinistra) nelle
     * coordinate di gioco (origine in basso a sinistra) prima del confronto.
     *
     * @return {@code true} se il click è avvenuto all'interno del rettangolo
     */
    public boolean isClicked() {
        //logica fatta dall'AI
        if (Gdx.input.justTouched()) {
            float mx = Gdx.input.getX();
            float my = Gdx.graphics.getHeight() - Gdx.input.getY(); // inverti Y
            return mx >= getX() && mx <= getX() + getWidth()
                && my >= getY() && my <= getY() + getHeight();
        }
        return false;
    }
}
