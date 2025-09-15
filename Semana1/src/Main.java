import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class Main {
    // Declare global variables
    static ArrayList<String> name_products = new ArrayList<>();
    static double[] prices = new double[0];
    static HashMap<String, Integer> stocks = new HashMap<>();
    static double total = 0;

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
        String name;
        do {
            String input_name = javax.swing.JOptionPane.showInputDialog("Product name: ");
            // check if the user pressed cancel and return to the main menu
            if (input_name == null) {
                return;
            // check if the input is empty and show a warning message
            } else if (input_name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty name",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            name = input_name.toLowerCase().trim();
            // check if the product already exist and show a warning message
            if (name_products.contains(name)) {
                JOptionPane.showMessageDialog(null, "Already exist product",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            // add the product name to the list
            name_products.add(name);
            break;
        } while (true);
        do {
            try {
                String input_price = JOptionPane.showInputDialog("Producto price");
                // check if the user pressed cancel and return to the main menu
                if (input_price == null) {
                    return;
                }
                double price = Double.parseDouble(input_price);
                // check if the price is less than or equal to 0 and throw an exception
                if (price <= 0) {
                    throw new IllegalArgumentException("");
                }
                // create a new array with the new price and copy the old prices
                double[] prices_copy = new double[prices.length + 1];
                for (int i = 0; i < prices.length; i++) {
                    prices_copy[i] = prices[i];
                }
                prices_copy[prices_copy.length - 1] = price;
                // assign the new array to the old array
                prices = prices_copy;
                break;
            } catch (Exception e) {
                // show an error message if the input is not a valid number
                JOptionPane.showMessageDialog(null, "Invalid price",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);
        do {
            try {
                String input_stock = JOptionPane.showInputDialog("Product stock");
                // check if the user pressed cancel and return to the main menu
                if (input_stock == null) {
                    return;
                }
                int stock = Integer.parseInt(input_stock);
                // check if the stock is less than or equal to 0 and throw an exception
                if (stock <= 0) {
                    throw new IllegalArgumentException("");
                }
                // add the stock to the hashmap with the product name as key
                stocks.put(name, stock);
                break;
            } catch (Exception e) {
                // show an error message if the input is not a valid number
                JOptionPane.showMessageDialog(null, "Invalid stock",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);
        // show a message that the product was added
        JOptionPane.showMessageDialog(null, "Product add");
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
