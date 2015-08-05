package com.redmart.skiing.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.redmart.skiing.comparator.NodeValueComparator;
import com.redmart.skiing.model.Node;

/**
 * @author albertojg
 * @version 1.0
 */
public class GridPathFinder
{
    public List<Node> findLongestPath(List<Node> nodes) throws IllegalArgumentException
    {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("Unable to find longest path for null / empty nodes!");
        }

        Collections.sort(nodes, new NodeValueComparator());

        List<Node> longest = new ArrayList<Node>();
        for (Node node: nodes) {
            List<Node> path = calculateLongestPath(node);
            if (path == null) {
                continue;
            }

            if (path.size() > longest.size()) {
                longest = path;
            } else if (path.size() == longest.size()) {
                int pathDepth = path.get(0).value() - path.get(path.size() - 1).value();
                int longestDepth = longest.get(0).value() - longest.get(longest.size() - 1).value();

                if (pathDepth < longestDepth) {
                    longest = path;
                }
            }
        }

        Collections.sort(longest, new NodeValueComparator());
        return longest;
    }

    private List<Node> calculateLongestPath(Node node)
    {
        // compute longest path for each direction
        List<Node> northPath = null;
        if (node.north() != null && node.value() > node.north().value()) {
            northPath = calculateLongestPath(node.north());
        }

        List<Node> eastPath = null;
        if (node.east() != null && node.value() > node.east().value()) {
            eastPath = calculateLongestPath(node.east());
        }

        List<Node> southPath = null;
        if (node.south() != null && node.value() > node.south().value()) {
            southPath = calculateLongestPath(node.south());
        }

        List<Node> westPath = null;
        if (node.west() != null && node.value() > node.west().value()) {
            westPath = calculateLongestPath(node.west());
        }

        // take the longest path of all 4 directions and add current node to generate the longest path for current node
        List<Node> longestPath = getLongestList(northPath, eastPath);
        longestPath = getLongestList(longestPath, southPath);
        longestPath = getLongestList(longestPath, westPath);

        if (longestPath == null) {
            longestPath = new ArrayList<Node>();
        }
        longestPath.add(node);

        return longestPath;
    }

    private List<Node> getLongestList(List<Node> a, List<Node> b)
    {
        if (a == null && b == null) {
            return null;
        } else if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            return a.size() > b.size() ? a : b;
        }
    }
}
