package Model;

import javax.swing.*;

public class Electrical extends Producto{
    public Electrical(String name, double price) {
        super(name, price);
    }
    @Override
    public void getDescription() {
        JOptionPane.showMessageDialog(null,"This is a electrical product named " + getName() + " with a price of " + getPrice());
    }
}

