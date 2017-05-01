package games.tsl.swing.actions.input;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.swing.actions.GameComponentManager;
import org.apache.commons.lang3.Validate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;

/**
 * Action that can be used by a user to a new game of three-syllable lingo.
 */
public class StartNewThreeSyllableWordGameAction extends AbstractAction {

    private final GameComponentManager gameComponentManager;

    public StartNewThreeSyllableWordGameAction(final GameComponentManager gameComponentManager) {
        this(gameComponentManager, true);
    }

    public StartNewThreeSyllableWordGameAction(final GameComponentManager gameComponentManager, final boolean enabled) {
        Validate.notNull(gameComponentManager, "gameComponentManager is null");
        this.gameComponentManager = gameComponentManager;

        this.putValue(Action.NAME, "Start");
        this.setEnabled(enabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StartNewThreeSyllableWordGameAction.this.gameComponentManager.lockGUI();

        final SwingWorker<ThreeSyllableLingoWordCharacter[], Void> guessWorker = new SwingWorker<ThreeSyllableLingoWordCharacter[], Void>() {
            @Override
            protected ThreeSyllableLingoWordCharacter[] doInBackground() throws Exception {
                return StartNewThreeSyllableWordGameAction.this.gameComponentManager.getThreeSyllableLingoGameEngine().startNewGame();
            }

            @Override
            protected void done() {
                try {
                    final ThreeSyllableLingoWordCharacter[] guessResult = get();

                    StartNewThreeSyllableWordGameAction.this.gameComponentManager.getLingoGamePanel().reset();

                    StartNewThreeSyllableWordGameAction.this.gameComponentManager.getLingoGamePanel().addGuessForThreeSyllableWord(guessResult);

                    final AddGuessForThreeSyllableWordAction addGuessForThreeSyllableWordAction = new AddGuessForThreeSyllableWordAction(StartNewThreeSyllableWordGameAction.this.gameComponentManager);
                    StartNewThreeSyllableWordGameAction.this.gameComponentManager.getCommandPanel().setInputButtonAction(addGuessForThreeSyllableWordAction);

                    StartNewThreeSyllableWordGameAction.this.gameComponentManager.unlockGUI();
                } catch (InterruptedException | ExecutionException e) {
                    StartNewThreeSyllableWordGameAction.this.gameComponentManager.lockGUI();
                    e.printStackTrace();
                }
            }
        };
        guessWorker.execute();
    }
}