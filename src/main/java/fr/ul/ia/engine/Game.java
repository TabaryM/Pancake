package fr.ul.ia.engine;

import fr.ul.ia.modele.Board;

public interface Game {
    /**
     * Initialize the objects used in the game
     * example :
     *  - The board object
     *  - The player objects
     */
    void init();

    /**
     * Start the game
     */
    void start();

    /**
     * @return true if one player has won or if no more move is available, otherwise return false
     */
    boolean isFinished();

    /**
     * The game evolve (systems interacts together, or player interact with systems)
     */
    void evolve();

    Board getBoard();
}
