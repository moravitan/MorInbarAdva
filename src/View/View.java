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

public class View  implements Observer {

    private Controller controller;
    private Insert insertWindow;
    private Update updateWindow;
    private Read readWindow;
    private Stage primaryStage;
    public javafx.scene.control.Button btn_create;
    public javafx.scene.control.Button btn_read;
    public javafx.scene.control.Button btn_update;
    public javafx.scene.control.Button btn_delete;

    public void setController(Controller controller, Stage primaryStage){
        this.controller = controller;
        this.primaryStage = primaryStage;
    }

    @Override
    public void update(Observable o, Object arg) {

    }


    public void create(ActionEvent actionEvent) {
        //btn_create.setDisable(true);
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("insert.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load(getClass().getResource("insert.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        //set what you want on your scene
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Welcome!");
        Scene scene = new Scene(root1, 950, 700);
        stage.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("Welcome.css").toExternalForm());
        //stage.setScene(scene);
        stage.setResizable(false);
        SetStageCloseEvent(stage);
        stage.show();
        insertWindow = fxmlLoader.getController();
        //view.setResizeEvent(scene);
        insertWindow.setController(controller, stage);
        controller.addObserver(insertWindow);
    }

    public void read(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("read.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load(getClass().getResource("read.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        //set what you want on your scene
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Welcome!");
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("Welcome.css").toExternalForm());
        //stage.setScene(scene);
        stage.setResizable(false);
        SetStageCloseEvent(stage);
        stage.show();
        readWindow = fxmlLoader.getController();
        //view.setResizeEvent(scene);
        readWindow.setController(controller, stage);
       // controller.addObserver(readWindow);

    }

    public void update(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("update.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load(getClass().getResource("update.fxml").openStream());
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
        updateWindow = fxmlLoader.getController();
        //view.setResizeEvent(scene);
        updateWindow.setController(controller, stage);
        controller.addObserver(updateWindow);
    }

    public void delete(ActionEvent actionEvent) {

    }

    /**
     *
     * @param actionEvent
     */
    public void exit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("exit");
        alert.setContentText("Are you sure you want to exit?");
        ((Button)alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button)alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            // Close program
            primaryStage.close();
        }
        alert.close();

    }


    private void SetStageCloseEvent(Stage primaryStage) {
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
