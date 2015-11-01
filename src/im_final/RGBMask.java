/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im_final;

import java.awt.image.BufferedImage;

/**
 *
 * @author Yas
 */
public class RGBMask {
    public  BufferedImage getRedChannelMask(BufferedImage Im) 
    {
        int imageWidth = Im.getWidth();
        int imageHeight = Im.getHeight();

        for (int x = 0; x < imageWidth; x++) 
        {
            for (int y = 0; y < imageHeight; y++) 
            {
                int rMask = getRed(Im.getRGB(x, y));               
                Im.setRGB(x, y, getRGBint(rMask, 0, 0));
            }
        }
return Im;
    }
    
    public BufferedImage getGreenChannelMask(BufferedImage Im) 
    {
        int imageWidth = Im.getWidth();
        int imageHeight = Im.getHeight();

        for (int x = 0; x < imageWidth; x++) 
        {
            for (int y = 0; y < imageHeight; y++) 
            {
                int gMask = getGreen(Im.getRGB(x, y));               
                Im.setRGB(x, y, getRGBint(0, gMask, 0));
            }
        }
return Im;
    }
    
    public  BufferedImage getBlueChannelMask(BufferedImage Im) 
    {
        int imageWidth = Im.getWidth();
        int imageHeight = Im.getHeight();

        for (int x = 0; x < imageWidth; x++) 
        {
            for (int y = 0; y < imageHeight; y++) 
            {
                int bMask = getBlue(Im.getRGB(x, y));               
                Im.setRGB(x, y, getRGBint(0, 0, bMask));
            }
        }
return  Im;
    }
    

    public static int getRed(int RGBint) 
    {
        return (RGBint & 0x00ff0000) >> 16;
    }
    
    public static int getGreen(int RGBint) 
    {
        return (RGBint & 0x0000ff00) >> 8;
    }
    
    public static int getBlue(int RGBint) 
    {
        return (RGBint & 0x000000ff);
    }

    public static int getRGBint(int R, int G, int B)
    {
        int RGBint = R;
        RGBint = (RGBint << 8) + G;
        RGBint = (RGBint << 8) + B;
        return RGBint;
    }
}
