package com.redmart.skiing.parser;

import com.redmart.skiing.model.Node;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
     * @throws IOException
     * @throws ParseException
     */
    @SuppressWarnings("UnnecessaryInterfaceModifier")
    public List<Node> parseInput(String path) throws IOException, ParseException;
}
