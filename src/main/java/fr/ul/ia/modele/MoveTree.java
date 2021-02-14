package fr.ul.ia.modele;

import fr.ul.ia.engine.State;
import fr.ul.ia.engine.Tree;
import fr.ul.ia.exception.InvalidTreeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoveTree implements Tree {
    private final Move moveFromPreviousSate;
    private boolean expanded = false;
    private Tree father = null;
    private final State currentState;
    private List<Tree> childrens = new ArrayList<>();
    private int numberSimulation = 0;
    private int sumSimulation = 0;
    private final boolean max;
    private final double c = Math.sqrt(2);

    public MoveTree(Move moveFromPreviousSate, State currentState,boolean max,Tree father) {
        this.moveFromPreviousSate = moveFromPreviousSate;
        this.currentState = currentState;
        this.max = max;
        this.father = father;
    }

    @Override
    public List<Tree> getChildren() {
        return childrens;
    }

    @Override
    public void addChildren(Tree tree) throws InvalidTreeException {
        if(tree == null) throw new InvalidTreeException("Parameter tree must not be null");
        if(currentState.compareTo(tree.getState()) != -1)
            throw new InvalidTreeException("Parameter tree must not be farther than 1 move");
        childrens.add(tree);
    }

    @Override
    public State getState() {
        return currentState;
    }

    @Override
    public int getNumberSimulation() {
        return numberSimulation;
    }

    @Override
    public int getSumSimulation() {
        return sumSimulation;
    }

    @Override
    public double getBValue() {
        return (max ? 1 : -1) *  ((float)sumSimulation/numberSimulation) + c *Math.sqrt(Math.log(father.getNumberSimulation())/numberSimulation);
    }

    @Override
    public Tree getFather() {
        return father;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public Tree getCopy() {
        MoveTree copy = new MoveTree(moveFromPreviousSate,currentState.getCopy(),max,father);
        copy.numberSimulation = numberSimulation;
        copy.sumSimulation = sumSimulation;
        copy.childrens = new ArrayList<>(childrens);
        copy.expanded = expanded;
        return copy;
    }

    @Override
    public boolean isMax() {
        return max;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public void addSimulation(int n, int r) {
        numberSimulation += n;
        sumSimulation += r;
    }

    @Override
    public Move getMoveFromPreviousState() {
        return moveFromPreviousSate;
    }


    @Override
    public Iterator<Tree> iterator() {
        return childrens.iterator();
    }
}
