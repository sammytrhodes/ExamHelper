/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author sammy
 */
public class Interface extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorp = new AnchorPane();
        Button btn = new Button();
        btn.setText("Submit information");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Successful!");
            }
        });
        Label label1 = new Label("Below, Enter in the Exam question and its respective subject.");
       AnchorPane.setTopAnchor(label1, 40.0);
       AnchorPane.setLeftAnchor(label1, 50.0);
       AnchorPane.setRightAnchor(label1, 70.0);
 
       Label label2 = new Label("Question:");
       AnchorPane.setTopAnchor(label2, 90.0);
       AnchorPane.setLeftAnchor(label2, 50.0);
       AnchorPane.setRightAnchor(label2, 70.0);
       
       TextField textbox = new TextField("Enter in a question");
       // (B2) Anchor to the Top + Left + Right
       AnchorPane.setTopAnchor(textbox, 120.0);
       AnchorPane.setLeftAnchor(textbox, 50.0);
       AnchorPane.setRightAnchor(textbox, 70.0);
 
       Label label3 = new Label("Subject:");
       AnchorPane.setTopAnchor(label3, 170.0);
       AnchorPane.setLeftAnchor(label3, 50.0);
       AnchorPane.setRightAnchor(label3, 70.0);
       
       TextField textbox2 = new TextField("Enter the subject:");
       AnchorPane.setTopAnchor(textbox2, 200.0);
       AnchorPane.setLeftAnchor(textbox2, 50.0);
       AnchorPane.setRightAnchor(textbox2, 70.0);
 
       //button to submit the question
       AnchorPane.setTopAnchor(btn, 300.0);
       AnchorPane.setLeftAnchor(btn, 40.0);
       AnchorPane.setRightAnchor(btn, 50.0);
 
       // Add buttons to AnchorPane
       anchorp.getChildren().addAll(label1, label2, textbox, label3, textbox2, btn);
//        
//       root.getChildren().add(btn);
        Scene scene = new Scene(anchorp, 600, 400);
        primaryStage.setTitle("Question");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

