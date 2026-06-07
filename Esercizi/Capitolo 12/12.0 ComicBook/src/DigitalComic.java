public class DigitalComic extends Comic implements Gradable {

    public DigitalComic(String title, int rarityIndex, Publisher publisher) {
        super(title, rarityIndex, publisher);
    }

    @Override
    public String getFormatDetails() {
        return "Digital Format: PDF/CBZ";
    }

    @Override
    public boolean isWorthGrading() {
        if (getRarityIndex() >= 50) {
            return true;
        }
        return false;
    }
}
