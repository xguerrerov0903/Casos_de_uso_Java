package Model;

import javax.swing.*;

public class Food extends Producto{
    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public void getDescription() {
        JOptionPane.showMessageDialog(null,"This is a food product named " + getName() + " with a price of " + getPrice());
    }
}