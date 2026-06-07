public class PhysicalComic extends Comic {
    private int boxNumer;

    public PhysicalComic(String title, int rarityIndex, Publisher publisher, int boxNumer) {
        super(title, rarityIndex, publisher);
        this.boxNumer = boxNumer;
    }

    @Override
    public String getFormatDetails() {
        return "Physical Copy - Box: "+boxNumer;
    }
}
