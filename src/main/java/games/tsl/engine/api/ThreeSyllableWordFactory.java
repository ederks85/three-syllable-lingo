package games.tsl.engine.api;

/**
 * Factory class that defines methods for creating {@link ThreeSyllableWord}s.
 *
 * Created by Edwin on 21-8-2016.
 */
public interface ThreeSyllableWordFactory {

    /**
     * @return A random {@link ThreeSyllableWord}, never null.
     */
    ThreeSyllableWord createRandomThreeSyllableWord();
}