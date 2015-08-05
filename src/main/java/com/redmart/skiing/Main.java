package com.redmart.skiing;

import com.redmart.skiing.model.Node;
import com.redmart.skiing.parser.InputParser;
import com.redmart.skiing.parser.impl.GridInputParser;
import com.redmart.skiing.solver.GridPathFinder;

import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        if (args.length != 1) {
            throw new RuntimeException("Missing input file path argument!");
        }

        InputParser parser = new GridInputParser();
        List<Node> nodes = parser.parseInput(args[0]);

        GridPathFinder finder = new GridPathFinder();
        List<Node> longestPath = finder.findLongestPath(nodes);

        System.out.println("Longest path consist of " + longestPath.size() + " nodes. Path is:");
        for (int i = 0; i < longestPath.size(); i++) {
            System.out.println(longestPath.get(i).value());
        }
    }
}
