import java.util.ArrayList;

import toxi.geom.*;

public class pointStack extends PointOctree {
	thesisWindErode p;
	int density = 10;
	int RAD = 10;
	int size;

	public pointStack(Vec3D c, float s, thesisWindErode parent) {
		super(c, s);
		p = parent;
		size = (int) s;
	}
	
	void update(){
		
		for(force f : p.wind.forces){
			ArrayList<Vec3D> pts = this.getPointsWithinSphere(f.loc, RAD*2);
			if(pts == null) return;
			for(Vec3D pt : pts){
				this.remove(pt);
			}
			f.hits++;
			if(f.hits >= f.weight) f.on = false; 
		}
	}

	void draw() {
		drawNode(this);
	}

	void drawNode(PointOctree n) {
		if (n.getNumChildren() > 0) {
			p.noFill();
			p.stroke(n.getDepth(), 20);
			p.pushMatrix();
			p.translate(n.x, n.y, n.z);
			p.box(n.getNodeSize());
			p.popMatrix();
			PointOctree[] childNodes = n.getChildren();
			for (int i = 0; i < 8; i++) {
				if (childNodes[i] != null)
					drawNode(childNodes[i]);
			}
		}
	}

	void populate(int nX, int nY, int nZ, int sX, int sY, int sZ) {
		float x = (sX * nX) / 2;
		float y = (sY * nY) / 2;

		for (int i = 0; i < nX; i++) {
			for (int j = 0; j < nY; j++) {
				for (int k = 0; k < nZ; k++) {
					this.addPoint(new Vec3D(x - (i * sX), y - (j * sY), k * sZ));
				}
			}
		}
	}
	
	void drawPoints(){
		p.stroke(0);
		p.fill(255,100,0);
		for(Vec3D v : this.getPoints()){
			p.gfx.box(new AABB(v, RAD/2));
		}
	}

}
