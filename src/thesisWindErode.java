import java.io.File;
import java.util.ArrayList;

import peasy.PeasyCam;
import processing.core.*;
import toxi.geom.*;
import toxi.geom.mesh.STLReader;
import toxi.geom.mesh.TriangleMesh;
import toxi.processing.ToxiclibsSupport;

public class thesisWindErode extends processing.core.PApplet {
	private static final long serialVersionUID = 1L;

	// General
	public ToxiclibsSupport gfx;
	PeasyCam nav;
	PFont font;
	grid grid;
	ArrayList<Vec3D> importPts = new ArrayList<Vec3D>();
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
		
		File f = new File("");
		try {
			
		f = new File(dataPath("import.txt"));
		
		} catch (NullPointerException ex) {
		println("File: " + " could not be found.");
		//return false;
		}

		String[] strLines = loadStrings(f.getAbsolutePath());
		
		//String[] strLines = loadStrings(dataPath("import.txt"));
		
		  for(int i = 0; i < strLines.length; i++){
			  String clean = strLines[i].substring(1, strLines[i].length()-1);
			  
			    String[] splitToken = clean.split(", ");      
			    
			    float xx = parseFloat(splitToken[0]);                     
			    float yy = parseFloat(splitToken[1]);                     
			    float zz = parseFloat(splitToken[2]);  
			    Vec3D ptt = new Vec3D (xx, yy, zz);
			    importPts.add(ptt);             
			  }
		

		font = createFont("Arial Bold", 48);
		gfx = new ToxiclibsSupport(this);

		nav = new PeasyCam(this, 500);
		nav.setMinimumDistance(-50);

		grid = new grid(10, 10, 25, 25, this);

		tree = new pointStack(new Vec3D(-1, -1, 0).scaleSelf(TREE_SIZE / 2), TREE_SIZE, this);
		tree.populate(10, 10, 50, 5, 5, 5);
		
		wind = new emitter(new Vec3D(25,-300,50), new Vec3D(-50, 300, 100), 25, this);
		//TriangleMesh tmesh = (TriangleMesh) new STLReader().loadBinary(sketchPath("input.stl"), STLReader.TRIANGLEMESH);
		tree.populateFromFile(importPts);
	}

	public void update() {
		tree.update();
		wind.update();
		if(millis() % 10 == 0) wind.addPts(5);
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
