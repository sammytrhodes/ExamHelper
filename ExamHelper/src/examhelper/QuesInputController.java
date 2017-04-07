/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author David
 */
public class QuesInputController implements Initializable, Controller {
    
    dbHelper db;
    ScreensController myController;
    
    
    @FXML
    public ComboBox<String> cb;
    
    
    private void populateCombo(){
        ArrayList<String> list = db.getSubjects();
        String arr[] = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }
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
        populateCombo();

        System.out.println("here2");
        
    }    
    
    @Override
    public void setScreenParent(ScreensController screenPage){
        myController = screenPage;
    }    
}
