package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;

    public Node(String a) {
        name = a;
        edges = new ArrayList<>();
        msg = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }

    public List<Node> getEdges() {
        return edges;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public Message getMsg() {
        return msg;
    }

    public void addEdge(Node b) {
        edges.add(b);
    }


    public boolean hasCycles() {
        Set<Node> visited = new HashSet<>();
        Set<Node> recursionStack = new HashSet<>();
        return hasCyclesHelper(this, visited, recursionStack);
    }

    private boolean hasCyclesHelper(Node current, Set<Node> visited, Set<Node> recursionStack) {
        if (recursionStack.contains(current)) {
            return true;
        }


        if (visited.contains(current)) {
            return false;
        }

        visited.add(current);
        recursionStack.add(current);


        for (Node neighbor : current.getEdges()) {
            if (hasCyclesHelper(neighbor, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }



}
