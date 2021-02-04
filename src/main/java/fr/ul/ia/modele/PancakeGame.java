package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.engine.State;
import fr.ul.ia.exception.PancakeException;

public class PancakeGame implements Game {

    private State currentState;

    private Player players[];
    private int currentPlayer;
    public static final int nbDiscsToWin = 4;

    public PancakeGame() {
        currentState = PancakeState.getInitialState();
        init();
    }

    @Override
    public void init() {

        players = new Player[2];

        players[0] = new HumanPlayer();
        players[1] = new HumanPlayer();

        currentPlayer = 0;
    }

    @Override
    public void start() {
        while(!isFinished()){
            evolve();
            System.out.println(currentState.toString());
        }
    }

    @Override
    public boolean isFinished() {
        return currentState.testEnd() != EndState.NOT_FINISHED;
    }

    @Override
    public void evolve() {
        nextPlayer();
        currentState.applyMove(players[currentPlayer].play());
    }

    void nextPlayer(){
        currentPlayer = (currentPlayer + 1)%2;
    }
}
