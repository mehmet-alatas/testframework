package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public  static void createConnection(){
        // connection = DriverManager.getConnection("jdbc:postgresql://64.227.123.49:5432/prettierhomes", "tech_pro_edu", "testingIsFun");
                    //jdbc:postgresql://64.227.123.49:5432/prettierhomes
        String url = "jdbc:postgresql://64.227.123.49:5432/prettierhomes";
        String user = "techprotester";
        String password = "myPassword";

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }


    /**
     * This method returns the list of table names in the "public" schema.
     */


    //  Veritabanındaki tablo isimlerini döner.

    /**
     * This method returns the list of table names in the "public" schema.
     */


    public static void executeQuery(String query) {
        createConnection();

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }


    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return columns;
    }


    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }
    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }
    public static ResultSet executeQueryResultSet(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    //mehmet hoca---------------

    public static Connection connectToDatabase(){

        String url = "jdbc:postgresql://64.227.123.49:5432/prettierhomes";
        String user = "tech_pro_edu";
        String password = "testingIsFun";

        try {
            connection = DriverManager.getConnection(url,user,password);//database'e baglandık
            //tekrar Connection yazmaya gerek yok cunku yukarda degişkenimizin varlıgından bahsettık
        } catch (SQLException e) {//olası exception ıcın try-catch ıle sarmaladık
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Statement createStatement(){


        try {
            statement = connectToDatabase().createStatement();//method uzerınden statement olusturduk
            //            //tekrar Statement yazmaya gerek yok cunku yukarda degişkenimizin varlıgından bahsettık

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public static ResultSet executeQueryM(String sql){

        ResultSet resultSet ;//degıskenımızı scope ıcınde kalsın dıye yazdık

        try {
            resultSet = createStatement().executeQuery(sql);//createStatement()methodu uzerınden executeQueryi calıstırdık
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    //  Veritabanındaki tablo isimlerini döner.

    /**
     * This method returns the list of table names in the "public" schema.
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, "public", "%", new String[] { "TABLE" });
            while (tables.next()) {
                tableNames.add(tables.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }




    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}



