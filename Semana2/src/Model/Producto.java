package Model;

import javax.swing.*;

public class Producto {
    private String name;
    private double price;

    public Producto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name need to be filled");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Model.Producto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public void getDescription() {
        JOptionPane.showMessageDialog(null,"This is a product named " + name + " with a price of " + price );
    }
}





