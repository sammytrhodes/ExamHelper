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
 *
 * @author David
 */
public class ExamOrQuesController implements Initializable,Controller {

    ScreensController myController;
    
    /**
     * goes to the create exam screen
     * @param event clicking the button
     */
    @FXML
    private void createExam(ActionEvent event) {
        myController.setScreen(ScreensFramework.screen4ID);
    }
    
    /**
     * goes to the question or text file screen
     * @param event clicking the button
     */
    @FXML
    private void inputQuestion(ActionEvent event){
        myController.setScreen(ScreensFramework.screen2ID);
    }
    
    /**
     * goes to the listview of questions screen
     * @param e clicking the button
     */
    @FXML
    private void goToQuestions(ActionEvent e){
        myController.setScreen(ScreensFramework.screen7ID);
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
     * sets the controller to change the screens
     * @param screenPage 
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
}
