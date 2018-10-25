package View;

import Controller.Controller;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class Update implements Observer {

    private Controller controller;
    private Stage stage;

    private String userDetails;

    private String [] userDetailsSplited;

    public javafx.scene.control.TextField firstName;
    public javafx.scene.control.TextField lastName;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField passwordReplay;
    public javafx.scene.control.TextField address;



    /**
     *
     * @param controller
     * @param stage
     */
    void setController(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.userDetails = "";
    }


    @Override
    public void update(Observable o, Object arg) {

    }


    public void setUserDetails(String userdetails) {
        userDetails = userdetails;
        splitToFields();
    }



    private void splitToFields(){
        userDetailsSplited = userDetails.split(",");
        firstName.setText(userDetailsSplited[2]);
        lastName.setText(userDetailsSplited[3]);
        password.setText(userDetailsSplited[1]);
        passwordReplay.setText(userDetailsSplited[1]);
        address.setText(userDetailsSplited[5]);
    }

    public void setNewDetails(){
        //need to change the date of birth to the new one!!!!!!1
        String details = userDetailsSplited[0]+password+firstName+lastName+ userDetailsSplited[4]+address;

    }


}
