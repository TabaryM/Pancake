package fr.ul.ia.modele;

import fr.ul.ia.engine.AIStrategy;
import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

public class AIPlayer extends PancakePlayer {
    private final AIStrategy strat;

    public AIPlayer(Game game, AIStrategy strat, String name, int num) {
        super(game, name, num);
        this.strat = strat;
    }

    @Override
    public Move play() throws IllegalMoveException {
        return strat.getNextMove(getGame().getCurrentState());
    }

    @Override
    public String toString() {
        return "AIPlayer{"+ getName() + '(' + getToken(getNum()) + ") : " +strat.getName() +"}";
    }
}
