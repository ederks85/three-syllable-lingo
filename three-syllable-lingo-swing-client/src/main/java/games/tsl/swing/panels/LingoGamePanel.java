package games.tsl.swing.panels;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import java.awt.Color;

/**
 * Created by Edwin on 21-8-2016.
 */
public class LingoGamePanel extends JPanel {

    private final JList<String> threeSyllableLingoWordsList;

    public LingoGamePanel(final String initialValue) {
        this.setBackground(Color.BLUE);

        final DefaultListModel listModel = new DefaultListModel();
        listModel.addElement(initialValue);

        this.threeSyllableLingoWordsList = new JList<>();
        this.threeSyllableLingoWordsList.setEnabled(false);
        this.threeSyllableLingoWordsList.setModel(listModel);
        this.add(this.threeSyllableLingoWordsList);
    }
}