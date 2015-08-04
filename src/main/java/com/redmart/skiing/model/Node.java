package com.redmart.skiing.model;

/**
 * Model object represents the node of the graph
 *
 * @author albertojg
 * @version 1.0
 */
public class Node
{
    private int sequenceNumber;

    private int value;

    private Node north;
    private Node south;
    private Node east;
    private Node west;

    public Node(int sequenceNumber, int value)
    {
        this(sequenceNumber, value, null, null, null, null);
    }

    public Node(int sequenceNumber, int value, Node north, Node east, Node south, Node west)
    {
        this.sequenceNumber = sequenceNumber;
        this.value = value;

        this.setNorth(north);
        this.setEast(east);
        this.setSouth(south);
        this.setWest(west);
    }

    public void setNorth(Node north)
    {
        this.north = north;
    }

    public void setSouth(Node south)
    {
        this.south = south;
    }

    public void setEast(Node east)
    {
        this.east = east;
    }

    public void setWest(Node west)
    {
        this.west = west;
    }

    public int getSequenceNumber()
    {
        return this.sequenceNumber;
    }

    public Node north()
    {
        return this.north;
    }

    public Node south()
    {
        return this.south;
    }

    public Node east()
    {
        return this.east;
    }

    public Node west()
    {
        return this.west;
    }

    public int value()
    {
        return this.value;
    }
}
