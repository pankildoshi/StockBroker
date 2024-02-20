package Models;

import java.text.DecimalFormat;

public class PortfolioStock {
    private Stock stock;
    private int quantity;
    private double purchasedPrice;

    public PortfolioStock(Stock stock, int quantity, double purchasedPrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.purchasedPrice = purchasedPrice;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    @Override
    public String toString() {
        return "PortfolioStock{" +
                "stock=" + stock +
                ", quantity=" + quantity +
                ", purchasedPrice=" + new DecimalFormat("#.##").format(purchasedPrice) +
                '}';
    }
}
