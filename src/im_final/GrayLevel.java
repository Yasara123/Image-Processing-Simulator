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
public class GrayLevel implements Comparable<GrayLevel> {

            Float probability;
            int level = -1;
            GrayLevel parent;
            boolean left = true;

            public void setLeft(boolean left) {
                this.left = left;
            }

            public void setParent(GrayLevel parent) {
                this.parent = parent;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getLevel() {
                return level;
            }

            public String getBlock() {
                return (parent != null ? parent.getBlock() : "") + (left ? "1" : "0");
            }

            public GrayLevel(float probability) {
                this.probability = probability;
            }

            public GrayLevel(float probability, int level) {
                this.probability = probability;
                this.level = level;
            }

            @Override
            public String toString() {
                return this.level + ":" + this.probability + ":" + this.getBlock();
            }

            @Override
            public int compareTo(GrayLevel o) {
                return this.probability.compareTo(o.probability);
            }
        }
