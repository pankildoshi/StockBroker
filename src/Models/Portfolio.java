package Models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<PortfolioStock> stocks;
    private double totalInvestment;
    public Portfolio(){
        stocks = new ArrayList<>();
    }

    public List<PortfolioStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<PortfolioStock> stocks) {
        this.stocks = stocks;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public void viewPortfolio(){
        System.out.println("Total Investment: " + new DecimalFormat("#.##").format(totalInvestment));
        List<PortfolioStock> currentStocks = new ArrayList<>(this.stocks);
        if(currentStocks.isEmpty()){
            System.out.println("No stocks in your portfolio");
            return;
        }
        for(PortfolioStock stock : currentStocks){
            System.out.println(stock);
        }
        System.out.println("Returns: " + new DecimalFormat("#.##").format(getReturns(currentStocks)));
    }

    private double getReturns(List<PortfolioStock> stocks) {
        double profitNLoss = 0;

        for(PortfolioStock stock : stocks){
            double investment = stock.getPurchasedPrice() * stock.getQuantity();
            double currentValue = stock.getStock().getPrice() * stock.getQuantity();
            profitNLoss += currentValue - investment;
        }

        return profitNLoss;
    }

    public void listPortfolioStocks(){
        if(this.stocks.isEmpty()){
            System.out.println("No stocks in your portfolio");
            return;
        }
        for(PortfolioStock stock : this.stocks){
            System.out.println(stock);
        }
    }
}
