/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im_final;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Yas
 */
class imageLoad extends Canvas
{Image img;
public imageLoad(Image img)
{
this.img = img;
}
public void paint (Graphics g)
{
if (img != null)
{
g.drawImage(img,400,30,this);
}

}

public void setImage (Image img)
{
this.img = img;
}
}
