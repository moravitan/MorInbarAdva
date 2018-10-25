package View;

import Model.Model;
import javafx.scene.input.DataFormat;

import javax.xml.crypto.Data;
import java.util.Date;

public class test {

    public static void main(String[] args) {

        Model m = new Model();
        m.update("n,1,1,1,1,1");
        m.read("n");

    }
}
