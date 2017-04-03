/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FileInput extends Application{
    private Desktop desktop = Desktop.getDesktop();
    
    public void start (Stage primaryStage){
        AnchorPane anchorp = new AnchorPane();
        final FileChooser fileChooser = new FileChooser();
        
        Button btn = new Button();
        btn.setText("Enter File into the Database");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Data Entered!");
            }
        });
        Label label1 = new Label("Below, Click the Find File button to browse through and \nselect the file you would like to enter into the database.");
       AnchorPane.setTopAnchor(label1, 40.0);
       AnchorPane.setLeftAnchor(label1, 50.0);
       AnchorPane.setRightAnchor(label1, 70.0);
 
       
       Button openFile = new Button("Find File");
       
       openFile.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        openFile(file);
                    }
                }
            });
       AnchorPane.setTopAnchor(openFile, 110.0);
       AnchorPane.setLeftAnchor(openFile, 50.0);
       AnchorPane.setRightAnchor(openFile, 70.0);
 
       //button to submit the question
       AnchorPane.setTopAnchor(btn, 160.0);
       AnchorPane.setLeftAnchor(btn, 40.0);
       AnchorPane.setRightAnchor(btn, 50.0);
 
       // Add buttons to AnchorPane
       anchorp.getChildren().addAll(label1, openFile, btn);
//        
//       root.getChildren().add(btn);
        Scene scene = new Scene(anchorp, 550, 250);
        primaryStage.setTitle("File Input");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                FileChooser.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
    
}
