package games.tsl.swing.panels;

import games.tsl.swing.actions.WindowCloseAction;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Created by Edwin on 21-8-2016.
 */
public class CommandPanel extends JPanel {

    private final JTextField inputField;
    private final JButton quitButton;

    public CommandPanel() {
        this.setBackground(Color.RED);

        this.inputField = new JTextField();
        this.inputField.setPreferredSize(new Dimension(400, 50));
        this.add(this.inputField);

        this.quitButton = new JButton(new WindowCloseAction());
        this.quitButton.setPreferredSize(new Dimension(200, 50));
        this.add(this.quitButton);
    }
}