package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * Classe principale del gioco. Gestisce il ciclo di vita dell'applicazione LibGDX,
 * il menu iniziale, la selezione del livello e il game loop.
 * Estende {@link ApplicationAdapter} e funge da punto di ingresso per rendering,
 * input e logica di gioco.
 */
public class Main extends ApplicationAdapter {

    /** SpriteBatch condiviso per il rendering di texture e testi. */
    private SpriteBatch batch;

    /** Font usato per il testo nel menu. */
    private BitmapFont font;

    /**
     * Modalità display del monitor corrente.
     * Pubblico per essere accessibile da altre classi (es. {@link Map}) per il ridimensionamento.
     */
    public static Graphics.DisplayMode displayMode;

    /** Personaggio giocabile corrente. */
    private Character character;

    /** Mappa di gioco corrente. */
    private Map map;

    /** Indica se il gioco è in corso ({@code true}) o si è nel menu ({@code false}). */
    private boolean inGame;

    /** Fattore di scala globale, necessario in tutte le classi. */
    public float scale;

    /** Delta time del frame corrente, necessario in tutte le classi. */
    public float dt;

    /** Renderer per le forme geometriche (rettangoli UI). */
    private ShapeRenderer shapeRenderer;

    private UI startButton;
    private UI settingsButton;
    private UI exitButton;
    /** Pannello contenitore per la lista dei livelli. */
    private UI levelSelector;

    private Music bgMusic;

    /** Array dei nomi dei livelli letti dal file index. */
    private String[] levels;
    /** File index contenente i nomi dei livelli disponibili. */
    private FileHandle file;
    /** Lista dei componenti UI per ogni livello disponibile. */
    private ArrayList<UI> levelsUI;
    /** Livello attualmente selezionato nel menu. */
    private UI selectedLevel;

