package fr.ul.ia.modele;

import fr.ul.ia.engine.Game;
import fr.ul.ia.engine.Player;
import fr.ul.ia.exception.IllegalMoveException;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private Game game;
    private String playerName;

    public HumanPlayer(Game game, String playerName) {
        this.game = game;
        this.playerName = playerName;
    }

    @Override
    public Move play() throws IllegalMoveException {
        int col, lig;
        Board board = game.getBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Column:");
        col = scanner.nextInt();
        System.out.print('\n');
        lig = board.getPlayableLineAt(col);
        return new Move(col, lig);
    }

    @Override
    public Board getBoard() {
        return game.getBoard();
    }

    @Override
    public String toString() {
        return "HumanPlayer{" + playerName + '}';
    }
}
