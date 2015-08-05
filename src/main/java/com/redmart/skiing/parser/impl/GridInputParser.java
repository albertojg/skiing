package com.redmart.skiing.parser.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import com.redmart.skiing.exception.UnparsableInputFileException;
import com.redmart.skiing.model.Node;
import com.redmart.skiing.parser.InputParser;

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
    public List<Node> parseInput(String path) throws UnparsableInputFileException, IOException
    {
        File file = FileUtils.getFile(path);
        LineIterator iter = FileUtils.lineIterator(file);

        // extract dimension from first line
        int row = 0, col = 0;
        if (iter.hasNext()) {
            String line = iter.next();

            String[] dimension = line.split(" ");
            if (dimension.length != 2) {
                throw new UnparsableInputFileException("Unable to parse grid dimension!");
            }

            try {
                row = Integer.parseInt(dimension[0]);
                col = Integer.parseInt(dimension[1]);
            } catch (NumberFormatException nfe) {
                throw new UnparsableInputFileException("Invalid dimension entry!", nfe);
            }
        }

        return parseGrid(iter, row, col);
    }

    private List<Node> parseGrid(LineIterator iterator, int row, int col) throws UnparsableInputFileException
    {
        int sequence = 1;
        List<Node> nodes = new ArrayList<Node>();

        // parse the grid
        try {
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }

                String[] values = line.split(" ");
                for (String value: values) {
                    if (!StringUtils.isEmpty(value)) {
                        nodes.add(new Node(sequence, Integer.parseInt(value)));
                        sequence++;
                    }
                }
            }
        } catch (NumberFormatException nfe) {
            throw new UnparsableInputFileException("Invalid grid entry!", nfe);
        }

        if (nodes.size() < (row * col) || nodes.size() > (row * col)) {
            throw new UnparsableInputFileException("Invalid grid entry count!");
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
}
