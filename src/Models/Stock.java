package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String name;
    private double price;

    public Stock(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static List<Stock> readStocksFromFile(String filename){
        List<Stock> stocks = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String record;
            while((record = reader.readLine()) != null){
                String[] fields = record.split(",");
                String name = fields[0];
                double price = Double.parseDouble(fields[1]);

                Stock stock = new Stock(name, price);
                stocks.add(stock);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong, Please try again later" + e);
        }

        return stocks;
    }

    public static void writeStocksToFile(String filename, List<Stock> stocks){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            for(Stock stock : stocks){
                writer.println(stock.getName() + ", " + stock.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong, Please try again later" + e);
        }
    }
}
