package tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private ArrayList<Node> children;
    private int minMax;
    private boolean player;
    private int move;
    public String teste;

    // Constructors
    public Node() {
        setChildren(new ArrayList<>());
    }

    public Node(boolean player, int move, int minMax) {
        setPlayer(player);
        setMove(move);
        setMinMax(minMax);
        setChildren(new ArrayList<>());
    }

    public Node hasChild(int move) {
        for (Node node : children) {
            if (node.getMove() == move) {
                return node;
            }
        }
        return null;
    }

    public int bestMove() {
        int value = Integer.MIN_VALUE;
        int valueChildren = 0;
        Node bestNode = null;

        for (Node node : getChildren()) {
            if (node.getMinMax() > value) {
                value = node.getMinMax();
                bestNode = node;
                valueChildren = node.getChildren().stream().mapToInt(Node::getMinMax).sum();
            } else if (node.getMinMax() == value && !node.getChildren().isEmpty()) {
                int aux = node.getChildren().stream().mapToInt(Node::getMinMax).sum();
                if (aux > valueChildren) {
                    valueChildren = aux;
                    value = node.getMinMax();
                    bestNode = node;
                }
            }
        }

        if (bestNode != null) {
            return bestNode.getMove();
        } else {
            return this.getMove();
        }
    }

    // Gets and Sets
    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public int getMinMax() {
        return minMax;
    }

    public void setMinMax(int minMax) {
        this.minMax = minMax;
    }

    public boolean getPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}