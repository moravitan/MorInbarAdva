package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Insert implements Observer {

    private Controller controller;
    private Stage stage;

    //<editor-fold desc="Text Fields">
    public javafx.scene.control.TextField txtfld_userName;
    public javafx.scene.control.TextField txtfld_password;
    public javafx.scene.control.TextField txtfld_confirmPassword;
    public javafx.scene.control.TextField txtfld_firstName;
    public javafx.scene.control.TextField txtfld_lastName;
    public javafx.scene.control.TextField txtfld_Birthday;
    public javafx.scene.control.TextField txtfld_Address;
    public javafx.scene.control.DatePicker datepicker_date;
    //</editor-fold>


    void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    public void submit(ActionEvent actionEvent) {
        String userName = String.valueOf(txtfld_userName.getText());
        String password = String.valueOf(txtfld_password.getText());
        String confirmPassword = String.valueOf(txtfld_confirmPassword.getText());
        String firstName = String.valueOf(txtfld_firstName.getText());
        String lastName = String.valueOf(txtfld_lastName.getText());
        String birthday = String.valueOf(txtfld_Birthday.getText());
        String address = String.valueOf(txtfld_Address.getText());
        LocalDate d = datepicker_date.getValue();
        String date = datepicker_date.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Checking if the user name already exist in the data base
        if (controller.read(userName) != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("אופס..");
            alert.setContentText("שם המשתמש כבר קיים במערכת, אנא בחר שם משתמש חדש");
            alert.showAndWait();
            alert.close();
        }
        // Checking that both password text fields are equal
        else if (!password.equals(confirmPassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("אופס..");
            alert.setContentText("סיסמאות לא תואמות");
            alert.showAndWait();
            alert.close();
        }
        // Checking if all the text fields are not empty
        else if (!validation()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("אופס..");
            alert.setContentText("אחד או יותר מהשדות לא מלאים");
            alert.showAndWait();
            alert.close();
        }
        else{
            controller.insert(userName,password,firstName,lastName,date,address);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setHeaderText("");
            alert.setContentText("התחברת בהצלחה");
            alert.showAndWait();
            alert.close();
            stage.close();
        }
    }

    /**
     * This method check if all the text fields are not empty
     * @return true if all the text fields are not empty, otherwise return false
     */

    private boolean validation() {
        if (txtfld_userName.getText() == null || txtfld_userName.getText().trim().isEmpty())
            return false;
        if (txtfld_password.getText() == null || txtfld_password.getText().trim().isEmpty())
            return false;
        if (txtfld_confirmPassword.getText() == null || txtfld_confirmPassword.getText().trim().isEmpty())
            return false;
        if (txtfld_firstName.getText() == null || txtfld_firstName.getText().trim().isEmpty())
            return false;
        if (txtfld_lastName.getText() == null || txtfld_lastName.getText().trim().isEmpty())
            return false;
        if (txtfld_Birthday.getText() == null || txtfld_Birthday.getText().trim().isEmpty())
            return false;
        if (txtfld_Address.getText() == null || txtfld_Address.getText().trim().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }
}
