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
        s.startsWith("h");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.remove(0);
        list.remove(1);
    }
}
