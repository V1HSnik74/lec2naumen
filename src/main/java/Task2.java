import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        List<Double> list = new Random()
                .doubles(n, -100, 100)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
        insertionSort(list);
        System.out.println(list);
    }

    private static void insertionSort(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            Double nextEl = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > nextEl) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, nextEl);
        }
    }
}
