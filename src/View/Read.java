package View;

import Controller.Controller;
import javafx.stage.Stage;

public class Read {

    private Controller controller;
    private Stage stage;

    /**
     *
     * @param controller
     * @param stage
     */
    void setController(Controller controller, Stage stage){
        this.controller = controller;
        this.stage = stage;
    }
}
