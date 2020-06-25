package oop.ch06.generic.secured;

/**
 * This interface must be implemented by any client that is going to be
 * authorized by a challenge-response approach.
 *
 * @author Mark Minas
 */
public interface SecurityClient {
    /**
     * Returns the response for the specified challenge. Authorization succeeds
     * if this response is the same as the one expected by the server.
     *
     * @param challenge the integer number for which the correct response has to be
     *                  computed.
     */
    int challengeResponse(int challenge);
}
