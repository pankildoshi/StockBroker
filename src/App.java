import Models.Stock;
import Models.User;
import Services.StockUpdater;

import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class App {
    public static void main(String[] args){
        System.out.println("Stock Broker Application Running...\n");

        String filename = "data/stocks.txt";
        List<Stock> stocks = Stock.readStocksFromFile(filename);
        StockUpdater.start(stocks);

        User user = new User("pankil19", "Pankil", "Doshi", "1234", 10000);
        Scanner scanner = new Scanner(System.in);

        // Login Flow
        /*System.out.println("Welcome!");
        System.out.println("Please enter your username and password to login\n");

        final int MAX_LOGIN_ATTEMPTS = 3;
        int loginAttempts =  0;
        while (loginAttempts < MAX_LOGIN_ATTEMPTS){
            System.out.print("username: ");
            String username = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();

            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("Login successful!\n");
                break;
            }

            loginAttempts++;
            if(loginAttempts == MAX_LOGIN_ATTEMPTS){
                System.out.println("Invalid Username or Password, Please Try again later :(\n");
            }
            else{
                System.out.println("Invalid Username or Password, "
                        + (MAX_LOGIN_ATTEMPTS - loginAttempts)
                        + " Attempts left.\n");
            }
        }*/
        // Login Flow Ends

        // Main Application Flow
        while(true){
            System.out.println("\nSelect one of the below option\n");

            System.out.println("1. View Stock");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Add Funds");
            System.out.println("6. Withdraw Funds");
            System.out.println("7. Exit");

            System.out.print("\nEnter option number: ");
            int option = Integer.parseInt(scanner.nextLine());
            System.out.println();

            String stockName;
            int quantity;
            Stock selectedStock;
            switch (option){
                case 1:
                    System.out.println("1. View Stock");
                    for(Stock stock : stocks){
                        System.out.println(stock);
                    }
                    break;
                case 2:
                    System.out.println("2. Buy Stock");
                    System.out.println("Enter Stock name: ");
                    stockName = scanner.nextLine();
                    System.out.println("Enter quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());

                    selectedStock = null;
                    for(Stock stock : stocks){
                        if(stock.getName().equals(stockName)){
                            selectedStock = stock;
                            break;
                        }
                    }

                    if(selectedStock != null){
                        user.buyStock(selectedStock, quantity);
                    }
                    else{
                        System.out.println("Wrong stock name, Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("3. Sell Stock");
                    if(user.getPortfolio().getStocks().isEmpty()){
                        System.out.println("No Stocks in your portfolio");
                        break;
                    }
                    user.getPortfolio().listPortfolioStocks();
                    System.out.println("Enter Stock name: ");
                    stockName = scanner.nextLine();
                    System.out.println("Enter quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());

                    selectedStock = null;
                    for(Stock stock : stocks){
                        if(stock.getName().equals(stockName)){
                            selectedStock = stock;
                            break;
                        }
                    }

                    if(selectedStock != null){
                        user.sellStock(selectedStock, quantity);
                    }
                    else{
                        System.out.println("Wrong stock name, Please try again.");
                    }
                    break;
                case 4:
                    user.getPortfolio().viewPortfolio();
                    break;
                case 5:
                    System.out.println("Enter amount to be added: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    user.addFunds(amount);
                    break;
                case 6:
                    System.out.println("Enter amount to withdraw: ");
                    amount = Double.parseDouble(scanner.nextLine());
                    user.withdrawFunds(amount);
                    break;
                case 7:
                    StockUpdater.stop();
                    Stock.writeStocksToFile(filename, stocks);
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid Option Choice, Please select appropriate option to proceed.");
                    break;
            }
        }
    }
}
