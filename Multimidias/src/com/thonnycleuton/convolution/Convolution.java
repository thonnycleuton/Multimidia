package com.thonnycleuton.convolution;

import processing.core.PApplet;
import ddf.minim.*;
import javax.sound.sampled.*;

public class Convolution extends PApplet {
AudioSample audioIn;
AudioSample audioOut;
AudioFormat formato;
Minim minim;

private float[] arrayIn;
private float[] arrayOut;

	public void setup() {
		size(600, 300);
		
		formato = new AudioFormat(44100, 16, 2, true, true);
		minim = new Minim(this);
		
		audioIn = minim.loadSample ("Thriller.mp3",100);
		arrayIn = audioIn.getChannel(AudioSample.LEFT);
		arrayOut = histograma(arrayIn);
		
		audioOut = minim.createSample(arrayIn, formato);
		audioOut.trigger();
	}

	public void draw() {
		background(0);
		stroke (255, 200);
		line(0, 50, audioOut.bufferSize()-1, 50);

		for (int i=0; i < audioOut.bufferSize()-1; i++){
//			line (i,250 + audioOut.left.get(i)*50, i, 50 + audioOut.left.get(i)*50);
		    line(i,50 + audioOut.left.get(i)*50, i, 50 + audioOut.left.get(i)*50);
		    line (i,250 + audioOut.right.get(i)*50, i+1, 250 + audioOut.right.get(i+1)*50);
		}
	}

	public float[] histograma(float[] arrayIn2) {
		float [] output = new float[1024];
		float aux=0;
		
		for (int i=0; i<arrayIn2.length; i++){
			aux = arrayIn2[i] * 100;
			if (aux <0){
				aux *= -1;
			}
			if (aux < width){
				for (int j = (int) aux; j >= 0; j--){
					output [j]++;
				}
			}
		}
		return output;
	}
}