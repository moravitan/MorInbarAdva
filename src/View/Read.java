package View;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Read {

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

    public void confirm (){
        String userName = String.valueOf(txtfld_userinput.getText());

        if (txtfld_userinput.getText() == null || txtfld_userinput.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("אופס..");
            alert.setContentText("הכנס שם משתמש");
            alert.showAndWait();
            alert.close();
        }
        else if (controller.read(userName) == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("אופס..");
            alert.setContentText("שם משתמש לא קיים במערכת");
            alert.showAndWait();
            alert.close();
        }
        else{
            String data = controller.read(userName);
            String [] values = data.split(",");

        }
    }

    public void exit(){
        stage.close();
    }


}
