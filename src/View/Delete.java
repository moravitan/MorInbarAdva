package View;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class Delete implements Observer {

    private Controller controller;
    private Stage stage;
    public javafx.scene.control.TextField txtfld_userinput;


    /**
     *
     * @param controller
     * @param stage
     */

    void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
    }

    /**
     * This delete the user name that equal to the user input
     */
    public void confirm(){
        String userName = String.valueOf(txtfld_userinput.getText());
        // checks if the user enter an input
        if (txtfld_userinput.getText() == null || txtfld_userinput.getText().trim().isEmpty()){
            controller.alert();
        }
        else {
            String data = controller.read(userName,false);
            if (data != null) {
                controller.delete(userName);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("המחיקה התבצעה בהצלחה");
                alert.showAndWait();
                alert.close();
                stage.close();
            }
        }
    }

    public void exit(){
        stage.close();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
