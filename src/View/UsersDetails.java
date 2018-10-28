package View;

import Controller.Controller;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class UsersDetails extends View implements Observer {


    private Controller controller;
    private Stage stage;
    private String userDetails;
    private String [] userDetailsSplited;

    public javafx.scene.control.Label userName;
    public javafx.scene.control.Label firstName;
    public javafx.scene.control.Label lastName;
    public javafx.scene.control.ComboBox combo_box_day;
    public javafx.scene.control.ComboBox combo_box_month;
    public javafx.scene.control.ComboBox combo_box_year;
    public javafx.scene.control.Label address;



    public void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
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
        userName.setText(userDetailsSplited[0]);
        firstName.setText(userDetailsSplited[2]);
        lastName.setText(userDetailsSplited[3]);
        String [] date = userDetailsSplited[4].split("/");
        combo_box_day.setValue(date[0]);
        combo_box_month.setValue(date[1]);
        combo_box_year.setValue(date[2]);
        address.setText(userDetailsSplited[5]);


    }

    public void close(){
        stage.close();
    }


}
