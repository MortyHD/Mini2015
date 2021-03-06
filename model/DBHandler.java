package model;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Morten Ricki Rasmussen
 */
public class DBHandler {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_USR = "mortyhdd_minipro";
    private static final String DATABASE_PWD = "miniprokya94cjw";
    private static final String DATABASE_URL = "jdbc:mysql://mortyhd.dk:3306";
    private static final String SCHEMA = "/mortyhdd_minipro";
    private static Connection conn;
    private static Statement stmt;

    //--------------------------------------------------------------------------
    // CreateCoon = Laver connection til DB
    //--------------------------------------------------------------------------
    public static void createConn() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DATABASE_URL + SCHEMA, DATABASE_USR, DATABASE_PWD);
        stmt = (Statement) conn.createStatement();

    }

    //--------------------------------------------------------------------------
    // close = Lukker Connection og statement
    //--------------------------------------------------------------------------
    public static void close() throws SQLException {
        stmt.close();
        conn.close();
    }

    //--------------------------------------------------------------------------
    // databaseRetrive henter data fra databasen og returner et ResultSet
    //--------------------------------------------------------------------------
    public static ResultSet databaseRetrive(String mySQLStatement) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        createConn();
        rs = stmt.executeQuery(mySQLStatement);
        return rs;
    }

    //--------------------------------------------------------------------------
    // getTShirts, getShirts og getPants laver oplysningerne fra databasen om
    // til de retmæssige objekter.
    //--------------------------------------------------------------------------
    public static TShirt getTShirts(ResultSet rs) throws SQLException {
        String productName = null;
        String color = null;
        double price = 0;
        boolean sizeS = false;
        boolean sizeM = false;
        boolean sizeL = false;
        boolean sizeXL = false;
        boolean sizeXXL = false;

        productName = rs.getString("product_name");
        color = rs.getString("color");
        price = rs.getFloat("price");
        sizeS = rs.getBoolean("sizeS");
        sizeM = rs.getBoolean("sizeM");
        sizeL = rs.getBoolean("sizeL");
        sizeXL = rs.getBoolean("sizeXL");
        sizeXXL = rs.getBoolean("sizeXXL");

        TShirt tShirt = new TShirt(productName, color, (float) price, sizeS, sizeM, sizeL, sizeXL, sizeXXL);

        return tShirt;
    }

    public static Shirt getShirts(ResultSet rs) throws SQLException {
        String productName = null;
        String color = null;
        double price = 0;
        boolean size38 = false;
        boolean size39 = false;
        boolean size40 = false;
        boolean size41 = false;
        boolean size42 = false;
        boolean size43 = false;
        boolean size44 = false;
        boolean size45 = false;

        productName = rs.getString("Product_name");
        color = rs.getString("color");
        price = rs.getFloat("price");
        size38 = rs.getBoolean("size38");
        size39 = rs.getBoolean("size39");
        size40 = rs.getBoolean("size40");
        size41 = rs.getBoolean("size41");
        size42 = rs.getBoolean("size42");
        size43 = rs.getBoolean("size43");
        size44 = rs.getBoolean("size44");
        size45 = rs.getBoolean("size45");

        Shirt shirt = new Shirt(productName, color, (float) price, size38, size39, size40, size41, size42, size43, size44, size45);

        return shirt;
    }

    public static Pants getPants(ResultSet rs) throws SQLException {
        String productName = null;
        String color = null;
        double price = 0;
        boolean size28 = false;
        boolean size29 = false;
        boolean size30 = false;
        boolean size31 = false;
        boolean size32 = false;
        boolean size33 = false;
        boolean size34 = false;
        boolean size36 = false;
        boolean size38 = false;

        productName = rs.getString("Product_name");
        color = rs.getString("color");
        price = rs.getFloat("price");
        size28 = rs.getBoolean("size28");
        size29 = rs.getBoolean("size29");
        size30 = rs.getBoolean("size30");
        size31 = rs.getBoolean("size31");
        size32 = rs.getBoolean("size32");
        size33 = rs.getBoolean("size33");
        size34 = rs.getBoolean("size34");
        size36 = rs.getBoolean("size36");
        size38 = rs.getBoolean("size38");

        Pants pants = new Pants(productName, color, (float) price, size28, size29, size30, size31, size32, size33, size34, size36, size38);

        return pants;
    }

    //--------------------------------------------------------------------------
    // LoadArrayLists ligger objekterne fra getTShirts osv. ind i arrayLists 
    // som programmet bruger
    //--------------------------------------------------------------------------
    public static void loadArrayLists(ArrayList tShirts, ArrayList pants, ArrayList shirts) throws ClassNotFoundException, SQLException {

        ResultSet rs = DBHandler.databaseRetrive("SELECT * FROM tshirts");
        while (rs.next()) {
            tShirts.add(DBHandler.getTShirts(rs));
        }
        rs = DBHandler.databaseRetrive("SELECT * FROM shirts");
        while (rs.next()) {
            shirts.add(DBHandler.getShirts(rs));
        }
        rs = DBHandler.databaseRetrive("SELECT * FROM pants");
        while (rs.next()) {
            pants.add(DBHandler.getPants(rs));
        }
        DBHandler.close();

    }

}
