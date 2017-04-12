/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author liambarry
 */
public class CreateExamScreenController implements Initializable {
    
    dbHelper db;
    ScreensController myController;
    
    
    @FXML
    public ComboBox<String> cb;
    
    private void populateCombo(ArrayList<String> list){
    
        cb.getItems().addAll(list);
        System.out.println("should have added");
    
    }
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new dbHelper();
        db.connectToDB();
        db.createPopTables();
        ArrayList<String> list = db.getSubjects();
        populateCombo(list);

        System.out.println("here2"); 
    }
    
    
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
    
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    } 
}
    

