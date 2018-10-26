package View;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class SignIn implements Observer{

    private Controller controller;
    private Stage stage;

    private String userDetails;

    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField password;

    public SignIn() {

    }

    void setController(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;

    }


    @Override
    public void update(Observable o, Object arg) {

    }

    public void signin(){

        String userName = username.getText();
        String Password = password.getText();

        //if one or more is empty
        if(userName == null || Password == null || username.getText().trim().isEmpty() || password.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("שגיאה");
            alert.setContentText("שדה אחד או יותר ריקים");
            alert.showAndWait();
            alert.close();
        }

        // read the user name from the data base
        // if doesn't exist showing alert message
        userDetails = controller.read(username.getText());

        //if the password is not correct
        if(!validPassword()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("שגיאה");
            alert.setContentText("סיסמה שגויה");
            alert.showAndWait();
            alert.close();
        }
    }

    private boolean validPassword(){

        //implement here get user details and split ;
        //then check if the passwods matchs
        

        return true;
    }

}

