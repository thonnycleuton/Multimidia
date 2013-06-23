package com.thonnycleuton.RGB;

import controlP5.ColorPicker;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;

public class HSV extends PApplet {

   ControlP5 cp5;
   ColorPicker cp;
   private PImage imagem;

   public void setup() {
       imagem = loadImage("abstract.jpg");
       size(imagem.width, imagem.height + 60);
       noStroke();
       cp5 = new ControlP5(this);
       cp = cp5.addColorPicker("picker").setPosition(10, imagem.height+2);
   }

   public void draw() {
       image(imagem, 0, 0);
       loadPixels();
       for (int x = 0; x < imagem.width; x++) {
           for (int y = 0; y < imagem.height; y++) {

               int loc = x + y * imagem.width;

               float r = red(pixels[loc]);
               float g = green(pixels[loc]);
               float b = blue(pixels[loc]);
               float a = alpha(pixels[loc]);

               int c = color(r * cp.getArrayValue()[0] / 255,
                       g * cp.getArrayValue()[1] / 255, 
                       b * cp.getArrayValue()[2] / 255,
                       a * cp.getArrayValue()[3] / 255);
               pixels[loc] = c;
           }
       }
       updatePixels();
   }

   public void controlEvent(ControlEvent c) {
       // when a value change from a ColorPicker is received, extract the ARGB
       // values
       // from the controller's array value
       if (c.isFrom(cp)) {
           int r = (int) c.getArrayValue(0);
           int g = (int) c.getArrayValue(1);
           int b = (int) c.getArrayValue(2);
           int a = (int) c.getArrayValue(3);
           int col = color(r, g, b, a);
       }
   }
}