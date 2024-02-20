package Models;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private double tradingBalance;
    private Portfolio portfolio;

    public User() { }

    public User(String username, String firstName, String lastName, String password, double tradingBalance) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.tradingBalance = tradingBalance;
        this.portfolio = new Portfolio();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getTradingBalance() {
        return tradingBalance;
    }

    public void setTradingBalance(double tradingBalance) {
        this.tradingBalance = tradingBalance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void buyStock(Stock stock, int quantity){
        double stockPrice = stock.getPrice();
        double purchaseAmount = stockPrice * quantity;
        if(tradingBalance >= purchaseAmount){
            PortfolioStock portfolioStock = new PortfolioStock(stock, quantity, stockPrice);

            tradingBalance -= purchaseAmount;
            this.portfolio.getStocks().add(portfolioStock);
            this.portfolio.setTotalInvestment(this.portfolio.getTotalInvestment() + purchaseAmount);
            System.out.println(stock.getName() + " added to your portfolio.");
        }
        else{
            System.out.println("Insufficient Trading balance, Add Funds to continue.\n");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        double stockPrice = stock.getPrice();
        PortfolioStock selectPortfolioStock = null;
        for(PortfolioStock portfolioStock : portfolio.getStocks()){
            if(portfolioStock.getStock().getName().equals(stock.getName())){
                selectPortfolioStock = portfolioStock;
            }
        }

        if(selectPortfolioStock != null){
            int purchasedQuantity = selectPortfolioStock.getQuantity();
            if(quantity <= purchasedQuantity){
                tradingBalance += stockPrice * quantity;
                portfolio.setTotalInvestment(portfolio.getTotalInvestment() - (selectPortfolioStock.getPurchasedPrice() * quantity));
                selectPortfolioStock.setQuantity(purchasedQuantity - quantity);
                if(quantity == purchasedQuantity){
                    portfolio.getStocks().remove(selectPortfolioStock);
                }
            }
            else{
                System.out.println("Invalid stock quantity. \n");
            }
        }
        else{
            System.out.println("No such stock in your portfolio, Please try again.");
        }
    }

    public void addFunds(double amount) {
        setTradingBalance(this.tradingBalance + amount);
        System.out.println("Funds Added");
        System.out.println("Current Trading Balance: " + this.tradingBalance);
    }

    public void withdrawFunds(double amount) {
        setTradingBalance(this.tradingBalance - amount);
        System.out.println("Funds Withdrawn");
        System.out.println("Current Trading Balance: " + this.tradingBalance);
    }
}
