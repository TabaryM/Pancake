package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

public class AIPlayer implements Player {
    private Game game;

    public AIPlayer(Game game) {
        this.game = game;
    }

    @Override
    public Move play() throws IllegalMoveException {
        return null;
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }
}
