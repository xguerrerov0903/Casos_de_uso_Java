import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ArrayList<String> name_products = new ArrayList<>();
        double[] prices;


        HashMap<String, Integer> stock = new HashMap<>();
        double total = 0;

        while (true) {
            System.out.println("Options:");
        }
    }

    public static void add_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock){

    }

    public static void print_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock){

    }

    public static void buy_product (ArrayList<String> name_products, double[] prices, HashMap<String, Integer> stock, double total){

    }

    public static void stats_products (ArrayList<String> name_products, double[] prices){
        ArrayList<Integer> index_p = new ArrayList<>();

        for (int i= 0; i<prices.length; i++){
            index_p.add(i);
        }

        index_p.sort((i1,i2)-> Double.compare(prices[i1], prices[i2]));
         for (int num : index_p){

         }
    }

}
