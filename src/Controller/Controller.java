package Controller;

import Model.Model;
import javafx.scene.control.Alert;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {

    private Model model;

    /**
     * Constructor for the class Controller
     * @param model
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     *
     * @param userName
     * @param password
     * @param birthday
     * @param firstName
     * @param lastName
     * @param address
     * This method insert a new row to the data base with the given parameters
     */
    public void insert (String userName, String password, String birthday, String firstName, String lastName, String address) {
        model.insert(userName,password,birthday,firstName,lastName,address);
    }

    /**
     * This method search and return the row in the database which is equal to the given userName
     * @param userName
     * @return the row
     */
    public String read(String userName){
        return model.read(userName);
    }

    /**
     * This method update the data base with the new userDetails (user input)
     * @param userDetails
     */
    public void updateDB(String userDetails){
        model.update(userDetails);
    }

    /**
     * This method delete a row from the data base where the primary key is equal to @param userName
     * @param userName
     */
    public void delete (String userName){
        model.delete(userName);

    }

    /**
     * This method create an Alert object.
     * This method invoked when the user didn't insert an input
     */
    public void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("אופס..");
        alert.setContentText("הכנס שם משתמש");
        alert.showAndWait();
        alert.close();
    }


    @Override
    public void update(Observable o, Object arg) {

    }

}
