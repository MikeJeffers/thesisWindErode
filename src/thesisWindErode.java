import peasy.PeasyCam;
import processing.core.*;
import toxi.geom.*;
import toxi.processing.ToxiclibsSupport;

public class thesisWindErode extends processing.core.PApplet {
	private static final long serialVersionUID = 1L;

	// General
	public ToxiclibsSupport gfx;
	PeasyCam nav;
	PFont font;
	grid grid;

	pointStack tree;
	emitter wind;
	int TREE_SIZE = 500;

	// Boolean
	boolean paused = false;
	boolean drawTree = true;
	boolean drawGrid = true;

	public void setup() {
		size(1280, 720, OPENGL);
		smooth();
		frameRate(30);

		font = createFont("Arial Bold", 48);
		gfx = new ToxiclibsSupport(this);

		nav = new PeasyCam(this, 500);
		nav.setMinimumDistance(-50);

		grid = new grid(10, 10, 25, 25, this);

		tree = new pointStack(new Vec3D(-1, -1, 0).scaleSelf(TREE_SIZE / 2), TREE_SIZE, this);
		tree.populate(13, 7, 20, 10, 10, 10);
		
		wind = new emitter(new Vec3D(0,-1,0), new Vec3D(0, 300, 100), 25, this);

	}

	public void update() {
		tree.update();
		wind.update();
	}

	public void draw() {
		background(50);

		if (!paused)
			update();

		if (drawGrid)
			grid.drawGrid();

		if (drawTree)
			tree.draw();
		
		tree.drawPoints();
		
		wind.draw();

		nav.beginHUD();
		fill(0);
		text(frameRate, 20, 20);
		nav.endHUD();
	}

	public void keyPressed() {
		switch (key) {
		case ' ':
			paused = !paused;
			break;
		case 't':
			drawTree = !drawTree;
			break;

		case 'g':
			drawGrid = !drawGrid;
			break;
		}
	}

}
