package Services;

import Models.Stock;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockUpdater {
    private static ExecutorService executor;

    public static void start(List<Stock> stocks){
        final int NUM_STOCKS = stocks.size();
        executor = Executors.newFixedThreadPool(NUM_STOCKS);

        for (int i = 0; i < NUM_STOCKS; i++) {
            StockUpdaterTask task = new StockUpdaterTask(stocks.get(i));
            executor.execute(task);
        }
    }

    public static void stop(){
        executor.shutdown();
    }
}

class StockUpdaterTask implements Runnable{
    Stock stock;
    public StockUpdaterTask(Stock stock){
        this.stock = stock;
    }
    @Override
    public void run() {
        try {
            while(true){
                double stockPriceChange = generatePriceChange();
                double newStockPrice = this.stock.getPrice() + stockPriceChange;
    
                stock.setPrice(newStockPrice);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private double generatePriceChange() {
        return Math.random() * 10 - 5;
    }
}