package games.tsl.swing.panels;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Edwin on 21-8-2016.
 */
public class BannerPanel extends JPanel {

    private final transient BufferedImage backgroundImage;

    public BannerPanel() throws IOException{
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1015, 164));

        this.backgroundImage = ImageIO.read(ClassLoader.getSystemResource( "logo.png" ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final Rectangle currentWindowBounds = SwingUtilities.getRoot(this).getBounds();
        final int paintStartPosition = Double.valueOf(currentWindowBounds.getWidth() / 2).intValue() - (this.backgroundImage.getWidth() / 2);
        g.drawImage(this.backgroundImage, paintStartPosition, 0, null);
    }
}