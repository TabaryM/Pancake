package fr.ul.ia.modele;

import fr.ul.ia.engine.AIStrategy;
import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

public class AIPlayer implements Player {
    private Game game;
    private final AIStrategy strat;

    public AIPlayer(Game game,AIStrategy strat) {
        this.game = game;
        this.strat = strat;
    }

    @Override
    public Move play() throws IllegalMoveException {
        return strat.getNextMove(game.getCurrentState());
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }
}
