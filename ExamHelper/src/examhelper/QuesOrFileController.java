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
     * goes to the add question screen
     * @param event clicking the button
     */
    @FXML
    private void questionInput(ActionEvent event){
        myController.setScreen(ScreensFramework.screen3ID);
    }
    
    /**
     * goes to the input file screen
     * @param event clicking the button
     */
    @FXML
    private void questionFile(ActionEvent event){
        myController.setScreen(ScreensFramework.screen5ID);
    }
    
    /**
     * goes to the previous screen
     * @param e clicking the back button
     */
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
    /**
     * initializes the screen
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * 
     * @param screenPage sets the controller to change the screens
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    
    
}
