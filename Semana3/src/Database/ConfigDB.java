package Database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConfigDB {

    public static   Connection objConnection = null;

    public  static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/store";
            String user = "root";
            String password = "Qwe.123*";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
        } catch ( ClassNotFoundException error){
            System.out.println("driver no instalado"+ error.getMessage());
        } catch (SQLException error){
            System.out.println(" error al conectar a la base de datos"+ error.getMessage());
        }
        return  objConnection;
    }

    public static void closeConnection(){
        try {
            if (objConnection != null){
                if (!objConnection.isClosed()){
                    objConnection.close();
                }
            }
        } catch (SQLException error){
            System.out.println("error al cerrar la conexion"+ error.getMessage());
        }
    }
}
