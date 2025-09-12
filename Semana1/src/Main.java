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
            break;
        }
    }

    public static void add_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stocks){
        String name;
        do{
            name = javax.swing.JOptionPane.showInputDialog("Product name: ").toLowerCase().trim();
            if (name_products.contains(name)){
                JOptionPane.showMessageDialog(null, "Producto ya existente",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            name_products.add(name);
            break;
        }while (true);
        do{
            try{
                double price = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Producto price"));
                if (price<=0){
                    throw new IllegalArgumentException("");
                }
                double [] prices_copy = new double[prices.length+1];

                for (int i= 0; i<prices.length; i++){
                    prices_copy[i] = prices[i];
                }
                prices_copy[prices_copy.length-1] = price;
                prices = prices_copy;
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid price",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }while(true);
        do{
            try{
                int stock = Integer.parseInt( javax.swing.JOptionPane.showInputDialog("Producto stock"));
                if (stock<=0){
                    throw new IllegalArgumentException("");
                }
                stocks.put(name,stock);
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid stock",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }while(true);
        JOptionPane.showMessageDialog(null,"Product add");
    }
    public static void print_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock){
        String out_print = "";
        for (int i=0; 0<name_products.size(); i++){
            out_print += name_products.get(i)+" price: "+prices[i]+" stock: "+stock.get(name_products.get(i))+"\n";
        }
        JOptionPane.showMessageDialog(null,out_print);

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
             output_print+= name_products.get(num)+" price: "+prices[num]+"\n";
         }
        JOptionPane.showMessageDialog(null,output_print);
    }

    public static void search_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock, double total){
        String name = javax.swing.JOptionPane.showInputDialog("Product name: ").toLowerCase().trim();
        String output_print = "";
        if (name_products.contains(name)){
            int i = name_products.indexOf(name);
            output_print+= name_products.get(i)+" price: "+prices[i]+" stock: "+stock.get(name_products.get(i));
            JOptionPane.showMessageDialog(null,output_print);
        }else{
            JOptionPane.showMessageDialog(null, "Product not find",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }



}
