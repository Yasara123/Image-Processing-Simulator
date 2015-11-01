package im_final;


import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Norm extends JFrame {

	String imageName ;

	BufferedImage sourceImage = null;
	String sWidth, sHeight;
    Normalization norm;
    File sourceFile, savedFile;
	
	public Norm(String d){
            ImageUtilities util= new ImageUtilities(d);
          imageName = util.getImageName();         
		norm= new Normalization(d);
		//hist= new HistogramEqualizerDemo(d);
        sourceImage = ImageUtilities.getBufferedImage(d, this);
		
	    sWidth = Integer.toString(sourceImage.getWidth());
	    sHeight = Integer.toString(sourceImage.getHeight());
	    
        savedFile = new File("Normalized.jpg");
        try {
            ImageIO.write(norm.getNormalizedImage(), "jpeg", savedFile);
        } catch (Exception e) {
        }
        BufferedImage normlz=norm.getNormalizedImage();
	ImageView imageView2 = new ImageView();
        imageView2.drawImage(normlz); 	
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
    
    public String getImagename() {
		return imageName;
	}
}
