public class App {
    public static void main(String[] args) {
        String s = "Hello World";
        System.out.println(s.length());
        String[] str = s.split(" ");
        for (String string : str) {
            System.out.println(string);
        }
    }
}
