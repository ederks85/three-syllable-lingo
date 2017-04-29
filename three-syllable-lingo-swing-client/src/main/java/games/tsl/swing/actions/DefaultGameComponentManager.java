package games.tsl.swing.actions;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.swing.panels.CommandPanel;
import games.tsl.swing.panels.LingoGamePanel;

import java.io.Serializable;

/**
 * Default implementation of a {@link GameComponentManager}. Nothing fancy (yet).
 */
public class DefaultGameComponentManager implements GameComponentManager, Serializable {

    private static final long serialVersionUID = -3070075146274596128L;

    private final transient ThreeSyllableLingoGameEngine threeSyllableLingoGameEngine;
    private final CommandPanel commandPanel;
    private final LingoGamePanel lingoGamePanel;

    public DefaultGameComponentManager(
            final ThreeSyllableLingoGameEngine threeSyllableLingoGameEngine,
            final CommandPanel commandPanel,
            final LingoGamePanel lingoGamePanel) {
        this.threeSyllableLingoGameEngine = threeSyllableLingoGameEngine;
        this.commandPanel = commandPanel;
        this.lingoGamePanel = lingoGamePanel;
    }

    @Override
    public ThreeSyllableLingoGameEngine getThreeSyllableLingoGameEngine() {
        return this.threeSyllableLingoGameEngine;
    }

    @Override
    public CommandPanel getCommandPanel() {
        return this.commandPanel;
    }

    @Override
    public LingoGamePanel getLingoGamePanel() {
        return this.lingoGamePanel;
    }

    public Object readResolve() {
        return null;
    }
}