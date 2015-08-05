package com.redmart.skiing.comparator;

import com.redmart.skiing.model.Node;

import java.util.Comparator;

/**
 * @author albertojg
 * @version 1.0
 */
public class NodeValueComparator implements Comparator<Node>
{
    @Override
    public int compare(Node a, Node b)
    {
        if (a == null && b == null) {
            return 0;
        } else if (a == null) {
            return 1;
        } else if (b == null) {
            return -1;
        } else {
            if (a.value() == b.value()) {
                return 0;
            } else if (a.value() < b.value()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
