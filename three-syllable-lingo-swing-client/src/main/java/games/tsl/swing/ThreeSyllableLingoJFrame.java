package games.tsl.swing;

import games.tsl.engine.LocalThreeSyllableLingoGameEngine;
import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;
import games.tsl.swing.actions.DefaultGameComponentManager;
import games.tsl.swing.actions.GameComponentManager;
import games.tsl.swing.actions.input.StartNewThreeSyllableWordGameAction;
import games.tsl.swing.panels.BannerPanel;
import games.tsl.swing.panels.CommandPanel;
import games.tsl.swing.panels.LingoGamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ThreeSyllableLingoJFrame extends JFrame {

    private final BannerPanel bannerPanel;
    private final LingoGamePanel lingoGamePanel;
    private final CommandPanel commandPanel;

    // Transient because we don't want to serialize the whole engine. Code quality was complaining about this.
    private final transient ThreeSyllableLingoGameEngine threeSyllableLingoGameEngine;

    private final GameComponentManager gameComponentManager;

    private ThreeSyllableLingoJFrame() throws ThreeSyllableLingoGameException, IOException {
        this.threeSyllableLingoGameEngine = new LocalThreeSyllableLingoGameEngine();

        this.setLayout(new BorderLayout());

        // Setup layout
        this.bannerPanel = new BannerPanel();
        this.add(this.bannerPanel, BorderLayout.NORTH);

        this.lingoGamePanel = new LingoGamePanel();
        this.add(this.lingoGamePanel, BorderLayout.CENTER);

        this.commandPanel = new CommandPanel();
        this.add(this.commandPanel, BorderLayout.SOUTH);

        // Setup the initial state of the game
        this.gameComponentManager = new DefaultGameComponentManager(this.threeSyllableLingoGameEngine, this.commandPanel, this.lingoGamePanel);

        final StartNewThreeSyllableWordGameAction startNewThreeSyllableWordGameAction = new StartNewThreeSyllableWordGameAction(this.gameComponentManager);
        this.commandPanel.setInputButtonAction(startNewThreeSyllableWordGameAction);

        // Make the game UI visible
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ThreeSyllableLingoJFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}