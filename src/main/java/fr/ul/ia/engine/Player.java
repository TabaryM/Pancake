package fr.ul.ia.engine;

import fr.ul.ia.exception.IllegalMoveException;
import fr.ul.ia.modele.Move;

public interface Player {

    /**
     * Return the move chosen by the player.
     * @return Move that the player want to make
     * @throws IllegalMoveException if the move is not possible or not allowed
     */
    Move play() throws IllegalMoveException;
}
