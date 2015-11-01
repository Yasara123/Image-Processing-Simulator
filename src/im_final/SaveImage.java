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


import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class SaveImage extends Component implements ActionListener {

    String descs[] = {
    "Original", 
      
    };

    int opIndex;
    private BufferedImage bi, biFiltered;
    int w, h;
    
    public void SaveAsImage(String Path) {
        File saveFile = new File(Path);
        String format;
        if(saveFile.getAbsolutePath().contains(".jpg"))
                format=".jpg";
            else if(saveFile.getAbsolutePath().contains(".jpeg"))
                format=".jpeg";
            else if(saveFile.getAbsolutePath().contains(".bmp"))
                format=".bmp";
            else 
                format=".jpg";
        try {//path="C:\\Users\\Yas\\Desktop\\dex\\tumblr_m8z0o8FYNo1qm9z7ro1_500.jpg"     
            bi = ImageIO.read(new File(Path));
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 =
                    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(bi, 0, 0, null);
                
            }
        } catch (IOException e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
        try {
                     ImageIO.write(bi,format, saveFile);
                 } catch (IOException ex) {
                 }
    }
public SaveImage(String Path) {
        JFrame f = new JFrame("Save Image Sample");
        f.addWindowListener(new WindowAdapter() {
          
        });
       
        f.add("Center", this);
        JComboBox choices = new JComboBox(this.getDescriptions());
        choices.setActionCommand("SetFilter");
        choices.addActionListener(this);
        JComboBox formats = new JComboBox(this.getFormats());
        formats.setActionCommand("Formats");
        formats.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(choices);
        panel.add(new JLabel("Save As"));
        panel.add(formats);
        f.add("South", panel);
        f.pack();
        f.setVisible(true);
        
        
        try {//path="C:\\Users\\Yas\\Desktop\\dex\\tumblr_m8z0o8FYNo1qm9z7ro1_500.jpg"
            bi = ImageIO.read(new File(Path));
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 =
                    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(bi, 0, 0, null);
                biFiltered = bi = bi2;
            }
        } catch (IOException e) {
            System.out.println("Image could not be read");
            System.exit(1);
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    String[] getDescriptions() {
        return descs;
    }

    void setOpIndex(int i) {
        opIndex = i;
    }

    public void paint(Graphics g) {
        filterImage();
        g.drawImage(biFiltered, 0, 0, null);
    }

    int lastOp;
    public void filterImage() {
        BufferedImageOp op = null;

        if (opIndex == lastOp) {
            return;
        }
        lastOp = opIndex;
        switch (opIndex) {

        case 0: biFiltered = bi; /* original */
                return; 
        case 1:  /* low pass filter */
     
        }
        biFiltered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        op.filter(bi, biFiltered);
    }

    /* Return the formats sorted alphabetically and in lower case */
    public String[] getFormats() {
        String[] formats = ImageIO.getWriterFormatNames();
        TreeSet<String> formatSet = new TreeSet<String>();
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
        }
        return formatSet.toArray(new String[0]);
    }


     public void actionPerformed(ActionEvent e) {
         JComboBox cb = (JComboBox)e.getSource();
         if (cb.getActionCommand().equals("SetFilter")) {
             setOpIndex(cb.getSelectedIndex());
             repaint();
         } else if (cb.getActionCommand().equals("Formats")) {
             String format = (String)cb.getSelectedItem();
             File saveFile = new File("savedimage."+format);
             JFileChooser chooser = new JFileChooser();
             chooser.setSelectedFile(saveFile);
             int rval = chooser.showSaveDialog(cb);
             if (rval == JFileChooser.APPROVE_OPTION) {
                 saveFile = chooser.getSelectedFile();
                 try {
                     ImageIO.write(biFiltered, format, saveFile);
                 } catch (IOException ex) {
                 }
             }
         }
    }
}
