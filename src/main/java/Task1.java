import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int[] array = new Random().ints(n, -100, 101).toArray();
        int count = Arrays.stream(array).filter(num -> num > 0).sum();
        System.out.println(Arrays.toString(array));
        System.out.println(count);
    }
}
