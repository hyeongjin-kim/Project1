package edu.postech.csed409h.internals;

/**
 * A <em>Node</em> represents a single Node in a Local Area Network (LAN).
 * Several types of Nodes exist.
 */
public class Node {
    // enumeration constants specifying all legal node types
    /**
     * A node with type NODE has only basic functionality.
     */
    public static final byte NODE = 0;
    /**
     * A node with type WORKSTATION may initiate requests on the LAN.
     */
    public static final byte WORKSTATION = 1;
    /**
     * A node with type PRINTER may accept packages to be printed.
     */
    public static final byte PRINTER = 2;

    /**
     * Holds the type of the Node.
     */
    public byte type;
    /**
     * Holds the name of the Node.
     */
    public String name;
    /**
     * Holds the next Node in the token ring architecture.
     *
     * @see lanSimulation.internals.Node
     */
    public Node nextNode;

    /**
     * Construct a <em>Node</em> with given #type and #name.
     */
    public Node(byte _type, String _name) {
        assert (_type >= NODE) & (_type <= PRINTER);
        type = _type;
        name = _name;
        nextNode = null;
    }

    /**
     * Construct a <em>Node</em> with given #type and #name, and which is linked
     * to #nextNode.
     */
    public Node(byte _type, String _name, Node _nextNode) {
        assert (_type >= NODE) & (_type <= PRINTER);
        type = _type;
        name = _name;
        nextNode = _nextNode;
    }

}