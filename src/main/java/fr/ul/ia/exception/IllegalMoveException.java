package fr.ul.ia.exception;

import fr.ul.ia.modele.Move;

public class IllegalMoveException extends PancakeException{
    public IllegalMoveException(String msg, Move move) {
        super(msg + move.toString());
    }

    public IllegalMoveException(String msg){
        super(msg);
    }
}
