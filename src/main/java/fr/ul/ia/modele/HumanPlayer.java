package fr.ul.ia.modele;

import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

import java.io.IOException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    @Override
    public Move play() throws IllegalMoveException {
        int col, lig;
        Scanner scanner = new Scanner(System.in);
        col = scanner.nextInt();
        lig = scanner.nextInt();
        return new Move(col, lig);
    }
}
