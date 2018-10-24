package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class Controller extends Observable implements Observer {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void create (String userName, String password, String birthday, String firstName, String lastName, String address) {
        model.Create(userName,password,birthday,firstName,lastName,address);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