    /**
     * Inizializza tutte le risorse del gioco: batch, font, componenti UI,
     * musica del menu e lista dei livelli disponibili.
     * Chiamato una sola volta all'avvio dell'applicazione.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        displayMode = Gdx.graphics.getDisplayMode(); //ottiene grandezza monitor, bpp e Hz, chiesto a chat gpt
        file = Gdx.files.internal("Levels/index.txt"); //file contenente nomi livelli, chiesto a chat gpt

        scale = resizeWidth(Gdx.graphics.getWidth());
        shapeRenderer = new ShapeRenderer();

        font = new BitmapFont();
        font.getData().setScale(displayMode.width / 1920f); // scaling base
        font.setColor(Color.WHITE);
        startButton = new UI((float) displayMode.width/2-300, (float) displayMode.height/2, 600, 150, "Start (Enter)");
        settingsButton = new UI((float) displayMode.width/2-300, (float) displayMode.height/2-200, 600, 150, "Settings");
        exitButton = new UI((float) displayMode.width/2-300, (float) displayMode.height/2-400, 600, 150, "Exit (Esc)");
        levelSelector = new UI((float) displayMode.width/2+450, 100, 450, displayMode.height-200, "");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Musics/menu.mp3"));
        bgMusic.setVolume(0.3f);
        bgMusic.setLooping(true);
        bgMusic.play();

        levelsUI = new ArrayList<>();
        getLevels();
        UIlevels();

        selectedLevel = levelsUI.get(0);//di default livello 1
    }

    /**
     * Chiamato ad ogni frame. Gestisce il rendering e la logica di gioco.
     * Se {@code inGame} è {@code false} mostra il menu, altrimenti esegue
     * la fisica, gli input e il rendering della mappa.
     */
    @Override
    public void render() {
        dt = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0.3f, 0.8f, 0.8f, 1f);
        if (!inGame) {
            DrawMenu();
            handleGeneralInputs();

            //prende tutti i livelli e controlla quale viene selezionato, poi li colora
            for (int i = 0; i < levelsUI.size(); i++) {
                UI level = levelsUI.get(i);
                if (level.isClicked()) {
                    selectedLevel = level;
                }
                level.setColor(Color.GRAY);
            }
            selectedLevel.setColor(Color.BROWN);

        } else {
            DrawMap();

            character.setVelY(character.getVelY() - map.getGRAVITY() * dt);

            handleGeneralInputs();
            handleCharacterInputs();
            character.handleHitboxes(map);

            character.setY(character.getY() + character.getVelY()*dt);
        }
    }

    /**
     * Libera tutte le risorse allocate (batch, mappa, personaggio, shape renderer e UI).
     * Chiamato automaticamente alla chiusura dell'applicazione.
     */
    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
        character.dispose();
        shapeRenderer.dispose();

        for ( Entity ent : Entity.getTotal() ) {
            if (ent instanceof UI) {
                UI ui = (UI) ent;
                ui.dispose();
            }
        }
    }

    /**
     * Disegna il menu principale: rettangoli dei pulsanti e testi.
     * Itera su tutte le entità UI presenti in {@link Entity#getTotal()}.
     */
    public void DrawMenu() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        startButton.drawRect(batch, font, 15);
        exitButton.drawRect(batch, font, 15);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for ( Entity ent : Entity.getTotal() ) {
            if (ent instanceof UI) {
                UI ui = (UI) ent;
                ui.drawRect(batch, font, 15);
            }
        }
        shapeRenderer.end();

        batch.begin();
        for ( Entity ent : Entity.getTotal() ) {
            if (ent instanceof UI) {
                UI ui = (UI) ent;
                ui.drawText(batch, font, 4);
            }
        }
        batch.end();
    }

    /**
     * Calcola la larghezza scalata rispetto alla risoluzione del monitor.
     *
     * @param scale divisore: 1 = larghezza intera, 2 = metà, 4 = un quarto, ...
     * @return larghezza in pixel corrispondente alla frazione indicata
     */
    public float resizeWidth(float scale) {
        return (float) displayMode.width / scale;
    }

    /**
     * Calcola l'altezza scalata rispetto alla risoluzione del monitor.
     *
     * @param scale divisore: 1 = altezza intera, 2 = metà, 4 = un quarto, ...
     * @return altezza in pixel corrispondente alla frazione indicata
     */
    public float resizeHeight(float scale) {
        return (float) displayMode.height / scale;
    }

    /**
     * Ridimensiona la finestra alle dimensioni specificate.
     * In modalità fullscreen rimuove le decorazioni della finestra,
     * in modalità windowed le ripristina.
     *
     * @param width  nuova larghezza della finestra
     * @param height nuova altezza della finestra
     */
    public void windowResized(int width, int height) {
        if (Gdx.graphics.isFullscreen()) {
            // fullscreen → togli bordi (o mantieni)
            Gdx.graphics.setUndecorated(true);
        } else {
            // windowed → bordi ON
            Gdx.graphics.setUndecorated(false);
        }
        Gdx.graphics.setWindowedMode(width, height);
    }

    /**
     * Disegna la mappa e il personaggio durante il gameplay.
     */
    public void DrawMap() {
        batch.begin();
        map.draw(batch);
        character.draw(batch);
        batch.end();
    }

    /**
     * Gestisce gli input generali validi sia nel menu che durante il gioco:
     * uscita dall'applicazione (ESC o click su Exit) e toggle finestra (F11).
     * Avvia la mappa selezionata se premuto ENTER o cliccato Start.
     */
    private void handleGeneralInputs() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
            || exitButton.isClicked()) {
            Gdx.app.exit(); //chiude l'app, chiesto all'AI
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            windowResized(960, 540);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)
            || startButton.isClicked()) {
            startMap(selectedLevel.getText());
        }
    }

    /**
     * Gestisce gli input di movimento del personaggio durante il gameplay:
     * movimento orizzontale (A/D o frecce), salto (SPAZIO, doppio salto incluso).
     */
    private void handleCharacterInputs() {
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.Move(-character.getVelX()*dt);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.Move(character.getVelX()*dt);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && character.getJumpAmount() < character.getMAXJUMPS()) {
            character.setY(character.getY());
            character.jump();
        }
    }

    /**
     * Carica e avvia la mappa con il nome specificato.
     * Crea la mappa, istanzia il personaggio alla posizione di spawn
     * e avvia la musica associata alla mappa.
     * In caso di errore (livello non trovato) stampa un messaggio in console.
     *
     * @param name nome del livello da caricare (senza estensione)
     */
    public void startMap(String name) {
        try {
            map = new Map(name);
            map.txtToMap();
            character = new Character(map.getSpawnX(), map.getSpawnY(), resizeWidth(45), resizeHeight(25), resizeWidth(15), resizeHeight(3.f), "Skins\\Characters\\character_1.png");
            inGame = true;
            bgMusic.stop();

            bgMusic = map.getMusic();
            bgMusic.setVolume(0.3f);
            bgMusic.setLooping(true);
            bgMusic.play();
        } catch (com.badlogic.gdx.utils.GdxRuntimeException e) {
            System.out.println("ERRORE, livello non trovato");
        }
    }

    /**
     * Legge il file index dei livelli e popola l'array {@code levels}
     * con i nomi dei livelli disponibili.
     */
    public void getLevels() {
        String content = file.readString();
        levels = content.split("\n");
    }

    /**
     * Crea i componenti UI per ogni livello letto dall'index,
     * posizionandoli verticalmente all'interno del pannello {@code levelSelector}.
     */
    public void UIlevels() {
        for (int i = 0; i < levels.length; i++) {
            levelsUI.add(new UI(
                levelSelector.getX()+50, (levelSelector.getY()+levelSelector.getHeight())-i*150-150,
                levelSelector.getWidth()-100, 100,
                levels[i].trim().toLowerCase())
            );
            levelsUI.get(i).setColor(Color.BROWN);
        }
    }
}
