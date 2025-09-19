package Interface;

import Model.Invent;
import Model.Producto;

public interface IProducto_Invent
{
    Producto addProduct(Invent invet, String name, double price, int stock, int cat);
    String getProducts(Invent invet, java.util.ArrayList<Producto> products);
}
