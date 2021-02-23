package fr.ul.ia;

import fr.ul.ia.engine.Game;
import fr.ul.ia.modele.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static String beginEC = "", endEC = "";
    public static boolean haveEC = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        // loader.setControllerFactory(iC->new ***Controller(modele));
        Parent root = loader.load();


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        if(!System.getProperty("os.name").toLowerCase().contains("win")){
            haveEC = true;
            beginEC = "\033[";
            endEC = "m";
        }
        Game game = new PancakeGame();
        game.start();
        System.exit(0);

        //launch(args);
    }
}
