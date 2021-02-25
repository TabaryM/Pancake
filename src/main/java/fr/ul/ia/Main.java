package fr.ul.ia;

import fr.ul.ia.engine.*;
import fr.ul.ia.modele.*;

import java.util.InputMismatchException;
import java.util.Scanner;

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

        boolean hasChose, hasIA = false;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        Player[] players = new Player[2];

        do{
            System.out.print("Le premier joueur est un humain (1) ou une IA (2) : ");
            try{
                choice = scanner.nextInt();
                hasChose = ((choice == 1) || (choice == 2));
            } catch (InputMismatchException e){
                hasChose = false;
            }
        }
        while (!hasChose);

        if(choice == 1){
            players[0] = new HumanPlayer(game, "Alice", 1);
        } else {
            hasIA = true;
            players[0] = new AIPlayer(game, MCTS.getInstance(), "AWESOM-O", 1);
        }

        do{
            System.out.print("Le second joueur est un humain (1) ou une IA (2) : ");
            try{
                choice = scanner.nextInt();
                hasChose = ((choice == 1) || (choice == 2));
            } catch (InputMismatchException e){
                hasChose = false;
            }
        }
        while (!hasChose);

        if(choice == 1){
            players[1] = new HumanPlayer(game, "Bob", 2);
        } else {
            hasIA = true;
            players[1] = new AIPlayer(game, MCTS.getInstance(), "Bender", 2);
        }

        if(hasIA) {
            do{
                System.out.print("Veuillez saisir le temps de reflexion de l'IA (en millisecondes) : ");
                try{
                    choice = scanner.nextInt();
                    hasChose = choice >= 0;
                } catch (InputMismatchException e){
                    hasChose = false;
                }
            }
            while (!hasChose);
            MCTS.setTime(choice);
        }

        game.setPlayers(players);
        game.start();
        System.exit(0);
    }
}
