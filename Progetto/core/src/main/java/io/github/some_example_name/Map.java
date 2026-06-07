package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Gestisce il caricamento, la rappresentazione e il rendering della mappa di gioco.
 * La mappa viene letta da un file {@code .txt} strutturato in due sezioni:
 * la prima definisce le texture e le proprietà (gravità, musica),
 * la seconda contiene la griglia dei blocchi tramite ID numerici.
 */
public class Map {

    /** Dimensione in pixel di ogni lato di un blocco. Visibili 15 blocchi in larghezza. */
    static float side = (float)(Main.displayMode.width)/15.0f;

    /** Nome della mappa, usato anche per caricare il file e la musica. */
    private String name;

    /** Matrice di interi rappresentante la griglia della mappa. */
    private ArrayList<ArrayList<Integer>> map;

    /** Lista delle texture associate a ciascun ID blocco. */
    private ArrayList<Texture> textures;

    /** Lista delle entità blocco con hitbox e logica di collisione. */
    private ArrayList<Block> blocks;

    /** Tipo di oggetto (block, kill, rope, ...) associato a ciascun ID. */
    private ArrayList<String> type;

    /** Offset orizzontale corrente usato durante il parsing della mappa. */
    private float x;

    /** Offset verticale corrente usato durante il parsing della mappa. */
    private float y;

    /** Coordinata X di spawn del personaggio. */
    private static float spawnX;

    /** Coordinata Y di spawn del personaggio. */
    private static float spawnY;

    /** File {@code .txt} contenente le informazioni della mappa. */
    private FileHandle file;

    /** Valore della gravità applicata al personaggio durante il gioco. */
    private float GRAVITY;

    /** Musica di sottofondo associata alla mappa. */
    private Music music;

