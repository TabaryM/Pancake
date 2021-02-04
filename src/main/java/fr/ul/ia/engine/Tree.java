package fr.ul.ia.engine;

import fr.ul.ia.exception.InvalidTreeException;

import java.util.List;

public interface Tree extends Iterable<Tree>{
    /**
     * Return a copy of the children of the tree.
     * @return List<Tree>
     */
    List<Tree> getChildren();

    /**
     * Add a branch to the tree
     * @param tree the new branch added.
     * @throws InvalidTreeException if the tree is null,
     *                              if the State of this tree is farther than one Move.
     */
    void addChildren(Tree tree) throws InvalidTreeException;

    /**
     * Return the state defining the tree
     * @return this.state
     */
    State getState();
}
