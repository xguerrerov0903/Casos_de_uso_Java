import Model.Invent;
import Service.ProductoSer;
import Utils.Inputs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javax.swing.*;

import Model.Producto;

/*
Agregar get descripcion a la impresion de productos
Agregar validaciones en los inputs en su propia clase
Tener una validacion de producto ya existente reutilizable (puede ser el mismo buscar producto)
Imprimir tickey parcial, ver como organizar eso
Tambien el ticket al final
Darle al menu su propia clase
*/


public class Main {

    static ArrayList<String> name_products = new ArrayList<>();
    static double[] prices = new double[0];
    static HashMap<String, Integer> stocks = new HashMap<>();
    static double total = 0;
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
                    // exit the program and show the total
                    JOptionPane.showMessageDialog(null, "Your total is: " + total);
                    return;
                default:
                    // in case the user close the program it shows the total anyway
                    JOptionPane.showMessageDialog(null, "Your total is: " + total);
                    return;
            }
        }
    }

    // function to add a product
    public static void add_product() {
        String name = "";
        do {
            name = Inputs.requestString("Product name: ");
            if (invet.getName_products().contains(name)) {
                JOptionPane.showMessageDialog(null, "Already exist product",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            break;
        } while (true);
        Optional<Double> price_d = Inputs.requestDouble("Product price: ");
        double price = price_d.get();
        int stock = Inputs.requestInteger("Product stock: ");
        String[] options = {"Any", "Food", "Electronics"};
        int cat = JOptionPane.showOptionDialog(
                null,
                "What type of product is it?",
                "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        String message = "Product name: " + name + "\n" +
                "Product price: " + price + "\n" +
                "Product stock: " + stock + "\n" +
                "Product category: " + options[cat] + "\n" +
                "Are you sure you want to add this product?";
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to Save your Previous Note First?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            products.add(ProductoSer.addProduct(invet, name, price, stock, cat));
            JOptionPane.showMessageDialog(null, "Product add");
        }
        return;
    }

    // function to print the products
    public static void print_product() {
        // check if there are products in the list
        if (name_products.isEmpty()) {
            // show a warning message if the list is empty and return to the main menu
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String out_print = "";
        // loop through the products and print their name, price and stock
        for (int i = 0; i < name_products.size(); i++) {
            out_print += name_products.get(i) + " price: " + prices[i] + " stock: " + stocks.get(name_products.get(i)) + "\n";
        }
        // show the products in a message dialog and return to the main menu
        JOptionPane.showMessageDialog(null, out_print);
        return;

    }

    // function to buy a product
    public static void buy_product() {
        // check if there are products in the list
        if (name_products.isEmpty()) {
            // show a warning message if the list is empty and return to the main menu
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ask the user for the product name
        String input_name = javax.swing.JOptionPane.showInputDialog("Product name: ");
        // check if the user pressed cancel and return to the main menu
        if (input_name == null) {
            return;
        }
        // convert the input to lowercase and trim spaces
        String name = input_name.toLowerCase().trim();
        String output_print = "";
        // check if the product exists in the list
        if (name_products.contains(name)) {
            int stock = 0;
            // get the index of the product in the list to access its price and stock
            int i = name_products.indexOf(name);
            // print the product details
            output_print += name_products.get(i) + " price: " + prices[i] + " stock: " + stocks.get(name_products.get(i));
            JOptionPane.showMessageDialog(null, output_print);
            try {
                // ask the user for the amount to buy
                stock = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("How many?"));
                // check if the amount is valid and if there is enough stock
                if (stock <= 0 || stock > stocks.get(name_products.get(i))) {
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
            int stock_amount = stocks.get(name);
            stocks.put(name, stock_amount - stock);
            double sub_total = prices[i] * stock;
            total += sub_total;
            // show a message that the product was purchased with the total
            JOptionPane.showMessageDialog(null, "Purchased product: " + name + " amount: " + stock + " total: " + sub_total);

        } else {
            // show an error message if the product does not exist
            JOptionPane.showMessageDialog(null, "Product not found",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        return;

    }

    // function to show the stats of the products sorted by price min to max
    public static void stats_products() {
        // check if there are products in the list
        if (name_products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // create an arraylist to store the indexes of the products
        ArrayList<Integer> index_p = new ArrayList<>();
        String output_print = "";

        // fill the arraylist with the indexes of the products
        for (int i = 0; i < prices.length; i++) {
            index_p.add(i);
        }
        // sort the arraylist by the prices of the products using a lambda function
        index_p.sort((i1, i2) -> Double.compare(prices[i1], prices[i2]));
        // loop through the sorted arraylist and print the product details
        for (int num : index_p) {
            output_print += name_products.get(num) + " price: " + prices[num] + "\n";
        }
        // show the sorted products in a message dialog and return to the main menu
        JOptionPane.showMessageDialog(null, output_print);
        return;
    }

    // function to search for a product by name
    public static void search_product() {
        // check if there are products in the list if not show a warning message and return to the main menu
        if (name_products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ask the user for the product name
        String name = javax.swing.JOptionPane.showInputDialog("Product name: ").toLowerCase().trim();
        String output_print = "";
        // check if the product exists in the list
        if (name_products.contains(name)) {
            // get the index of the product in the list to access its price and stock
            int i = name_products.indexOf(name);
            output_print += name_products.get(i) + " price: " + prices[i] + " stock: " + stocks.get(name_products.get(i));
            // show the product details in a message dialog and return to the main menu
            JOptionPane.showMessageDialog(null, output_print);
        } else {
            // show an error message if the product does not exist
            JOptionPane.showMessageDialog(null, "Product not found",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        return;
    }
}
