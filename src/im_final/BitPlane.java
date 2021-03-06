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
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.Raster;
public class BitPlane{
public BitPlane(String path){
BufferedImage img = null;
try{
img = ImageIO.read(new File(path));
}catch (IOException e){
e.printStackTrace();
}
int m = img.getWidth();
int n = img.getHeight();
double[][] matrix = new double[n][m];
int [][]pixels = new int[n*m][8];
String s=null;
int cnt=0;
for (int row = 0; row < n; ++row)
{
for (int col = 0; col < m; ++col)
{
Raster rst = img.getRaster();
int grayLevel = rst.getSample(col,row,0);
matrix[row][col] = grayLevel;
s=Integer.toBinaryString((int)matrix[row][col]);
int len = s.length()-1;
boolean b = false;
int len2 = 7;
if(len<7)
b = true;
for(int i=len;i>=0;i--){
char c = s.charAt(i);
int x = Character.getNumericValue(c);
if(b==false)
pixels[cnt][i] = x;
else if(b==true)
{
pixels[cnt][len2] = x;
len2--;
}
}
cnt++;
}
}
int bit0[],bit1[],bit2[],bit3[],bit4[],bit5[],bit6[],bit7[];
bit0 = new int[(n*m)];
bit1 = new int[(n*m)];
bit2 = new int[(n*m)];
bit3 = new int[(n*m)];
bit4 = new int[(n*m)];
bit5 = new int[(n*m)];
bit6 = new int[(n*m)];
bit7 = new int[(n*m)];
// BIT 7
for(int i=0;i<n*m;i++){
bit7[i] = pixels[i][0];
}
//BIT 6
for(int i=0;i<n*m;i++){
bit6[i] = pixels[i][1];
}
//BIT 5
for(int i=0;i<n*m;i++){
bit5[i] = pixels[i][2];
}
//BIT 4
for(int i=0;i<n*m;i++){
bit4[i] = pixels[i][3];
}
//BIT 3
for(int i=0;i<n*m;i++){
bit3[i] = pixels[i][4];
}
//BIT 2
for(int i=0;i<n*m;i++){
bit2[i] = pixels[i][5];
}
//BIT 1
for(int i=0;i<n*m;i++){
bit1[i] = pixels[i][6];
}
//BIT 0
for(int i=0;i<n*m;i++){
bit0[i] = pixels[i][7];
}
getImageFromArray(bit7,n,m,"Bit 7");
getImageFromArray(bit6,n,m,"Bit 6");
getImageFromArray(bit5,n,m,"Bit 5");
getImageFromArray(bit4,n,m,"Bit 4");
getImageFromArray(bit3,n,m,"Bit 3");
getImageFromArray(bit2,n,m,"Bit 2");
getImageFromArray(bit1,n,m,"Bit 1");
getImageFromArray(bit0,n,m,"Bit 0");
}
public void getImageFromArray(int[] pixels, int height, int width,String name){
BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
int k=0;
int data[]=new int[width*height];
int imData[][]= new int[height][width];

for(int i=0;i<height;i++){
for(int j=0;j<width;j++){
imData[i][j] = pixels[k];
k++;
}
}
k=0;
for(int i=0;i<height;i++){
for(int j=0;j<width;j++){
try{
if(imData[i][j]==1){
int r = 255;
int g = 255;
int b = 255;
data[k] = (255 << 24) | (r << 16) | (g << 8) | b;  //WHITE
}
else if(imData[i][j]==0)
{
int r = 0;

int g = 0;

int b = 0;
data[k] = (255 << 24) | (r << 16) | (g << 8) | b;  //BLACK
}
k++;
}
catch(Exception e){}
}
}
MemoryImageSource mis = new MemoryImageSource(width, height, data, 0, width);
Toolkit tk = Toolkit.getDefaultToolkit();
Image imgOut =  tk.createImage(mis);
Graphics2D g = im.createGraphics();
g.drawImage(imgOut, 0,0,null);
try
{ImageIO.write(im, "jpg", new File(name+".jpg"));
}
catch(IOException e ){
System.out.println("saveJpeg "+e);
}
}
}
