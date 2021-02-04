package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.engine.State;
import fr.ul.ia.exception.PancakeException;

public class PancakeGame implements Game {

    private State currentState;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    public static final int nbDiscsToWin = 4;

    public PancakeGame() {
        currentState = PancakeState.getInitialState();
        init();
    }

    @Override
    public void init() {
        playerOne = new HumanPlayer();
        playerTwo = new HumanPlayer();

        currentPlayer = playerOne;
    }

    @Override
    public void start() {
        while(!isFinished()){
            evolve();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void evolve() {
        nextPlayer();
        currentState.applyMove(currentPlayer.play());
    }

    void nextPlayer(){
        if(currentPlayer == playerOne){
            currentPlayer = playerTwo;
        } else if(currentPlayer == playerTwo){
            currentPlayer = playerOne;
        } else {
            throw new PancakeException("On ne sait plus qui est le joueur courrant");
        }
    }
}
