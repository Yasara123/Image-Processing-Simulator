/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IM_Final;

/**
 *
 * @author Yas
 */
import java.awt.Color;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.imageio.metadata.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class Metadata {
    String data=new String();
    public void readAndDisplayMetadata( String fileName ) {
        try {
            data="<html>";
           
            File file = new File( fileName );
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

            if (readers.hasNext()) {

                // pick the first available ImageReader
                ImageReader reader = readers.next();

                // attach source to the reader
                reader.setInput(iis, true);

                // read metadata of first image
                IIOMetadata metadata = reader.getImageMetadata(0);
                
                String[] names = metadata.getMetadataFormatNames();
                int length = names.length;
                for (int i = 0; i < length; i++) {
                    System.out.println( "Format name: " + names[ i ] );
                    data=data.concat("Format name: " +names[ i ]+"<br>");
                    displayMetadata(metadata.getAsTree(names[i]));
                }
            }
            disp();
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void disp(){
       data=data.concat("</html>");
        System.out.println("Load Image");
            JFrame fr = new JFrame ("Image loading");
            fr.setSize(500,800);
            fr.setLocation(50,200);
            fr.setBackground(Color.lightGray);
            JLabel label1=new JLabel("Test", SwingConstants.RIGHT);
        
            label1.setText(data.toString());
            fr.add(label1);
            fr.show();
    }

    public void displayMetadata(Node root) {
        displayMetadata(root, 0);
    }

    public void indent(int level) {
        for (int i = 0; i < level; i++)
            System.out.print("    ");
            data=data.concat("  ");
    }

    public void displayMetadata(Node node, int level) {
        // print open tag of element
        indent(level);
        System.out.print("<" + node.getNodeName());
        data=data.concat( node.getNodeName()+" ");
        NamedNodeMap map = node.getAttributes();
        if (map != null) {

            // print attribute values
            int length = map.getLength();
            for (int i = 0; i < length; i++) {
                Node attr = map.item(i);
                System.out.print(" " + attr.getNodeName() +
                                 "=\"" + attr.getNodeValue() + "\"");
                data=data.concat(" " + attr.getNodeName() +"=\"" + attr.getNodeValue() + "\"");
            }
        }

        Node child = node.getFirstChild();
        if (child == null) {
            // no children, so close element and return
            System.out.println("/>");
            data=data.concat(" <br> ");
            return;
        }

        // children, so close current tag
        System.out.println(">");
        data=data.concat(" <br> ");
        while (child != null) {
            // print children recursively
            displayMetadata(child, level + 1);
            child = child.getNextSibling();
        }
        // print close tag of element
        indent(level);
        System.out.println("</" + node.getNodeName() + ">");
        data=data.concat("</" + node.getNodeName() + ">"+" <br> "); 
    }

}