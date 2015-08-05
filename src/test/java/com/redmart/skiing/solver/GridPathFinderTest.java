package com.redmart.skiing.solver;

import com.redmart.skiing.model.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GridPathFinderTest
{
    @SuppressWarnings("ConstantConditions")
    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestPath_NullNodesInput_ShouldThrowIllegalArgumentException() throws Exception
    {
        List<Node> nodes = null;

        GridPathFinder finder = new GridPathFinder();
        finder.findLongestPath(nodes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLongestPath_EmptyNodesInput_ShouldThrowIllegalArgumentException() throws Exception
    {
        List<Node> nodes = new ArrayList<Node>();

        GridPathFinder finder = new GridPathFinder();
        finder.findLongestPath(nodes);
    }

    @Test
    public void testFindLongestPath_SingleNodeInput_ShouldReturnSingleNodePath() throws Exception
    {
        /*
         * Grid:
         *     10
         */

        Node node = new Node(1, 10);

        List<Node> nodes = new ArrayList<Node>();
        nodes.add(node);

        List<Node> expected = new ArrayList<Node>();
        expected.add(node);

        GridPathFinder finder = new GridPathFinder();
        List<Node> result = finder.findLongestPath(nodes);

        assertLongestPathNodesAreEquals(expected, result);
    }

    @Test
    public void testFindLongestPath_FlatGridInput_ShouldReturnSingleNodePath() throws Exception
    {
        /*
         * Grid:
         *     2 2 2
         *     2 2 2
         *     2 2 2
         */

        int[] values = new int[] {
                2, 2, 2,
                2, 2, 2,
                2, 2, 2
        };
        int row = 3;
        int col = 3;

        List<Node> nodes = createNodesFromIntArray(values, row, col);

        List<Node> expected = new ArrayList<Node>();
        expected.add(nodes.get(0));

        GridPathFinder finder = new GridPathFinder();
        List<Node> result = finder.findLongestPath(nodes);

        assertLongestPathNodesAreEquals(expected, result);
    }

    @Test
    public void testFindLongestPath_WaterfallNodesInput_ShouldReturnTopToBottomPath() throws Exception
    {
        /*
         * Grid:
         *     3 3 3
         *     2 2 2
         *     1 1 1
         *
         * Path:
         *     3 2 1
         */

        int[] values = new int[] {
                3, 3, 3,
                2, 2, 2,
                1, 1, 1
        };
        int row = 3;
        int col = 3;

        List<Node> nodes = createNodesFromIntArray(values, row, col);

        List<Node> expected = new ArrayList<Node>();
        expected.add(nodes.get(0));
        expected.add(nodes.get(3));
        expected.add(nodes.get(6));

        GridPathFinder finder = new GridPathFinder();
        List<Node> result = finder.findLongestPath(nodes);

        assertLongestPathNodesAreEquals(expected, result);
    }

    @Test
    public void testFindLongestPath_MultipleSameLengthPath_ShouldReturnSteepestPath() throws Exception
    {
        /*
         * Grid:
         *     6 2 3 8
         *     5 4 4 7
         *     5 3 3 7
         *     5 1 1 5
         *
         * Multiple paths:
         *     6 5 4 3 1
         *     8 7 4 3 1
         */

        int[] values = new int[] {
                6, 2, 3, 8,
                5, 4, 4, 7,
                5, 3, 3, 7,
                5, 1, 1, 5
        };
        int row = 4;
        int col = 4;

        List<Node> nodes = createNodesFromIntArray(values, row, col);

        List<Node> expected = new ArrayList<Node>();
        expected.add(nodes.get(3));
        expected.add(nodes.get(7));
        expected.add(nodes.get(6));
        expected.add(nodes.get(10));
        expected.add(nodes.get(14));

        GridPathFinder finder = new GridPathFinder();
        List<Node> result = finder.findLongestPath(nodes);

        assertLongestPathNodesAreEquals(expected, result);
    }

    private List<Node> createNodesFromIntArray(int[] values, int row, int col)
    {
        List<Node> nodes = new ArrayList<Node>();
        for (int sequence = 0; sequence < (row * col); sequence++) {
            nodes.add(new Node(sequence + 1, values[sequence]));
        }

        // set the connection
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                Node node = nodes.get(r * row + c);

                // north
                if (r - 1 >= 0) {
                    node.setNorth(nodes.get((r - 1) * row + c));
                }

                // south
                if (r + 1 < row) {
                    node.setSouth(nodes.get((r + 1) * row + c));
                }

                // east
                if (c + 1 < col) {
                    node.setEast(nodes.get((r * row) + (c + 1)));
                }

                // west
                if (c - 1 >= 0) {
                    node.setWest(nodes.get((r * row) + (c - 1)));
                }
            }
        }

        return nodes;
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void assertLongestPathNodesAreEquals(List<Node> path1, List<Node> path2)
    {
        if (path1 == null && path2 == null) {
            return;
        } else if (path1 == null) {
            assertTrue(path2.isEmpty());
        } else if (path2 == null) {
            assertTrue(path1.isEmpty());
        } else {
            assertEquals(path1.size(), path2.size());
            for (int i = 0; i < path1.size(); i++) {
                assertEquals(path1.get(i), path2.get(i));
            }
        }
    }
}