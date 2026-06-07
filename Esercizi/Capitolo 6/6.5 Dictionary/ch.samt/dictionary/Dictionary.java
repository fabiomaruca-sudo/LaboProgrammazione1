package dictionary;

import java.util.HashMap;

public class Dictionary {
    final private HashMap<String, Entry> vocaboli;


    public Dictionary() {
        vocaboli = new HashMap<String, Entry>();
    }


    public HashMap<String, Entry> getVocaboli() {
        return vocaboli;
    }


    public void aggiungi(Entry e) {
        vocaboli.put(e.getParolaItaliano(), e);
    }

    public Entry cerca(String parolaItaliano) {
        return vocaboli.get(parolaItaliano);
    }

    public void stampaTutto() {
        for (Entry e : vocaboli.values()) {
            System.out.println(e);
        }
    }

}
