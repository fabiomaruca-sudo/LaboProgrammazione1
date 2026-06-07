//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            House house = new House();
            house.visualizzaStanze();
            house.aggiungiStanza("cucina", 20);
            house.aggiungiStanza("bagno", 5.5f);
            house.aggiungiStanza("villa", 150);

            house.visualizzaStanze();
            System.out.println(house.getSuperficieTotale());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}