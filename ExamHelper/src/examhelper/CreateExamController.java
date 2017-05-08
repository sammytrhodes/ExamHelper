/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author liambarry and David
 */
public class CreateExamController implements Initializable,Controller {
    
    dbHelper db;
    ScreensController myController;
    boolean possible;
    Logger logger = Logger.getLogger(ScreensFramework.class);
    
    
    @FXML
    public ChoiceBox<String> cb;
    public TextField quesNums;

    
    
    
    /***
     * populates the combo box on the interface
     * @param subs list of subjects to choose from
     */
    private void populateCombo(ArrayList<String> subs){
        cb.getItems().addAll(subs);
        logger.info("should have added subjects to combo box");
    }
    
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new dbHelper();
        ArrayList<String> subs = db.getSubjects();
        populateCombo(subs);
    }
    
    
    /**
     * will change the screen 
     * @param e action event (aka clicking the button)
     */
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen1ID);
    }
    
    
    
    
    /***
     * generates an exam.txt
     * checks to make sure number of questions requested possible for given sub
     * @param e
     */
    @FXML
    private void generateExam(ActionEvent e){
        String subject = "";
        int numQues = 0;
        try{
            subject = cb.getValue().toString();
            numQues = Integer.parseInt(quesNums.getText().replace(" ",""));
        }
        catch(Exception ex){
            //didn't enter a number at all
            logger.error("please enter a valid integer number and choose a subject");
        }
        
        if(numQues <= 0){
            logger.error("not a valid number");
            //invalid user entry
            myController.setScreen(ScreensFramework.screen6ID);

        }
        else{
            logger.info("sub: "+subject+"\nques#: "+numQues);
            possible = db.valid(numQues, subject);
            
            if(possible){
                //get list of questions with database function here
                ArrayList<String> questions = db.getQuestions(numQues,subject);
                try{
                    
                    //get the date for the file name
                    //could include time to distinguish multiple exams created in a single day
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
                    LocalDate date = LocalDate.now();
                    String today = dtf.format(date);
                    
                    
                    //create the file
                    File file = new File(today+"-"+subject+"-exam.txt");
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter writer = new BufferedWriter(fw);
                    
               
                    //formatting
                    writer.write("Exam Topic: ");
                    writer.write(subject);
                    writer.write("\n\n");
                    writer.write("\nNAME____________________");
                    writer.write("DATE____________________");
                    writer.write("\n\n");
                    //write the questions
                    for(int i = 0;i < questions.size(); i++){
                        writer.write(String.valueOf(i+1));
                        writer.write(") ");
                        writer.write(questions.get(i));
                        writer.write("\n\n\n\n");
                    }    
                    writer.flush();
                    writer.close();
                    //should open the file in default editor
                    java.awt.Desktop.getDesktop().edit(file);
        
                }
                catch (Exception es) {
                    logger.error("Couldn't Create Exam");
                    logger.error(es.getMessage()); 
                }    
            }else{
                //not possible
                //number greater than number of questions for subject
                myController.setScreen(ScreensFramework.screen6ID);

            }
           
        }
    }
    
    /***
     * 
     * @param screenPage sets the controller to change the screens
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    } 
}
    

