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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author liambarry
 */
public class CreateExamController implements Initializable,Controller {
    
    dbHelper db;
    ScreensController myController;
    boolean possible;
    Logger logger = Logger.getLogger(ScreensFramework.class);
    
    
    @FXML
    public ChoiceBox<String> cb;
    public TextField quesNums;
    
    private void populateCombo(ArrayList<String> subs){
        cb.getItems().addAll(subs);
        System.out.println("should have");
        logger.info("should have added subjects to combo box");
    }
    
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new dbHelper();
        db.connectToDB();
        db.createPopTables();
        ArrayList<String> subs = db.getSubjects();
        populateCombo(subs);
        System.out.println("here2"); 
    }
    
    
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
    
    
    
    /***
     * generates an exam.txt
     * checks to make sure number of questions requested possible for given sub
     * @param e
     * @return true if valid, false if not 
     */
    @FXML
    private void generateExam(ActionEvent e){
        String subject = "";
        int numQues = 0;
        try{
            subject = cb.getValue().toString();
            numQues = Integer.parseInt(quesNums.getText());
        }
        catch(Exception ex){
            //didn't enter a number at all
            System.out.println("please enter a valid integer number and choose a subject");
        }
        
        if(numQues <= 0){
            System.out.println("not a valid number");
        }
        else{
            System.out.println("sub: "+subject+"\nques#: "+numQues);
            possible = db.valid(numQues, subject);
            
            if(possible){
                //get list of questions with database function here
                ArrayList<String> questions = db.getQuestions(numQues,subject);
                System.out.println(questions.toString());
            }
        }
    }
    
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    } 
}
    

