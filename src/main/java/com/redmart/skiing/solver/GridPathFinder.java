package com.redmart.skiing.solver;

import java.util.List;

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

        return null;
    }
}
