// importing necessary classes
import Model.Invent;
import Model.Producto;
import Service.ProductoSer;
import Utils.Inputs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javax.swing.*;



public class Main {

    // Create an instance of the inventory and an arraylist to store the products
    static Invent invet = new Invent();
    static ArrayList<Producto> products = new ArrayList<>();

    public static void main(String[] args) {
        // main menu loop
        while (true) {
            // menu options for the button dialog
            String[] options = {"Add product", "List inventory", "Buy product", "Show stats", "Share product", "Exit"};
            int selection = JOptionPane.showOptionDialog(
                    null,
                    "Menu options",
                    "",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            // switch case for the menu options
            switch (selection) {
                case 0:
                    add_product();
                    break;
                case 1:
                    print_product();
                    break;
                case 2:
                    buy_product();
                    break;
                case 3:
                    stats_products();
                    break;
                case 4:
                    search_product();
                    break;
                case 5:
                    // exit the program and show the ticket
                    String print_ticket = "----- TICKET -----\n";
                    for (String line : invet.getTicket()) {
                        print_ticket += line;
                    }
                    print_ticket += "Total: " + invet.getTotal() + "\n";
                    print_ticket += "------------------\n";
                    JOptionPane.showMessageDialog(null, print_ticket);
                    return;
                default:
                    // in case the user close the program it shows the ticket anyway
                    String print_ticket_a = "----- TICKET -----\n";
                    for (String line : invet.getTicket()) {
                        print_ticket_a += line;
                    }
                    print_ticket_a += "Total: " + invet.getTotal() + "\n";
                    print_ticket_a += "------------------\n";
                    JOptionPane.showMessageDialog(null, print_ticket_a);
                    return;
            }
        }
    }

    // function to add a product
    public static void add_product() {
        String name = "";
        do {
            // ask the user for the product name
            name = Inputs.requestString("Product name: ");
            System.out.println(name);
            // check if the product already exists in the list
            if (invet.getName_products().contains(name)) {
                JOptionPane.showMessageDialog(null, "Already exist product",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            // if the user press cancel return to the main menu
            if (name == null){
                return;
            }
            break;
        } while (true);
        // ask the user for the product price and stock in case of cancel return to the main menu
        Optional<Double> price_d = Inputs.requestDouble("Product price: ");
        if (price_d.isEmpty()){
            return;
        }
        // get the value of the price in case of empty (cancel) return to the main menu
        double price = price_d.get();
        Integer stock = Inputs.requestInteger("Product stock: ");
        if (stock == null){
            return;
        }
        // ask the user for the product category using a button dialog
        String[] options = {"Food", "Electronics"};
        Integer cat = JOptionPane.showOptionDialog(
                null,
                "What type of product is it?",
                "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        // in case of cancel return to the main menu
        if (cat==-1){
            return;
        }
        // show a confirmation dialog with the product details
        String message = "Product name: " + name + "\n" +
                "Product price: " + price + "\n" +
                "Product stock: " + stock + "\n" +
                "Product category: " + options[cat] + "\n" +
                "Are you sure you want to add this product?";
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, message, "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            // if the user confirms, add the product to the list and show a message
            products.add(ProductoSer.addProduct(invet, name, price, stock, cat));
            JOptionPane.showMessageDialog(null, "Product add");
        }

    }

    // function to print the products
    public static void print_product() {
        // check if there are products in the list
        if (invet.getName_products().isEmpty()) {
            // show a warning message if the list is empty and return to the main menu
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // get the products from the inventory service and show them in a message dialog
        String out_print = ProductoSer.getProducts(invet, products);
        JOptionPane.showMessageDialog(null, out_print);

    }

    // function to buy a product
    public static void buy_product() {
        // check if there are products in the list
        if (invet.getName_products().isEmpty()) {
            // show a warning message if the list is empty and return to the main menu
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ask the user for the product name
        String name = Inputs.requestString("Product name: ");
        // in case of cancel return to the main menu
        if (name == null){
            return;
        }
        String output_print = "";
        // check if the product exists in the list
        if (invet.getName_products().contains(name)) {
            Integer stock = 0;
            // get the index of the product in the list to access its price and stock
            int i = invet.getName_products().indexOf(name);
            // print the product details
            output_print += invet.getName_products().get(i) + " price: " + invet.getPrices()[i] + " stock: " + invet.getStocks().get(invet.getName_products().get(i));
            JOptionPane.showMessageDialog(null, output_print);
            try {
                // ask the user for the amount to buy
                stock = Inputs.requestInteger("Amount to buy: ");
                // check if the amount is valid and if there is enough stock
                if (stock <= 0 || stock > invet.getStocks().get(invet.getName_products().get(i))) {
                    // if not, throw an exception
                    throw new IllegalArgumentException("");
                }
            } catch (Exception e) {
                // show an error message if the input is not a valid number or if there is not enough stock
                JOptionPane.showMessageDialog(null, "Invalid amount or out of stock",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // update the stock in the hashmap and calculate the total and change the global total
            int stock_amount = invet.getStocks().get(name);
            invet.getStocks().put(name, stock_amount - stock);
            double sub_total = invet.getPrices()[i] * stock;
            invet.addTotal(sub_total);
            // show a message that the product was purchased with the total
            JOptionPane.showMessageDialog(null, "Purchased product: " + name + " amount: " + stock + " total: " + sub_total);
            // create a ticket entry and add it to the ticket list
            String ticket = "Name: " + name +
                    " Price: " + sub_total +
                    " Stock: " + stock + "\n" +
                    "Description: " + products.get(i).getDescription() +
                    "\n------------------------\n";
            invet.getTicket()
                    .add(ticket);
        } else {
            // show an error message if the product does not exist
            JOptionPane.showMessageDialog(null, "Product not found",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }

    }

    // function to show the stats of the products sorted by price min to max
    public static void stats_products() {
        // check if there are products in the list
        if (invet.getName_products().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // create an arraylist to store the indexes of the products
        ArrayList<Integer> index_p = new ArrayList<>();
        String output_print = "";

        // fill the arraylist with the indexes of the products
        for (int i = 0; i < invet.getPrices().length; i++) {
            index_p.add(i);
        }
        // sort the arraylist by the prices of the products using a lambda function
        index_p.sort((i1, i2) -> Double.compare(invet.getPrices()[i1], invet.getPrices()[i2]));
        // loop through the sorted arraylist and print the product details
        for (int num : index_p) {
            output_print += invet.getName_products().get(num) + " price: " + invet.getPrices()[num] + "\n";
        }
        // show the sorted products in a message dialog and return to the main menu
        JOptionPane.showMessageDialog(null, output_print);

    }

    // function to search for a product by name
    public static void search_product() {
        // check if there are products in the list if not show a warning message and return to the main menu
        if (invet.getName_products().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ask the user for the product name
        String name = Inputs.requestString("Product name: ");
        if (name == null) {
            return;
        }

        String output_print = "";
        // check if the product exists in the list
        if (invet.getName_products().contains(name)) {
            // get the index of the product in the list to access its price and stock
            int i = invet.getName_products().indexOf(name);
            output_print += invet.getName_products().get(i) + " price: " + invet.getPrices()[i] + " stock: " + invet.getStocks().get(invet.getName_products().get(i));
            // show the product details in a message dialog and return to the main menu
            JOptionPane.showMessageDialog(null, output_print);
        } else {
            // show an error message if the product does not exist
            JOptionPane.showMessageDialog(null, "Product not found",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }

}