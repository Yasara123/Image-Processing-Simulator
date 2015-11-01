/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im_final;

/**
 *
 * @author Yas
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ImageFrame extends JFrame {
    BufferedImage image;

    public ImageFrame(final BufferedImage image) {
        this(image, "No Title");
    }

    public ImageFrame(final BufferedImage image, final String title) {
        this.image = image;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (image != null) {
                    setSize(image.getWidth(null), image.getHeight(null));
                } else {
                    setSize(250, 90);
                }
                setTitle(title);
                setVisible(true);
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        if (image == null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 250, 90);
            System.out.println("image null");
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.RED);
            g.drawString("Invalid or No Image", 10, 50);
        } else {
            g.drawImage(image, 0, 0, null);
        }
    }
}
