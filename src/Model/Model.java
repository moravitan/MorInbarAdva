package Model;

import Database.DBConnect;
import javafx.scene.control.Alert;

import java.util.Observable;

public class Model extends Observable {

    private DBConnect usersDatabase;

    /**
     * Constructor for class Model
     * The constructor create a new database with the name "Vacation4U"
     * and create a new table by the name "Users"
     */
    public Model() {
        this.usersDatabase = new DBConnect("Vacation4U");
        usersDatabase.connect();
        usersDatabase.createTable("Users");
    }

    /**
     * This method insert to the database a new row with the given parameters
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param birthday
     * @param address
     */
    public void insert(String userName, String password, String firstName, String lastName, String birthday, String address) {
        String data = userName  + "," + password + "," + firstName + "," + lastName + "," + birthday + "," + address;
        usersDatabase.insertIntoTable("Users", data);
    }

    /**
     * This method search and return the row in the database with the same user name as @param userName if exist
     * if doesn't exist - alert message shows up
     * @param userName
     * @return
     */
    public String read(String userName, Boolean isInsert) {
       if (usersDatabase.read("Users", userName) != null){
           return usersDatabase.read("Users", userName);
       }
       else if (!isInsert){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("אופס..");
           alert.setContentText("שם משתמש לא קיים במערכת");
           alert.showAndWait();
           alert.close();
       }
       return null;
    }

    /**
     * This method update the database with the given @param data
     * @param data
     */

    public void update(String data) {
        usersDatabase.updateDatabase("Users", data);
    }

    /**
     * This method delete a row from the database where user name is equal to @param userName
     * @param userName
     */
    public void delete(String userName) {
        usersDatabase.deleteFromTable("Users", userName);
    }
}
