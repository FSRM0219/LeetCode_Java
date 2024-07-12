import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println(s.length());
        String[] str = s.split(" ");
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }
}
