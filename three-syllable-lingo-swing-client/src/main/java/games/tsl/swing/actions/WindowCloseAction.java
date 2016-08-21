package games.tsl.swing.actions;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * Created by Edwin on 21-8-2016.
 */
public class WindowCloseAction extends AbstractAction {

    public WindowCloseAction() {
        this.setEnabled(true);
        this.putValue(Action.NAME, "Quit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(
                SwingUtilities.getRoot((Component)e.getSource()),
                "Are you sure you want to quit?",
                "Leaving so soon?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            SwingUtilities.getWindowAncestor((Component)e.getSource()).dispose();
        }
    }
}