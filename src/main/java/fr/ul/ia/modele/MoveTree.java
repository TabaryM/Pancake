package fr.ul.ia.modele;

import fr.ul.ia.engine.State;
import fr.ul.ia.engine.Tree;
import fr.ul.ia.exception.InvalidTreeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoveTree implements Tree {
    private final Move moveFromPreviousSate;
    private final State currentState;
    private final List<Tree> childrens = new ArrayList<>();

    public MoveTree(Move moveFromPreviousSate, State currentState) {
        this.moveFromPreviousSate = moveFromPreviousSate;
        this.currentState = currentState;
    }

    @Override
    public List<Tree> getChildren() {
        return new ArrayList<>(childrens);
    }

    @Override
    public void addChildren(Tree tree) throws InvalidTreeException {
        if(tree == null) throw new InvalidTreeException("Parameter tree must not be null");
        if(currentState.compareTo(tree.getState()) != -1)
            throw new InvalidTreeException("Parameter tree must not be farther than 1 move");
    }

    @Override
    public State getState() {
        return currentState;
    }

    @Override
    public Iterator<Tree> iterator() {
        return childrens.iterator();
    }
}
