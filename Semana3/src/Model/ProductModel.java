package Model;
import Database.CRUD;
import Database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Product;

public class ProductModel implements  CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Product objProduct = (Product) obj;



        try {
            String sql = "INSERT INTO products (name, price, stock, category) VALUES (?,?,?,?)";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setString(4, objProduct.getCategory());


            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objProduct.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "Product registered successfully");

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());

        }

        ConfigDB.closeConnection();

        return objProduct;



    }

    @Override
    public List<Object> findAll() {


        List<Object> listProducts = new ArrayList<>();


        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT * FROM products";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Product objProduct = new Product();
                objProduct.setId(objResult.getInt("id_product"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getInt("price"));
                objProduct.setStock(objResult.getInt("stock"));
                objProduct.setCategory(objResult.getString("category"));

                listProducts.add(objProduct);
            }

        }  catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listProducts;

    }

    @Override
    public Boolean update(Object obj) {
        Product objProduct = (Product) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isUpdated = false;

        try {

            String sql = "UPDATE products SET name = ?, price = ?, stock = ?, category = ? WHERE id_product = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setString(4, objProduct.getCategory());
            objPrepare.setInt(5, objProduct.getId());


            int result = objPrepare.executeUpdate();
            if (result > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Product updated successfully");
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;

    }

    @Override
    public Boolean delete(Object obj) {

        Product objProduct = (Product) obj;

        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM products WHERE id_coder = ?";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objProduct.getId());
            int result = objPrepare.executeUpdate();
            if (result > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Coder deleted successfully");
                return isDeleted;
            }
        } catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public Product findById(int id) {
        Product objProduct = new Product();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM products WHERE id_coder = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objProduct.setId(objResult.getInt("id_product"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getInt("price"));
                objProduct.setStock(objResult.getInt("stock"));
                objProduct.setCategory(objResult.getString("category"));

            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        if (objProduct.getId() == 0) {
            return null;
        } else {
            return objProduct;}
    }

    public List<String> findAllNames() {

        List<String> listName = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try{

            String sql = "SELECT name FROM products";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                String name = objResult.getString("name");


                listName.add(name);
            }

        }  catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listName;

    }

    public Product findByName(String nameSearch) {
        Product objProduct = new Product();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM products WHERE name = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, nameSearch);
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objProduct.setId(objResult.getInt("id_product"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getInt("price"));
                objProduct.setStock(objResult.getInt("stock"));
                objProduct.setCategory(objResult.getString("category"));

            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        if (objProduct.getId() == 0) {
            return null;
        } else {
            return objProduct;}
    }


}

