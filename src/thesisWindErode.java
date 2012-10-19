import peasy.PeasyCam;
import processing.core.*;
import toxi.processing.ToxiclibsSupport;


public class thesisWindErode extends processing.core.PApplet {
	private static final long serialVersionUID = 1L;

	// General
	ToxiclibsSupport gfx;
	PeasyCam nav; // cam
	PFont font;


	// Boolean
	boolean paused = false;
	

	public void setup() {
		size(1280, 720, OPENGL);
		smooth();
		frameRate(30);

		font = createFont("Arial Bold", 48);
		gfx = new ToxiclibsSupport(this);

		nav = new PeasyCam(this, 500);
		nav.setMinimumDistance(-50);

	}
	
	public void update() {

	}

	public void draw() {
		background(50);

		if (!paused)
			update();


		nav.beginHUD();
		fill(0);
		text(frameRate, 20, 20);
		nav.endHUD();
	}
	
}
