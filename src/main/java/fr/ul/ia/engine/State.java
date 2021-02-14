package fr.ul.ia.engine;

import fr.ul.ia.modele.Board;
import fr.ul.ia.modele.EndState;
import fr.ul.ia.modele.Move;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.List;

public interface State extends Comparable{
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
     * Return the current EndState of the game
     * @return EndState : PLAYER1_WON if the first player won,
     *                    PLAYER2_WON if the second player won,
     *                    DRAW if no player have won and no moves are available,
     *                    NOT_FINISHED otherwise
     */
    EndState testEnd();

    /**
     * Create and return a new list of all available moves from this state.
     * @return List<Move> the list of all moves that the player currently playing can do.
     *         If no moves are available, the list is empty.
     */
    List<Move> getAvailableMoves();

    Board getCurrentBoard();

    State getCopy();

    void setBoard(Board board);

    void setCurrentPlayer(int currentPlayer);
}
