package task3;
import java.util.ArrayList;
import java.util.List;

interface Loader {
    void load();
}

class LoaderRealization implements Runnable, Loader {
    private static final int MAX_WEIGHT = 150; 
    private static int FullWeight = 0;   
    private static final Object lock = new Object();
    private List<Integer> warehouse; 
    private List<Integer> productList = new ArrayList<>(); 

    public LoaderRealization(List<Integer> warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        load();
    }

    @Override
    public void load() {
        while (true) {
            int localWeight = 0;

            synchronized (lock) {
                while (!warehouse.isEmpty() && FullWeight + warehouse.get(0) <= MAX_WEIGHT) {
                    int item = warehouse.remove(0);
                    productList.add(item);
                    FullWeight += item;
                    localWeight += item;
                }

                if (localWeight == 0) {
                    break;
                }
            }

            System.out.println(Thread.currentThread().getName() + " переносит товары " + productList + " общий вес " + FullWeight + " кг");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (lock) {
                FullWeight -= localWeight;
            }

            System.out.println(Thread.currentThread().getName() + " разгрузил товары. Общий вес теперь " + FullWeight + " кг");
            productList.clear();
        }
    }
}

