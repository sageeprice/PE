package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Minimum spanning tree.
 *
 * Answer: 259679
 */
public class Euler107 implements Problem {

    private static final String FILE_NAME = "src/text/p107_network.txt";

    @Override
    public String solve() {
        Forest forest = new Forest(40);
        Set<NetworkEdge> networkEdges = new TreeSet<>();
        int totalWeight = 0;

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null) {
                    String[] row = line.split(",");
                    for (int i = 0; i < lineNum; i++) {
                        if (!row[i].equals("-")) {
                            networkEdges.add(new NetworkEdge(lineNum, i, Integer.valueOf(row[i])));
                            totalWeight += Integer.valueOf(row[i]);
                        }
                    }
                    lineNum++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + FILE_NAME);
        }

        Iterator<NetworkEdge> edgeIterator = networkEdges.iterator();
        int forestWeight = 0;
        while (!forest.isConnected()) {
            NetworkEdge edge = edgeIterator.next();
            if (forest.findRoot(edge.getStart()) != forest.findRoot(edge.getEnd())) {
                forestWeight += edge.getLength();
                forest.joinTrees(edge.getStart(), edge.getEnd());
            }
        }
        return String.valueOf(totalWeight - forestWeight);
    }

    private static class Forest {

        private int[] trees;
        private int size;

        public Forest(int size) {
            this.trees = new int[size];
            this.size = size;
            initiateTrees();
        }

        public boolean isConnected() {
            for (int i = 1; i < size; i++) {
                if (trees[i] != trees[0]) {
                    return false;
                }
            }
            return true;
        }

        public int findRoot(int tree) {
            if (trees[tree] == tree) {
                return tree;
            } else {
                trees[tree] = findRoot(trees[tree]);
                return trees[tree];
            }
        }

        public int joinTrees(int treeA, int treeB) {
            trees[findRoot(treeA)] = findRoot(treeB);
            return trees[treeA];
        }

        private void initiateTrees() {
            for (int i = 0; i < size; i++) {
                trees[i] = i;
            }
        }
    }

    protected static class NetworkEdge implements Comparable<NetworkEdge> {

        private int start;
        private int end;
        private int length;

        public NetworkEdge(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(NetworkEdge o) {
            if (o.getLength() > getLength()) {
                return -1;
            } else if (o.getLength() < getLength()) {
                return 1;
            } else if (o.getStart() > getStart()) {
                return -1;
            } else if (o.getStart() < getStart()) {
                return 1;
            } else if (o.getEnd() > getEnd()) {
                return -1;
            } else if (o.getEnd() < getEnd()) {
                return 1;
            } else {
                return 0;
            }
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getLength() {
            return length;
        }

        public String toString() {
            return "{start: " + start + ", end: " + end + ", length: " + length + "}";
        }
    }
}