import java.time.LocalDate; // import the LocalDate class
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDate myObj = LocalDate.now();// Create a date object
        System.out.println(myObj); // Display the current date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = dateFormat.format(myObj);//Riformatta la data
        System.out.println(date);

        myObj = myObj.plusYears(100);
        System.out.println(myObj);
    }
}