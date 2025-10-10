//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int length = 0;
        int direction = 0;

        if (args.length < 2) {
            System.out.println("Insufficient arguments! At least 2 arguments are required.");
            return;
        }
        try {
            length = Integer.parseInt(args[0]);
            direction = Integer.parseInt(args[1]);

            if (length < 1) {
                System.out.println("You must enter a positive integer!");
                return;
            }
            if (direction > 1) {
                System.out.println("Direction can only be 0 or 1");
                return;
            }
        }catch (NumberFormatException e) {
            System.out.println("value error! Insert a number!");
        }

        for (int i = 1; i <= length; i++) {
            if (direction == 0) {
                System.out.print("*\t");
            } else {
                System.out.println("*");
            }
        }
    }
}