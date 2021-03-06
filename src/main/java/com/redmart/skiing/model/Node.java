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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (getSequenceNumber() != node.getSequenceNumber()) return false;
        if (value() != node.value()) return false;

        if (north() == null) {
            if (node.north() != null) {
                return false;
            }
        } else {
            if (north().getSequenceNumber() != node.north().getSequenceNumber() ||
                    north().value() != node.north().value()) {
                return false;
            }
        }

        if (east() == null) {
            if (node.east() != null) {
                return false;
            }
        } else {
            if (east().getSequenceNumber() != node.east().getSequenceNumber() ||
                    east().value() != node.east().value()) {
                return false;
            }
        }

        if (south() == null) {
            if (node.south() != null) {
                return false;
            }
        } else {
            if (south().getSequenceNumber() != node.south().getSequenceNumber() ||
                    south().value() != node.south().value()) {
                return false;
            }
        }

        if (west() == null) {
            if (node.west() != null) {
                return false;
            }
        } else {
            if (west().getSequenceNumber() != node.west().getSequenceNumber() ||
                    west().value() != node.west().value()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = getSequenceNumber();
        result = 31 * result + value;
        result = 31 * result + (north != null ? north.value() : 0);
        result = 31 * result + (south != null ? south.value() : 0);
        result = 31 * result + (east != null ? east.value() : 0);
        result = 31 * result + (west != null ? west.value() : 0);
        return result;
    }
}
