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
    public void insert(String userName, String password, String confirmPassword,  String firstName, String lastName, String birthday, String address) {
        String data = userName  + "," + password + "," + firstName + "," + lastName + "," + birthday + "," + address;

        // Checking if the user name already exist in the data base
        if (read(userName, true) != null){
            alert("שם המשתמש שהזנת כבר קיים", Alert.AlertType.ERROR);
        }

        // Checking that both password text fields are equal
        else if (!password.equals(confirmPassword)){
            alert("הסיסמאות אינן תואמות", Alert.AlertType.ERROR);
        }
        else{
            usersDatabase.insertIntoTable("Users", data);
            alert("התחברת בהצלחה", Alert.AlertType.INFORMATION);

        }

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
            alert("שם משתמש לא קיים במערכת", Alert.AlertType.ERROR);
        }
        return null;
    }

    /**
     * This method update the database with the given @param data
     * @param userName
     * @param password
     * @param confirmPassword
     * @param firstName
     * @param lastName
     * @param birthday
     * @param address
     */

    public void update(String userName, String password, String confirmPassword,  String firstName, String lastName, String birthday, String address) {
        String data = userName  + "," + password + "," + firstName + "," + lastName + "," + birthday + "," + address;
        // Checking that both password text fields are equal
        if(!password.equals(confirmPassword)){
            alert("הסיסמאות אינן תואמות", Alert.AlertType.ERROR);
        }
        else{
            usersDatabase.updateDatabase("Users", data);
            alert("פרטי החשבון עודכנו בהצלחה", Alert.AlertType.INFORMATION);
        }

    }

    /**
     * This method delete a row from the database where user name is equal to @param userName
     * @param userName
     */
    public void delete(String userName) {
        usersDatabase.deleteFromTable("Users", userName);
        alert("החשבון נמחק בהצלחה", Alert.AlertType.INFORMATION);
    }

    public void signIn(String userName, String password) {
        String details = read(userName,false);
        boolean isLegal = true;
        if (details!=null){
            String UserDetails = usersDatabase.read("Users", userName);
            String [] detailsArr = UserDetails.split(",");
            if (!password.equals(detailsArr[1])) {
                alert("הסיסמאות אינן תואמות", Alert.AlertType.ERROR);
                isLegal = false;
                setChanged();
                notifyObservers(isLegal);
            }
            else{
                setChanged();
                notifyObservers(isLegal);

            }
        }

    }

    private void alert(String messageText, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setContentText(messageText);
        alert.showAndWait();
        alert.close();
    }

}
