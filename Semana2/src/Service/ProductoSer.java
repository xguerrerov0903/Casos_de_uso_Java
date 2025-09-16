package Service;

import Model.Invent;
import Model.Producto;
import Model.Food;
import Model.Electrical;

import java.util.ArrayList;


public class ProductoSer
{

    public static Producto addProduct(Invent invet, String name, double price, int stock, int cat)
    {
        if ( cat == 0){
            Producto producto = new Producto(name, price);
        } else if ( cat == 1){
            Food producto = new Food(name, price);

        } else if ( cat == 2){
            Electrical producto = new Electrical(name, price);

        }
        Producto producto = new Producto(name, price);
        invet.getName_products().add(name);
        double[] upd_prices = new double[invet.getPrices().length + 1];
        for (int i = 0; i < invet.getPrices().length; i++) {
            upd_prices[i] = invet.getPrices()[i];
        }
        upd_prices[upd_prices.length - 1] = price;
        invet.setPrices(upd_prices);
        invet.getStocks().put(name, stock);
        return producto;
    }

    public static String getProducts(Invent invet, ArrayList<Producto> products)
    {
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
