package im_final;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.*;
public class Normalization extends JFrame{
	private static final long serialVersionUID = -4834892318628515297L;
    
    private static BufferedImage normalizedImage; //grey image of input image
    BufferedImage sourceImage = null;
    String d;
    File sourceFile, savedFile;
	public Normalization(String d) //throws Exception
    {  this.d=d;
        sourceImage = ImageUtilities.getBufferedImage(d, this);
             
        int[][] greyScale = RGBToGrey(sourceImage);
        greyScale = normalizeMagic(greyScale,sourceImage.getWidth(),sourceImage.getHeight());
        normalizedImage = makeNewBufferedImage(greyScale, sourceImage.getWidth(), sourceImage.getHeight());
        
        savedFile = new File("tem.jpg");
        try {
            ImageIO.write(normalizedImage, "jpeg", savedFile);
        } catch (Exception e) {
        }
    }

	public int[][] RGBToGrey(BufferedImage source){
		int greyScale[][] = new int[source.getWidth()][source.getHeight()];
		for(int x=0; x<source.getWidth(); x++){
			for(int y=0; y<source.getHeight(); y++){
				int c = source.getRGB(x, y);
				float r = (c&0x00ff0000)>>16;
				float g = (c&0x0000ff00)>>8;
				float b = c&0x000000ff;
				greyScale[x][y] = (int)(0.3*r + 0.59*g + 0.11*b);
			}
		}
		return greyScale;
	}
	

	public int findMinimum(int[][] input, int width, int height){
		int min = input[0][0];
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				int n = input[x][y];
				if(n<min){
					min = n;
				}
			}
		}
		return min;
	}
	public int findMaximum(int[][] input, int width, int height){
		int max = input[0][0];
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				int n = input[x][y];
				if(n>max){
					max = n;
				}
			}
		}
		return max;
	}
   public int[][] normalizeMagic(int[][] input, int width, int height){
	   int[][] output = new int[width][height];
	   int a=0, b=255;
	   int c = findMaximum(input,width,height)-findMinimum(input,width,height);
	   for(int x=0; x<width; x++){
		   for(int y=0; y<height; y++){
			   a = (input[x][y])-findMinimum(input,width,height);
			   int e =b/c;
			   output[x][y] = e*a;
		   }
	   }
	   return output;
   }
   
   

   public BufferedImage makeNewBufferedImage(int[][] gs, int width, int height){
	   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	   int[] iArray = {0,0,0,255};
	   WritableRaster r = image.getRaster();
	   for(int x=0; x<width; x++){
		   for(int y=0; y<height; y++){
			   int v = gs[x][y];
			   iArray[0] = v;
			   iArray[1] = v;
			   iArray[2] = v;
			   r.setPixel(x, y, iArray);
		   }
	   }
	   image.setData(r);
	   return image;
   }

   public static BufferedImage getNormalizedImage() {
		return normalizedImage;
	}

}
