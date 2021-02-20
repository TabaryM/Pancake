package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.Scanner;

public class HumanPlayer extends PancakePlayer {
    public HumanPlayer(Game game, String name, int num) {
        super(game, name, num);
    }

    @Override
    public Move play() throws IllegalMoveException {
        int col, lig;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Column:");
        col = scanner.nextInt();
        System.out.print('\n');
        lig = getBoard().getPlayableLineAt(col);
        return new Move(col, lig);
    }

    @Override
    public String toString() {
        return "HumanPlayer{" + getName() + '(' + getToken(getNum()) + ')' +'}';
    }
}
