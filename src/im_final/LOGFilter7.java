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
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class LOGFilter7  {
//extends Component implements ActionListene
    int opIndex;
    private BufferedImage bi, biFiltered, biFiltered2;
    int w, h;
    
 
    public static final float[] MaskGaussian2= { // sharpening filter kernel
        1.f, 1.f,  2.f,2.f,  2.f,1.f,1.f,  
        1.f, 3.f, 4.f,5.f, 4.f,3.f,1.f,  
        2.f, 4.f, 7.f, 8.f,7.f,4.f,2.f,  
        2.f, 5.f, 8.f,10.f, 8.f,5.f,5.f,  
        2.f, 4.f, 7.f, 8.f,7.f,4.f,2.f,   
        1.f, 3.f, 4.f,5.f, 4.f,3.f,1.f,  
        1.f, 1.f,  2.f,2.f,  2.f,1.f,1.f
    };
    public static final float[] MaskLaplacianHigh= { // sharpening filter kernel
        -1.f, -1.f,  -1.f,-1.f,  -1.f,-1.f,  -1.f,
        -1.f, -1.f,  -1.f,-1.f,  -1.f,-1.f,  -1.f,
        -1.f, -1.f,  -1.f,-1.f,  -1.f,-1.f,  -1.f,
        -1.f, -1.f,  -1.f, 48.f, -1.f, -1.f,-1.f,
        -1.f, -1.f,  -1.f,-1.f,  -1.f,-1.f,  -1.f,
        -1.f, -1.f,  -1.f,-1.f,  -1.f,-1.f,  -1.f,
        -1.f, -1.f,  -1.f,-1.f,  -1.f,-1.f,  -1.f
    };
 String d;
    public LOGFilter7(String Path) {
        d=Path;
        System.out.println("Mask Called");
        try {
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



    int lastOp;
    public void filterImage() {
        System.out.println("Filtered Image");
        BufferedImageOp op1 = null;
        BufferedImageOp op2 = null;
        float[] data;
        
       
            data=MaskLaplacianHigh;
            op1 = new ConvolveOp(new Kernel(7, 7, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
     
            data=MaskGaussian2 ;
            for (int i = 0; i < data.length; i++) {
            data[i]=data[i]/170;
        }
            op2 = new ConvolveOp(new Kernel(7, 7, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);

        biFiltered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
         biFiltered2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        op2.filter(bi, biFiltered);
        op1.filter(biFiltered, biFiltered2);
        
        ImLoad();
    }

    public void ImLoad(){
            JFrame fr = new JFrame ("LOG Filtered Image");
            imageLoad Canvas3;
            fr.setSize(500,500);
            fr.setLocation(50,200);
            fr.setBackground(Color.lightGray);
            fr.setLayout(new FlowLayout());
            Canvas3 = new imageLoad(null);
            Canvas3.setSize(1000,1000);
            fr.add(Canvas3);
            Canvas3.setImage(biFiltered2);
            Canvas3.repaint();  
            fr.show();
        
    }
    /*
        public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedImage RGBimage = ImageIO.read(new File("test2.jpg"));;
        LOGFilter7 nw=new LOGFilter7("test2.jpg");
        nw.filterImage();
       
    }*/
}
