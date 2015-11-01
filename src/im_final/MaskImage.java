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

public class MaskImage  {
//extends Component implements ActionListener
    String descs[] = {
    "Original", 
        "Convolve : LowPass",
        "Convolve : Sharpen", 
        "LookupOp",
    };

    int opIndex;
    private BufferedImage bi, biFiltered;
    int w, h;
    
    public static final float[] SHARPEN3x3 = { // sharpening filter kernel
        0.f, 1.f,  0.f,
       1.f,  -4.f, 1.f,
        0.f, 1.f,  0.f
    };
    public static final float[] Mask13x3 = { // sharpening filter kernel
        1.f, 1.f,  1.f,
        1.f,  1.f, 1.f,
        1.f, 1.f,  1.f
    };
    public static final float[] Mask23x3 = { // sharpening filter kernel
        1.f, 1.f,  1.f,
        1.f,  2.f, 1.f,
        1.f, 1.f,  1.f
    };
    public static final float[] Mask33x3 = { // sharpening filter kernel
        1.f, 2.f,  1.f,
        2.f,  4.f, 2.f,
        1.f, 2.f,  1.f
    };
    public static final float[] Mask43x3 = { // sharpening filter kernel
        -1.f, 0.f,  1.f,
        -1.f,  0.f, 1.f,
        -1.f, 0.f,  1.f
    };
    public static final float[] Mask53x3 = { // sharpening filter kernel
        1.f, 1.f,  1.f,
        0.f, 0.f, 0.f,
        -1.f, -1.f,  -1.f
    };
    public static final float[] Mask63x3 = { // sharpening filter kernel
        0.f, 1.f,  0.f,
        1.f,  -4.f, 1.f,
        0.f, 1.f,  0.f
    };
    public static final float[] BLUR3x3 = {
        0.1f, 0.1f, 0.1f,    // low-pass filter kernel
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };
 String d;
    public MaskImage(String Path) {
        d=Path;
        System.out.println("Mask Called");
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
        filterImage(1);
        g.drawImage(biFiltered, 0, 0, null);
    }

    int lastOp;
    public void filterImage(int OPi) {
        System.out.println("Filtered Image");
        BufferedImageOp op = null;
        opIndex = OPi;
        float[] data;
        System.out.println(OPi);
        switch (opIndex) {

        case 0: biFiltered = bi; /* original */
                return; 
        case 1:  /* low pass filter */
            data=Mask13x3 ;
            op = new ConvolveOp(new Kernel(3, 3, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
            break;
        case 2:  /* sharpen */
            data=Mask23x3 ;
            op = new ConvolveOp(new Kernel(3, 3, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
            break;
        case 3:  /* low pass filter */
            data=Mask33x3 ;
            op = new ConvolveOp(new Kernel(3, 3, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
            break;
        case 4:  /* sharpen */
            data=Mask43x3 ;
            op = new ConvolveOp(new Kernel(3, 3, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
            break;
        case 5:  /* low pass filter */
            data=Mask53x3 ;
            op = new ConvolveOp(new Kernel(3, 3, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
            break;
        case 6:  /* sharpen */
            data=Mask63x3 ;
            op = new ConvolveOp(new Kernel(3, 3, data),
                                ConvolveOp.EDGE_NO_OP,
                                null);
            break;

        case 7 : /* lookup */
            byte lut[] = new byte[256];
            for (int j=0; j<256; j++) {
                lut[j] = (byte)(256-j); 
            }
            ByteLookupTable blut = new ByteLookupTable(0, lut); 
            op = new LookupOp(blut, null);
            break;
        }

        biFiltered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        op.filter(bi, biFiltered);
        ImLoad();
    }

    public void ImLoad(){
        System.out.println("Load Image");
            JFrame fr = new JFrame ("Image loading");
            imageLoad Canvas3;
            fr.setSize(500,500);
            fr.setLocation(50,200);
            fr.setBackground(Color.lightGray);
            fr.setLayout(new FlowLayout());
            Canvas3 = new imageLoad(null);
            Canvas3.setSize(1000,1000);
            fr.add(Canvas3);
            Canvas3.setImage(biFiltered);
            Canvas3.repaint();  
            fr.show();
        
    }
}