    /**
     * Restituisce la matrice della mappa.
     *
     * @return griglia della mappa come lista di liste di interi
     */
    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }

    /**
     * Imposta la matrice della mappa.
     *
     * @param map nuova griglia della mappa
     */
    public void setMap(ArrayList<ArrayList<Integer>> map) {
        this.map = map;
    }

    /**
     * Restituisce la dimensione del lato di un blocco.
     *
     * @return dimensione in pixel
     */
    public static float getWidth() {
        return side;
    }

    /**
     * Restituisce la lista dei blocchi istanziati nella mappa.
     *
     * @return lista di {@link Block}
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * Imposta la lista dei blocchi della mappa.
     *
     * @param blocks nuova lista di blocchi
     */
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Restituisce il nome della mappa.
     *
     * @return nome della mappa
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce l'offset orizzontale corrente.
     *
     * @return coordinata X corrente
     */
    public float getX() {
        return x;
    }

    /**
     * Restituisce l'offset verticale corrente.
     *
     * @return coordinata Y corrente
     */
    public float getY() {
        return y;
    }

    /**
     * Restituisce il file handle della mappa.
     *
     * @return file {@code .txt} della mappa
     */
    public FileHandle getFile() {
        return file;
    }

    /**
     * Imposta la dimensione del lato di ogni blocco.
     *
     * @param width nuova dimensione in pixel
     */
    public static void setWidth(float width) {
        Map.side = width;
    }

    /**
     * Imposta il nome della mappa.
     *
     * @param name nuovo nome
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Imposta l'offset orizzontale corrente.
     *
     * @param x nuova coordinata X
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Imposta l'offset verticale corrente.
     *
     * @param y nuova coordinata Y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Imposta il file handle della mappa.
     *
     * @param file nuovo file handle
     */
    public void setFile(FileHandle file) {
        this.file = file;
    }

    /**
     * Restituisce la coordinata X di spawn del personaggio.
     *
     * @return X di spawn
     */
    public float getSpawnX() {
        return spawnX;
    }

    /**
     * Imposta la coordinata X di spawn del personaggio.
     *
     * @param spawnX nuova X di spawn
     */
    public void setSpawnX(float spawnX) {
        this.spawnX = spawnX;
    }

    /**
     * Restituisce la coordinata Y di spawn del personaggio.
     *
     * @return Y di spawn
     */
    public float getSpawnY() {
        return spawnY;
    }

    /**
     * Imposta la coordinata Y di spawn del personaggio.
     *
     * @param spawnY nuova Y di spawn
     */
    public void setSpawnY(float spawnY) {
        this.spawnY = spawnY;
    }

    /**
     * Restituisce il valore della gravità della mappa.
     *
     * @return gravità
     */
    public float getGRAVITY() {
        return GRAVITY;
    }

    /**
     * Imposta il valore della gravità della mappa.
     *
     * @param GRAVITY nuovo valore di gravità
     */
    public void setGRAVITY(float GRAVITY) {
        this.GRAVITY = GRAVITY;
    }

    /**
     * Restituisce la musica di sottofondo della mappa.
     *
     * @return oggetto {@link Music} associato alla mappa
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Imposta la musica di sottofondo della mappa.
     *
     * @param music nuova musica
     */
    public void setMusic(Music music) {
        this.music = music;
    }

    /**
     * Crea una nuova mappa con il nome specificato e inizializza
     * tutte le liste interne. Carica il file {@code .txt} corrispondente
     * dalla cartella {@code Levels/}.
     *
     * @param name nome della mappa (senza estensione)
     */
    public Map(String name) {
        this.name = name;
        this.map = new ArrayList<>();
        this.blocks = new ArrayList<>();
        textures = new ArrayList<>();
        this.textures.add(null); //per allineare id nel txt e posizione nell'array
        type = new ArrayList<>();
        this.type.add(null); //per allineare posizione tra textures e type
        file = Gdx.files.internal("Levels/" + name + ".txt"); //file contenente informazioni mappa
        x = 0;
        y = 0;
    }

    /**
     * Disegna tutti i blocchi visibili della mappa.
     *
     * @param batch SpriteBatch usato per il rendering
     */
    public void draw(SpriteBatch batch) {
        for (Block block : blocks) {
            if (block != null) {
                block.draw(batch, block.getCorpse());
            }
        }
    }

    /**
     * Libera la memoria di tutti i blocchi della mappa.
     * Da chiamare quando la mappa non è più necessaria.
     */
    public void dispose() {
        for (Block block : blocks) {
            block.dispose();
        }
    }

    /**
     * Legge il file {@code .txt} della mappa e ne esegue il parsing completo.
     * Il file è diviso in due sezioni separate da {@code +\n}:
     * la prima contiene le definizioni delle texture e delle proprietà,
     * la seconda contiene la griglia dei blocchi.
     */
    public void txtToMap() {
        String content = file.readString(); //usata l'AI
        String[] parts = content.split("\\+\n");

        //SUDDIVISIONE SKINS
        setSkins(parts);

        //SUDDIVISIONE MAPPA
        setBlocksZones(parts);
    }

    /**
     * Analizza la prima sezione del file per caricare texture, gravità e musica.
     * Ogni riga ha il formato {@code nomeFile=tipo}, dove il tipo può essere:
     * {@code block}, {@code rope}, {@code kill}, {@code gravity}, {@code music}.
     *
     * @param parts array delle sezioni del file, separato da {@code +\n}
     */
    public void setSkins(String[] parts) {
        String[] lines = parts[0].split("\n");
        for (String line : lines) {

            String[] elements = line.split("=");

            if (Objects.equals(getType(elements[1]), "block")) {
                textures.add(new Texture("Skins\\Blocks\\"+elements[0].trim()));
                type.add("block");
            }
            else if (Objects.equals(getType(elements[1]), "rope")) {
                textures.add(new Texture("Skins\\Blocks\\"+elements[0].trim()));
                type.add("rope");
            }
            else if (Objects.equals(getType(elements[1]), "gravity")) {
                GRAVITY = Float.parseFloat(elements[0]);
            }
            else if (Objects.equals(getType(elements[1]), "music")) {
                music = Gdx.audio.newMusic(Gdx.files.internal("Musics/"+name+".mp3"));
            }
            else if (Objects.equals(getType(elements[1]), "kill")) {
                textures.add(new Texture("Skins\\Blocks\\"+elements[0].trim()));
                type.add("kill");
            }
        }
    }

    /**
     * Analizza la seconda sezione del file per costruire la griglia dei blocchi.
     * Legge la mappa dal basso verso l'alto (ultima riga del file = riga in basso
     * nella mappa), in modo da allineare il sistema di coordinate del txt
     * con quello di gioco. Il marker {@code -0} indica la posizione di spawn.
     *
     * @param parts array delle sezioni del file, separato da {@code +\n}
     */
    public void setBlocksZones(String[] parts) {
        String[] lines = parts[1].split("\n"); //prende tutte le line dell'array
        x = 0;
        y = 0;

        for (int line_id = lines.length-1; line_id >= 0; line_id--) { //parte dall'ultima riga (quella visibile in basso) per costruire la mappa basata sul txt e non al contrario
            String line = lines[line_id];
            x = 0;
            String[] ids = line.trim().split(","); //separa ogni id di blocco

            ArrayList<Integer> row = new ArrayList<>(); //riga da aggiungere alla mappa

            for (String s : ids) { //aggiunge l'id del blocco (tipo 1 o 2)
                if (s.equals("-0")) {
                    spawnX = x+(side/2);
                    spawnY = y+(side/2);
                }
                int id = Integer.parseInt(s.trim());
                row.add(id); //aggiunge l'id nella mappa

                if (id > 0) {
                    if (type.get(id).equals("block")) {
                        blocks.add(new Block(x, y, side, side, textures.get(id)));
                    }
                    else if (type.get(id).equals("rope")) {
                        blocks.add(new Rope(x, y, side, side, textures.get(id)));
                    }
                    else if (type.get(id).equals("kill")) {
                        blocks.add(new KillBlock(x, y, side, side, textures.get(id)));
                    }
                    else {
                        blocks.add(new Block(x, y, side, side, textures.get(id)));
                    }
                }
                x += side;
            }
            y += side;
            map.add(row);
        }
    }

    /**
     * Normalizza e restituisce il tipo di blocco dalla stringa letta nel file.
     * Converte la stringa in minuscolo e rimuove spazi prima del confronto.
     *
     * @param type stringa del tipo letta dal file
     * @return tipo normalizzato, oppure {@code "unknown"} se non riconosciuto
     */
    public String getType(String type) {
        switch (type.trim().toLowerCase()) {
            case "block":
                return "block";
            case "rope":
                return "rope";
            case "kill":
                return "kill";
            case "gravity":
                return "gravity";
            case "character":
                return "character";
            case "music":
                return "music";
            default:
                return "unknown";
        }
    }
}
