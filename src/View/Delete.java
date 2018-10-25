package View;

import Controller.Controller;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class Delete implements Observer {

    private Controller controller;
    private Stage stage;


    void setController(Controller controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
