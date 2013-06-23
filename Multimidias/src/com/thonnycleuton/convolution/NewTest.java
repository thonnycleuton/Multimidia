package com.thonnycleuton.convolution;

import javax.sound.sampled.Control;

import ddf.minim.*;
import processing.core.PApplet;

public class NewTest extends PApplet {
	Minim minim;
	AudioOutput out;
	AudioPlayer player;
	private Control[] controls;
	
	public void setup() {
		size(512, 200);
		minim = new Minim(this);
		out = minim.getLineOut();
		controls = out.getControls();
		textFont(createFont("Arial", 12));
	}
	
	public void draw() {
		background(0);
		for (int i=0; i < controls.length; i++){
			text("control" + (i+1) + "é um " + controls[i].toString() + ".", 5, 15 * i+15);
		}
	}

	public void stop() {
		// always close Minim audio classes when you are done with them
		player.close();
		minim.stop();
		super.stop();
	}
}