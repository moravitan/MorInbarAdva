package View;

import Controller.Controller;
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

public class Read extends View implements Observer{

    private Controller controller;
    private Stage stage;
    private String userDetails;
    private UsersDetails usersDetails;

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

    /**
     * This method search a row in the data base where the primary key is equal to the user input
     * if a row is founded, a new window with the user details is shows up
     */
    public void confirm (){
        String userName = String.valueOf(txtfld_userinput.getText());

        if (txtfld_userinput.getText() == null || txtfld_userinput.getText().trim().isEmpty()) {
            controller.alert();
        }

        String data = controller.read(userName);

        if (data != null) {
            data = controller.read(userName);
            String[] values = data.split(",");

            FXMLLoader fxmlLoader = new
                    FXMLLoader(getClass().getResource("tableView.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load(getClass().getResource("tableView.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            //set what you want on your scene
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Welcome!");
            Scene scene = new Scene(root, 500, 300);
            stage.setScene(scene);
            //scene.getStylesheets().add(getClass().getResource("Welcome.css").toExternalForm());
            //stage.setScene(scene);
            stage.setResizable(false);
            SetStageCloseEvent(stage);
            stage.show();
            usersDetails = fxmlLoader.getController();
            usersDetails.setController(controller, stage);
            controller.addObserver(usersDetails);
            usersDetails.setUserDetails(userDetails);
        }
    }

    public void exit(){
        stage.close();
    }

    /**
     * This method close the window according to user request
     * @param primaryStage
     */
    protected void SetStageCloseEvent(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                ((Button)alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
                ((Button)alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
                alert.setContentText("Are you sure you want to exit?");
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
}
