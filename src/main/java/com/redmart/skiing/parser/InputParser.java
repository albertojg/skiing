package com.redmart.skiing.parser;

import com.redmart.skiing.model.Node;
import com.sun.media.sound.InvalidFormatException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Parser to parse the given input data and produce the graph representation of the problem
 *
 * @author albertojg
 * @version 1.0
 */
public interface InputParser
{
    @SuppressWarnings("UnnecessaryInterfaceModifier")
    public List<Node> parseInput(String path) throws FileNotFoundException, InvalidFormatException;
}
