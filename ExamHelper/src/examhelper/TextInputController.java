/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author David
 */
public class TextInputController implements Initializable, Controller {
    
    dbHelper db;
    ScreensController myController;
    FileChooser fc;
    Logger logger = Logger.getLogger(ScreensFramework.class);
    //this is populated when reading in a text file
    ArrayList<Question> questions = new ArrayList<Question>();

    
    
    @FXML
    TextField fileName;

    
    /***
     * takes you to previous screen
     * @param e clicking the back button
     */
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen2ID);
    }
    
    /***
     * adds the question to the database
     * @param e clicking the button
     */
    @FXML
    private void addQuestions(ActionEvent e){
        if(questions.size() == 0){
            logger.error("did not read in any questions");
        }
        else{
            for(Question q : questions){
                db.addQuestion(q);
            }
            fileName.setText(questions.size()+" questions were added successfully");
            
        }
    }
    
    /***
     * 
     * will load the file and display the name in the box so the user
     * knows it's loaded
     * @param e clicked the button
     * @throws FileNotFoundException
     */
    @FXML
    private void loadFile(ActionEvent e) throws FileNotFoundException{
        Window win = myController.screens.get("TextInput").getScene().getWindow();
        File file = fc.showOpenDialog(win);
        if( file != null){
            String name = file.getName();
            logger.info(name);
            
            fileName.setText(name);
            
            FileReader reader = new FileReader(file);
            BufferedReader bufreader = new BufferedReader(reader);
            try{
                String doc;
                String text = "";
                int numLines = 0;
                String subject = "";

                //create one string that is the whole file
                while((doc = bufreader.readLine()) != null){
                    //if you're looking at the first line get the subject
                    if(numLines == 1)
                        subject = doc;
                   
                    text += doc;
                    numLines++;
                }          
                String[] oldLines = text.split("~!");
                String[] lines = Arrays.copyOfRange(oldLines, 1, oldLines.length);
               
                for(String line: lines){
                    Question q = new Question(subject, line);
                    logger.info("added following question from file: \n"+q.toString());
                    questions.add(q);
                }
            } catch (Exception ex){
                
                logger.error(ex.getMessage());
            }
                
            
            
            
        }
    }
    
    /***
     * opens the file
     * @param file 
     */
    public void openFile(File file){
        try{
            java.awt.Desktop.getDesktop().open(file);
        } catch(IOException e){
            logger.error("Couldn't open file\n"+e.getMessage());
        }
            
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new dbHelper();
        ArrayList<String> list = db.getSubjects();
        fc = new FileChooser();
    }    
    
    /***
     * allows for screen change
     * @param screenPage sets the controller to change the screens
     */
    @Override
    public void setScreenParent(ScreensController screenPage){
        myController = screenPage;
    }    
    
}
