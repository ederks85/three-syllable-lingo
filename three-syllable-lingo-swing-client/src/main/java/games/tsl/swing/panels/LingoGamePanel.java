package games.tsl.swing.panels;

import games.tsl.engine.api.ThreeSyllableLingoWordCharacter;
import games.tsl.swing.renderers.ThreeSyllableLingoListCellRenderer;

import javax.swing.*;
import java.awt.*;

public class LingoGamePanel extends JPanel {

    private final JList<ThreeSyllableLingoWordCharacter[]> threeSyllableLingoWordsList;
    private final DefaultListModel<ThreeSyllableLingoWordCharacter[]> listModel;

    public LingoGamePanel() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        this.listModel = new DefaultListModel<>();

        this.threeSyllableLingoWordsList = new JList<>();
        this.threeSyllableLingoWordsList.setEnabled(false);
        this.threeSyllableLingoWordsList.setModel(listModel);
        this.threeSyllableLingoWordsList.setCellRenderer(new ThreeSyllableLingoListCellRenderer());

        final Dimension listDimenson = new Dimension(800, 0);
        this.threeSyllableLingoWordsList.setPreferredSize(listDimenson);
        this.threeSyllableLingoWordsList.setMaximumSize(listDimenson);

        this.setLayout(new GridBagLayout());
        final GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.VERTICAL;
        this.add(this.threeSyllableLingoWordsList, gc);
    }

    public void addGuessForThreeSyllableWord(final ThreeSyllableLingoWordCharacter[] guess) {
        this.listModel.addElement(guess);
    }
}