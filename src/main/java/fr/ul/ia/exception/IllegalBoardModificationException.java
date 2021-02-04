package fr.ul.ia.exception;

public class IllegalBoardModificationException extends RuntimeException {
    public IllegalBoardModificationException(String msg) {
        super(msg);
    }
}
