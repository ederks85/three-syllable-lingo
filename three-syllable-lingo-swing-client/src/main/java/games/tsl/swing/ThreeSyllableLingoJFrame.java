package games.tsl.swing;

import games.tsl.engine.api.ThreeSyllableLingoGameEngine;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.engine.api.ThreeSyllableLingoWordCharacterGuessStatus;
import games.tsl.engine.api.exception.ThreeSyllableLingoGameException;
import games.tsl.engine.model.LocalThreeSyllableLingoGameEngine;
import games.tsl.swing.panels.BannerPanel;
import games.tsl.swing.panels.CommandPanel;
import games.tsl.swing.panels.LingoGamePanel;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.IOException;

/**
 * Created by Edwin on 21-8-2016.
 */
public class ThreeSyllableLingoJFrame extends JFrame {

    private BannerPanel bannerPanel;
    private LingoGamePanel lingoGamePanel;
    private CommandPanel commandPanel;

    // Transient because we don't want to serialize the whole engine. Code quality was complaining about this.
    private transient ThreeSyllableLingoGameEngine threeSyllableLingoGameEngine;

    public ThreeSyllableLingoJFrame() throws ThreeSyllableLingoGameException, IOException {
        this.threeSyllableLingoGameEngine = new LocalThreeSyllableLingoGameEngine();

        this.setLayout(new BorderLayout());

        this.bannerPanel = new BannerPanel();
        this.add(this.bannerPanel, BorderLayout.NORTH);

        final ThreeSyllableLingoWordCharacter[] initialThreeSyllableWord = this.threeSyllableLingoGameEngine.startNewGame();
        this.lingoGamePanel = new LingoGamePanel(initialThreeSyllableWord);
        this.add(this.lingoGamePanel, BorderLayout.CENTER);

        this.commandPanel = new CommandPanel();
        this.add(this.commandPanel, BorderLayout.SOUTH);

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