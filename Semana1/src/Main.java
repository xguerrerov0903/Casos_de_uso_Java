import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ArrayList<String> name_products = new ArrayList<>();
    static double[] prices = new double[0];
    static HashMap<String, Integer> stocks = new HashMap<>();
    static double total = 0;
    public static void main(String[] args) {
        while (true) {
            String[] options = {"Add product", "List inventory", "Buy product", "Show stats", "Share product", "Exit"};
            int selection = JOptionPane.showOptionDialog(
                    null,
                    "Menu options",
                    "",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

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
                    JOptionPane.showMessageDialog(null,"Your total is: "+total);
                    return;
                default:
                    return;
            }
        }

    }

    public static void add_product (){
        String name;
        do{
            String input_name = javax.swing.JOptionPane.showInputDialog("Product name: ");
            if (input_name == null) {
                return;
            } else if ( input_name.isEmpty()){
                JOptionPane.showMessageDialog(null, "Empty name",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            name = input_name.toLowerCase().trim();
            if (name_products.contains(name)){
                JOptionPane.showMessageDialog(null, "Already exist product",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            name_products.add(name);
            break;
        }while (true);
        do{
            try{
                String input_price = JOptionPane.showInputDialog("Producto price");

                if (input_price == null) {
                    return;
                }
                double price = Double.parseDouble(input_price);
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
                String input_stock = JOptionPane.showInputDialog("Product stock");

                if (input_stock == null) {
                    return;
                }
                int stock = Integer.parseInt(input_stock);
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
        return;
    }
    public static void print_product (){
        if (name_products.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String out_print = "";
        for (int i=0; i<name_products.size(); i++){
            out_print += name_products.get(i)+" price: "+prices[i]+" stock: "+stocks.get(name_products.get(i))+"\n";
        }
        JOptionPane.showMessageDialog(null,out_print);
        return;

    }
    public static void buy_product (){
        if (name_products.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String input_name = javax.swing.JOptionPane.showInputDialog("Product name: ");
        if (input_name == null) {
            return;
        }
        String name = input_name.toLowerCase().trim();
        String output_print = "";
        if (name_products.contains(name)){
            int stock = 0;
            int i = name_products.indexOf(name);
            output_print+= name_products.get(i)+" price: "+prices[i]+" stock: "+stocks.get(name_products.get(i));
            JOptionPane.showMessageDialog(null,output_print);
            try{
                stock = Integer.parseInt( javax.swing.JOptionPane.showInputDialog("How many?"));
                if (stock<=0 || stock>stocks.get(name_products.get(i))){
                    throw new IllegalArgumentException("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid amount or out of stock",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int stock_amount = stocks.get(name);
            stocks.put(name, stock_amount-stock);
            double sub_total = prices[i]*stock;
            total += sub_total;
            JOptionPane.showMessageDialog(null,"Purchased product: "+name+" amount: "+stock+" total: "+sub_total);

        }else{
            JOptionPane.showMessageDialog(null, "Product not found",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        return;

    }
    public static void stats_products (){
        if (name_products.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
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
        return;
    }


    public static void search_product (){
        if (name_products.isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty stock",
                    "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String name = javax.swing.JOptionPane.showInputDialog("Product name: ").toLowerCase().trim();
        String output_print = "";
        if (name_products.contains(name)){
            int i = name_products.indexOf(name);
            output_print+= name_products.get(i)+" price: "+prices[i]+" stock: "+stocks.get(name_products.get(i));
            JOptionPane.showMessageDialog(null,output_print);
        }else{
            JOptionPane.showMessageDialog(null, "Product not found",
                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        return;
    }



}
