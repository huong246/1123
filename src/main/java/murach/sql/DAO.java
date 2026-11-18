package murach.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    protected static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/murach";
    private static final String USER = "root";
    private static final String PASS = "Orenda123";
    public DAO() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}