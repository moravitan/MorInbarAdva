package Database;

import java.io.File;
import java.sql.*;

public class DBConnect {

    private String databaseName;


    public DBConnect(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * This method create if doesn't exist a new database by the name which equal to the databaseName field.
     */
    public void connect() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
            connection.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void createTable(String tableName){
        String createStatement = "CREATE TABLE IF NOT EXISTS Users (\n"
                + "	user_name text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + " first_name text NOT NULL,\n"
                + " last_name text NOT NULL,\n"
                + "	birthday text,\n"
                + "address text NOT NULL\n"
                + ");";

        String url = "jdbc:sqlite:" + databaseName + ".db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(createStatement);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public void insertIntoTable(String tableName, String data){

        String [] values = data.split(",");
        String insertStatement = "INSERT INTO Users (user_name,password,first_name,last_name,birthday,address) VAlUES (?,?,?,?,?,?)";

        String url = "jdbc:sqlite:" + databaseName + ".db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertStatement)) {
            // set the corresponding parameters
            pstmt.setString(1,values[0]);
            pstmt.setString(2,values[1]);
            pstmt.setString(3,values[1]);
            pstmt.setString(4,values[3]);
            pstmt.setString(5,values[4]);
            pstmt.setString(6,values[5]);
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public String read (String tableName, String userName){

        String selectQuery = "SELECT * FROM users WHERE user_name = ?";

        String url = "jdbc:sqlite:" + databaseName + ".db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

            // set the value
            pstmt.setString(1,userName);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                String res = rs.getString("user_name") + "," +
                        rs.getString("password") + "," +
                                rs.getString("first_name") + "," +
                                rs.getString("last_name") + "," +
                                rs.getString("birthday") + "," +
                                rs.getString("address");
                System.out.println(res);
                return res;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public void updateDatabase(String tableName, String data) {
        String[] values = data.split(",");
        String updatetatement = "UPDATE Users SET password = ? ,"
                                + "first_name = ? ,"
                                + "last_name = ? ,"
                                + "birthday = ? ,"
                                + "address = ?"
                                + "WHERE user_name = ?";

        String url = "jdbc:sqlite:" + databaseName + ".db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(updatetatement)) {

            // set the corresponding param
            pstmt.setString(1, values[1]);
            pstmt.setString(2, values[2]);
            pstmt.setString(3, values[3]);
            pstmt.setString(4, values[4]);
            pstmt.setString(5, values[5]);
            pstmt.setString(6, values[0]);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        public void deleteFromTable (String tableName, String userName){
        String deleteStatement = "DELETE FROM Users WHERE user_name = ?";

        String url = "jdbc:sqlite:" + databaseName + ".db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(deleteStatement)) {
            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    /**
     *
     * @return the name of the database
     */
    public String getDatabaseName() {
        return databaseName;
    }
}

