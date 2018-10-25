package Model;

import Database.DBConnect;
import javafx.scene.control.Alert;

import java.util.Observable;

public class Model extends Observable {

    private DBConnect usersDatebase = new DBConnect("Vacation4U");

    public void insert(String userName, String password, String firstName, String lastName, String birthday, String address) {
        String data = userName  + "," + password + "," + firstName + "," + lastName + "," + birthday + "," + address;
        usersDatebase.insertIntoTable("Users", data);
    }

    public String read(String userName) {
       if (usersDatebase.read("Users", userName) == null){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("אופס..");
           alert.setContentText("שם משתמש לא קיים במערכת");
           alert.showAndWait();
           alert.close();
       }
       else{
           return usersDatebase.read("Users", userName);
       }

       return null;

    }


    public void update() {

    }

    public void delete(String userName) {
        usersDatebase.deleteFromTable("Users", userName);
    }
}
