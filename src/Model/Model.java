package Model;

import Database.DBConnect;

import java.util.Observable;

public class Model extends Observable {

    private DBConnect usersDatebase;

    public Model() {
        this.usersDatebase = new DBConnect("Vacation4U");
        usersDatebase.connect();
        usersDatebase.createTable("Users");
    }

    public void insert(String userName, String password, String firstName, String lastName, String birthday, String address) {
        String data = userName  + "," + password + "," + firstName + "," + lastName + "," + birthday + "," + address;
        usersDatebase.insertIntoTable("Users", data);

    }

    public String read(String userName) {
       return usersDatebase.read("Users", userName);
    }


    public void update() {

    }

    public void delete(String userName) {
        usersDatebase.deleteFromTable("Users", userName);
    }
}
