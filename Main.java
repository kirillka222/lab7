package task3;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<Integer> warehouse = new ArrayList<>(List.of(50, 70, 30, 40, 20,
                                                90, 60, 40, 50, 80, 30, 70, 20, 40, 30));

        ExecutorService executor = Executors.newFixedThreadPool(3); // пул из потоков (грузчики)

        for (int i = 0; i < 3; i++) {
            executor.submit(new LoaderRealization(warehouse));
        }
        executor.shutdown();
    }
}


