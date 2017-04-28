/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    
    @FXML
    TextField fileName;

    
    @FXML
    private void back(ActionEvent e){
        myController.setScreen(ScreensFramework.screen2ID);
    }
    
    @FXML
    private void loadFile(ActionEvent e){
        Window win = myController.screens.get("TextInput").getScene().getWindow();
        File file = fc.showOpenDialog(win);
        if( file != null){
            //openFile(file);
            String name = file.getName();
            logger.info(name);
            
            fileName.setText(name);
        }
    }
    
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
    
    @Override
    public void setScreenParent(ScreensController screenPage){
        myController = screenPage;
    }    
    
}
