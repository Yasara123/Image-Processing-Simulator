/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im_final;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 *
 * @author Yas
 */
public class ImagesLoading implements ActionListener
{
JFrame fr = new JFrame ("Image loading");
JFrame fr2 = new JFrame ("Image Clone");
JFrame fr3 = new JFrame ("Image Mask RED");
JFrame fr4 = new JFrame ("Image Mask GREEN");
JFrame fr5 = new JFrame ("Image Mask BLUE");
Label Label1 = new Label("Choose your image");
Button Button1 = new Button("select");

Image Image1,image2;
BufferedImage RGBimage;
imageLoad Canvas1,Canvas2,Canvas3,Canvas4,Canvas5;
String d;
FileDialog fd = new FileDialog(fr,"Open", FileDialog.LOAD);
ChannelSplitter cl=new ChannelSplitter();

    public String getPath() {
      return d; 
    }

public enum Resizer {
    NEAREST_NEIGHBOR {
        @Override
        public BufferedImage resize(BufferedImage source,
                int width, int height) {
            return commonResize(source, width, height,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        }
    },
    BILINEAR {
        @Override
        public BufferedImage resize(BufferedImage source,
                int width, int height) {
            return commonResize(source, width, height,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    },
    BICUBIC {
        @Override
        public BufferedImage resize(BufferedImage source,
                int width, int height) {
            return commonResize(source, width, height,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        }
    };

    public abstract BufferedImage resize(BufferedImage source,
            int width, int height);

    private static BufferedImage progressiveResize(BufferedImage source,
            int width, int height, Object hint) {
        int w = Math.max(source.getWidth()/2, width);
        int h = Math.max(source.getHeight()/2, height);
        BufferedImage img = commonResize(source, w, h, hint);
        while (w != width || h != height) {
            BufferedImage prev = img;
            w = Math.max(w/2, width);
            h = Math.max(h/2, height);
            img = commonResize(prev, w, h, hint);
            prev.flush();
        }
        return img;
    }

    private static BufferedImage commonResize(BufferedImage source,int width, int height, Object hint) {
        BufferedImage img = new BufferedImage(width, height,
                source.getType());
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g.drawImage(source, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return img;
    }
};
void initialize ()
{
fr.setSize(500,500);
fr.setLocation(50,200);
fr.setBackground(Color.lightGray);
fr.setLayout(new FlowLayout());
fr2.setSize(500,500);
fr2.setLocation(50,200);
fr2.setBackground(Color.lightGray);
fr2.setLayout(new FlowLayout());
fr3.setSize(500,500);
fr3.setLocation(50,200);
fr3.setBackground(Color.lightGray);
fr3.setLayout(new FlowLayout());
fr4.setSize(500,500);
fr4.setLocation(50,200);
fr4.setBackground(Color.lightGray);
fr4.setLayout(new FlowLayout());
fr5.setSize(500,500);
fr5.setLocation(50,200);
fr5.setBackground(Color.lightGray);
fr5.setLayout(new FlowLayout());
  
fr.add(Label1);
fr.add(Button1);


fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


Canvas1 = new imageLoad(null);
Canvas1.setSize(1000,1000);
 ;
Canvas2 = new imageLoad(null);
Canvas2.setSize(1000,1000);

Canvas3 = new imageLoad(null);
Canvas3.setSize(1000,1000);
 
Canvas4 = new imageLoad(null);
Canvas4.setSize(1000,1000);
Canvas5 = new imageLoad(null);
Canvas5.setSize(1000,1000);
Button1.addActionListener(this);
fr.add(Canvas1);
fr2.add(Canvas2);
fr3.add(Canvas3);
fr4.add(Canvas4);
fr5.add(Canvas5);
fr.show();



}
public void actionPerformed(ActionEvent event)
{
Button b = (Button)event.getSource();
if(b == Button1)
{
imageload();
//new imageprocessing.Metadata().readAndDisplayMetadata(d);
}

}
void imageload ()
{
fd.show();
if(fd.getFile() == null)
{
Label1.setText("You have not select");
}
else
{
d = (fd.getDirectory() + fd.getFile());
    System.out.println("d is "+d);
Toolkit toolkit = Toolkit.getDefaultToolkit();
Image1 = toolkit.getImage(d);
Canvas1.setImage(Image1);
Canvas1.repaint();
}
}
public void imageClone()
{
image2=Image1.getScaledInstance(Image1.getWidth(fr), Image1.getHeight(fr),0);
Canvas2.setImage(image2);
Canvas2.repaint();
fr2.show();

}

public void ReSample(String type,String path,int rate)
{ 
if (type=="NearestNeighbour"){
    try {
            BufferedImage originalImage = ImageIO.read(new File(d));
            BufferedImage reImage;
            reImage = Resizer.commonResize(originalImage,originalImage.getWidth()*rate,originalImage.getHeight()*rate,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            ImageIO.write(reImage, "jpg", new File("ResizedImage.jpg"));
            ImageView imageView2 = new ImageView();
            imageView2.drawImage(reImage); 
        } catch (IOException ex) {
            Logger.getLogger(Resizer.class.getName()).log(Level.SEVERE, null, ex);
        }	
}
if (type=="BiLinear"){
    try {
            BufferedImage originalImage = ImageIO.read(new File(d));
            BufferedImage reImage;
            reImage = Resizer.commonResize(originalImage,originalImage.getWidth()*rate,originalImage.getHeight()*rate,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            ImageIO.write(reImage, "jpg", new File("ResizedImage.jpg"));
            ImageView imageView2 = new ImageView();
            imageView2.drawImage(reImage); 
        } catch (IOException ex) {
            Logger.getLogger(Resizer.class.getName()).log(Level.SEVERE, null, ex);
        }	
}
if (type=="BiCubic"){
     try {
            BufferedImage originalImage = ImageIO.read(new File(d));
            BufferedImage reImage;
            reImage = Resizer.commonResize(originalImage,originalImage.getWidth()*rate,originalImage.getHeight()*rate,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            ImageIO.write(reImage, "jpg", new File("ResizedImage.jpg"));
            ImageView imageView2 = new ImageView();
            imageView2.drawImage(reImage); 
        } catch (IOException ex) {
            Logger.getLogger(Resizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
public void RedMask(){
 BufferedImage RGBimage = null;
try {
    RGBimage = ImageIO.read(new File(d));
} catch (IOException e) {
    RGBimage=null;
}
RGBimage=new RGBMask().getRedChannelMask(RGBimage);
Canvas3.setImage(RGBimage);
Canvas3.repaint();  
fr3.show();
}

public void GreenMask(){
 BufferedImage RGBimage = null;
try {
    RGBimage = ImageIO.read(new File(d));
} catch (IOException e) {
    RGBimage=null;
}
RGBimage=new RGBMask().getGreenChannelMask(RGBimage);
Canvas4.setImage(RGBimage);
Canvas4.repaint(); 
fr4.show();
}
public void Histrogram(String path) throws IOException{
 JFrame frame = new JFrame("Generated Hitogram");
 frame.setSize(200, 200);
 frame.setLayout(new BorderLayout());
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 Histogram his = new Histogram();
 his.load(path);
frame.add(his,BorderLayout.CENTER);
frame.setVisible(true);
}

public void BlueMask(){
 BufferedImage RGBimage = null;
try {
    RGBimage = ImageIO.read(new File(d));
} catch (IOException e) {
    RGBimage=null;
}

RGBimage=new RGBMask().getBlueChannelMask(RGBimage);
Canvas5.setImage(RGBimage);
Canvas5.repaint(); 
fr5.show();
}
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
public void ImageSplit()
{
cl.run(d);
}
}
