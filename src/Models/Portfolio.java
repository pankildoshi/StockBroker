package Models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<PortfolioStock> stocks;
    private double totalInvestment;
    private double returns;

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

    public double getReturns() {
        return returns;
    }

    public void setReturns(double returns) {
        this.returns = returns;
    }

    public void viewPortfolio(){
        System.out.println("Total Investment: " + new DecimalFormat("#.##").format(totalInvestment));
        listPortfolioStocks();
        System.out.println("Returns: " + new DecimalFormat("#.##").format(returns));
    }

    public void listPortfolioStocks(){
        for(PortfolioStock stock : this.stocks){
            System.out.println(stock);
        }
    }
}
