package Controller;

import Model.Model;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void insert (String userName, String password, String birthday, String firstName, String lastName, String address) {
        model.insert(userName,password,birthday,firstName,lastName,address);
    }

    public String read (String userName){
        return model.read(userName);
    }

    public void delete (String userName){

    }

    public void update(Observable o, Object arg) {

    }

    public void alert (){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("אופס..");
        alert.setContentText("הכנס שם משתמש");
        alert.showAndWait();
        alert.close();
    }



}
