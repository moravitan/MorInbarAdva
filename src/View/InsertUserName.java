package View;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class InsertUserName implements Observer {

    private Controller controller;
    private Stage stage;
    private Update updateWindow;
    private String userDetails = "";

    public javafx.scene.control.TextField userName;



    void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
    }

    /**
     * This method search the row in the database where primary key user_name is equal to the user input
     * @param actionEvent
     */
    public void search(ActionEvent actionEvent){

        String username = String.valueOf(userName.getText());

        if (userName.getText() == null || userName.getText().trim().isEmpty()) {
            controller.alert();
        }

        // read the user name from the data base
        // if doesn't exist showing alert message
        userDetails = controller.read(username);

        if (userDetails != null){
            FXMLLoader fxmlLoader = new
                    FXMLLoader(getClass().getResource("Update.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load(getClass().getResource("Update.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            //set what you want on your scene
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("עדכון פרטים אישיים");
            Scene scene = new Scene(root1, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            SetStageCloseEvent(stage);
            stage.show();
            updateWindow = fxmlLoader.getController();
            updateWindow.setController(controller, stage);
            controller.addObserver(updateWindow);
            updateWindow.setUserDetails(userDetails);
        }
    }

    /**
     *
     * @return the userDetails field
     */
    public String getUserDetails(){
        return userDetails;

    }

    /**
     * This method close the window according to user request
     * @param primaryStage
     */
    private void SetStageCloseEvent(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                ((Button)alert.getDialogPane().lookupButton(ButtonType.OK)).setText("כן");
                ((Button)alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("חזור");
                alert.setContentText("האם אתה בטוח שברצונך לעזוב?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    // Close program
                } else {
                    // ... user chose CANCEL or closed the dialog
                    windowEvent.consume();
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
