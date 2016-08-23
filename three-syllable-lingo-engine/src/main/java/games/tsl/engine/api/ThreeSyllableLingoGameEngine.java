package games.tsl.engine.api;

/**
 * Interface that represents a three-syllable Lingo game. This interface acts a facade that manages the status and all
 * possible operations on the game.
 *
 * Created by Edwin on 21-8-2016.
 */
public interface ThreeSyllableLingoGameEngine {

    /**
     * Starts a new game, randomly choosing a new three-syllable word before setting this one as the current word in play.
     *
     * @return The initial state of a three-syllable word to play the game with. Never null.
     */
    ThreeSyllableLingoWordCharacter[] startNewGame();
}