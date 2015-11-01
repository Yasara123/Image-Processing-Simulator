package im_final;


import java.awt.*;
import java.awt.image.*;


public class ImageUtilities {

    String imageName ;//sourcr image
    public ImageUtilities(String d) {
        imageName = d;//sourcr image
    }

 

	
  public static BufferedImage getBufferedImage(String imageFile,
                                               Component c) {
    Image image = c.getToolkit().getImage(imageFile);
    waitForImage(image, c);
    BufferedImage bufferedImage =
      new BufferedImage(image.getWidth(c), image.getHeight(c),
                        BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = bufferedImage.createGraphics();
    g2d.drawImage(image, 0, 0, c);
    return(bufferedImage);
  }

  public static boolean waitForImage(Image image, Component c) {
    MediaTracker tracker = new MediaTracker(c);
    tracker.addImage(image, 0);
    try {
      tracker.waitForAll();
    } catch(InterruptedException ie) {}
    return(!tracker.isErrorAny());
  }

  /** Take some Images associated with files, and wait until they
   *  are done loading. Just a simple application of MediaTracker.
   */

  public static boolean waitForImages(Image[] images, Component c) {
    MediaTracker tracker = new MediaTracker(c);
    for(int i=0; i<images.length; i++)
      tracker.addImage(images[i], 0);
    try {
      tracker.waitForAll();
    } catch(InterruptedException ie) {}
    return(!tracker.isErrorAny());
  }

  public String getImageName() {
	return imageName;
  }
}