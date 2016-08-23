package games.tsl.engine.model;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import org.apache.commons.lang3.Validate;

/**
 * Immutable implementation of {@link ThreeSyllableLingoWordCharacter};
 *
 * Created by Edwin on 23-8-2016.
 */
public class ImmutableThreeSyllableLingoWordCharacter implements ThreeSyllableLingoWordCharacter {

    private final ThreeSyllableLingoWordCharacterGuessStatus status;
    private final char character;

    public ImmutableThreeSyllableLingoWordCharacter(ThreeSyllableLingoWordCharacterGuessStatus status, char character) {
        Validate.notNull(status);

        this.status = status;
        this.character = character;
    }

    @Override
    public ThreeSyllableLingoWordCharacterGuessStatus getStatus() {
        return this.status;
    }

    @Override
    public char getCharacter() {
        return this.character;
    }
}