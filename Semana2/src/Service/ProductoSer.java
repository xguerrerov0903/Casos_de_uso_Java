package Service;

// import the models
import Interface.IProducto_Invent;
import Model.Invent;
import Model.Producto;
import Model.Food;
import Model.Electrical;

import java.util.ArrayList;


public class ProductoSer
{

    // Method to add a product to the inventory

    public static Producto addProduct(Invent invet, String name, double price, int stock, int cat)
    {
        Producto producto;
        // add the product to the inventory based on the category
        if ( cat == 0){
            System.out.println("food");
            producto = new Food(name, price);
        } else if ( cat == 1){
            System.out.println("ELE");
            producto = new Electrical(name, price);
        } else {
            throw new IllegalArgumentException("Invalid category selected");
        }
        // add the product to the inventory
        invet.getName_products().add(name);
        // update the prices array
        double[] upd_prices = new double[invet.getPrices().length + 1];
        for (int i = 0; i < invet.getPrices().length; i++) {
            upd_prices[i] = invet.getPrices()[i];
        }
        upd_prices[upd_prices.length - 1] = price;
        // recreate the prices array
        invet.setPrices(upd_prices);
        // add the stock to the inventory
        invet.getStocks().put(name, stock);
        return producto;
    }


    public static String getProducts( Invent invet, ArrayList<Producto> products)
    {
        // return a string with the products in the inventory

        StringBuilder out_products = new StringBuilder();
        for (Producto product: products ) {
            String name = product.getName();
            out_products.append("Name: ").append(name)
                    .append(", Price: ").append(invet.getPrices()[invet.getName_products().indexOf(name)])
                    .append(", Stock: ").append(invet.getStocks().get(name))
                    .append("\n")
                    .append(product.getDescription()).append("\n");
        }
        return out_products.toString();
    }

}