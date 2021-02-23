package fr.ul.ia;

import fr.ul.ia.engine.Game;
import fr.ul.ia.modele.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main{

    public static String beginEC = "", endEC = "";
    public static boolean haveEC = false;


    public static void main(String[] args) {
        if(!System.getProperty("os.name").toLowerCase().contains("win")){
            haveEC = true;
            beginEC = "\033[";
            endEC = "m";
        }
        Game game = new PancakeGame();
        game.start();
        System.exit(0);
    }
}
