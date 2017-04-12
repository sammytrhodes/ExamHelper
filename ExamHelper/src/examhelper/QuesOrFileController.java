/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author David
 */
public class QuesOrFileController implements Initializable, Controller {

    
    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void questionInput(ActionEvent event){
        myController.setScreen(ScreensFramework.screen3ID);
    }
    
    @FXML
    private void questionFile(ActionEvent event){
        
    }
    
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    
    
}
