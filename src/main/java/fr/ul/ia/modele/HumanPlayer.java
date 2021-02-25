package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends PancakePlayer {
    public HumanPlayer(Game game, String name, int num) {
        super(game, name, num);
    }

    @Override
    public Move play() throws IllegalMoveException {
        int col = -1, lig;
        boolean hasPlayed = false;
        System.out.print("Column:");
        // TODO : gérer les inputs invalides
        do{
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input a number between 0 and "+(Board.BOARD_WIDTH - 1));
                col = scanner.nextInt();
                hasPlayed = (col >= 0 && col <= Board.BOARD_WIDTH);
            } catch (InputMismatchException e){
                hasPlayed = false;
            }
        }
        while(!hasPlayed);
        lig = getBoard().getPlayableLineAt(col);
        return new Move(col, lig);
    }

    @Override
    public String toString() {
        return getNum()+" HumanPlayer{" + getName() + '(' + getToken(getNum()) + ')' +'}';
    }
}
