package com.redmart.skiing.parser.impl;

import com.redmart.skiing.model.Node;
import com.redmart.skiing.parser.InputParser;
import com.sun.media.sound.InvalidFormatException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Parse the input file in the grid pattern.
 *
 * <p>
 * The input file is expected to have 2 integers, representing the dimension of the grid, r row and c column,
 * separated by spaces. The subsequent r rows represent the grid. Each row contain c numbers.
 *
 * <pre>
 * For example:
 * 4 4
 * 4 8 7 3
 * 2 5 9 3
 * 6 3 2 5
 * 4 4 1 6
 * </pre>
 *
 * <p>
 * In the example above, the input is 4x4 grid. The first row is {@code 4 8 7 3}, the second row is
 * {@code 2 5 9 3}, etc. The connectivity between node is representing in the graph itself. For example,
 * item 9 (row 2 column 3), has north-connectivity to item 7 (row 1 column 3), east-connectivity to item 3
 * (row 2 column 4), south-connectivity to item 2 (row 3 column 3), west-connectivity to item 5 (row 2 column 2).
 *
 * @author albertojg
 * @version 1.0
 */
public class GridInputParser implements InputParser
{
    @Override
    public List<Node> parseInput(String path) throws FileNotFoundException, InvalidFormatException
    {
        return null;
    }
}
