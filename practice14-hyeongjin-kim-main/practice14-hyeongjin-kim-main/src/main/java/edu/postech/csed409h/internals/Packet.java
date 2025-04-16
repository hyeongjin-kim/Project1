package edu.postech.csed409h.internals;

/**
 * A <em>Packet</em> represents a unit of information to be sent over the Local
 * Area Network (LAN).
 */
public class Packet {
    /**
     * Holds the actual message to be send over the network.
     */
    public String message;
    /**
     * Holds the name of the Node which initiated the request.
     */
    public String origin;
    /**
     * Holds the name of the Node which should receive the information.
     */
    public String destination;

    /**
     * Construct a <em>Packet</em> with given #message and #destination.
     */
    public Packet(String _message, String _destination) {
        message = _message;
        origin = "";
        destination = _destination;
    }

    /**
     * Construct a <em>Packet</em> with given #message, #origin and #receiver.
     */
    public Packet(String _message, String _origin, String _destination) {
        message = _message;
        origin = _origin;
        destination = _destination;
    }

}