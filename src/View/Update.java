package View;

import Controller.Controller;
import javafx.scene.control.ComboBox;
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
    public javafx.scene.control.ComboBox day;
    public javafx.scene.control.ComboBox month;
    public javafx.scene.control.ComboBox year;

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


    /**
     * This method set the userDetails field
     * @param userdetails
     */
    public void setUserDetails(String userdetails) {
        userDetails = userdetails;
        splitToFields();
    }


    /**
     * This method split the data from the database into fields and initials the Text Fields in the
     * update window
     */
    private void splitToFields(){
        userDetailsSplited = userDetails.split(",");
        firstName.setText(userDetailsSplited[2]);
        lastName.setText(userDetailsSplited[3]);
        password.setText(userDetailsSplited[1]);
        passwordReplay.setText(userDetailsSplited[1]);
        address.setText(userDetailsSplited[5]);
        String [] date = userDetailsSplited[4].split("/");
        day.setPromptText(date[0]);
        month.setPromptText(date[1]);
        year.setPromptText(date[2]);
    }

    public void confirm (){

        String newPassword = String.valueOf(password.getText());
        String newFirstName = String.valueOf(firstName.getText());
        String newLastName = String.valueOf(lastName.getText());
        String newBirthday = getBirthday();
        String newAddress = String.valueOf(address.getText());
        String data = userDetailsSplited[0] + "," + newPassword + "," + newFirstName + "," + newLastName + "," + newBirthday + "," + newAddress;
        controller.updateDB(data);

    }

    private String getBirthday(){
        String newDay = (String) day.getValue();
        String newMonth = (String) month.getValue();
        String newYear = (String) year.getValue();
        return newDay  + "/" + newMonth + "/" + newYear;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
