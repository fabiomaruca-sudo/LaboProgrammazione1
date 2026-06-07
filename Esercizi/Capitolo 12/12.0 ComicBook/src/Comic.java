public abstract class Comic {
    private String title;
    private int rarityIndex;
    private Publisher publisher;

    public Comic(String title, int rarityIndex, Publisher publisher) {
        this.title = title;
        if (rarityIndex < 0 || rarityIndex > 100) {
            throw new IllegalArgumentException("Rarity must be between 0 and 100");
        }
        this.rarityIndex = rarityIndex;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public int getRarityIndex() {
        return rarityIndex;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void boostRarity(int points) {
        if (rarityIndex + points > 100) {
            throw new IllegalArgumentException("Rarity exceeds 100");
        }

        if (rarityIndex + points < 0) {
            return;
        }

        rarityIndex += points;
    }

    abstract public String getFormatDetails();
}
