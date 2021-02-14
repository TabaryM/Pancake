package fr.ul.ia;

import fr.ul.ia.engine.Game;
import fr.ul.ia.modele.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

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
        Game game = new PancakeGame();
        game.start();
        System.out.println("Le jeu est fini, vous pouvez partir");

        System.exit(0);

        //launch(args);
    }
}
