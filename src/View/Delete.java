package View;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class Delete extends View implements Observer {

    private Controller controller;
    private Stage stage;
    public javafx.scene.control.TextField txtfld_userinput;


    /**
     *
     * @param controller
     * @param stage
     */
    public void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
    }

    public void confirm(){
        String userName = String.valueOf(txtfld_userinput.getText());
        if (txtfld_userinput.getText() == null || txtfld_userinput.getText().trim().isEmpty()) {
            controller.alert("אנא בחר שם משתמש לחיפוש");
        }
        else {
            String data = controller.read(userName,false);
            if (data != null) {
                controller.delete(userName);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                //alert.setHeaderText("");
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
