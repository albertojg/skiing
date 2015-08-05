package com.redmart.skiing.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.redmart.skiing.exception.UnparsableInputFileException;
import com.redmart.skiing.model.Node;
import com.redmart.skiing.parser.InputParser;

import static org.junit.Assert.*;

/**
 * @author albertojg
 * @version 1.0
 */
public class GridInputParserTest
{
    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseDimensionLine_IntegerEntryLessThanTwo_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/less_than_two_entries_for_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseDimensionLine_IntegerEntryMoreThanTwo_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/more_than_two_entries_for_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseDimensionLine_InvalidIntegerEntry_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/invalid_entries_for_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseGridLines_RowCountLessThanDimension_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/row_less_than_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseGridLines_RowCountMoreThanDimension_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/row_more_than_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseGridLines_ColumnCountLessThanDimension_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/column_less_than_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseGridLines_ColumnCountMoreThanDimension_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/column_more_than_dimension.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test(expected = UnparsableInputFileException.class)
    public void testParseInput_ParseGridLines_InvalidIntegerEntry_ShouldThrowUnparsableInputFileException()
            throws Exception
    {
        String path = "./src/test/resources/input/invalid_integer_entry_in_content.txt";

        InputParser parser = new GridInputParser();
        parser.parseInput(path);
    }

    @Test
    public void testParseInput_ValidInputFile_SingleEntryGrid_ShouldReturnListOfNodesWithSingleElement()
            throws Exception
    {
        String path = "./src/test/resources/input/single_element_entry.txt";

        InputParser parser = new GridInputParser();
        List<Node> nodes = parser.parseInput(path);

        assertNotNull("Single element entry cannot have null nodes!", nodes);
        assertEquals("Single element entry can only contain single node!", 1, nodes.size());

        Node node = nodes.get(0);
        assertNull("North connection must be null for single node!", node.north());
        assertNull("East connection must be null for single node!", node.east());
        assertNull("South connection must be null for single node!", node.south());
        assertNull("West connection must be null for single node!", node.west());
    }

    @Test
    public void testParseInput_ValidInputFile_MultipleEntriesGrid_ShouldReturnListOfNodesWithCorrectElements()
            throws Exception
    {
        String path = "./src/test/resources/input/valid_input.txt";

        List<Node> expectedNodes = constructExpectedNodesForValidInput();

        InputParser parser = new GridInputParser();
        List<Node> nodes = parser.parseInput(path);

        assertNotNull(nodes);
        assertEquals(expectedNodes.size(), nodes.size());
        for (int i = 0; i < expectedNodes.size(); i++) {
            assertEquals(expectedNodes.get(i), nodes.get(i));
        }
    }

    private List<Node> constructExpectedNodesForValidInput()
    {
        /*
         * input file contain:
         * 4 4
         * 4 8 7 3
         * 2 5 9 3
         * 6 3 2 5
         * 4 4 1 6
         */
        int row = 4;
        int col = 4;
        int[] entries = new int[] {
                4, 8, 7, 3,
                2, 5, 9, 3,
                6, 3, 2, 5,
                4, 4, 1, 6
        };

        // generate the nodes
        List<Node> nodes = new ArrayList<Node>(entries.length);
        for (int i = 0; i < entries.length; i++) {
            nodes.add(new Node(i + 1, entries[i]));
        }

        // set the links between nodes
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