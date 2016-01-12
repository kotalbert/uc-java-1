package guimodule;

import processing.core.PApplet;
import processing.core.PImage;

public class VisualClock extends PApplet {
	private static final long serialVersionUID = 1L;
	
	PImage img;
	
	public void setup() {
		size(400,400);
		background(255);
		stroke(0);
		img = loadImage("https://upload.wikimedia.org/wikipedia/commons/6/61/Laguna_Beach_Heisler_Park.jpg");
		img.resize(0, height);
		image(img,0,0);
	}
	public void draw() {
		
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse((width*3)/4,height/5, width/4,height/5);
		
	}
	
	private int[] sunColorSec(float seconds) {
		int[] rgb = new int[3];
		float diff = Math.abs(30-seconds);
		float ratio = diff/30;
		rgb[0] = (int) (255*ratio);
		rgb[1] = (int) (255*ratio);
		rgb[2] = 0;
		return rgb;
	}

}
