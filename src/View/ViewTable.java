package View;

import Controller.Controller;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class ViewTable implements Observer {


    private Controller controller;
    private Stage stage;

    public javafx.scene.control.TableColumn r;


    void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
    }


    @Override
    public void update(Observable o, Object arg) {
        r.setId("b");
    }
}
