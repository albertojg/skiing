package com.redmart.skiing.parser;

import java.io.IOException;
import java.util.List;

import com.redmart.skiing.exception.UnparsableInputFileException;
import com.redmart.skiing.model.Node;

/**
 * Parser to parse the given input data and produce the graph representation of the problem
 *
 * @author albertojg
 * @version 1.0
 */
public interface InputParser
{
    /**
     * Parse the input file located at {@code path} and return the graph representing the nodes of the input.
     *
     * @param path path to the input file
     * @return List of nodes
     * @throws UnparsableInputFileException
     * @throws IOException
     */
    @SuppressWarnings("UnnecessaryInterfaceModifier")
    public List<Node> parseInput(String path) throws UnparsableInputFileException, IOException;
}
