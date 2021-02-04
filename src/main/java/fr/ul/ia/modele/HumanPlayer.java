package fr.ul.ia.modele;

import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

import java.io.IOException;

public class HumanPlayer implements Player {
    @Override
    public Move play() throws IllegalMoveException {
        int col = 0, lig = 0;
        try {
            col = System.in.read();
            lig = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Move(col, lig);
    }
}
