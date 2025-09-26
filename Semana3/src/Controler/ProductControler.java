package Controler;

import Entity.Product;
import Model.ProductModel;
import Utils.Inputs;

import javax.swing.*;
import java.util.Optional;

public class ProductControler {


    public static void create() {
        ProductModel productModel = new ProductModel();
        String name = "";
        do {
            // ask the user for the product name
            name = Inputs.requestString("Product name: ");


            if (productModel.findByName(name) != null) {
                JOptionPane.showMessageDialog(null, "Already exist product",
                        "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                continue;
            }
            // if the user press cancel return to the main menu
            if (name == null) {
                return;
            }
            break;
        } while (true);
        // ask the user for the product price and stock in case of cancel return to the main menu
        Optional<Double> price_d = Inputs.requestDouble("Product price: ");
        if (price_d.isEmpty()) {
            return;
        }
        // get the value of the price in case of empty (cancel) return to the main menu
        double price = price_d.get();
        Integer stock = Inputs.requestInteger("Product stock: ");
        if (stock == null) {
            return;
        }
        // ask the user for the product category using a button dialog
        String[] options = {"Food", "Appliance"};
        Integer cat = JOptionPane.showOptionDialog(
                null,
                "What type of product is it?",
                "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        // in case of cancel return to the main menu
        if (cat == -1) {
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
            Product objProduct = new Product();
            objProduct.setName(name);
            objProduct.setPrice(price);
            objProduct.setStock(stock);
            String category = options[cat];
            objProduct.setCategory(category);
            productModel.insert(objProduct);
        } else {
            JOptionPane.showMessageDialog(null, "Operation cancelled");
        }
    }

    public static void getAll() {
        ProductModel productModel = new ProductModel();
        String listProduct = "List products\n";
        for (Object i : productModel.findAll()) {
            Product objProduct = (Product) i;
            listProduct += objProduct.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listProduct);
    }

    public static void delete() {
        ProductModel productModel = new ProductModel();

        Integer idDelete = Inputs.requestInteger("Give me the id to delete");
        if (idDelete == null) {
            return;
        }
        Product objProduct = productModel.findById(idDelete);
        if (objProduct == null) {
            JOptionPane.showMessageDialog(null, "Product not found");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + objProduct.toString());
            if (confirm == 0) productModel.delete(objProduct);

        }
    }

    public static void updatePrice() {
        ProductModel productModel = new ProductModel();

        Integer idDelete = Inputs.requestInteger("Give me the id to delete");
        if (idDelete == null) {
            return;
        }
        Product objProduct = productModel.findById(idDelete);
        if (objProduct == null) {
            JOptionPane.showMessageDialog(null, "Product not found");
        } else {
            double price = objProduct.getPrice();

            while (true) {
                String priceAlt = JOptionPane.showInputDialog(objProduct.toString() + "\nGive the new price", objProduct.getPrice());

                if (priceAlt == null)
                    return;

                try {
                    if (Double.parseDouble(priceAlt) < 0) {
                        throw new NumberFormatException();
                    }
                    price = Double.parseDouble(priceAlt);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "The input is not a valid number. Please try again.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                }
                objProduct.setPrice(price);
                productModel.update(objProduct);
            }
        }
    }

    public static void updateStock() {
        ProductModel productModel = new ProductModel();

        Integer idDelete = Inputs.requestInteger("Give me the id to delete");
        if (idDelete == null) {
            return;
        }
        Product objProduct = productModel.findById(idDelete);
        if (objProduct == null) {
            JOptionPane.showMessageDialog(null, "Product not found");
        } else {
            int stock = objProduct.getStock();

            while (true) {
                String stockAlt = JOptionPane.showInputDialog(objProduct.toString() + "\nGive the new stock", objProduct.getStock());

                if (stockAlt == null)
                    return;

                try {
                    if (Integer.parseInt(stockAlt) < 0) {
                        throw new NumberFormatException();
                    }
                    stock = Integer.parseInt(stockAlt);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "The input is not a valid number. Please try again.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                }

                objProduct.setPrice(stock);
                productModel.update(objProduct);

            }

        }
    }

    public static void findByName() {
        ProductModel productModel = new ProductModel();
        do {
            String name = Inputs.requestString("Give me the name to search");
            if (name == null) {
                return;
            }
            Product objProduct = productModel.findByName(name);
            if (objProduct == null) {
                JOptionPane.showMessageDialog(null, "Product not found");
            } else {
                JOptionPane.showMessageDialog(null, objProduct.toString());
            }
        } while (true);

    }
}
