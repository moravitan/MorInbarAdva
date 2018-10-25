package Controller;

import Model.Model;
import javafx.scene.control.Alert;

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

    @Override
    public void update(Observable o, Object arg) {

    }

    public String read(String userName){
        return model.read(userName);
    }

    public void updateDB(String userDetails){
        ///complete this
    }

    public String read(String userName){
        return model.read(userName);
    }

    public void updateDB(String userDetails){
        ///complete this
    }

    public void delete (String userName){
        model.delete(userName);

    }

    public void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("אופס..");
        alert.setContentText("הכנס שם משתמש");
        alert.showAndWait();
        alert.close();
    }



}
