package Controller;

import Model.Model;

import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void create (String userName, String password, String birthday, String firstName, String lastName, String address) {
        model.insert(userName,password,birthday,firstName,lastName,address);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
