package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.engine.State;
import fr.ul.ia.exception.PancakeException;

public class PancakeGame implements Game {

    private PancakeState currentState;

    private Player players[];
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

        currentState.applyMove(currentState.getAvailableMoves().get(0));
        currentState.applyMove(currentState.getAvailableMoves().get(0));
        currentState.applyMove(currentState.getAvailableMoves().get(0));
        currentState.applyMove(currentState.getAvailableMoves().get(0));
        currentState.applyMove(currentState.getAvailableMoves().get(0));

    }

    @Override
    public void start() {
        while(!isFinished()){
            displayState();
            evolve();
        }
        displayState();
        displayEnd();
    }

    @Override
    public boolean isFinished() {
        return currentState.testEnd() != EndState.NOT_FINISHED;
    }

    @Override
    public void evolve() {
        boolean hasPlayed = false;
        while(!hasPlayed) {
            try{
                Move move = players[getCurrentPlayer()].play();
                currentState.applyMove(move);
                hasPlayed = true;
            } catch (Exception e) {
                hasPlayed = false;
            }
        }
    }


    void displayState(){
        System.out.println("Turn: player "+ getCurrentPlayer());
        System.out.println(currentState.toString() + " 0| 1| 2| 3| 4| 5| 6|");
    }

    void displayEnd(){
        String endState = "";
        switch (currentState.testEnd()){
            case DRAW:
                endState = "Draw";
                break;
            case PLAYER1_WON:
                endState = "Player 1 won";
                break;
            case PLAYER2_WON:
                endState = "Player 2 won";
                break;
            case NOT_FINISHED:
                endState = "The game is not finished";
                break;
        }
        System.out.println(endState);
    }

    private int getCurrentPlayer(){
        return currentState.getCurrentPlayer() - 1;
    }
}
