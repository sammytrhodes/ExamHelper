/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author David
 */
public class QuesListController implements Initializable, Controller {

    dbHelper db;
    ScreensController myController;
    Logger logger = Logger.getLogger(ScreensFramework.class);
    
    @FXML
    public ListView<String> ques;
    public ListView<Integer> ids;
    public ListView<String> subs;

    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new dbHelper();
        populateListView(db.getAllQuestions());
        
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
     * populates the list views in the interface
     * @param map contains the question and associated id's
     */
    public void populateListView(Map<Integer,Question> map){
        ObservableList qs; //questions
        //arraylists to be converted into ObservableLists above
        ArrayList<String> q = new ArrayList<String>();//question

       
        for(Integer j : map.keySet()){
            Question question = map.get(j);
            q.add(j+"\t"+question.subject+"\t"+question.question);
        }
        
        //convert to observableList to set listView
        qs = FXCollections.observableArrayList(q);
        
        //set the listviews
        ques.setItems(qs);
        
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
