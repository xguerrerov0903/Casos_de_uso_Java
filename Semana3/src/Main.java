// importing necessary classes
import Controler.ProductControler;
import Database.ConfigDB;
import Entity.Product;
import Utils.Inputs;

import javax.swing.*;
import java.sql.Connection;


public class Main {

    public static void main(String[] args) {
        Connection connection = ConfigDB.openConnection();

        int selection = -1;

        do {
            // menu options for the button dialog
            String[] options = {"Add product", "List inventory", "Update price", "Update stock", "Delete product", "Find by name", "Exit"};
            selection = JOptionPane.showOptionDialog(
                    null,
                    "Menu options",
                    "",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            // switch case for the menu options
            switch (selection) {
                case 0:
                    Controler.ProductControler.create();
                    break;
                case 1:
                    Controler.ProductControler.getAll();
                    break;
                case 2:
                    Controler.ProductControler.updatePrice();
                    break;
                case 3:
                    Controler.ProductControler.updateStock();
                    break;
                case 4:
                    Controler.ProductControler.delete();
                    break;
                case 5:
                    Controler.ProductControler.findByName();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Thank you for using our services");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");

            }
        } while (selection != 6);
    }


}
