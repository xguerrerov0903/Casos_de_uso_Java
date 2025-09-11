import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> name_products = new ArrayList<>();
        double[] prices;
        HashMap<String, Integer> stocks = new HashMap<>();
        double total = 0;
        while (true) {
            System.out.println("Options:");
        }
    }

    public static void add_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock){
        var name = javax.swing.JOptionPane.showInputDialog("Product name: ");
        double price = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Producto price"));
        int stocks = Integer.parseInt( javax.swing.JOptionPane.showInputDialog("What is your name?"));

    }
    public static void print_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock){

    }
    public static void buy_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock, double total){

    }
    public static void stats_products (ArrayList<String> name_products, double[] prices){
        ArrayList<Integer> index_p = new ArrayList<>();
        String output_print = "";

        for (int i= 0; i<prices.length; i++){
            index_p.add(i);
        }
        index_p.sort((i1,i2)-> Double.compare(prices[i1], prices[i2]));
        for (int num : index_p){
             output_print+= name_products.get(num)+" price: "+prices[num];
         }
        JOptionPane.showMessageDialog(null,output_print);
    }

}
