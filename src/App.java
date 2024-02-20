import Models.Stock;
import Services.StockUpdater;

import java.util.List;

public class App {
    public static void main(String[] args){
        System.out.println("Stock Broker Application Running...");

        String filename = "data/stocks.txt";
        List<Stock> stocks = Stock.readStocksFromFile(filename);

        StockUpdater.start(stocks);

    }
}
