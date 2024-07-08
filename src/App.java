import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Random rm = new Random();
        for (int i = 0; i < 6; i++) {
            int num = rm.nextInt(3);
            System.out.printf("%d ", num);
        }
    }
}
