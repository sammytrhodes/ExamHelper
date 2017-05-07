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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;


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
    public TextArea txt = new TextArea();
    
    /***
     * populates the combo box with all the existing subjects in the database
     */
    private void populateCombo(){
        ArrayList<String> list = db.getSubjects();
        
        cb.getItems().addAll(list);
    }
    
    /**
     * returns to the previous screen
     * @param e clicking the back button
     */
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen2ID);
    }
    
    /**
     * adds the inputted question to the database 
     * @param e clicking the done button
     */
    @FXML
    private void enterToDB(ActionEvent e){
        // get rid of all the quotes in the question
        String ques = txt.getText();
        ques = ques.replaceAll("\'","");
        if(ques.indexOf('"') >= 0){
		ques = ques.replaceAll("\"|\"", "");
	}
        
        //get rid of all the quotes in a subject
        String sub = cb.getValue();
        sub = sub.replaceAll("\'","");
        if(sub.indexOf('"') >= 0){
		sub = sub.replaceAll("\"|\"", "");
	}
        
        Question q = new Question(sub, ques);
        db.addQuestion(q);
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new dbHelper();
        ArrayList<String> list = db.getSubjects();
        populateCombo();        
    }    
    
    /**
     * 
     * @param screenPage sets the controller to change the screens 
     */
    @Override
    public void setScreenParent(ScreensController screenPage){
        myController = screenPage;
    }    
}
