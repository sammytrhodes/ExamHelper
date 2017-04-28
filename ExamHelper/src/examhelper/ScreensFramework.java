/*
 * Mine
 */ 

package examhelper;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author David
 */
public class ScreensFramework extends Application {
   
    public static Stage primaryStage;
    public static String screen1ID = "ExamOrQuestion";
    public static String screen1File = "ExamOrQuestion.fxml";
    public static String screen2ID = "quesOrFile";
    public static String screen2File = "QuesOrFile.fxml";
    public static String screen3ID = "QuesInput";
    public static String screen3File = "QuesInput.fxml";
    public static String screen4ID = "CreateExam";
    public static String screen4File = "CreateExam.fxml";
    public static String screen5ID = "TextInput";
    public static String screen5File = "TextInput.fxml";
    public static String screen6ID = "InvalidExam";
    public static String screen6File = "InvalidExam.fxml";
    
    
    public static void resizeScreen(){
	primaryStage.sizeToScene();
	primaryStage.centerOnScreen();
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        mainContainer.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
        mainContainer.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
        
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ScreensFramework.class);
        BasicConfigurator.configure();
        launch(args);
        
    }
}
