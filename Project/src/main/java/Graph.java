import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;


public class Graph {
    private int nodeNum;
    private int edgeNum;
    private ArrayList<ArrayList<Integer>> adj;
    private ArrayList<ArrayList<Integer>> adjWeights;
    private ArrayList<Boolean> marked;
    private ArrayList<Node> storedNodes; //can produce the index
    //store map of short name to userid

    /**
     * Constructor
     * Initializes the graph with zero nodes
     */
    public Graph() {
        this(0);
    }

    /**
     * Constructor
     * Initalizes the graph with the given number of nodes
     * @param nodeNum The number of nodes in the graph
     */
    public Graph(int nodeNum) {
        if(nodeNum < 0) {
            throw new IllegalArgumentException("Number of nodes must be greater than or equal to 0");
        }
        this.nodeNum = nodeNum;
        this.edgeNum = 0;
        this.marked = new ArrayList<Boolean>(nodeNum);
        this.adj = new ArrayList<ArrayList<Integer>>();
        this.adjWeights = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < nodeNum; i++) {
            this.adj.add(new ArrayList<Integer>());
            this.adjWeights.add(new ArrayList<Integer>());
        }
    }

    /**
     *
     * @param desiredNode the node the user is looking to find the index of
     * @return the index of the desired node or -1 for failure
     */
    public int mapNodeToIndex(Node desiredNode){
        for(Node n: storedNodes){
            if(desiredNode.equals(storedNodes.indexOf(n))) {
                return storedNodes.indexOf(n);
            }
        }
        return -1;
    }

    public int mapNodeIDToIndex(String desiredNodeID){
        for(Node n: storedNodes){
            if(n.getNodeID().equals(desiredNodeID)) {
                return storedNodes.indexOf(n);
            }
        }
        return -1;
    }

    /**
     *
     * @param desiredIndex the location of the desired node
     * @return the node at the desired index
     */
    public Node mapIndexToNode(int desiredIndex) {
        return storedNodes.get(desiredIndex);
    }


    /**
     * Gets the shortest path between the two given nodes
     * @param start The node to start at
     * @param target The node to end at
     * @return A List of Integers that represent the node path to get to the target,
     *          the first number is the distance of the path
     */
    public List<Integer> shortestPath(int start, int target) {
        int current = start;
        int[] distance = new int[nodeNum];
        boolean[] marked = new boolean[nodeNum];
        Queue<Integer> queue = new LinkedList<Integer>();
        ArrayList<List<Integer>> paths = new ArrayList<List<Integer>>();
        for(int i = 0; i < nodeNum; i++) {
            paths.add(new ArrayList<Integer>());
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        paths.get(start).add(start);
        queue.add(current);

        // BFS of nodes and get the distances of each node
        while(queue.size() != 0) {
            current = queue.remove();
            for(int i = 0; i < adj.get(current).size(); i++) {
                int nextNode = adj.get(current).get(i);
                int currentDistance = distance[current] + adjWeights.get(current).get(i);
                if(currentDistance < distance[nextNode]) {
                    distance[nextNode] = currentDistance;
                    List<Integer> newPath = deepCopy(paths.get(current));
                    newPath.add(nextNode);
                    paths.set(nextNode, newPath);
                }
                if(!marked[nextNode]) {
                    queue.add(nextNode);
                    marked[nextNode] = true;
                }
            }
            System.out.println("P - " + paths);
            System.out.println("D - " + Arrays.toString(distance));
        }
        paths.get(target).add(0, distance[target]);
        return paths.get(target);
    }

    /**
     * Creates a deep copy of the given List
     * @param original The original list to copy
     * @return The deep copy of the list
     */
    private List<Integer> deepCopy(List<Integer> original) {
        List<Integer> copy = new ArrayList<Integer>();
        for(int i = 0 ; i < original.size(); i++) {
            copy.add(original.get(i));
        }
        return copy;
    }

    /**
     * Adds a single node to the graph
     */
    public void addNode(Node newNode) {
        storedNodes.add(newNode);
        adj.add(new ArrayList<Integer>());
        adjWeights.add(new ArrayList<Integer>());
    }

    /*/**
     * Adds a number of nodes to the graph
     * @param num The number of nodes to add

    public void addNodes(int num) {
        for(int i = 0; i < num; i++) {
            addNode();
        }
    }*/ //removed since not necessary - Niski

    /**
     * Removes the node at from the graph
     * @param node The node to remove
     */
    public void removeNode(int node) {
        for(int i = 0; i < nodeNum; i++) {
            removeBiEdge(node, i);
        }

        adj.remove(node);
        adjWeights.remove(node);
        nodeNum--;

        for(int i = 0; i < nodeNum; i++) {
            List<Integer> adjList = adj.get(i);
            for(int j = 0; j < adjList.size(); j++) {
                if(adjList.get(j) > node) {
                    adjList.set(j, adjList.get(j) - 1);
                }
            }
        }
    }


    public void addEdge(String nodeID1, String nodeID2) {
        //check if nodes exist
        int node1Index = mapNodeIDToIndex(nodeID1);
        int node2Index = mapNodeIDToIndex(nodeID2);

        if(node1Index == -1 || node2Index == -1) {
            throw new IllegalArgumentException("Node does not exist");
        }

        Node node1 = storedNodes.get(node1Index);
        Node node2 = storedNodes.get(node2Index);
        //calculate weight
        double xWeight = abs(node1.getXcoord() - node2.getXcoord());
        double yWeight = abs(node1.getYcoord() - node2.getYcoord());

        int weight = (int) sqrt(Math.pow(xWeight, 2) + Math.pow(yWeight, 2));

        adj.get(node1Index).add(node2Index);
        adjWeights.get(node1Index).add(weight);
    }

    /**
     * Adds a bidirectional edge to the graph
     * @param n1 The first node that is connected
     * @param n2 The second node that is connected
     * @param weight The weight of the edge
     */
   /* public void addBiEdge(int n1, int n2, int weight) {
        addEdge(n1, n2, weight);
        addEdge(n2, n1, weight);
    }*/

    /**
     * Removes an edge between the two given nodes
     * @param n1 The first node to disconnect
     * @param n2 The second node to disconnect
     */
    public void removeEdge(int n1, int n2) {
        if(n1 == n2) {
            return;
        }
        int edgeIndex = adj.get(n1).indexOf(n2);
        if(edgeIndex >= 0) {
            edgeNum--;
            adj.get(n1).remove(edgeIndex);
            adjWeights.get(n1).remove(edgeIndex);
        }
    }

    /**
     * Removes the any edges between the two given nodes
     * @param n1 The first node to disconnect
     * @param n2 The second node to disconnect
     */
    public void removeBiEdge(int n1, int n2) {
        removeEdge(n1, n2);
        removeEdge(n2, n1);
    }

    public ArrayList<Integer> getEdges(int n) {
        return adj.get(n);
    }

    public int degreeOf(int n) {
        return adj.get(n).size();
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }
}
