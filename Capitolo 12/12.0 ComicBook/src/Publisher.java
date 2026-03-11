public class Publisher {
    private String name;
    private int foundedYear;

    public Publisher(String name, int foundedYear) {
        this.name = name;
        this.foundedYear = foundedYear;
    }

    public String getName() {
        return name;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    //METODI
    public String getInfo() {
        return "Publisher: ["+name+"] (Founded: ["+foundedYear+"])";
    }
}
