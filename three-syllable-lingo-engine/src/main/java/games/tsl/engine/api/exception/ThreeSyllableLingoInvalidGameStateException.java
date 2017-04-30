package games.tsl.engine.api.exception;

import org.apache.commons.lang3.Validate;

/**
 * Exception that can be thrown when an invalid action occurs in a three-syllable-lingo game that can be handled by the client.
 */
public class ThreeSyllableLingoInvalidGameStateException extends Exception {

    public enum Reason {CURRENTLY_NO_WORD_IN_PLAY}

    private final Reason reason;

    public ThreeSyllableLingoInvalidGameStateException(final Reason reason) {
        Validate.notNull(reason, "Reason is null");
        this.reason = reason;
    }
}