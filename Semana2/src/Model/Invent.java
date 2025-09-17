package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Invent {
    private ArrayList<String> name_products = new ArrayList<>();
    private double[] prices = new double[0];
    private HashMap<String, Integer> stocks = new HashMap<>();
    private ArrayList<String> ticket = new ArrayList<>();
    private double total = 0;

    public ArrayList<String> getName_products() {
        return name_products;
    }
    public void setPrices (double[] prices) {
        this.prices = prices;
    }

    public double[] getPrices() {
        return prices;
    }

    public HashMap<String, Integer> getStocks() {
        return stocks;
    }

    public double getTotal() {
        return total;
    }

    public void addTotal (double price) {
        this.total += price;
    }

    public ArrayList<String> getTicket() {
        return ticket;
    }
}
