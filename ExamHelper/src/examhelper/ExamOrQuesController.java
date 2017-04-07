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
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
 *
 * @author David
 */
public class ExamOrQuesController implements Initializable,Controller {

    ScreensController myController;
    
    @FXML
    private void createExam(ActionEvent event) {
        //go to create exam screen
    }
    
    @FXML
    private void inputQuestion(ActionEvent event){
        myController.setScreen(ScreensFramework.screen2ID);
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
