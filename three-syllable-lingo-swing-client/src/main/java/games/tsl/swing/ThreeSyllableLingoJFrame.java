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

        //TODO In the future, convert input from engine nicely to Lingo model in LingoGamePanel
        final ThreeSyllableLingoWordCharacter[] initialThreeSyllableWord = this.threeSyllableLingoGameEngine.startNewGame();
        StringBuilder convertedValue = new StringBuilder();
        for (ThreeSyllableLingoWordCharacter threeSyllableLingoWordCharacter : initialThreeSyllableWord) {
            if (threeSyllableLingoWordCharacter.getStatus() == ThreeSyllableLingoWordCharacterGuessStatus.IN_PLACE) {
                convertedValue.append(String.valueOf(threeSyllableLingoWordCharacter.getCharacter()) + " ");
            } else {
                convertedValue.append(". ");
            }
        }

        this.lingoGamePanel = new LingoGamePanel(convertedValue.toString());
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