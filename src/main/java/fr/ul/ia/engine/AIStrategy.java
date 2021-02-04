package fr.ul.ia.engine;

import fr.ul.ia.modele.Move;

public interface AIStrategy {
    /**
     * Return the move that correspond the best to the strategy
     * @param state The state from which the AI has to chose a move.
     * @return the move chosen by the strategy
     */
    Move getNextMove(State state);
}
