/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package im_final;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Yas
 */
public class Haffman {
BufferedImage image;
    public Haffman(BufferedImage image)  {
        this.image=image;
        //Compress();
    }
    public  String getCodeBook() throws IOException{
       String CdBk=Compress(); 
      return CdBk;
    }
    public  String Compress() throws IOException{
         int grayLevelCounts[]=new int[256];
        long pixelCount=0;
        for (int i=0;i<image.getWidth();i++){
            for (int j=0;j<image.getHeight();j++){
                Color c=new Color(image.getRGB(i,j));
                grayLevelCounts[c.getRed()]++;
                pixelCount++;
            }
        }
        ArrayList<GrayLevel> levels = new ArrayList<>();
        for (int i=0;i<grayLevelCounts.length;i++){
            GrayLevel gl=new GrayLevel((float)grayLevelCounts[i]/(float)pixelCount,i);
            levels.add(gl);
        }
        ArrayList<GrayLevel> originals = new ArrayList<>(levels);

        while (levels.size() > 2) {
            Collections.sort(levels);
            GrayLevel lowest1 = levels.remove(0);
            GrayLevel lowest2 = levels.remove(0);

            GrayLevel newLevel = new GrayLevel(lowest1.probability + lowest2.probability);

            lowest1.setParent(newLevel);
            lowest1.setLeft(true);

            lowest2.setParent(newLevel);
            lowest2.setLeft(false);

            levels.add(newLevel);
        }
            BufferedWriter WriteHand=new BufferedWriter(new FileWriter("table.txt"));
             BufferedWriter WriteHand2=new BufferedWriter(new FileWriter("comp.txt"));
            //WriteHand.write(mydata_coded);
            //WriteHand.flush();
            //WriteHand.close();
        GrayLevel p = originals.get(4);
        while (p != null) {
            System.out.println(p.probability + ": " + p.getBlock());
            WriteHand.write(p.probability + ": " + p.getBlock()+" "+"\n");
            p = p.parent;
        }
        WriteHand.flush();
        WriteHand.close();
        Collections.sort(levels);

        levels.get(0).setLeft(true);
        levels.get(1).setLeft(false);

        //System.out.println(levels);
        String codeBook="";

        for (GrayLevel level : originals) {
            if (level.getLevel() >= 0) {
                codeBook+=level.getLevel()+":"+level.getBlock()+",";
            }
        }
        System.out.println("");
        System.out.println("-----------------CODEBOOK---------------------------------");
        System.out.println(codeBook);
        String coded="";
        for (int i=0;i<image.getHeight();i++){
            for (int j=0;j<image.getWidth();j++){
                Color c=new Color(image.getRGB(j,i));
                String blck=originals.get(c.getRed()).getBlock();
                coded+=blck;
            }
            coded+=";";
        }
        System.out.println("");
        System.out.println("-----------------Copressed Image--------------------------------");
        System.out.println(coded);
        WriteHand2.write(coded);
        WriteHand2.flush();
        WriteHand2.close();
        String output = codeBook+"\n\r"+coded;
        return output;
    }
    
}
 