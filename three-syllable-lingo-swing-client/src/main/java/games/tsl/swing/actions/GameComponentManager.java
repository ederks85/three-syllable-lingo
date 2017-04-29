package games.tsl.swing.actions;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.swing.panels.CommandPanel;
import games.tsl.swing.panels.LingoGamePanel;

/**
 * Interface that provides access to the various components of the game GUI. Actions can use this interface to
 * access their the components they need, preventing them from receiving every component via their constructor.
 */
public interface GameComponentManager {

    /**
     * Access the game's {@link ThreeSyllableLingoGameEngine}.
     *
     * @return The {@link ThreeSyllableLingoGameEngine} being used, never null.
     */
    ThreeSyllableLingoGameEngine getThreeSyllableLingoGameEngine();

    /**
     * Access the game's {@link CommandPanel}.
     *
     * @return The {@link CommandPanel} being used, never null.
     */
    CommandPanel getCommandPanel();

    /**
     * Access the game's {@link LingoGamePanel}.
     *
     * @return The {@link LingoGamePanel} being used, never null.
     */
    LingoGamePanel getLingoGamePanel();
}