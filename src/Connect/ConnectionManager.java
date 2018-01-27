package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionManager {
	//private static String url = "jdbc:mysql://192.168.1.109:3306/storedb";    
    private static String url = "jdbc:mysql://localhost:3306/storedb?useSSL=false";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
            	JOptionPane.showMessageDialog(null, ex.getMessage());
            	 JOptionPane.showMessageDialog(null, "Daabase not Connected,Close Software and Enter Again");	
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
        	JOptionPane.showMessageDialog(null, "Database Driver Not Found");
        }
        return con;
    }
}