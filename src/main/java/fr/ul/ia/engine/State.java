package fr.ul.ia.engine;

import fr.ul.ia.modele.EndState;
import fr.ul.ia.modele.Move;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.List;

public interface State {
    /*
     * Copy all elements of the state parameter into this state.
     *
     * @param state the state copied.
     */
    // Constructor by deep copy

    /**
     * Apply the Move to the state to modify this state in the state "this + move"
     * @param move a move that can be applied to this state.
     * @throws IllegalMoveException if the move cannot be applied
     */
    void applyMove(Move move) throws IllegalMoveException;

    /**
     * Return the
     * @return
     */
    EndState testEnd();

    List<Move> getAvailableMoves();
}
