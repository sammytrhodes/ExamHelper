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
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author David
 */
public class InvalidExamController implements Initializable, Controller {
    
    
    ScreensController myController;
    Logger logger = Logger.getLogger(ScreensFramework.class);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    } 
    
    
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen4ID);
    }

    
}
